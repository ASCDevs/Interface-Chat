package models;

public class Usuario {
	
	private int idUser;
	private String nomeUsuario;
	private String username;
	private int porta_ip;
	
	public void setIdUser(int id) {
		this.idUser = id;
	}
	
	public int getIdUser() {
		return this.idUser;
	}
	
	public void setNome(String nome) {
		this.nomeUsuario = nome;
	}
	
	public String getNome() {
		return this.nomeUsuario;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setPorta(int porta) {
		this.porta_ip = porta;
	}
	
	public int getPorta() {
		return porta_ip;
	}
	

}
