package services.blocking;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient implements Runnable{
	//IP onde o servidor está rodando, no caso será localhost,
	//futuramente hospedar em um ip na net
	private static final String SERVER_ADDRESS = "127.0.0.1";
	private static final int SERVER_PORT = 5656;
	private ClientSocket clientSocket;
	private Scanner scanner;
	
	public ChatClient(){
		scanner = new Scanner(System.in);
	}
	
	public void start() {
		try {
			try {
				//socket cliente local do lado do cliente
				clientSocket = new ClientSocket(new Socket(SERVER_ADDRESS,SERVER_PORT));  //(String host, int porta)  a porta do servidor
				
				//Fluxo de dados (texto, arquivos, etc) através do OutputStream() que trabalha com bytes, e trata o envio de dados 
				//atraves do out consegue enviar texto
				//this.out = new PrintWriter(clientSocket.getOutputStream(), true); //autoFlsuh = true (permite confirmar envio)	
				
				System.out.println("Cliente conectado ao servidor em "+SERVER_ADDRESS+":"+SERVER_PORT);
				
				new Thread(this).start();
				messageLoop();
			} catch (UnknownHostException e) {
				System.out.println("Erro ao iniciar cliente: "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Erro ao iniciar cliente: "+e.getMessage());
				e.printStackTrace();
			}
		}finally {
			clientSocket.close();
		}
	}
	
	@Override
	public void run() {
		String msg;
		while((msg = clientSocket.getMessage()) != null){
			System.out.printf("\nMsg recebida do servidor: %s",msg);
		}
		
	}
	
	private void messageLoop() throws IOException {
		String msg;
		do {
			System.out.print("\nDigite uma mensagem (sair para finalizar): ");
			msg = scanner.nextLine();
			clientSocket.sendMsg(msg);
			
			//out.write(msg); via BufferedWriter
			//out.newLine(); //quebra de linha via BufferedWriter
			//out.flush(); //confirma o envio, faz com que seja enviada via BufferedWriter
		}while(!msg.equalsIgnoreCase("sair")); //encerra o cliente qnd digitado sair
	}

	public static void main(String[] args) {
		ChatClient client = new ChatClient();
		client.start();
		
		System.out.println("Cliente finalizado");
	}

}