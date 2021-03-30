package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class TelaCadastroController {

	@FXML
	public Button btnCancelar;
	
	@FXML
	public void	cancelarAction() {
		try {
			Pane telaLogin = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
			Main.openTela(telaLogin);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
