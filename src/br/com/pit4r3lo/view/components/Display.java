package br.com.pit4r3lo.view.components;

import br.com.pit4r3lo.model.Memoria;
import br.com.pit4r3lo.model.MemoriaObsevador;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class Display extends FlowPane implements MemoriaObsevador {
	
	private final Label label;
	
	public Display() {
		Memoria.getMemoria().adicionarObservadorResultado(this); // agora o display será notificado
		
		String stilo = getClass().getResource("/br/com/pit4r3lo/view/assets/css/style.css").toExternalForm();
		
		getStylesheets().add(stilo);
		setAlignment(Pos.BOTTOM_LEFT);
		getStyleClass().add("display");
		
		label = new Label(Memoria.getMemoria().getTextoAtual());
			
		label.setAlignment(Pos.CENTER);
		label.getStylesheets().add(stilo);
		label.getStyleClass().add("display-label");
		
		getChildren().add(label);
	}

	@Override
	public void valorAlterado(String novoValor) {
		
		if (novoValor.length() > 14) {
		    String texto = novoValor.substring(0, 13) + "...";
		    label.setText(texto);
		} else {
			label.setText(novoValor);
		}
	}
}
