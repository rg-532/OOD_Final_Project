package controller.rooms;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.entities.pseudo.Feedback;
import model.rooms.FeedbackRoomModel;
import model.rooms.interfaces.FeedbackObserver;
import view.objects.FeedbackListCell;

public class FeedbackReviewController implements FeedbackObserver {

	protected Stage prmStage;
	protected FeedbackRoomModel model;

	@FXML
	protected ListView<Feedback> feedbackList;

	// Basic:
	public void init(Stage prmStage, FeedbackRoomModel model) {
		this.prmStage = prmStage;
		this.model = model;
		
		initList();
		loadFeedback();
		model.attach(this);
	}
	
	public void initList() {
		feedbackList.setCellFactory(new Callback<ListView<Feedback>, ListCell<Feedback>>() {
			@Override
			public ListCell<Feedback> call(ListView<Feedback> param) {
				return new FeedbackListCell();
			}
		});
	}

	public void loadFeedback() {
		for (Feedback fb : model.getFeedback())
			feedbackList.getItems().add(fb);
	}
	

	@Override
	public void updateFeedback(Feedback fb) {
		feedbackList.getItems().add(fb);
	}

}
