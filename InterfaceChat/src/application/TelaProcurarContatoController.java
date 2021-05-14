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
import javafx.stage.Stage;
import models.Contato;

public class TelaProcurarContatoController implements Initializable{

	public ArrayList<Contato> contatos;
	public Contato contatoEscolhido;
	
	@FXML
	public TextField campoBusca;
	@FXML
	public ListView<Contato> listaResultados;
	@FXML
	public Button btnVoltar;
	@FXML
	public Label contatoSelecionado;
	
	
	TelaProcurarContatoController(ArrayList<Contato> contatos){
		this.contatos = contatos;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		campoBusca.textProperty().addListener((observable, oldValue, newValue) ->{
			listaResultados.getItems().clear();
			contatoSelecionado.setText("");
			contatoEscolhido = null;
			
			String busca = newValue;
			for(Contato contact: contatos) {
				if(contact.toString().toLowerCase().contains(busca.toLowerCase())) {
					listaResultados.getItems().add(contact);
				}
			}
			
		});
		
		listaResultados.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contato>(){

			@Override
			public void changed(ObservableValue<? extends Contato> observable, Contato oldValue, Contato newValue) {
				contatoSelecionado.setText("");
				contatoEscolhido = listaResultados.getSelectionModel().getSelectedItem();
				if(contatoEscolhido!=null) {
					contatoSelecionado.setText(contatoEscolhido.toString());
				}
				
				
			}
			
		});
		
	}
	
	@FXML
	public void abrirConversa() {
		if(contatoEscolhido!=null) {
			contatoEscolhido.getController().getReferenciaPainel().exibePainel(contatoEscolhido.getController().getChat());
			voltarPainel();
		}
	}
	
	
	
	@FXML
	public void voltarPainel() {
		final Stage stage = (Stage) btnVoltar.getScene().getWindow();
		stage.close();
	}
	
}
