package serverobject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ChatServer {
	//porta constante a ser utilizada no servidor
	//evitar portas de número baixo
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
	
	private void clientConnectionLoop() throws IOException { //irá ficar aguardando as conexões do cliente
		while(true) {
			ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
			clients.add(clientSocket);
			
			new Thread(()-> clientMessageLoop(clientSocket)).start(); 
			
		}
	}
	
	private void clientMessageLoop(ClientSocket clientSocket){
		Mensagem msg;
		try {
			while((msg = clientSocket.getMessage()) != null) {
				if("sair".equalsIgnoreCase(msg.getMensagem())) {
					return;
				}
				System.out.printf("\nMsg recebida do cliente %s: %s",clientSocket.getRemoteSocketAddress(),msg);
				sendMsgToAll(clientSocket, msg.getMensagem());
			}
		}finally {
			clientSocket.close();
		}
	}
	
	private void sendMsgToAll(ClientSocket sender, String msg) {
		Iterator<ClientSocket> iterator = clients.iterator();
		while(iterator.hasNext()){
			ClientSocket clientSocket = iterator.next();
			if(!sender.equals(clientSocket)) {
				String textoMsg = "Cliente "+clientSocket.getRemoteSocketAddress()+" diz: "+msg;
				Mensagem message = new Mensagem();
				message.setMensagem(textoMsg);
				clientSocket.sendMsg(message);
				/*if(!clientSocket.sendMsg(message)) {
					iterator.remove();
				}*/
			}

		}
	}
	
	public static void main(String[] args) {
		ChatServer server = new ChatServer();
		server.start();
		
		System.out.println("Servidor finalizado.");
	}

}
