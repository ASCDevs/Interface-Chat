package models;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import application.ContatoController;
import services.serverconnect.DbConnection;

public class Contato {
	private int idContato;
	private Usuario userLogado;
	private String nome;
	private String sobrenome;
	private String equipe;
	private String status;
	private List<Mensagem> conversa; 
	private ContatoController contatoController;

	
	Contato(int idContato,Usuario userLogado, String nome, String sobrenome, String equipe){
		this.idContato = idContato;
		this.userLogado = userLogado;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.equipe = equipe;
		this.conversa = new ArrayList<Mensagem>();
		contatoController = new ContatoController(this);
		this.loadConversa();
	}
	
	public void recebeMensagemContato(Mensagem msg) {
		contatoController.recebeMensagem(msg);
	}
	
	private void loadConversa() {
		conversa = userLogado.carregaConversaEntreContatoUser(idContato);
		System.out.println("Conversa de("+nome+") carregada");
	}
	
	public void salvaMensagem(Mensagem msg) {
		userLogado.salvaMensagemComContato(msg, this.idContato);
	}
	
	public void addMensagemConversa(Mensagem msg) {
		conversa.add(msg);
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getSobrenome() {
		return this.sobrenome;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public int getIdContato() {
		return this.idContato;
	}
	
	public Usuario getUserLogado() {
		return this.userLogado;
	}
	
	public String getEquipe() {
		return this.equipe;
	}
	
	
	public ContatoController getController() {
		return this.contatoController;
	}
	
	public List<Mensagem> getConversa(){
		return this.conversa;
	}

	@Override
	public String toString() {
		return nome + " " + sobrenome;
	}
	
	
	
	
}
