package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Usuario;

public class FormularioController implements Initializable {
	
	private Usuario userLogado;
	private EmailService emailService;
	
	@FXML
	public Button btnFecharSobre;
	
	@FXML
	public Button btnAddArquivo;
	
	@FXML
	public Button btnAbrirArquivo;
	
	@FXML
	public Button btnRemoverArquivo;
	
	@FXML
	public Label lblErro;
	
	@FXML
	public TextField campoAssunto;
	
	@FXML
	public TextField campoEmail;
	
	@FXML
	public TextArea campoObs;
	
	@FXML
	public ListView<File> listaArquivos;
	
	@FXML
	public Label labelAviso;
	
	
	FormularioController(Usuario userLogado){
		this.userLogado = userLogado;
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	public void enviarEmail() {
		
		if(validarCampos()) {
			labelAviso.setText("");
			String assunto = campoAssunto.getText();
			String emailDestino = campoEmail.getText();
			String mensagem = campoObs.getText();
			List<File> arquivos = listaArquivos.getItems();
			
			emailService =  new EmailService();
			boolean sucesso = emailService.enviar(emailDestino, assunto, mensagem, arquivos);
			
			if(sucesso) {
				JOptionPane.showMessageDialog(null, "Email enviado com sucesso!");
			}else {
				JOptionPane.showMessageDialog(null, "Email não eviado, por favor verifique se as informações estão corretas");
			}
			
		}
		
		
	}
	
	@FXML
	public void abrirSobre() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/TelaSobreFormulario.fxml"));
			loader.setController(new FormularioController(userLogado));
			Pane telaSobreConfig = new Pane();
			telaSobreConfig = loader.load();
			String titulo = "Sobre Configurações de Usuário";
			Stage janelaSobre = new Stage();
			janelaSobre.setResizable(false);
			janelaSobre.setTitle(titulo);
			janelaSobre.setScene(new Scene(telaSobreConfig));
			janelaSobre.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void selecionarArquivo() {
		Stage janelaArquivos = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File file = fileChooser.showOpenDialog(janelaArquivos);
		if(file != null) {
			if(!listaArquivos.getItems().contains(file)) {
				listaArquivos.getItems().add(file);
				lblErro.setText("");
			} 
			else {
				lblErro.setText("Erro - O arquivo escolhido já foi selecionado.");
			}
		}
		
	}
	
	@FXML
	public void abrirArquivo() throws IOException {
			lblErro.setText("");
			if(listaArquivos.getSelectionModel().getSelectedItem() != null) {
				File file = listaArquivos.getSelectionModel().getSelectedItem();
				java.awt.Desktop.getDesktop().open(file);
			}
			else {
				lblErro.setText("Erro - Nenhum arquivo selecionado!!");
			}		
		 
	}
	
	@FXML
	public void removerArquivo() {
		File arquivoSelecionado = listaArquivos.getSelectionModel().getSelectedItem();
		listaArquivos.getItems().remove(arquivoSelecionado);
		lblErro.setText("");
	}
	
	public boolean validarCampos() {
		labelAviso.setText("");
		boolean flag = true;
		String assunto = campoAssunto.getText();
		String emailDestino = campoEmail.getText();
		String msgAviso = "Erro: ";
		if(assunto.isEmpty()||assunto.substring(0,1).equals(" ")) {
			msgAviso += "\r\n"+"- Campo assunto deve ser preenchido corretamente";
			flag = false;
		}
		
		if(emailDestino.isEmpty()||emailDestino.substring(0,1).equals(" ")) {
			msgAviso += "\r\n"+"- Campo do email deve ser preenchido corretamente";
			flag = false;
		}
		
		labelAviso.setText(msgAviso);
		
		return flag;
	}
	
	
	@FXML
	public void fecharSobre(){
		final Stage stage = (Stage) btnFecharSobre.getScene().getWindow();
		stage.close();
	}
	
	
	
}
