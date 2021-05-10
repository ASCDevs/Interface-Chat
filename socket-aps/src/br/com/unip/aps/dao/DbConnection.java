package br.com.unip.aps.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import br.com.unip.aps.model.Erro;

public class DbConnection {

	public Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String bdUrl = "jdbc:mysql://baseaps.mysql.dbaas.com.br/baseaps?serverTimezone=UTC&autoReconnect=true";
			String bdUsuario = "baseaps";
			String bdSenha = "Lab881@UNIP";
			
			Connection c = DriverManager.getConnection(bdUrl, bdUsuario, bdSenha);

			return c;

		} catch (Exception ex) {
			System.out.println(new Erro(ex));

			ex.printStackTrace();

			return null;
		}
	}
}