package models;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Mensagem implements Serializable {
	private static final long serialVersionUID = 1L;

	//private List<UsuarioDTO> lista;
	private String nomeRemetente;
	private int idRemetente;
	private int idDestinatario;
	private List<Integer> idDestinatarios;
	private String mensagem;
	private Map<String, byte[]> arquivos = new HashMap<>();
	
	public String getNomeRemetente() {
		return this.nomeRemetente;
	}
	
	public void setNomeRemetente(String nome) {
		this.nomeRemetente = nome;
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

	public Map<String, byte[]> getArquivos() {
		return arquivos;
	}

	public void setArquivos(Map<String, byte[]> arquivos) {
		this.arquivos = arquivos;
	}
	
	public void addDestinatario(int idDestinatario) {
		this.idDestinatarios.add(idDestinatario);
	}
	
	public List<Integer> getIdDestinatarios(){
		return this.idDestinatarios;
	}

}
