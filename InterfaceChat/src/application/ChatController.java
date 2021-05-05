package application;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Contato;
import models.Mensagem;

public class ChatController implements Initializable{

	private Contato contato;
	private String tipoChat;
	public double vValue = 0.0;
	@FXML
	public Text txtNomeContato;
	
	@FXML
	public VBox painelConversa;
	
	@FXML
	public ScrollPane painelScroll;
	
	@FXML
	public TextArea campoMsg;
	
	ChatController(Contato contato){
		this.contato = contato;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtNomeContato.setText(contato.getNome()+" - "+contato.getStatus());
		painelScroll.vvalueProperty().bind(painelConversa.heightProperty());
		
		//carregaConversa();
	}
	
	public void carregaConversa() {
		/*List<List<String>> conversas = contato.getConversa();
		Pane[] balaoConversa = new Pane[conversas.size()];
		
		
		for(int i=0;i<balaoConversa.length;i++) {
			String idRemetente = conversas.get(i).get(0);
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
				
				loader.setController(new conversaController(idRemetente,mensagem,dataHora,tipoContato));//(Dono da msg, msg, data e hora)
				balaoConversa[i] = loader.load();
				
				
				painelConversa.getChildren().add(balaoConversa[i]);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/
	}
	/*private void messageLoop() {
		String msg;
		do {
			System.out.print("\nDigite uma mensagem (sair para finalizar): ");
			msg = scanner.nextLine();
			contato.getUserLogado().getChatSocket().sendMessage2(msg);
			
		}while(!msg.equalsIgnoreCase("sair")); //encerra o cliente qnd digitado sair
	}*/
	
	@FXML
	public void enviarUserMensagem() {

		if(campoMsg.getText().trim().length()!=0) {
			//contato.enviaMensagemServerViaSocket
			Mensagem Msg = new Mensagem();
			Msg.setIdDestinatario(contato.getIdContato());
			Msg.setIdRemetente(contato.getUserLogado().getIdUser());
			Msg.setMensagem(campoMsg.getText());
			
			contato.salvaMsg(Msg.getMensagem());
			contato.getUserLogado().getChatSocket().sendMessage(Msg);
			
			FXMLLoader balaoConversa = new FXMLLoader(getClass().getResource("/resources/itemConversaUser.fxml"));
			balaoConversa.setController(new conversaController(campoMsg.getText()));
			try {
				painelConversa.getChildren().add(balaoConversa.load());
				campoMsg.clear();
				campoMsg.requestFocus();
				//rolarConversa();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void recebeContatoMensagemTexto(String msg) {
		//adicionar uma função observer que fica es
		contato.addMsgTextoContato(msg);
		FXMLLoader balaoConversa = new FXMLLoader(getClass().getResource("/resources/itemConversaContato.fxml"));
		balaoConversa.setController(new conversaController(msg));
		try {
			painelConversa.getChildren().add(balaoConversa.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void rolarConversa() {
		
		Platform.runLater(()->{
			painelScroll.layout();
			vValue = painelScroll.getVvalue();
			System.out.println("Desceu: "+vValue);
			
			painelScroll.setVvalue(vValue);
		});
		
	}
	
	

}
