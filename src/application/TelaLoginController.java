package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class TelaLoginController {
	
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
	
	
}
