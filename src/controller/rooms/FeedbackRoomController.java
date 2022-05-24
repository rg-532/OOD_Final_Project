package controller.rooms;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.rooms.FeedbackRoomModel;
import model.rooms.interfaces.FeedbackObserver;

public class FeedbackRoomController extends FeedbackReviewController {

	@FXML
	private TextArea feedbackInput;

	@FXML
	private Button feedbackSendButton;

	// Basic:
	public void init(Stage prmStage, FeedbackRoomModel model) {
		super.init(prmStage, model);
		
		updateFeedbackSendButton();
		setupStageClose();
	}
	
	public void setupStageClose() {
		FeedbackObserver me = this;
		
		prmStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				model.detach(me);
			}
		});
	}

	
	// Control:
	public void updateFeedbackSendButton() {
		if (feedbackInput.getText().isEmpty())
			feedbackSendButton.setDisable(true);
		else
			feedbackSendButton.setDisable(false);
	}

	public void addFeedback() {
    	if (!feedbackInput.getText().isEmpty())
    		model.addFeedback(feedbackInput.getText());
    }

}
