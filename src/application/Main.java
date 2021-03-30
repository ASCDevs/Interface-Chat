package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	private static Stage stage;
	private Pane telaLogin;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			stage.setTitle("Login");
			
			
			telaLogin = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
			
			Scene cena = new Scene(telaLogin);
			stage.setScene(cena);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void openTela(Pane tela, String titulo) {
		stage.setTitle(titulo);
		Scene cena = new Scene(tela);
		stage.setScene(cena);
		stage.show();
	}
	
}
