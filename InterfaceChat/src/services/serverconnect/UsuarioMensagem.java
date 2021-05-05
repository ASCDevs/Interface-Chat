package services.serverconnect;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UsuarioMensagem implements Serializable {
	private static final long serialVersionUID = 1L;

	private String mensagem;
	private Map<String, byte[]> arquivos = new HashMap<>();

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


}
