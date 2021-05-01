package br.com.unip.aps.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.com.unip.aps.model.Erro;

public class SocketServer {
	private static List<ServerThread> serverThread = new ArrayList<ServerThread>();
	private ServerSocket serverSocket;

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		SocketServer server = new SocketServer();
	}

	public SocketServer() throws IOException {
		try {
			this.serverSocket = new ServerSocket(51995);

			System.out.println("Servidor ativo na porta: " + serverSocket.getLocalPort());

			while (true) {
				Socket socketClient = serverSocket.accept();

				ServerThread st = new ServerThread(socketClient);
				serverThread.add(st);
				System.out.println("cliente " + socketClient.getPort() + " adicionado na lista");
				new Thread(st).start();
			}

		} catch (Exception ex) {
			System.out.println(new Erro(ex));

		} finally {
			this.serverSocket.close();
		}
	}

	public static List<ServerThread> getServerThread() {
		return serverThread;
	}

}
