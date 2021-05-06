package services.blocking;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import models.Mensagem;
import models.Usuario;

public class ChatClient implements Runnable{
	//IP onde o servidor está rodando, no caso será localhost,
	//futuramente hospedar em um ip na net
	private static final String SERVER_ADDRESS = "127.0.0.1";
	private static final int SERVER_PORT = 5656;
	private ClientSocket clientSocket;
	private Usuario userAutenticado;
	private Scanner scanner;
	
	public ChatClient(Usuario userAutenticado){ //Usuario userAutenticado
		this.userAutenticado = userAutenticado;
		scanner = new Scanner(System.in);
	}
	
	public void start() {
		try {
			try {
				//socket cliente local do lado do cliente
				clientSocket = new ClientSocket(new Socket(SERVER_ADDRESS,SERVER_PORT));  //(String host, int porta)  a porta do servidor	
				userAutenticado.setChatSocket(clientSocket); //Add para implementar o message loop no chatController de cada contato
				System.out.println("Cliente conectado ao servidor em "+SERVER_ADDRESS+":"+SERVER_PORT);
				
				new Thread(this).start();
				//messageLoop();
			} catch (UnknownHostException e) {
				System.out.println("Erro ao iniciar cliente: "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Erro ao iniciar cliente: "+e.getMessage());
				e.printStackTrace();
			}
		}finally {
			//clientSocket.close(); Como o messageLoop n atua na interface o socket n pode fechar
		}
	}
	
	@Override
	public void run() {
		Mensagem msg;
		while(clientSocket.getSocket().isConnected()){
			msg = clientSocket.getMessage();
			String txt = msg.getMensagem();
			System.out.println("Objeto recebido do servidor: "+ msg);
			System.out.println("\nMsg recebida do servidor: "+txt);
		}
		
	}
	
	private void messageLoop() throws IOException {
		String text;
		
		do {
			System.out.print("\nDigite uma mensagem (sair para finaalizar): ");
			Mensagem message = new Mensagem();
			text = scanner.nextLine();
			message.setMensagem(text);
			clientSocket.sendMsg(message);
		}while(clientSocket.getSocket().isConnected()); //encerra o cliente qnd digitado sair
	}

	/*public static void main(String[] args) {
		
		ChatClient client = new ChatClient();
		client.start();
		
		System.out.println("Cliente finalizado");
	}*/

}