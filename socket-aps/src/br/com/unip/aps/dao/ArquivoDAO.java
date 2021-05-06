package br.com.unip.aps.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import javax.sql.rowset.serial.SerialBlob;

public class ArquivoDAO {
	private DbConnection dbConfig = new DbConnection();
	
	public void insertFiles(Map<String, byte[]> arquivos) {
		try {
			Connection con = dbConfig.createConnection();
			
			for(Map.Entry<String, byte[]> arquivo : arquivos.entrySet()) {
				Blob blob = new SerialBlob(arquivo.getValue());
				
				String query = "INSERT INTO ArquivosHistorico (nome, bytes) VALUES (?, ?);";
				
				PreparedStatement stmt = con.prepareStatement(query);
				
				stmt.setString(1, arquivo.getKey());
				stmt.setBlob(2, blob);
	
				stmt.execute();
			}
			
			con.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
