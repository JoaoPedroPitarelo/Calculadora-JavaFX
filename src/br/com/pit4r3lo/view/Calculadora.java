package br.com.pit4r3lo.view;

import br.com.pit4r3lo.view.components.Display;
import br.com.pit4r3lo.view.components.Teclado;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Calculadora extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
	
		Display displayInferior = new Display();
		Teclado teclado = new Teclado();
		
		root.setCenter(displayInferior);
		root.setBottom(teclado);
		
		Scene principalScene = new Scene(root, 340, 340);
		
		primaryStage.setScene(principalScene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
