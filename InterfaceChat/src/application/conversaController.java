package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Mensagem;

public class conversaController implements Initializable{
	
	//Atributo para Arquivo
	public Mensagem mensagem;
	
	@FXML
	public Text txtMsg;
	
	@FXML 
	public Button btnAbrirArquivo;
	
	@FXML	
	public Text nomeRemetente;
	
	@FXML 
	public VBox balaoConversaContato;
	
	conversaController(Mensagem mensagem){
		this.mensagem = mensagem;
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if(!mensagem.getArquivoPresente()) {
			btnAbrirArquivo.setVisible(false);
			btnAbrirArquivo.setManaged(false);
			txtMsg.setText(mensagem.getMensagem());
		}else {
			txtMsg.setVisible(false);
			txtMsg.setManaged(false);
			
		}
		
	}
	
	@FXML
	public void abrirArquivo() {
		File arquivo = mensagem.getArquivo();
		//File arquivoDowload = new File(null);
		try {
			java.awt.Desktop.getDesktop().open(arquivo);
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo da mensagem");
			//e.printStackTrace();
		}
	}
	
	
	
}
