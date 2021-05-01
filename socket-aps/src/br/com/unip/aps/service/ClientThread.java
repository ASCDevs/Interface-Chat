package br.com.unip.aps.service;

import java.io.DataInputStream;
import java.io.InputStream;

import br.com.unip.aps.model.Erro;

public class ClientThread implements Runnable {

	private InputStream is; // leitor de mensagem

	public ClientThread(InputStream is) {
		this.is = is;
	}

	@Override
	public void run() {

		DataInputStream dIS;
		try {

			dIS = new DataInputStream(is);

			while (true) {

				String mensagem = dIS.readUTF(); // ele trava, 'procurando' por mensagens
				System.out.println("Mensagem Recebida: " + mensagem);
			}

		} catch (Exception ex) {
			System.out.println(new Erro(ex));
		}
	}
}