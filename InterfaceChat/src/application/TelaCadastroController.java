package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TelaCadastroController {

	@FXML
	public Button btnCancelar;
	
	@FXML
	public Button btnCadastrar;
	
	@FXML
	public void	cancelarAction() {
		final Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void cadastrarAction() {
		
	}
	
}
