package controller.admin;

import java.io.IOException;

import controller.helpers.WindowLauncher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.admin.AdminModel;
import model.admin.NamePwdMismatchException;

public class LogInController {
	
	private Stage prmStage;
	private AdminModel model;
	
	@FXML
	private Button switchToDJButton, userEnterButton, djLogInButton, switchToUserButton;

	@FXML
	private TextField userNameField, djNameField, djPwdField;
	
    @FXML
    private Label errorLabel;
	

	// Basic:
	public void init(Stage prmStage, AdminModel model) {
		this.prmStage = prmStage;
		this.model = model;
		
		if (userNameField != null && userEnterButton != null)
			updateUserEnterButton();
		else
			updateDJLogInButton();
	}
	
	
	// Control:
	public void switchToDJ() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/admin/DJLogInView.fxml"));
			Parent root = loader.load();
			
			LogInController ctrl = loader.getController();
			ctrl.init(prmStage, model);
			
			Scene scene = new Scene(root);
			prmStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUserEnterButton() {
		if (userNameField.getText().isEmpty())
			userEnterButton.setDisable(true);
		else
			userEnterButton.setDisable(false);
	}
	
	public void enterAsUser() {
		WindowLauncher launcher = new WindowLauncher();
		
		if (!userNameField.getText().isEmpty())
			launcher.showUserAppView(model.getUserView(userNameField.getText()));
		
		prmStage.close();
	}
	
	public void switchToUser() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/admin/UserLogInView.fxml"));
			Parent root = loader.load();
			
			LogInController ctrl = loader.getController();
			ctrl.init(prmStage, model);

			Scene scene = new Scene(root);
			prmStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateDJLogInButton() {
		if (djNameField.getText().isEmpty() || djPwdField.getText().isEmpty())
			djLogInButton.setDisable(true);
		else
			djLogInButton.setDisable(false);
		errorLabel.setText("");
	}
	
	public void logInAsDJ() {
		WindowLauncher launcher = new WindowLauncher();
		
		if (!djNameField.getText().isEmpty() && !djPwdField.getText().isEmpty()) {
			try {
				launcher.showDJAppView(model.getDJView(djNameField.getText(), djPwdField.getText()));
				prmStage.close();
			}
			catch(NamePwdMismatchException npme) {
				errorLabel.setText(npme.getMessage());
			}
		}
	}
	
}
