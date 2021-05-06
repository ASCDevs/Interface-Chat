package br.com.unip.aps.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

public class EmailService {
	private final String LOGIN = "ApsUnipSocket@outlook.com";
	private final String PASSWORD = "Lab881@UNIP";
	
	public void enviar() { // String emailDestinatario, String assunto, Map<String, byte[]> arquivos
		MultiPartEmail email = new MultiPartEmail();
		
		email.setHostName("smtp.office365.com");
		
		email.setSmtpPort(587);
		
		email.setAuthenticator(new DefaultAuthenticator(LOGIN, PASSWORD));
		
		email.setStartTLSRequired(true);
		
		try {
			email.setFrom(LOGIN);
			
			email.setSubject("APS");
			
			email.setMsg("Teste de envio para o trabalho da APS");
			
			email.addTo("luiz.gustavon@hotmail.com");
			
			
			EmailAttachment anexo = new EmailAttachment();
			
			anexo.setPath("java.png");
			
			anexo.setName("Java.png"); // Renomear o arquivo
			
			email.attach(anexo);
			
			
			email.setDebug(true);
			
			email.send();
			
			System.out.println("Email enviado com sucesso");
			
		} catch(Exception erro) {
			erro.printStackTrace();
		}
	}
}
