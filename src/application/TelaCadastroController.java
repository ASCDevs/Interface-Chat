package application;

import java.io.IOException;

import application.Main;
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
			Pane telaLogin = FXMLLoader.load(getClass().getResource("/resources/TelaLogin.fxml"));
			String titulo = "Login";
			Main.openTela(telaLogin,titulo);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
