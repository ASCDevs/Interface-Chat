package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Contato;
import models.Usuario;

public class AddNovoGrupoController implements Initializable {
	
	public Usuario user;
	public ArrayList<Contato> contatos;
	
	@FXML
	public ListView<Contato> listaContatos;
	
	@FXML
	public ListView<Contato> listaIntegrantes;
	
	@FXML
	public Button btnInserirIntegrante;
	
	@FXML
	public Button btnTirarIntegrante;
	
	@FXML
	public Button btnCriar;
	
	@FXML
	public Button btnVoltar;
	
	@FXML 
	public Label labelAviso;
	
	@FXML
	public TextField nomeGrupo;
	
	AddNovoGrupoController(Usuario user){
		this.user = user;
		this.contatos = this.user.getContatos();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregaLista();
	}
		
	public void carregaLista() {
		
		for(Contato contato: contatos) {
			listaContatos.getItems().add(contato);
		}
		
	}
	
	@FXML
	public void insereListaIntegrante() {
		Contato contatoSelecionado =  listaContatos.getSelectionModel().getSelectedItem();
		if(!(listaIntegrantes.getItems().contains(contatoSelecionado))) {
			listaIntegrantes.getItems().add(contatoSelecionado);
		}
		
	}
	
	@FXML
	public void tiraListaIntegrante() {
		Contato contatoSelecionado = listaIntegrantes.getSelectionModel().getSelectedItem();
		listaIntegrantes.getItems().remove(contatoSelecionado);
	}
	
	@FXML
	public void criarGrupo() {
		if(validarCampos()) {
			labelAviso.setText("");
			//AddGrupo
		}else {
			
		}
	}
	
	public boolean validarCampos() {
		labelAviso.setText("");
		boolean flag = true;
		String textoCampo = nomeGrupo.getText();
		String mensagem = "Erro";
		if(textoCampo.isEmpty()||textoCampo.substring(0,1).equals(" ")) {
			mensagem += "\r\n"+" - Nome do grupo vazio ou inválido";
			flag = false;
		}
		
		if(listaIntegrantes.getItems().size()<=1) {
			mensagem += "\r\n"+" - Lista de integrantes vazia ou apenas 1 integrante";
			flag = false;
		}
		
		if(!flag) {
			labelAviso.setText(mensagem);
		}
		
		return flag;
	}
	
	@FXML
	public void	voltarPainel() {
		final Stage stage = (Stage) btnVoltar.getScene().getWindow();
		stage.close();
	}
	
	
}
