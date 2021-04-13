package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import models.Usuario;

public class ConfigUserController implements Initializable{
	
	private Usuario userLogado;
	
	ConfigUserController(Usuario userLogado){
		this.userLogado = userLogado;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
