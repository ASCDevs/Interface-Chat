package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class conversaController implements Initializable{
	
	//Atributo para Arquivo
	public String mensagem;
	public String remetente;
	//public String dataHora;
	//public String tipoConversa;
	
	@FXML 
	public Text txtMsg;
	
	@FXML	
	public Text nomeRemetente;
	conversaController(String mensagem){
		this.mensagem = mensagem;
		//this.dataHora = dataHora;
		//this.tipoConversa = tipoConversa;
	}
	
	//Construir Campo para data e hora
	//conversaController(String idRemetente, String mensagem, String dataHora, String tipoConversa){
	conversaController(String remetente, String mensagem){
		this.remetente = remetente;
		this.mensagem = mensagem;
		//this.dataHora = dataHora;
		//this.tipoConversa = tipoConversa;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.txtMsg.setText(this.mensagem);
		
	}
	
}
