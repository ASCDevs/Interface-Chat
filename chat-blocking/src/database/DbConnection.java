package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	public Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String bdUrl = "jdbc:mysql://baseaps.mysql.dbaas.com.br/baseaps?serverTimezone=UTC";
			String bdUsuario = "baseaps";
			String bdSenha = "Lab881@UNIP";

			Connection c = DriverManager.getConnection(bdUrl, bdUsuario, bdSenha);

			return c;

		} catch (Exception ex) {
			System.out.println("Erro no DbConnection: "+ex.getMessage());

			ex.printStackTrace();

			return null;
		}
	}
}