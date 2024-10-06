package br.com.pit4r3lo.model;

@FunctionalInterface
public interface MemoriaObsevador {

	public void valorAlterado(String novoValor);
}
