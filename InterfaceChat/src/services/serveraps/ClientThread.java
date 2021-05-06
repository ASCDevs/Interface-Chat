package services.serveraps;

import java.io.InputStream;
import java.io.ObjectInputStream;

import models.UsuarioMensagem;


public class ClientThread implements Runnable {

	private InputStream is; // leitor de mensagem

	public ClientThread(InputStream is) {
		this.is = is;
	}

	@Override
	public void run() {
		// DataInputStream dIS;
		
		ObjectInputStream ois;
		
		try {
			ois = new ObjectInputStream(is);
			
			// dIS = new DataInputStream(is);

			while(true) {
				UsuarioMensagem user = (UsuarioMensagem) ois.readObject();
				
				System.out.println(user.getRemetente() + " mandando a mensagem: " + user.getMensagem());
				
				// String mensagem = dIS.readUTF(); // ele trava, 'procurando' por mensagens
				
				// System.out.println("Mensagem Recebida: " + mensagem);
			}

		} catch (Exception ex) {
			System.out.println("Erro client Thread");
		}
	}
}