module HelloFX {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires javafx.base;
	requires java.sql;
	requires commons.email;
	
	opens application to javafx.graphics, javafx.fxml;
}
