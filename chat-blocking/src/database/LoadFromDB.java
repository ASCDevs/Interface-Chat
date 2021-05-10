package database;

import java.sql.Connection;
import database.DbConnection;

public class UsuarioDAO {
	private DbConnection db;
	private Connection conex;
	
	public UsuarioDAO() {
		db = new DbConnection();
		conex = db.createConnection();
	}
	
	public void encontraUser(int idUser) {
		
	}
	
	public void encontraGrupo(int idGrupo) {
		
	}
}
