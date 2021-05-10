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
			e.printStackTrace();
		}
		return idUser;
	}
	
	/*public static void main(String[] args) {
		LoadFromDB banco = new LoadFromDB();
	
		int port = banco.retornaIdUser(51119);
		System.out.println("Hello "+port);
	}*/
}
