package serverobject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import database.LoadFromDB;
import models.Mensagem;

public class ChatServer {

	private final int PORT = 5656;
	private ServerSocket serverSocket;
	private final List<ClientSocket> clients = new LinkedList<>();
	
	public void start() {
		
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("Servidor iniciado na porta: "+PORT);
			clientConnectionLoop();
		} catch (IOException e) {
			System.out.println("Erro ao iniciar ChatServer: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void clientConnectionLoop() throws IOException { 
		while(true) {
			ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
			clients.add(clientSocket);
			//listarClientes();
			
			new Thread(()-> clientMessageLoop(clientSocket)).start(); 
			
		}
	}
	
	private void clientMessageLoop(ClientSocket clientSocket){
		Mensagem msg;
		
		try {
			while(true) {
				if(!clientSocket.isClosed()) {
					
					msg = clientSocket.getMessage();
					if(msg!=null) {
						System.out.println("Mensagem recebida de("+msg.getNomeRemetente()+") para ("+msg.getNomeDestino()+"): "+msg.getMensagem());
						sendMsgToContact(clientSocket, msg);
					}else {
						int idDesconect = clientSocket.getIdUser();
						String nome = clientSocket.getNome();
						clients.removeIf(client -> client.getIdUser()==idDesconect);
						System.out.println("Cliente ("+nome+" , id:"+idDesconect+") desconectado");
						//listarClientes();
						break;
					}	
				}
			}
			
		}finally {
			clientSocket.close();
		}
	}
	
	private void sendMsgToContact(ClientSocket sender, Mensagem msg) {
		Iterator<ClientSocket> iterator = clients.iterator();
		while(iterator.hasNext()){
			ClientSocket clientSocket = iterator.next();
			if(!sender.equals(clientSocket)&&clientSocket.getIdUser()==msg.getIdDestinatario()) {
				clientSocket.sendMsg(msg);
			}

		}
	}
	
	
	public static void main(String[] args) {
		ChatServer server = new ChatServer();
		server.start();
		System.out.println("Servidor finalizado.");
	}
	
	public void listarClientes(){
		System.out.println("--------------------");
		for(ClientSocket cliente: clients) {
			System.out.println("Cliente: "+cliente.getIdUser());
		}
		System.out.println("--------------------");
	}
	

}
