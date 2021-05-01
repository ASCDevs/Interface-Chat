package br.com.unip.aps.execs;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.unip.aps.dao.UsuarioDAO;
import br.com.unip.aps.dto.UsuarioDTO;
import br.com.unip.aps.service.SocketClient;
import br.com.unip.aps.service.UsuarioMensagem;

public class Execute_Client {

	public static void main(String[] args) throws UnknownHostException {

		Scanner sC = new Scanner(System.in);

		UsuarioMensagem userMessage = new UsuarioMensagem();
		List<UsuarioDTO> usersDTO = new ArrayList<UsuarioDTO>();

		System.out.println("informe seu Login: ");
		String usuario = sC.nextLine();
		System.out.println("\ninforme sua Senha: ");
		String senha = sC.nextLine();

		SocketClient clienteB = new SocketClient(usuario, senha);

		while (true) {

			System.out.println("\ninforme o destinátario ");

			usersDTO.add(new UsuarioDAO().findByUser(sC.nextLine()));

			System.out.println("\nDigite a mensagem");
			userMessage.setLista(usersDTO);
			userMessage.setMensagem(sC.nextLine());

			// Envio de Arquivo
//			Map<String, byte[]> arquivo = new HashMap<>();
//			arquivo.put("arquivo.txt", Files.readAllBytes(Paths.get("arquivo.txt")));
//			userMessage.setArquivos(arquivo);

			clienteB.sendMessage(userMessage);

		}

	}

}
