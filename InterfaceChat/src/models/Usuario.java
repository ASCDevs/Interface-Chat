package models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.ContatoController;
import services.serverconnect.DbConnection;
import services.serverconnect.SocketClient;

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
	private SocketClient chatSocket;
	
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
	
	public void recebeMensagemContato(int idContato) {
		//busca o contato no array e verifica se é pertencente aos contatos ou n
		//se for pertencente n faz nada e se n for add na aba de conversas
		//contatoSetMensagemChat
	}
	
	public void setChatSocket(SocketClient socket) {
		this.chatSocket = socket;
	}
	
	public SocketClient getChatSocket() {
		return this.chatSocket;
	}
	
	public void alterarFoto() {
		
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

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}
	
	public void close() {
		
	}
	
	
}
