package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
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
			
			/*TelaLoginController loginControl = new TelaLoginController();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/TelaLogin.fxml"));
			loader.setController(loginControl);
			telaLogin = loader.load();*/
			telaLogin = FXMLLoader.load(getClass().getResource("/resources/TelaLogin.fxml"));
			
			Scene cena = new Scene(telaLogin);
			stage.setResizable(false);
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
		if(titulo=="Login") {
			stage.setResizable(false);
		}else {
			stage.setResizable(true);
		}
		stage.setScene(cena);
		stage.show();
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((screenBounds.getWidth()-stage.getWidth())/2);
		stage.setY((screenBounds.getHeight()-stage.getHeight())/2);
	}
	
}
