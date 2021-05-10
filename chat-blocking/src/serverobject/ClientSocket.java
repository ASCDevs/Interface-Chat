package serverobject;
import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DbConnection;
import models.Mensagem;
import models.Usuario;
import database.LoadFromDB;

public class ClientSocket {
	private final Socket socket;
	private LoadFromDB db;
	private int idUser;
	
	public ClientSocket(Socket socket) throws IOException {
		this.socket = socket;
		db = new LoadFromDB();
		idUser = db.retornaIdUser(socket.getPort());
		System.out.println("Cliente ("+idUser+") ("+socket.getRemoteSocketAddress()+") conectou - Porta("+socket.getPort()+")");
		db.closeConection();
	}
	
	public SocketAddress getRemoteSocketAddress() {
		return socket.getRemoteSocketAddress();
	}
	
	public void close() {
		try {
			socket.close();
		} catch(IOException e) {
			System.out.println("Erro ao fechar socket: "+e.getMessage());
		}
	}
	
	public Mensagem getMessage() {
		try {
			try {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				Mensagem msg = (Mensagem) in.readObject();
				
				return msg;
			} catch (ClassNotFoundException e) {
				System.out.println("(Server)Erro ao ler o objeto no getMessage(): "+e.getMessage());
				e.printStackTrace();
				return null;
			}
		} catch (IOException e) {
			System.out.println("(Server)Erro ao pegar mensagem: "+e.getMessage());
			//e.printStackTrace();
			return null;
		}
	}
	
	public void sendMsg(Mensagem msg) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(msg);
		} catch (IOException e) {
			System.out.println("(SocketClient - Server) Erro ao enviar msg");
			e.printStackTrace();
		}
	}
	

	
	public boolean isConnected() {
		return socket.isConnected();
	}
	
	public boolean isClosed() {
		return socket.isClosed(); //retorna true se a porta estiver fechada
	}
	
	public int getPort() {
		return socket.getPort(); 
	}
	
	public int getIdUser() {
		return idUser;
	}
}
