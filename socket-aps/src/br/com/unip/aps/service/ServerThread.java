package br.com.unip.aps.service;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import br.com.unip.aps.model.Erro;

public class ServerThread implements Runnable {
	// ServerThread = clientes

	private Socket socketClient;

	public ServerThread(Socket socketClient) {

		this.socketClient = socketClient;

		// Nome pra Thread
		Thread.currentThread().setName(String.valueOf(socketClient.getLocalPort()));
	}

	@Override
	public void run() {
		try {
			DataOutputStream dOS;

			InputStream is = socketClient.getInputStream();
			ObjectInputStream oIS = new ObjectInputStream(is);

			UsuarioMensagem userMensagem = (UsuarioMensagem) oIS.readObject();
			System.out.println("mensagen chegando no Servidor: " + userMensagem.getMensagem());

			// ServerThread = tem todos os Sockets Ativos no momento
			// ListaUserDto = recebe a lista com todas as portas que devemos despachar as
			// mensagens

			for (int i = 0; i < userMensagem.getLista().size(); i++) {
				for (int k = 0; k < SocketServer.getServerThread().size(); k++) {

					if (userMensagem.getLista().get(i).getPorta() == SocketServer.getServerThread().get(k)
							.getSocketClient().getPort()) {

						dOS = new DataOutputStream(
								SocketServer.getServerThread().get(k).getSocketClient().getOutputStream());
						dOS.writeUTF("Remetente Porta: " + socketClient.getPort() + " - " + userMensagem.getMensagem());

						// verifico se tenho arquivos
						if (userMensagem.getArquivos().size() > 0) {
							FileOutputStream fOS = new FileOutputStream("arquivoRecebido.txt");
							fOS.write(userMensagem.getArquivos().get("arquivo.txt"));
						}
					}
				}
			}

		} catch (Exception ex) {
			System.out.println(new Erro(ex));
		}
	}

	public Socket getSocketClient() {
		return socketClient;
	}

	public void setSocketClient(Socket socketClient) {
		this.socketClient = socketClient;
	}

}
