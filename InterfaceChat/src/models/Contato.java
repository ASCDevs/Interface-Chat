package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import application.ContatoController;

public class Contato {
	private int idContato;
	private Usuario userLogado;
	private String nome;
	private String status;
	private List<Mensagem> conversa; 
	private ContatoController contatoController;
	private List<Contato> integrantes;
	
	Contato(int idContato,Usuario userLogado, String nome){
		this.idContato = idContato;
		this.userLogado = userLogado;
		this.nome = nome;
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
		//userLogado.gravaMensagemConversa(msg); //-> grava no banco
	}
	
	public void addMensagemConversa(Mensagem msg) {
		conversa.add(msg);
	}
	
	public String getNome() {
		return this.nome;
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
	
	
	public ContatoController getController() {
		return this.contatoController;
	}
	
	public List<Mensagem> getConversa(){
		return this.conversa;
	}
	
}
