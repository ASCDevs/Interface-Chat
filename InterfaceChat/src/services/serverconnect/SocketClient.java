package services.serverconnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import services.serverconnect.Erro;
import models.Mensagem;
import models.Usuario;

public class SocketClient {
	private final Socket socket;
	private final ObjectInputStream in;
	private final ObjectOutputStream out;
	private Usuario userLogado;

	public SocketClient(Socket socket, Usuario user) throws IOException {
		this.socket = socket;
		this.userLogado = user;
		//this.userLogado.setSocketFuncitons(socket);
		System.out.println("Cliente "+user.getNome()+"("+socket.getRemoteSocketAddress()+") conectou.(Lado cliente)");
		this.in = new ObjectInputStream(socket.getInputStream());
		this.out = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("Iniciou os ObjectStream");

	}
	
	public SocketAddress getRemoteAddress() {
		return socket.getRemoteSocketAddress();
	}
	
	public Mensagem getMessage() {
		try {
			try {
				return (Mensagem) in.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("(Client)Erro ao ler o objeto no getMessage(): "+e.getMessage());
				e.printStackTrace();
				return null;
			}
		} catch (IOException e) {
			System.out.println("(Client)Erro ao pegar mensagem: "+e.getMessage());
			return null;
		}
	}
	
	public void sendMessage(Mensagem msg) {
		
		try {
			out.writeObject(msg);
		} catch (IOException e) {
			System.out.println("(SocketClient - Cliente) Erro ao enviar msg");
			e.printStackTrace();
		}
	}
	
	

	public void sendMessage1(UsuarioMensagem userMsg) { //Criar SendMessageText and SendMessageFile
		try {
			ObjectOutputStream oOS = new ObjectOutputStream(socket.getOutputStream());
			oOS.writeObject(userMsg);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}
	
	public void close() {
		try {
			in.close();
			out.close();
			socket.close();
		}catch(IOException e) {
			System.out.println("Erro ao fechar socket: "+e.getMessage());
		}
	}


}
