package testes;

import services.serverconnect.ServerDataInfo;

public class TesteBanco {

	public static void main(String[] args) {
		ServerDataInfo bd = new ServerDataInfo();
		
		System.out.println("Usu�rio v�lido: "+bd.validarLogin("rafaela", "123"));
		
		
		
		bd.close();
	}

}
