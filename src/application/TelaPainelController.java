package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaPainelController {
	
	@FXML
	public Button btnSair;
	
	@FXML
	public void sairAction() {
		System.exit(0);	
	}
}
