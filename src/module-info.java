module OODFinalProject {
	requires javafx.controls;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller.admin to javafx.fxml;
	opens controller.menus to javafx.fxml;
	opens controller.filter to javafx.fxml;
	opens controller.frame to javafx.fxml;
	opens controller.rooms to javafx.fxml;
}
