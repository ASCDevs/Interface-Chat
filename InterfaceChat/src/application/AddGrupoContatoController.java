package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.Contato;
import models.Usuario;

public class AddGrupoContatoController implements Initializable {
	
	public Usuario user;
	public ArrayList<Contato> contatos;
	@FXML 
	public ListView<Contato> listaContatos;
	@FXML
	public TextField campoBusca;
	@FXML
	public Button btnAddContato;
	@FXML
	public Button btnBuscaContato;
	@FXML
	public Label labelUserSelected;
	
	
	AddGrupoContatoController(Usuario user){
		this.user = user;
		this.contatos = this.user.getContatos();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregaLista();
		listaContatos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contato>() {
			@Override
			public void changed(ObservableValue<? extends Contato> observable, Contato oldValue, Contato newValue) {
				Contato contatoSelecionado = listaContatos.getSelectionModel().getSelectedItem();
				labelUserSelected.setText(contatoSelecionado.getNome());
			}
			
		});
	}
	
	public void carregaLista() {
		
		for(Contato contato: contatos) {
			//Verificar se Já etiver no grupo
			//E adicionar como não selecionavel
			listaContatos.getItems().add(contato);
		}
		
	
	}
	
	
	
	
}
