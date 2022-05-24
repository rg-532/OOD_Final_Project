package controller.frame;

import java.io.IOException;

import controller.menus.DJManageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class DJFrameController extends UserFrameController {
	
	@FXML
	private Button manageMenuButton;
	
	
	public void loadManage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/menus/DJManageView.fxml"));
			GridPane root = loader.load();

			DJManageController ctrl = loader.getController();
			ctrl.init(prmStage, model.getDJManageModel());

			menuPane.getChildren().clear();
			menuPane.getChildren().add(root);
			root.prefHeightProperty().bind(menuPane.heightProperty());
			root.prefWidthProperty().bind(menuPane.widthProperty());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
