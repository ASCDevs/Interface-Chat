package serverobject;
import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

public class ClientSocket {
	private final Socket socket;
	private final ObjectInputStream in;
	private final ObjectOutputStream out;
	private String nomeUsuario;
	
	public ClientSocket(Socket socket) throws IOException {
		this.socket = socket;
		System.out.println("Cliente "+socket.getRemoteSocketAddress()+" conectou");
		this.in = new ObjectInputStream(socket.getInputStream());
		this.out = new ObjectOutputStream(socket.getOutputStream());
	}
	
	public SocketAddress getRemoteSocketAddress() {
		return socket.getRemoteSocketAddress();
	}
	
	public void close() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch(IOException e) {
			System.out.println("Erro ao fechar socket: "+e.getMessage());
		}
	}
	
	public Mensagem getMessage() {
		try {
			try {
				return (Mensagem) in.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("(Server)Erro ao ler o objeto no getMessage(): "+e.getMessage());
				e.printStackTrace();
				return null;
			}
		} catch (IOException e) {
			System.out.println("(Server)Erro ao pegar mensagem: "+e.getMessage());
			return null;
		}
	}
	
	public void sendMsg(Mensagem msg) {
		try {
			out.writeObject(msg);
		} catch (IOException e) {
			System.out.println("(SocketClient - Server) Erro ao enviar msg");
			e.printStackTrace();
		}
	}
}
