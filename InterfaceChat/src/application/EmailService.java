package application;

import java.io.File;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.MultiPartEmail;

public class EmailService {
	private final String LOGIN = "ApsUnipSocket@outlook.com";
	private final String PASSWORD = "Lab881@UNIP";
	
	public boolean enviar(String emailDestinatario, String assunto, String mensagem, List<File> arquivos) {
		MultiPartEmail email = new MultiPartEmail();
		
		email.setHostName("smtp.office365.com");
		
		email.setSmtpPort(587);
		
		email.setAuthenticator(new DefaultAuthenticator(LOGIN, PASSWORD));
		
		email.setStartTLSRequired(true);
		
		try {
			email.setFrom(LOGIN);
			
			email.setSubject(assunto);
			
			email.setMsg(mensagem);
			
			email.addTo(emailDestinatario);
			
			for(File arquivo : arquivos) {
				email.attach(arquivo);
			}
			
			email.setDebug(true);
			
			email.send();
			
			return true;
			
		} catch(Exception erro) {
			erro.printStackTrace();
			
			return false;
		}
	}
}
