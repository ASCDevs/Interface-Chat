package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import models.Usuario;

public class FormularioController implements Initializable {
	
	private Usuario userLogado;
	
	FormularioController(Usuario userLogado){
		this.userLogado = userLogado;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
}
