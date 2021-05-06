package br.com.unip.aps.model;

public class UsuarioRelacao {
	private int id;
	private Usuario usuarioEmissor;
	private Usuario usuarioReceptor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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