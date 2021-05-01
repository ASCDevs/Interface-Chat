package br.com.unip.aps.model;

import java.time.LocalDateTime;

public class GrupoHistorico {
	private int id;
	private String mensagem;
	private boolean mensagemLida;
	private LocalDateTime mensagemData;
	private Usuario usuario;
	private Grupo grupo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isMensagemLida() {
		return mensagemLida;
	}

	public void setMensagemLida(boolean mensagemLida) {
		this.mensagemLida = mensagemLida;
	}

	public LocalDateTime getMensagemData() {
		return mensagemData;
	}

	public void setMensagemData(LocalDateTime mensagemData) {
		this.mensagemData = mensagemData;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
}