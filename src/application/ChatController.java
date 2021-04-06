package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class ChatController implements Initializable{

	public String nomeContato;
	public String status;
	public String conversa;
	
	@FXML
	public Text txtNomeContato;
	
	ChatController(String nome, String status){
		this.nomeContato = nome;
		this.status = status;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtNomeContato.setText(this.nomeContato);
	}

}
