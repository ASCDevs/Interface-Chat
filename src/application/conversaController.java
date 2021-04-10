package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class conversaController implements Initializable{

	public String mensagem;
	public String nome;
	public String dataHora;
	public String tipoConversa;
	
	@FXML 
	public Text txtMsg;
	
	@FXML	
	public Text nomeRemetente;
	
	//Construir Campo para data e hora
	
	conversaController(String nome, String mensagem, String dataHora, String tipoConversa){
		this.nome = nome;
		this.mensagem = mensagem;
		this.dataHora = dataHora;
		this.tipoConversa = tipoConversa;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(this.tipoConversa.equals("grupo")) {
			this.nomeRemetente.setText(this.nome);
		}
		this.txtMsg.setText(this.mensagem);
		
	}
}
