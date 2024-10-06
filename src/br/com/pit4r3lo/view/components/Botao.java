package br.com.pit4r3lo.view.components;

import javafx.scene.control.Button;

public class Botao extends Button {

	public Botao(String texto, String colorHexa) {
		String stilo = getClass().getResource("/br/com/pit4r3lo/view/assets/css/style.css").toExternalForm();
		getStylesheets().add(stilo);
		getStyleClass().add("botao");
		
		setText(texto);
		setStyle(String.format("-fx-background-color: %s", colorHexa));
		setPrefHeight(50);
		setPrefWidth(75);
		
		setOnMouseEntered(e -> {
			getStyleClass().add("botao-hover");
		});
		
		setOnMouseExited(e -> {
			getStyleClass().remove("botao-hover");
		});
	}
}
