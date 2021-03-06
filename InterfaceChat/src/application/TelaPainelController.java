package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Contato;
import models.Usuario;
import services.server.ChatClient;

public class TelaPainelController implements Initializable {
	
	public Usuario user;
	public ArrayList<ContatoController> contatos;
	public FormularioController formulario;
	public ConfigUserController configurarUser;
	public Pane painelForm;
	public Pane painelConfigUser;
	
	@FXML
	public Text nomeUser;
	
	@FXML
	public Button btnSair;
	
	@FXML
	public Text txtUnidade;
	
	@FXML
	public Button btnConfig;
	
	@FXML 
	public Button btnFormulario;
	
	@FXML
	public VBox pnContatos;
	
	@FXML
	public AnchorPane painelExibicao;
	
	@FXML
	public void sairAction() {
		System.exit(0);	
	}

	TelaPainelController(Usuario user){
		this.user = user;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nomeUser.setText(user.getNome());
		txtUnidade.setText("Inspetor - "+user.getEquipe());
		carregarConfigUser();
		carregarFormulario();
		carregarContatos();
		setReferenciaPainelContatos();
	}
	
	
	private void setReferenciaPainelContatos() {
		for(ContatoController contato: contatos) {
			contato.setReferenciaPainel(this);
		}
		
	}

	public void carregarConfigUser() {
		this.configurarUser = new ConfigUserController(user);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/PainelConfigUser.fxml"));
		loader.setController(configurarUser);
		try {
			this.painelConfigUser = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void carregarFormulario() {
		this.formulario = new FormularioController(user);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/PainelForm.fxml"));
		loader.setController(formulario);
		try {
			this.painelForm = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
		contatos = new ArrayList<ContatoController>();
		ArrayList<Contato> userContatos = user.getContatos();
		
		for(int i=0;i<userContatos.size();i++) {
			contatos.add(userContatos.get(i).getController());
		}
		
	}
	
	@FXML
	public void acessarConfigUser() {
		this.exibePainel(painelConfigUser);
	}
	
	@FXML
	public void acessarFormulario() {
		this.exibePainel(painelForm);
	}
	
	@FXML
	public void abrirProcurarConversa() {
		try {
			Pane layoutProcurarConversa = new Pane();
			Stage janelaProcurarConversa = new Stage();
			janelaProcurarConversa.setTitle("Procurar Conversa");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/TelaProcurarController.fxml"));
			loader.setController(new TelaProcurarContatoController(user.getContatos()));
			layoutProcurarConversa = loader.load();
			
			Scene cena = new Scene(layoutProcurarConversa);
			janelaProcurarConversa.setResizable(false);
			janelaProcurarConversa.setScene(cena);
			janelaProcurarConversa.show();
			
		}catch(IOException e) {
			e.printStackTrace();
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
