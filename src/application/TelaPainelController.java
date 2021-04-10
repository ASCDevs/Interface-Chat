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
import models.Contato;
import models.Usuario;

public class TelaPainelController implements Initializable {
	
	public Usuario user;
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
		user = new Usuario("123"); //Tratar recebimento do id
		carregarContatos();
	}
	
	
	public void carregarContatos() {
		setContatoControllers();
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
	
	public void setContatoControllers() {
		contatos = new ArrayList();
		ArrayList<Contato> userContatos = user.getContatos();
		
		for(int i=0;i<userContatos.size();i++) {
			contatos.add(new ContatoController(userContatos.get(i)));
		}
		
	}
	
	
	public void exibePainel(Pane painel) {
		painelExibicao.getChildren().setAll(painel);
		painelExibicao.setTopAnchor(painel, 0.0);
		painelExibicao.setLeftAnchor(painel, 0.0);
		painelExibicao.setRightAnchor(painel,0.0);
		painelExibicao.setBottomAnchor(painel, 0.0);
	}
}
