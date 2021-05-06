package services.serveraps;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Usuario;
import services.serveraps.SocketClient;
import models.UsuarioMensagem;

public class Execute_Client {

	public static void main(String[] args) throws UnknownHostException {
		Scanner sC = new Scanner(System.in);

		UsuarioMensagem userMessage = new UsuarioMensagem();
		List<Usuario> users = new ArrayList<Usuario>();

		System.out.println("informe seu Login: ");
		String usuario = sC.nextLine();
		

		SocketClient clienteB = new SocketClient(usuario);

		while(true) {
			//System.out.println("\ninforme o destinatario ");

			//users.add(new UsuarioDAO().findByUser(sC.nextLine()));

			System.out.println("\nDigite a mensagem");
			
			//userMessage.setLista(users);
			userMessage.setMensagem(sC.nextLine());
			userMessage.setRemetente(usuario);

			// Envio de Arquivo
//			Map<String, byte[]> arquivo = new HashMap<>();
//			arquivo.put("arquivo.txt", Files.readAllBytes(Paths.get("arquivo.txt")));
//			userMessage.setArquivos(arquivo);

			clienteB.sendMessage(userMessage);
			System.out.println("Sucesso envio do cliente");
		}
	}
}
