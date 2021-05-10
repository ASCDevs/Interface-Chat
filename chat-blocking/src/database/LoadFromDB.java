package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import database.DbConnection;
import models.Usuario;
import serverobject.ClientSocket;

public class LoadFromDB {
	private DbConnection db;
	private Connection conex;
	
	public LoadFromDB() {
		db = new DbConnection();
		conex = db.createConnection();
	}
	
	
	public void encontraGrupo(int idGrupo) {
		
	}
	
	
	public void closeConection() {
		try {
			this.conex.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public int retornaPorta(int idDestinatario) {
		String query = "SELECT * FROM Usuario WHERE id_usuario = ?";
		int porta = 0;
		try {
			PreparedStatement stmt = conex.prepareStatement(query);
			stmt.setInt(1, idDestinatario);
			stmt.execute();
			ResultSet dadosUser = stmt.getResultSet();
			
			while(dadosUser.next()) {
				porta = dadosUser.getInt("porta_ip_usuario");
			}
			stmt.close();
		}catch (SQLException e) {
			System.out.println("Erro ao retornar porta: "+ e.getMessage());
			e.printStackTrace();
		}
		return porta;
	}
	
	public int retornaIdUser(int portaUser) {
		String query = "SELECT * FROM Usuario WHERE porta_ip_usuario = ?";
		int idUser = 0;
		try {
			PreparedStatement stmt = conex.prepareStatement(query);
			stmt.setInt(1, portaUser);
			stmt.execute();
			ResultSet dadosUser = stmt.getResultSet();
			
			while(dadosUser.next()) {
				idUser = dadosUser.getInt("id_usuario");
			}
			stmt.close();
		}catch (SQLException e) {
			System.out.println("Erro ao retornar id user: "+e.getMessage());
			e.printStackTrace();
		}
		return idUser;
	}


	public String retornaNomeUser(int idUser) {
		String query = "SELECT * FROM Usuario WHERE id_usuario = ?";
		String nome = "";
		try {
			PreparedStatement stmt = conex.prepareStatement(query);
			stmt.setInt(1, idUser);
			stmt.execute();
			ResultSet dadosUser = stmt.getResultSet();
			
			while(dadosUser.next()) {
				nome = dadosUser.getString("nome_usuario");
			}
			stmt.close();
		}catch(SQLException e) {
			System.out.println("Erro ao retornar nome user: "+e.getMessage());
			e.printStackTrace();
		}
		
		return nome;
	}
	
}
