package br.com.unip.aps.model;

import java.time.LocalDateTime;

public class UsuarioHistorico {
	private int id;
	private String mensagem;
	private boolean mensagemLida;
	private LocalDateTime mensagemData;
	private Usuario usuarioEmissor;
	private Usuario usuarioReceptor;

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

	public Usuario getUsuarioEmissor() {
		return usuarioEmissor;
	}

	public void setUsuarioEmissor(Usuario usuarioEmissor) {
		this.usuarioEmissor = usuarioEmissor;
	}

	public Usuario getUsuarioReceptor() {
		return usuarioReceptor;
	}

	public void setUsuarioReceptor(Usuario usuarioReceptor) {
		this.usuarioReceptor = usuarioReceptor;
	}
}