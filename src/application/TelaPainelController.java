package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TelaPainelController implements Initializable {
	
	@FXML
	public Button btnSair;
	
	@FXML
	public Button btnConfig;
	
	@FXML
	public VBox pnContatos;
	
	public ArrayList<Contato> contatos;
	
	
	@FXML
	public void sairAction() {
		System.exit(0);	
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		contatosFake();
		carregarContatos();
	}
	
	
	public void carregarContatos() {
		
		Pane[] items = new Pane[contatos.size()];
		String cinza, branco, selecionado;
		cinza = "#EFEFEF";
		branco = "#FFFFFF";
		selecionado = "#e3e3e3" ;
		
		for(int i=0;i<items.length;i++) {
			try {
				final int j = i;
				final String corFundo;
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/itemContato.fxml"));
				loader.setController(contatos.get(i));
				items[i] = loader.load();
				
			
				if(i%2==0) {
					corFundo = cinza;
				}else {
					corFundo = branco;
				}
				items[i].setStyle("-fx-background-color: "+corFundo);
				items[i].setOnMouseEntered(event -> {
					items[j].setStyle("-fx-background-color : "+selecionado);
				});
				items[i].setOnMouseExited(event -> {
					items[j].setStyle("-fx-background-color: "+corFundo);
				});
				
				pnContatos.getChildren().add(items[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void contatosFake() {
		contatos = new ArrayList();
		
		contatos.add(new Contato("Ulisses Valente","online"));
		contatos.add(new Contato("Noémi Junqueira","online"));
		contatos.add(new Contato("Davide Paião","online"));
		contatos.add(new Contato("Pérola Mexia ","online"));
		contatos.add(new Contato("Catarina Noleto","online"));
		contatos.add(new Contato("Anastacia Lobato","online"));
		contatos.add(new Contato("Fernão Madureira","online"));
		contatos.add(new Contato("André Ramos","offline"));
		contatos.add(new Contato("Silvana Lameiras","offline"));
		contatos.add(new Contato("Pérola Ruela","offline"));
		contatos.add(new Contato("Emma Brito","offline"));
		contatos.add(new Contato("Brayan Moreno","offline"));
		
	}
}
