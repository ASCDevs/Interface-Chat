package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TelaPainelController implements Initializable {
	
	@FXML
	public Button btnSair;
	
	@FXML
	public Button btnConfig;
	
	@FXML
	public VBox pnContatos;
	
	@FXML
	public void sairAction() {
		System.exit(0);	
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Node[] contatos = new Node[20];
		String cinza, selecionado;
		cinza = "#EFEFEF";
		selecionado = "#c9c9c9" ;
		
		for(int i=0;i<contatos.length;i++) {
			try {
				contatos[i] = FXMLLoader.load(getClass().getResource("/resources/itemContato.fxml"));
				if(i%2==0) {
					contatos[i].setStyle("-fx-background-color: "+cinza);
				}
				
				pnContatos.getChildren().add(contatos[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
}
