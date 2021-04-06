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

public class ContatoController implements Initializable{
	
	public String nome;
	public String status;

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

	public ContatoController(String nome, String status){
		this.nome = nome;
		this.status = status;
		this.chatControl = new ChatController(this.nome,this.status);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nomeContato.setText(nome);
		txtStatus.setText(status);
		if(this.status=="online") {
			indicadorStatus.setStyle("-fx-fill: #04d939");
		}else {
			indicadorStatus.setStyle("-fx-fill: #c6c6c6");
		}
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
	
	
}
