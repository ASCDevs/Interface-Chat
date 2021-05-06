package br.com.unip.aps.model;

import java.util.ArrayList;
import java.util.List;

import br.com.unip.aps.dao.UsuarioDAO;

public class AutenticaUsuario {
	private String username;
	private String senha;
	
	private static UsuarioDAO usuarioDAO;
	
	public List<Usuario> autenticar(String username, String senha) {
		Usuario usuario = usuarioDAO.findByUserAndPassword(username, senha);
		
		if(usuario != null) {
			return usuarioDAO.findAll();
		}
		
		return new ArrayList<Usuario>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
