package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class TelaLoginController implements Initializable {
	
	@FXML
	public Button btnEntrar;
	
	@FXML
	public Button btnCadastrar;
	
	@FXML
	public void entrarAction() {
		try {
			Pane telaPainel = FXMLLoader.load(getClass().getResource("/resources/TelaPainel.fxml"));
			String titulo = "Chat - Secretaria do Meio Ambiente do Estado";
			Main.openTela(telaPainel,titulo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void cadastroAction() {
		try {
			Pane telaCadastro = FXMLLoader.load(getClass().getResource("/resources/TelaCadastro.fxml"));
			String titulo = "Cadastro de usuário";
			Main.openTela(telaCadastro,titulo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
}
