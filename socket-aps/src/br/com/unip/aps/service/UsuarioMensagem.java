package br.com.unip.aps.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.unip.aps.dto.UsuarioDTO;

public class UsuarioMensagem implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<UsuarioDTO> lista;
	private String mensagem;
	private Map<String, byte[]> arquivos = new HashMap<>();

	public List<UsuarioDTO> getLista() {
		return lista;
	}

	public void setLista(List<UsuarioDTO> lista) {
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
