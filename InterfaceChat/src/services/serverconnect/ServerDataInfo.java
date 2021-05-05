package services.serverconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerDataInfo {
	
	private DbConnection db;
	private Connection conex;
	
	public ServerDataInfo(){
		db = new DbConnection();
		conex = db.createConnection();	
	}
	
	public boolean validarLogin(String usuario, String senha) {
		String query = "SELECT username_usuario, senha_usuario FROM Usuario WHERE username_usuario = ? AND senha_usuario = ?";
		
		try {
			PreparedStatement stmt = conex.prepareStatement(query);
			
			stmt.setString(1, usuario);
			stmt.setString(2, senha);
			
			stmt.execute();
			
			ResultSet resultado = stmt.getResultSet();
			
			
			return resultado.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public void cadastrarUsuario() {
		
	}
	
	public void excluirConta() {
		
	}
	
	public void close() {
		try {
			conex.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
