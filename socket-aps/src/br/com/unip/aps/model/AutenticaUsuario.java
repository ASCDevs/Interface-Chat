package br.com.unip.aps.model;

import java.util.ArrayList;
import java.util.List;

import br.com.unip.aps.dao.UsuarioDAO;
import br.com.unip.aps.dto.UsuarioDTO;

public class AutenticaUsuario {
	private String username;
	private String senha;
	
	private static UsuarioDAO usuarioDAO;
	
	public List<UsuarioDTO> autenticar(String username, String senha) {
		UsuarioDTO usuario = usuarioDAO.findByUserAndPassword(username, senha);
		
		if(usuario != null) {
			usuarioDAO.findAll();
		}
		
		return new ArrayList<UsuarioDTO>();
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
