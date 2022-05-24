package controller.rooms;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.simple.Genre;
import model.rooms.RequestRoomModel;

public class RequestRoomController {

	private Stage prmStage;
	private RequestRoomModel model;

	@FXML
	private Button requestSendButton;

	@FXML
	private ComboBox<Genre> songGenreBox;

	@FXML
	private TextField songNameField;

	// Basic:
	public void init(Stage prmStage, RequestRoomModel model) {
		this.prmStage = prmStage;
		this.model = model;
		updateRequestSendButton();
		
		initGenreBox();
	}

	public void initGenreBox() {
		songGenreBox.getItems().clear();
		songGenreBox.getItems().addAll(model.getGenres());
		songGenreBox.setValue(songGenreBox.getItems().get(0));
	}

	// Control:
	public void updateRequestSendButton() {
		if (songNameField.getText().isEmpty())
			requestSendButton.setDisable(true);
		else
			requestSendButton.setDisable(false);
	}

	public void addRequest() {
		if (!songNameField.getText().isEmpty())
			model.addRequest(songNameField.getText(), songGenreBox.getValue());
	}

}
