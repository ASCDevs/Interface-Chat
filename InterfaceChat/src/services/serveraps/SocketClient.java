package services.serveraps;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import models.UsuarioMensagem;
import models.Usuario;

public class SocketClient {
	// Manda a mensagens

	private Socket socket;

	public SocketClient(String username) throws UnknownHostException {

		// Conseguimos Autenticar e recebemos os dados do Cliente
		Usuario user = new Usuario(username);

		try {
			socket = new Socket("localhost", 51995);

			System.out.println("Sou o Cliente: " + username + " - " + socket.getLocalPort());

			new Thread(new ClientThread(socket.getInputStream())).start();

		} catch (Exception ex) {
			System.out.println("Erro ao criar");
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
