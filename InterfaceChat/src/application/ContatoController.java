package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import models.Contato;

public class ContatoController implements Initializable{
	
	private Contato contato;

	@FXML
	public Ellipse imgContato;
	@FXML
	public Text nomeContato;
	@FXML
	public Ellipse indicadorStatus;
	@FXML
	public Text txtStatus;
	@FXML
	public HBox fundoCard;
	
	public Pane itemContato;
	public ChatController chatControl;

	public ContatoController(Contato contato){
		this.contato = contato;
		this.chatControl = new ChatController(this.contato);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nomeContato.setText(contato.getNome());
		txtStatus.setText(contato.getStatus());
		if(contato.getStatus()=="online") {
			indicadorStatus.setStyle("-fx-fill: #04d939");
		}else {
			indicadorStatus.setStyle("-fx-fill: #c6c6c6");
		}
	}
	
	
	public void contatoEnviaMsgArquivo() {
		
	}
	
	public void verificaStatus() {
		
	}
	
	public Pane getChat() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/PainelChat.fxml"));
			loader.setController(chatControl);
			itemContato = loader.load();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return itemContato;
	}
	
	public Contato getContato() {
		return this.contato;
	}
	
	
}
