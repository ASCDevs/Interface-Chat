package application;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
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
import services.blocking.ClientSocket;

public class ChatController implements Initializable{

	private Contato contato;
	private String tipoChat;
	public double vValue = 0.0;
	public Mensagem message;
	public ClientSocket clientSocket;
	
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
		carregaConversa();
		/*new Thread(new Runnable() {

			@Override
			public void run() {
				
				while(clientSocket.getSocket().isConnected()){
					Mensagem msg = clientSocket.getMessage();
					String txt = msg.getMensagem();
					System.out.println("Chegou no chat> "+txt);
					recebeContatoMensagemTexto(msg);
				}
			}
			
		}).start();*/
		
		
		
	}
	
	public void carregaConversa() {
		List<Mensagem> conversas = contato.getConversa();
		Pane[] balaoConversa = new Pane[conversas.size()];
		
		
		for(int i=0;i<balaoConversa.length;i++) {
			String nomeRemetente = conversas.get(i).getNomeRemetente();
			String mensagem = conversas.get(i).getMensagem();
			
			try {
				FXMLLoader loader;
				
				if(conversas.get(i).getIdRemetente()==contato.getUserLogado().getIdUser()) {
					loader = new FXMLLoader(getClass().getResource("/resources/itemConversaUser.fxml"));
				}else {
					loader = new FXMLLoader(getClass().getResource("/resources/itemConversaContato.fxml"));
				}
				
				loader.setController(new conversaController(nomeRemetente,mensagem));
				balaoConversa[i] = loader.load();
				
				
				painelConversa.getChildren().add(balaoConversa[i]);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@FXML
	public void enviarUserMensagem() {

		if(campoMsg.getText().trim().length()!=0) {
			//contato.enviaMensagemServerViaSocket
			Mensagem Msg = new Mensagem();
			Msg.setNomeRemetente(contato.getUserLogado().getNome());
			Msg.setNomeDestino(contato.getNome());
			Msg.setIdDestinatario(contato.getIdContato());
			Msg.setIdRemetente(contato.getUserLogado().getIdUser());
			Msg.setMensagem(campoMsg.getText());
			
			
			contato.addMensagemConversa(Msg); //Salva no array para consulta em memória
			contato.salvaMensagem(Msg); //Salva no banco
			contato.getUserLogado().enviarMensagem(Msg);
			
			FXMLLoader balaoConversa = new FXMLLoader(getClass().getResource("/resources/itemConversaUser.fxml"));
			balaoConversa.setController(new conversaController(campoMsg.getText()));
			try {
				painelConversa.getChildren().add(balaoConversa.load());
				campoMsg.clear();
				campoMsg.requestFocus();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	

	public void recebeContatoMensagemTexto(Mensagem msg) {
		System.out.println(msg.getNomeRemetente()+" diz: "+msg.getMensagem());
		contato.addMensagemConversa(msg);
		contato.salvaMensagem(msg); //Salva no banco
		
		//Permite ser chamada de qualquer Thread para alocar na Thread do JavaFX e rodar
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	FXMLLoader balaoConversa = new FXMLLoader(getClass().getResource("/resources/itemConversaContato.fxml"));
        		balaoConversa.setController(new conversaController(msg.getNomeRemetente(),msg.getMensagem()));
        		try {
        			painelConversa.getChildren().add(balaoConversa.load());
        			
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
            }
        });
	}

}
