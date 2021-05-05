package services.serverconnect;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import models.Mensagem;
import models.Usuario;
import services.blocking.ClientSocket;

public class ChatSocket implements Runnable{
	private static final String SERVER_ADDRESS = "127.0.0.1";
	private static final int SERVER_PORT = 5656; //51995 //5656
	private SocketClient socketClient;
	private Usuario userAutenticado;
	private Scanner scanner;
	
	public ChatSocket(Usuario userAutenticado) {
		this.userAutenticado = userAutenticado;
		scanner = new Scanner(System.in);
	}
	
	public void start() {
		try {
			try {
				//socket cliente local do lado do cliente
				socketClient = new SocketClient(new Socket(SERVER_ADDRESS,SERVER_PORT),userAutenticado);  //(String host, int porta)  a porta do servidor	
				System.out.println("socketClient criado");
				userAutenticado.setChatSocket(socketClient);
				
				System.out.println("Cliente("+userAutenticado.getNome()+") conectado ao servidor em "+SERVER_ADDRESS+":"+SERVER_PORT);
				
				new Thread(this).start();
				//messageLoop();
				
			}catch (UnknownHostException e) {
				System.out.println("Erro ao iniciar cliente: "+e.getMessage());
				e.printStackTrace();
			}catch (IOException e) {
				System.out.println("Erro ao iniciar cliente: "+e.getMessage());
				e.printStackTrace();
			}
		}finally {
			socketClient.close();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			Mensagem msg = socketClient.getMessage();
			System.out.println("Msg do server: "+msg.getMensagem());
		}
	}
	
	//Implementar messageLoop no chat
	
}
