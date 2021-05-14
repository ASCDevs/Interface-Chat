package models;


import java.io.Serializable;
import java.io.File;


public class Mensagem implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nomeRemetente;
	private String nomeDestinatario;
	private int idRemetente;
	private int idDestinatario;
	private String mensagem;
	private boolean arquivoPresente = false;
	private File arquivo;
	
	public String getNomeDestino() {
		return this.nomeDestinatario;
	}
	
	public void setNomeDestino(String nomeDestino) {
		this.nomeDestinatario = nomeDestino;
	}
	
	public String getNomeRemetente() {
		return this.nomeRemetente;
	}
	
	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}

	public void setIdDestinatario(int idDestinatario) {
		this.idDestinatario = idDestinatario;
	}
	
	public int getIdDestinatario() {
		return idDestinatario;
	}
	
	public void setIdRemetente(int idRemetente) {
		this.idRemetente = idRemetente;
	}
	
	public int getIdRemetente() {
		return idRemetente;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public boolean getArquivoPresente() {
		return this.arquivoPresente;
	}
	
	public void setArquivo(File arquivo) {
		this.arquivoPresente = true;
		this.arquivo = arquivo;
	}
	
	public File getArquivo() {
		return this.arquivo;
	}

}
