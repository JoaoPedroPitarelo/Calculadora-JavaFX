package br.com.pit4r3lo.view.components;

import br.com.pit4r3lo.model.Memoria;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class Teclado extends GridPane {

	private final String COR_CINZA_ESCURO = "#3A3A3A";
	private final String COR_CINZA_CLARO = "#505050";
	private final String COR_LARANJA = "#E56101";
	
	public Teclado() {
		String stilo = getClass().getResource("/br/com/pit4r3lo/view/assets/css/style.css").toExternalForm();
		getStylesheets().add(stilo);
		
		setAlignment(Pos.CENTER);
		setPrefSize(205, 230);
		setVgap(2);
		setHgap(2);
		getStyleClass().add("teclado");
		
		// Criando restrições para cada coluna
		ColumnConstraints col1 = new ColumnConstraints();
	    col1.setPercentWidth(10);  
	    ColumnConstraints col2 = new ColumnConstraints();
	    col2.setPercentWidth(10);  
	    ColumnConstraints col3 = new ColumnConstraints();
	    col3.setPercentWidth(10);  
	    ColumnConstraints col4 = new ColumnConstraints();
	    col4.setPercentWidth(10);  
		
	    ColumnConstraints ultima = new ColumnConstraints();
	    ultima.setPercentWidth(40); 
	    ultima.setHgrow(Priority.ALWAYS); 

		Botao btnIgual = new Botao("=", COR_LARANJA);
		btnIgual.setPrefHeight(103);
		btnIgual.setOnAction(e -> {
			if(e.getSource() instanceof Botao) {
				Memoria.getMemoria().processarComando(btnIgual.getText());
			}
		});
		
		Botao btnZerar = new Botao("C", COR_CINZA_ESCURO);
		btnZerar.setPrefWidth(230);
		btnZerar.setOnAction(e -> {
			if(e.getSource() instanceof Botao) {
				Memoria.getMemoria().processarComando(btnZerar.getText());
			}
		});
		
		add(btnZerar, 0, 0, 3, 1);
		adicionarBotao("±",COR_CINZA_ESCURO,  3, 0);
		adicionarBotao("π",  COR_CINZA_ESCURO,  4, 0);
		
		adicionarBotao("7", COR_CINZA_CLARO,   0, 1);
		adicionarBotao("8", COR_CINZA_CLARO,   1, 1);
		adicionarBotao("9", COR_CINZA_CLARO,   2, 1);
		adicionarBotao("÷", COR_CINZA_ESCURO,  3, 1);
		adicionarBotao("√", COR_CINZA_ESCURO,  4, 1);
		
		adicionarBotao("4", COR_CINZA_CLARO,   0, 2);
		adicionarBotao("5", COR_CINZA_CLARO,   1, 2);
		adicionarBotao("6", COR_CINZA_CLARO,   2, 2);
		adicionarBotao("x", COR_CINZA_ESCURO,   3, 2);
		adicionarBotao("n²",COR_CINZA_ESCURO,  4, 2);
		
		adicionarBotao("1", COR_CINZA_CLARO,   0, 3);
		adicionarBotao("2", COR_CINZA_CLARO,   1, 3);
		adicionarBotao("3", COR_CINZA_CLARO,   2, 3);
		adicionarBotao("-", COR_CINZA_ESCURO,  3, 3);
		add(btnIgual, 4, 3, 1, 2);

		adicionarBotao("0", COR_CINZA_CLARO,   0, 4);
		adicionarBotao(".", COR_CINZA_CLARO,   1, 4);
		adicionarBotao("%", COR_CINZA_CLARO,   2, 4);
		adicionarBotao("+", COR_CINZA_ESCURO,  3, 4);	
	}
		
	private void adicionarBotao(String texto, String cor, int x, int y) {
		Botao botao = new Botao(texto, cor);

		botao.setOnAction(e -> {
			if(e.getSource() instanceof Botao) {
				Memoria.getMemoria().processarComando(botao.getText());
			}
		});
		
		add(botao, x, y);	
	}
}
