package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TelaLoginController implements Initializable { 
	
	@FXML
	public Button btnFecharSobre;
	
	@FXML
	public Button btnEntrar;
	
	@FXML
	public Button btnCadastrar;
	
	@FXML
	public void entrarAction() {
		try {
			Pane telaPainel = FXMLLoader.load(getClass().getResource("/resources/TelaPainel.fxml"));
			String titulo = "Chat - Secretaria do Meio Ambiente do Estado";
			Main.abrirTela(telaPainel,titulo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Validar Usuario Server
	}
	
	@FXML
	public void cadastroAction() {
		try {
			Pane telaCadastro = FXMLLoader.load(getClass().getResource("/resources/TelaCadastro.fxml"));
			String titulo = "Cadastro de usu�rio";
			Stage stage = new Stage();
			stage.setTitle(titulo);
			stage.setResizable(false);
			stage.setScene(new Scene(telaCadastro));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void abrirSobreApp() {
		try {
			Pane telaSobre = FXMLLoader.load(getClass().getResource("/resources/TelaSobreApp.fxml"));
			String titulo = "Sobre a aplica��o";
			Stage janelaSobre = new Stage();
			janelaSobre.setResizable(false);
			janelaSobre.setTitle(titulo);
			janelaSobre.setScene(new Scene(telaSobre));
			janelaSobre.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void fecharSobreApp() {
		final Stage stage = (Stage) btnFecharSobre.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
}