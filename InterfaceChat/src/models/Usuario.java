package models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.ContatoController;
import services.blocking.ChatClient;
import services.blocking.ClientSocket;
import services.serverconnect.DbConnection;

public class Usuario {
	
	private int idUser;
	private String nome;
	private String username; //email
	private String senha;
	private String ip;
	private int porta;
	private ArrayList<Contato> contatos;
	private DbConnection db;
	private Connection conex;
	private ClientSocket chatSocket;
	private ChatClient chatClient;
	
	public Usuario(String username){
		db = new DbConnection();
		conex = db.createConnection();	
		this.username = username;
		carregaUsuario(this.username);
		contatos = new ArrayList();
		carregaContatos();
		System.out.println("Usuario Construido");
	}
	
	public void carregaUsuario(String username) {
		String query = "SELECT * FROM Usuario WHERE username_usuario = ?";
		
		try {
			PreparedStatement stmt = conex.prepareStatement(query);
			stmt.setString(1, username);
			stmt.execute();
			ResultSet dadosUser = stmt.getResultSet();
			
			while(dadosUser.next()) {
				this.idUser = dadosUser.getInt("id_usuario");
				this.nome = dadosUser.getString("nome_usuario");
				this.username = dadosUser.getString("username_usuario");
				this.senha = dadosUser.getString("senha_usuario");
			}
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void carregaContatos(){
		String query = "SELECT u.* FROM Usuario u, UsuarioContatos uc WHERE uc.id_contato = u.id_usuario AND uc.id_usuario = ?";
		
		try {
			PreparedStatement stmt = conex.prepareStatement(query);
			stmt.setInt(1, idUser);
			stmt.execute();
			ResultSet rsContato = stmt.getResultSet();
			while(rsContato.next()) {
				//IdContato, IdUserLogado, Nome
				int idContato;
				String nomeContato;
				
				idContato = rsContato.getInt("id_usuario");
				nomeContato = rsContato.getString("nome_usuario");
				contatos.add(new Contato(idContato,this,nomeContato));
				System.out.println("Contato adicionado");
			}
			
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Mensagem> carregaConversaEntreContatoUser(int idContato) {
		List<Mensagem> conversa = new ArrayList<Mensagem>();
		
		String query = "SELECT * FROM HistoricoConversa WHERE id_remetente in(?,?) AND id_destinatario in(?,?) ORDER BY id_conversa ASC";
		
		try {
			PreparedStatement stmt = conex.prepareStatement(query);
			stmt.setInt(1, idContato);
			stmt.setInt(2, this.idUser);
			stmt.setInt(3, idContato);
			stmt.setInt(4, this.idUser);
			stmt.execute();
			ResultSet rsConversa = stmt.getResultSet();
			while(rsConversa.next()) {
				Mensagem msg = new Mensagem();
				msg.setIdRemetente(rsConversa.getInt("id_remetente"));
				msg.setIdDestinatario(rsConversa.getInt("id_destinatario"));
				msg.setMensagem(rsConversa.getString("mensagem"));
				conversa.add(msg);
			}
			
			
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conversa;
	}
	
	
	public void enviarMensagem(Mensagem msg) {
		this.chatSocket.sendMsg(msg);
	}
	
	
	
	public void recebeMensagemContato(Mensagem msg) { 
		for(Contato contato : contatos) {
			if(contato.getIdContato()==msg.getIdRemetente()) {
				contato.recebeMensagemContato(msg);
			}
		}
	}
	
	public void setChatSocket(ClientSocket socket) {
		this.chatSocket = socket;
	}
	
	public int getPorta() {
		String query = "SELECT * FROM Usuario WHERE id_usuario = ?";
		int id = 0;
		try {
			PreparedStatement stmt = conex.prepareStatement(query);
			stmt.setInt(1, this.idUser);
			stmt.execute();
			
			ResultSet rsUser = stmt.getResultSet();
			while(rsUser.next()) {
				id = rsUser.getInt("porta_ip_usuario");
			}
			
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public ClientSocket getChatSocket() {
		return this.chatSocket;
	}
	
	public void setChatClient(ChatClient chatClient) {
		this.chatClient = chatClient;
	}
	
	public ChatClient getChatClient() {
		return this.chatClient;
	}
	
	public ArrayList<Contato> getContatos(){
		return this.contatos;
	}
	
	public void setContatos(ArrayList<Contato> contatos) {
		this.contatos = contatos;
	}
	
	public int getIdUser() {
		return this.idUser;
	}
	

	public void setIdUser(int id) {
		this.idUser = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}


	public void setPorta(int porta) {
		this.porta = porta;
	}
	
	public void close() {
		
	}
	
	
}
