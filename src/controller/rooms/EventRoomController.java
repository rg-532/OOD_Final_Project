package controller.rooms;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.rooms.EventRoomModel;
import model.time.CurrentTime;
import model.time.interfaces.TimeObserver;

public class EventRoomController implements TimeObserver {

	private Stage prmStage;
	private EventRoomModel model;
	
	@FXML
	private GridPane parent;
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Tab feedbackTab, requestTab;

	@FXML
	private Label eventLabel, usernameLabel, timeLabel;
	
	
	// Basic:
	public void init(Stage prmStage, EventRoomModel model) {
		this.prmStage = prmStage;
		this.model = model;
		updateTime(model.getCurrentTime());
		model.getCurrentTime().attach(this);
		
		usernameLabel.setText(model.getUser().getName());
		eventLabel.setText(model.getEvent().getName());
		bindTabPane();
		setupStageClose();
		
		build();
	}
	
	public void build() {
		try {			
			buildFeedback();
			buildRequest();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void buildFeedback() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		Parent root;
		
		if (!model.isEventOver()) {
			loader.setLocation(getClass().getResource("/view/rooms/FeedbackRoomView.fxml"));
			root = loader.load();
			FeedbackRoomController ctrl = loader.getController();
			ctrl.init(prmStage, model.getFeedbackModel());
		}
		else {
			loader.setLocation(getClass().getResource("/view/rooms/FeedbackReviewView.fxml"));
			root = loader.load();
			FeedbackReviewController ctrl = loader.getController();
			ctrl.init(prmStage, model.getFeedbackModel());
		}
		
		feedbackTab.setContent(root);
	}
	
	public void buildRequest() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		Parent root;
		
		if (model.isParticipatingDJ()) {
			loader.setLocation(getClass().getResource("/view/rooms/DJAnalysisView.fxml"));
			root = loader.load();
			
			DJAnalysisController ctrl = loader.getController();
			ctrl.init(prmStage, model.getRequestModel());
		}
		else {
			loader.setLocation(getClass().getResource("/view/rooms/RequestRoomView.fxml"));
			root = loader.load();
			
			RequestRoomController ctrl = loader.getController();
			ctrl.init(prmStage, model.getRequestModel());
		}
		
		requestTab.setContent(root);
	}
	
	public void bindTabPane() {
		tabPane.tabMinWidthProperty().bind(Bindings.createDoubleBinding(
				() -> (parent.getWidth() - 21.3)/2, parent.widthProperty()));
		tabPane.tabMaxWidthProperty().bind(Bindings.createDoubleBinding(
				() -> (parent.getWidth() - 21.3)/2, parent.widthProperty()));
		tabPane.setTabMinHeight(50);
		tabPane.setTabMaxHeight(50);
	}

	@Override
	public void updateTime(CurrentTime time) {
		timeLabel.setText(time.toString());
	}
	
	public void setupStageClose() {
		TimeObserver me = this;
		
		prmStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				model.getCurrentTime().detach(me);
			}
		});
	}

}
