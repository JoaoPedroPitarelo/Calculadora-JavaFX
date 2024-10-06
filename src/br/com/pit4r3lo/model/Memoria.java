package br.com.pit4r3lo.model;

import java.util.ArrayList;
import java.util.List;

public class Memoria { // Sendo o subject, o notificador

	// Um enum não necessariamente precisa ser um arquivo
	private enum TipoComando {
		ZERAR, NUMERO, DIV, MULT, SUB, SUM, IGUAL, PONTO, PORCEN, ALTERARSINAL,
		PI, PARENTESES, QUADRADO, RAIZ;
	}
	
	/* 
	 * SingleTom, uma classe que só tem uma única instância declarada dentro da própria classe,
	 * pois seu construtor é privado e ele possui um método get para acessarmos essa instância.
	 * */ 
	private static final Memoria instancia = new Memoria();
	private List<MemoriaObsevador> listaObservadoresResultado = new ArrayList<>();
	private List<MemoriaObsevador> listaObservadoresOperacao = new ArrayList<>();
		
	private String textoAtual = "";
	private String textoBuffer = "";	
	private TipoComando ultimaOperacao = null; 
	private boolean substituir = false;

	private Memoria() {	}
	
	public static Memoria getMemoria() {
		return instancia;
	}
	
	public String getTextoAtual() {
		return textoAtual.isBlank() ? "0" : textoAtual;
	}
		
	public void adicionarObservadorResultado(MemoriaObsevador observador) {
		listaObservadoresResultado.add(observador);
	}
	
	public void adicionarObservadorOperacao(MemoriaObsevador observador) {
		listaObservadoresOperacao.add(observador);
	}
	
	public void processarComando(String valor) {
		TipoComando tipoComando = detectarTipoComando(valor);
		
		if(tipoComando == null) {
			return;
		} else if(tipoComando == TipoComando.ZERAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			ultimaOperacao = null;
		} else if(tipoComando == TipoComando.ALTERARSINAL && textoAtual.contains("-")) {
			textoAtual = textoAtual.substring(1);
		} else if(tipoComando == TipoComando.ALTERARSINAL) {
			textoAtual = "-" + textoAtual; 		 
		} else if(tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.PONTO) {
			textoAtual = substituir ? valor : textoAtual + valor;
			substituir = false;
		} else if(tipoComando == TipoComando.QUADRADO) {
			ultimaOperacao = tipoComando;
			textoAtual = obterResultadoOperacao();
			ultimaOperacao = null;
		} else if(tipoComando == TipoComando.PI) {			
			textoAtual = "3.14";
			ultimaOperacao = null;
		} else if(tipoComando == TipoComando.RAIZ) {
			ultimaOperacao = tipoComando;
			textoAtual = obterResultadoOperacao();
			textoBuffer = textoAtual;
			ultimaOperacao = null;
		} else if(tipoComando == TipoComando.PORCEN) {
			ultimaOperacao = tipoComando;
			textoAtual = obterResultadoOperacao();
			textoBuffer = textoAtual;
			ultimaOperacao = null;
		} else {
			substituir = true;
			textoAtual = obterResultadoOperacao();
			textoBuffer = textoAtual;
			ultimaOperacao = tipoComando;			
		}
		
		listaObservadoresResultado.forEach(o -> o.valorAlterado(getTextoAtual())); // Notificando os interessados
	}

	private String obterResultadoOperacao() {
		if(ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
			return textoAtual;
		}
		
		double numeroBuffer = !textoBuffer.isBlank() ? Double.parseDouble(textoBuffer) : 0.0;
		double numeroAtual = Double.parseDouble(textoAtual);
			
		double resultado = 0;
		if(ultimaOperacao == TipoComando.SUM) {
			resultado = numeroBuffer + numeroAtual;	
		} else if(ultimaOperacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;
		} else if(ultimaOperacao == TipoComando.DIV) {
			resultado = numeroBuffer / numeroAtual;
		} else if(ultimaOperacao == TipoComando.MULT) {
			resultado = numeroBuffer * numeroAtual;
		} else if(ultimaOperacao == TipoComando.PORCEN) {
			resultado = numeroAtual / 100;
		} else if(ultimaOperacao == TipoComando.QUADRADO) {
			resultado = Math.pow(numeroAtual, 2);
		} else if(ultimaOperacao == TipoComando.SUB) {
			resultado = numeroBuffer + numeroAtual;
		} else if(ultimaOperacao == TipoComando.RAIZ) {
			resultado = Math.sqrt(numeroAtual);
		}
		
		String resultadoString = Double.toString(resultado);
		boolean inteiro = resultadoString.endsWith(".0");

		return inteiro ? resultadoString.replace(".0", "") : resultadoString;	
	}

	private TipoComando detectarTipoComando(String valor) {
		if(textoAtual.isEmpty() && valor == "0") { // valor inválido
			return null;
		}
		
		try {
			Integer.parseInt(valor);
			return TipoComando.NUMERO;
		} catch (NumberFormatException e) {
			// Quando não for número
			if(valor.equals("C")) {
				return TipoComando.ZERAR;
			} else if(valor.equals("+")) {
				return TipoComando.SUM;
			} else if(valor.equals("-")) {
				return TipoComando.SUB;
			} else if(valor.equals("x")) {
				return TipoComando.MULT;
			} else if(valor.equals("÷")) {
				return TipoComando.DIV;
			} else if(valor.equals("=")) {
				return TipoComando.IGUAL;
			} else if(valor.equals(".") && !textoAtual.contains(".")) {
				return TipoComando.PONTO;
			} else if(valor.equals("n²")) {
				return TipoComando.QUADRADO;
			} else if(valor.equals("√")) {
				return TipoComando.RAIZ;
			} else if(valor.equals("±")) {
				return TipoComando.ALTERARSINAL;
			} else if(valor.equals("π")) {
				return TipoComando.PI;
			} else if(valor.equals("%")) {
				return TipoComando.PORCEN;
			}
		}
		
		return null;
	}
}
