package br.com.unip.aps.service;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.com.unip.aps.dao.UsuarioDAO;
import br.com.unip.aps.model.Erro;
import br.com.unip.aps.model.UsuarioRelacao;

public class ServerThread implements Runnable {
	// ServerThread = clientes

	private Socket socketClient;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private UsuarioRelacao usuarioRelacao = new UsuarioRelacao();

	public ServerThread(Socket socketClient) {
		this.socketClient = socketClient;

		// Nome pra Thread
		Thread.currentThread().setName(String.valueOf(socketClient.getLocalPort()));
	}

	@Override
	public void run() {
		try {
			// DataOutputStream dOS;
			
			ObjectOutputStream oos;
			
			InputStream is = socketClient.getInputStream();
			ObjectInputStream oIS = new ObjectInputStream(is);

			UsuarioMensagem userMensagem = (UsuarioMensagem) oIS.readObject();
			System.out.println("mensagen chegando no Servidor: " + userMensagem.getMensagem());
			
			// ServerThread = tem todos os Sockets Ativos no momento
			// ListaUserDto = recebe a lista com todas as portas que devemos despachar as
			// mensagens
			
			boolean enviado = false;

			for(int i = 0; i < userMensagem.getLista().size(); i++) {
				for(int k = 0; k < SocketServer.getServerThread().size(); k++) {

					if(userMensagem.getLista().get(i).getPorta() == SocketServer.getServerThread().get(k).getSocketClient().getPort()) {
						oos = new ObjectOutputStream(SocketServer.getServerThread().get(k).getSocketClient().getOutputStream());
						
						oos.writeObject(userMensagem);
						
						usuarioRelacao.setUsuarioEmissor(usuarioDAO.findByUser(userMensagem.getRemetente()));
						usuarioRelacao.setUsuarioReceptor(usuarioDAO.findByUser(userMensagem.getLista().get(i).getNome()));
						
						if(userMensagem.getLista().size() > 1 && !enviado) {
							enviado = true;  // grupo
							
							// criar o no GrupoDAO o 'sendMessage' para armazenar como historico
							
						} else {
							enviado = true; // individual
							
							usuarioDAO.sendMessage(usuarioRelacao, userMensagem.getMensagem());
						}
						
						
//						dOS = new DataOutputStream(SocketServer.getServerThread().get(k).getSocketClient().getOutputStream());
//						
//						dOS.writeUTF("Remetente Porta: " + socketClient.getPort() + " - " + userMensagem.getMensagem());

						
						
						// verifico se tenho arquivos
//						if (userMensagem.getArquivos().size() > 0) {
//							
//							for(Map.Entry<String, byte[]> arquivo : userMensagem.getArquivos().entrySet()) {
//								FileOutputStream fOS = new FileOutputStream(arquivo.getKey());
//								
//								// ObjectOutputStream oos = new ObjectOutputStream(fOS);
//								
//								fOS.write(arquivo.getValue());
//							}
//						}
					}
				}
			}
		
		} catch (Exception ex) {
			System.out.println("Server Thread: "+new Erro(ex));
		}
	}

	public Socket getSocketClient() {
		return socketClient;
	}

	public void setSocketClient(Socket socketClient) {
		this.socketClient = socketClient;
	}
}
