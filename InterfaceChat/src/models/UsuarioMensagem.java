package models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Usuario;

public class UsuarioMensagem implements Serializable {
	private static final long serialVersionUID = 1L;

	private String remetente;
	private List<Usuario> lista;
	private String mensagem;
	private Map<String, byte[]> arquivos = new HashMap<>();
	
	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Map<String, byte[]> getArquivos() {
		return arquivos;
	}

	public void setArquivos(Map<String, byte[]> arquivos) {
		this.arquivos = arquivos;
	}

	@Override
	public String toString() {
		return "UsuarioMensagem [lista=" + lista + ", mensagem=" + mensagem + ", arquivos=" + arquivos + "]";
	}
}
