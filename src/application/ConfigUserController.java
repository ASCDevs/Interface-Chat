package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Usuario;

public class ConfigUserController implements Initializable{
	
	private Usuario userLogado;
	
	@FXML
	public Button btnEditFoto;
	
	@FXML
	public Button btnFecharSobre;
	
	ConfigUserController(Usuario userLogado){
		this.userLogado = userLogado;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	public void abrirSobre() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/TelaSobreConfigUser.fxml"));
			loader.setController(new ConfigUserController(userLogado));
			Pane telaSobreConfig = new Pane();
			telaSobreConfig = loader.load();
			String titulo = "Sobre Configurações de Usuário";
			Stage janelaSobre = new Stage();
			janelaSobre.setResizable(false);
			janelaSobre.setTitle(titulo);
			janelaSobre.setScene(new Scene(telaSobreConfig));
			janelaSobre.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void editarFoto() {
		JOptionPane.showMessageDialog(null, "Mesagem teste");
		userLogado.alterarFoto();
	}
	
	@FXML
	public void fecharSobre(){
		final Stage stage = (Stage) btnFecharSobre.getScene().getWindow();
		stage.close();
	}
}
