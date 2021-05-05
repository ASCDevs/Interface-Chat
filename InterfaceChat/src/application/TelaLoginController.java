package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Usuario;
import services.serverconnect.ServerDataInfo;

public class TelaLoginController implements Initializable { 
	
	@FXML
	public TextField campoLogin;
	
	@FXML
	public TextField campoSenha;
	
	@FXML
	public Button btnFecharSobre;
	
	@FXML
	public Button btnEntrar;
	
	@FXML
	public Button btnCadastrar;
	
	@FXML
	public void entrarAction() {
		if(validaCamposLogin()) {
			try {
				ServerDataInfo bd = new ServerDataInfo();
				String username = campoLogin.getText();
				String senha = campoSenha.getText();
				
				if(bd.validarLogin(username, senha)) {
					bd.close();
					System.out.println("Válidado com sucesso");
					TelaPainelController painelController = new TelaPainelController(username);
					Pane telaPainel = new Pane();
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/TelaPainel.fxml"));
					loader.setController(painelController);
					
					telaPainel = loader.load();
					String titulo = "Chat - Secretaria do Meio Ambiente do Estado";
					Main.abrirTela(telaPainel,titulo);
				}else {
					System.out.println("Erro ao validar");
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	public void cadastroAction() {
		try {
			Pane telaCadastro = FXMLLoader.load(getClass().getResource("/resources/TelaCadastro.fxml"));
			String titulo = "Cadastro de usuário";
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
			String titulo = "Sobre a aplicação";
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
	
	public boolean validaCamposLogin() {
		return true;
	}
	
}
