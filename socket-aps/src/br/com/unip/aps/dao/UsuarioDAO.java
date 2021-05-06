package br.com.unip.aps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.unip.aps.model.Grupo;
import br.com.unip.aps.model.Usuario;
import br.com.unip.aps.model.UsuarioRelacao;

public class UsuarioDAO {
	private DbConnection dbConfig = new DbConnection();

	public Usuario findByUserAndPassword(String username, String senha) {
		try (Connection con = dbConfig.createConnection();) {
			String query = "SELECT u.id_usuario, u.nome_usuario, u.username_usuario, u.porta_ip_usuario, g.nome_grupo FROM UsuarioGrupo ug inner join Usuario u on u.id_usuario = ug.id_usuario and nome_usuario = ? and senha_usuario = ? left join Grupo g on g.id_grupo = ug.id_grupo";

			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString(1, username);
			stmt.setString(2, senha);

			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			Usuario usuario = new Usuario();

			while(rs.next()) {
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setUsername(rs.getString("username_usuario"));
				usuario.setPorta(rs.getInt("porta_ip_usuario"));
			}

			return usuario;

		} catch (Exception ex) {
			ex.printStackTrace();

			return new Usuario();
		}
	}

	public Usuario findByUser(String username) {
		try (Connection con = dbConfig.createConnection();) {
			String query = "SELECT u.id_usuario, u.nome_usuario, u.username_usuario, u.porta_ip_usuario, g.nome_grupo FROM UsuarioGrupo ug inner join Usuario u on u.id_usuario = ug.id_usuario and nome_usuario = ? left join Grupo g on g.id_grupo = ug.id_grupo";

			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString(1, username);

			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			Usuario usuario = new Usuario();

			while (rs.next()) {
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setUsername(rs.getString("username_usuario"));
				usuario.setPorta(rs.getInt("porta_ip_usuario"));
			}

			return usuario;

		} catch (Exception ex) {
			ex.printStackTrace();

			return new Usuario();
		}
	}

	public List<Usuario> findAll() {
		try (Connection con = dbConfig.createConnection();) {

			List<Usuario> usuarios = new ArrayList<Usuario>();

			String query = "SELECT u.id_usuario, u.nome_usuario, u.username_usuario, u.porta_ip_usuario, g.nome_grupo FROM UsuarioGrupo ug left join Usuario u on u.id_usuario = ug.id_usuario left join Grupo g on g.id_grupo = ug.id_grupo";

			PreparedStatement stmt = con.prepareStatement(query);

			stmt.execute();
			ResultSet rs = stmt.getResultSet();

			Usuario usuario;
			
			while (rs.next()) {
				usuario = new Usuario();

				usuario.setId(rs.getInt("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setUsername(rs.getString("username_usuario"));
				usuario.setPorta(rs.getInt("porta_ip_usuario"));

				usuarios.add(usuario);
			}

			stmt.close();

			return usuarios;

		} catch (Exception ex) {
			return new ArrayList<Usuario>();
		}
	}

	public int createUser(Usuario usuario) {
		try (Connection con = dbConfig.createConnection();) {
			String query = "INSERT INTO Usuario(nome_usuario, username_usuario, senha_usuario, porta_ip_usuario) VALUES(?, ?, ?, ?, ?);";

			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getUsername());
			stmt.setString(3, usuario.getSenha());
			stmt.setString(4, usuario.getIp());
			stmt.setInt(5, usuario.getPorta());

			int row = stmt.executeUpdate();

			return row;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public int InsertUserGroup(Usuario usuario, Grupo grupo) {
		try (Connection con = dbConfig.createConnection();) {
			String query = "INSERT INTO UsuarioGrupo(id_usuario, id_grupo) VALUES(?, ?);";

			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setInt(1, usuario.getId());
			stmt.setInt(2, grupo.getId());

			int row = stmt.executeUpdate();

			return row;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public int addUser(Usuario usuarioEmissor, Usuario usuarioReceptor) {
		try (Connection con = dbConfig.createConnection();) {
			String query = "INSERT INTO UsuarioRelacao(id_usuario_emissor, id_usuario_receptor) VALUES(?, ?);";

			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setInt(1, usuarioEmissor.getId());
			stmt.setInt(2, usuarioReceptor.getId());

			int row = stmt.executeUpdate();

			return row;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public int sendMessage(UsuarioRelacao usuarioRelacao, String mensagem) {
		try (Connection con = dbConfig.createConnection();) {
			String query = "INSERT INTO UsuarioHistorico(mensagem, data_mensagem, id_usuario_relacao) VALUES(?, ?, ?);";

			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString(1, mensagem);
			stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setInt(3, usuarioRelacao.getId());

			int row = stmt.executeUpdate();

			return row;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public void updateUser(Usuario user) {
		try (Connection con = dbConfig.createConnection();) {
			String query = "UPDATE Usuario SET nome_usuario = ?, username_usuario = ?, porta_ip_usuario = ? WHERE id_usuario = ?;";

			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString(1, user.getNome());
			stmt.setString(2, user.getUsername());
			stmt.setInt(3, user.getPorta());
			stmt.setInt(4, user.getId());

			stmt.executeUpdate();

		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}