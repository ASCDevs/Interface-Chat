package services.serverconnect;

import java.util.HashMap;
import java.util.Map;

public class Erro extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private static Map<String, String> exception = new HashMap<String, String>();
	private String msgErro;
	
	static {
		exception.put("ClassNotFoundException", "Driver não encontrado");
		exception.put("SQLException", "Erro com a fonte de dados");
		exception.put("IOException", "Erro com IOException");
	}
	
	public Erro(Exception ex) {
		if(exception.containsKey(ex.getClass().getSimpleName())) {
			this.msgErro = exception.get(ex.getClass().getSimpleName());
			
		} else {
			this.msgErro = ex.getMessage();
		}
	}
	
	public static Map<String, String> getException() {
		return exception;
	}

	public String getMsgErro() {
		return msgErro;
	}

	public void setMsgErro(String nome) {
		this.msgErro = nome;
	}

	@Override
	public String toString() {
		return this.msgErro;
	}
}
