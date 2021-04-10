package models;

import java.util.ArrayList;

import application.ContatoController;

public class Usuario {
	
	private String id;
	private String nome;
	private String username;
	private String senha;
	private String ip;
	private String porta;
	private ArrayList<Contato> contatos;
	
	public Usuario(String id){
		this.id = id;
		contatos = new ArrayList();
		carregaContatos();
	}
	
	private void carregaContatos() {
		//Pegar os contatos relacionados no server a partir do id ou username
		//No server irá buscar a tabela de Relação de usuários
		
		//Dados de Contatos Falsos
		contatos.add(new Contato("1",id,"Ulisses Valente","online","individual"));
		contatos.add(new Contato("2",id,"Noémi Junqueira","online","individual"));
		contatos.add(new Contato("3",id,"Davide Paião","online","individual"));
		contatos.add(new Contato("4",id,"Pérola Mexia ","online","individual"));
		contatos.add(new Contato("5",id,"Catarina Noleto","online","individual"));
		contatos.add(new Contato("6",id,"Anastacia Lobato","online","individual"));
		contatos.add(new Contato("7",id,"Fernão Madureira","online","individual"));
		contatos.add(new Contato("8",id,"André Ramos","offline","individual"));
		contatos.add(new Contato("9",id,"Silvana Lameiras","offline","individual"));
		contatos.add(new Contato("10",id,"Pérola Ruela","offline","individual"));
		contatos.add(new Contato("11",id,"Emma Brito","offline","individual"));
		contatos.add(new Contato("12",id,"Brayan Moreno","offline","individual"));
	}
	
	public ArrayList<Contato> getContatos(){
		return this.contatos;
	}
	
	public String getId() {
		return this.id;
	}
}
