package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Contato {
	private String idContato;
	private String idUserLogado;
	private String nome;
	private String status;
	private String tipoContato;
	private List<List<String>> conversa; 
	private List<Contato> integrantes;
	
	Contato(String idContato,String idUserLogado, String nome, String status, String tipoContato){
		this.idContato = idContato;
		this.idUserLogado = idUserLogado;
		this.nome = nome;
		this.status = status;
		this.tipoContato = tipoContato;
		this.conversa = new ArrayList<List<String>>();
		this.loadConversa();
	}
	
	private void loadConversa() {
		//Em produção, com o id do usuário logado, este método
		//irá solicitar ao server o histórico de conversa
		//isso se dará da realação do id do usuário com id do contato
		
		//Fazer aqui o tratamento para caso o Contato seja do tipo Grupo
		
		//Dados de conversa fake do tipo Individual
		//(Id de quem mandou a mesangem, mensagem, data e hora)
		conversa.add(Arrays.asList(idContato,"Mensagem 1 Contato Lorem ipsum dolor sit amet, consectetur ad","(Data a ser Formatada)"));
		conversa.add(Arrays.asList(idContato,"Mensagem 2 Contato Lorem ipsum dolor sit amet, consectetur ad","(Data a ser Formatada)"));
		conversa.add(Arrays.asList(idUserLogado,"Mensagem 1 Usuário Lorem ipsum dolor sit amet, consectetur ad","(Data a ser Formatada)"));
		conversa.add(Arrays.asList(idContato,"Mensagem 3 Contato Lorem ipsum dolor sit amet, consectetur ad","(Data a ser Formatada)"));
		conversa.add(Arrays.asList(idUserLogado,"Mensagem 2 Usuário Lorem ipsum dolor sit amet, consectetur ad","(Data a ser Formatada)"));
		conversa.add(Arrays.asList(idContato,"Mensagem 4 Contato Lorem ipsum dolor sit amet, consectetur ad","(Data a ser Formatada)"));
		conversa.add(Arrays.asList(idUserLogado,"Mensagem 3 Usuário Lorem ipsum dolor sit amet, consectetur ad","(Data a ser Formatada)"));
		conversa.add(Arrays.asList(idUserLogado,"Mensagem 4 Usuário Lorem ipsum dolor sit amet, consectetur ad","(Data a ser Formatada)"));
		conversa.add(Arrays.asList(idContato,"Mensagem 5 = Mensagens gravadas as: "+new Date(),"(Data a ser Formatada)"));
	}
	
	public void addMsgTextoUserLogado(String msg) {
		//Quando conectar ao local de armazenamento 
		//não será necessário gravar no array, somente enviar pra onde 
		//irá armazenar
		conversa.add(Arrays.asList(idUserLogado,msg,"(Data a ser formatada)"));
	}
	
	public void addMsgTextoContato(String msg) {
		conversa.add(Arrays.asList(idContato,msg,"(Data a ser Formatada)"));
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public String getIdContato() {
		return this.idContato;
	}
	
	public String getIdUserLogado() {
		return this.idUserLogado;
	}
	
	public List<List<String>> getConversa(){
		return this.conversa;
	}
	
	public String getTipoContato() {
		return this.tipoContato;
	}
}
