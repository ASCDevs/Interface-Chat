package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Contato;

public class ChatController implements Initializable{

	private Contato contato;
	private String tipoChat;
	
	@FXML
	public Text txtNomeContato;
	
	@FXML
	public VBox painelConversa; 
	
	ChatController(Contato contato){
		this.contato = contato;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtNomeContato.setText(contato.getNome()+" - "+contato.getStatus());
		
		carregaConversa();
	}
	
	public void carregaConversa() {
		List<List<String>> conversas = contato.getConversa();
		Pane[] balaoConversa = new Pane[conversas.size()];
		
		
		for(int i=0;i<balaoConversa.length;i++) {
			String nomeRemetente = conversas.get(i).get(0);
			String mensagem = conversas.get(i).get(1);
			String dataHora = conversas.get(i).get(2);
			String tipoContato = contato.getTipoContato();
			try {
				FXMLLoader loader;
				//Irá pegar no array a indice 0 que corresponde ao id do dono da mensagem
				if(conversas.get(i).get(0).equals(contato.getIdUserLogado())) {
					loader = new FXMLLoader(getClass().getResource("/resources/itemConversaUser.fxml"));
				}else if((contato.getTipoContato().equals("individual"))) {
					loader = new FXMLLoader(getClass().getResource("/resources/itemConversaContato.fxml"));
				}else {
					loader = new FXMLLoader(getClass().getResource("/resources/itemConversaContatoGrupo.fxml"));
				}
				
				loader.setController(new conversaController(nomeRemetente,mensagem,dataHora,tipoContato));//(Dono da msg, msg, data e hora)
				balaoConversa[i] = loader.load();
				
				
				painelConversa.getChildren().add(balaoConversa[i]);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
