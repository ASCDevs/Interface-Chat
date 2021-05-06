package br.com.unip.aps.service;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import br.com.unip.aps.dao.UsuarioDAO;
import br.com.unip.aps.model.Erro;
import br.com.unip.aps.model.Usuario;

public class SocketClient {
	// Manda a mensagens

	private Socket socket;

	public SocketClient(String usuario, String senha) throws UnknownHostException {

		// Conseguimos Autenticar e recebemos os dados do Cliente
		Usuario user = new UsuarioDAO().findByUserAndPassword(usuario, senha);

		try {
			socket = new Socket("localhost", 51995, null, user.getPorta());

			System.out.println("Sou o Cliente: " + usuario + " - " + socket.getLocalPort());

			new Thread(new ClientThread(socket.getInputStream())).start();

		} catch (Exception ex) {
			System.out.println(new Erro(ex));
		}
	}

	public void sendMessage(UsuarioMensagem userMsg) {
		try {

			ObjectOutputStream oOS = new ObjectOutputStream(socket.getOutputStream());
			oOS.writeObject(userMsg);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
