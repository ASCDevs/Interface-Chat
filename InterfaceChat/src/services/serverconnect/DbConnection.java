package services.serverconnect;

import java.sql.Connection;
import java.sql.DriverManager;

import services.serverconnect.Erro;

public class DbConnection {

	public Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String bdUrl = "";
			String bdUsuario = "";
			String bdSenha = "";

			Connection c = DriverManager.getConnection(bdUrl, bdUsuario, bdSenha);

			return c;

		} catch (Exception ex) {
			System.out.println(new Erro(ex));

			ex.printStackTrace();

			return null;
		}
	}
}