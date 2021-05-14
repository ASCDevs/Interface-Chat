package services.server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import models.Mensagem;
import models.Usuario;

public class ChatClient implements Runnable{
	
	private static final String SERVER_ADDRESS = "127.0.0.1";
	private static final int SERVER_PORT = 5656;
	public ClientSocket clientSocket;
	private Usuario userAutenticado;
	
	
	public ChatClient(Usuario userAutenticado){
		this.userAutenticado = userAutenticado;
	}
	
	public void start() {
		try {
			int porta = userAutenticado.getPorta();
			clientSocket = new ClientSocket(new Socket(SERVER_ADDRESS,SERVER_PORT,null,porta));
			
			userAutenticado.setChatSocket(clientSocket); 
			System.out.println("Cliente conectado ao servidor em "+SERVER_ADDRESS+":"+SERVER_PORT);
			userAutenticado.setUserLogado(false);
			new Thread(this).start();
			
			
		} catch (UnknownHostException e) {
			System.out.println("1 - Erro ao iniciar cliente: "+e.getMessage());
			//e.printStackTrace();
		} catch (IOException e) {
			userAutenticado.setUserLogado(true);
			System.out.println("2 - Erro ao iniciar cliente: "+e.getMessage());
			//e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(clientSocket.getSocket().isConnected()){
			Mensagem msg = clientSocket.getMessage();
			userAutenticado.recebeMensagemContato(msg);
		}
	}


}