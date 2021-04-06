package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TelaPainelController implements Initializable {
	
	public ArrayList<ContatoController> contatos;
	
	@FXML
	public Button btnSair;
	
	@FXML
	public Button btnConfig;
	
	@FXML
	public VBox pnContatos;
	
	@FXML
	public AnchorPane painelExibicao;
	
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
				items[i].setOnMouseClicked(event -> {
					this.exibePainel(contatos.get(j).getChat());
				});
				
				pnContatos.getChildren().add(items[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void contatosFake() {
		contatos = new ArrayList();
		
		contatos.add(new ContatoController("Ulisses Valente","online"));
		contatos.add(new ContatoController("Noémi Junqueira","online"));
		contatos.add(new ContatoController("Davide Paião","online"));
		contatos.add(new ContatoController("Pérola Mexia ","online"));
		contatos.add(new ContatoController("Catarina Noleto","online"));
		contatos.add(new ContatoController("Anastacia Lobato","online"));
		contatos.add(new ContatoController("Fernão Madureira","online"));
		contatos.add(new ContatoController("André Ramos","offline"));
		contatos.add(new ContatoController("Silvana Lameiras","offline"));
		contatos.add(new ContatoController("Pérola Ruela","offline"));
		contatos.add(new ContatoController("Emma Brito","offline"));
		contatos.add(new ContatoController("Brayan Moreno","offline"));
		
	}
	
	public void exibePainel(Pane painel) {
		painelExibicao.getChildren().setAll(painel);
		painelExibicao.setTopAnchor(painel, 0.0);
		painelExibicao.setLeftAnchor(painel, 0.0);
		painelExibicao.setRightAnchor(painel,0.0);
		painelExibicao.setBottomAnchor(painel, 0.0);
	}
}
