package br.com.unip.aps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.unip.aps.model.Grupo;

public class GrupoDAO {

	private DbConnection dbConfig = new DbConnection();

	public int insert(String nomeGrupo) {
		try (Connection con = dbConfig.createConnection();) {

			String query = "INSERT INTO Grupo (nome_grupo) VALUES(?);";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString(1, nomeGrupo);

			int row = stmt.executeUpdate();
			return row;

		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public List<Grupo> findAll() {
		try (Connection con = dbConfig.createConnection();) {
			String query = "Select * from Grupo";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			List<Grupo> grupos = new ArrayList<Grupo>();

			while (rs.next()) {
				Grupo grupo = new Grupo();
				grupo.setId(rs.getInt("id_grupo"));
				grupo.setNome(rs.getString("nome_grupo"));

				grupos.add(grupo);
			}
			return grupos;

		} catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<Grupo>();
		}
	}
}
