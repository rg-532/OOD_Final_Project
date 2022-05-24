package controller.rooms;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import model.entities.pseudo.Request;
import model.rooms.GenreCounter;
import model.rooms.RequestRoomModel;
import model.rooms.interfaces.RequestObserver;
import view.objects.GenreAnalyseListCell;
import view.objects.RequestListCell;

public class DJAnalysisController implements RequestObserver {
	
	private Stage prmStage;
	private RequestRoomModel model;
	
	@FXML
	private ListView<GenreCounter> genreCountView;

	@FXML
	private Label mostRequestedGenre;

	@FXML
	private Label numberOfRequests;

	@FXML
	private ListView<Request> requestView;
	
	
	// Basic:
	public void init(Stage prmStage, RequestRoomModel model) {
		this.prmStage = prmStage;
		this.model = model;
		
		initLists();
		setupStageClose();
		loadRequests();
		model.attach(this);
		refreshView();
	}
	
	public void initLists() {
		requestView.setCellFactory(new Callback<ListView<Request>, ListCell<Request>>() {
			@Override
			public ListCell<Request> call(ListView<Request> param) {
				return new RequestListCell();
			}
		});
		
		genreCountView.setCellFactory(new Callback<ListView<GenreCounter>, ListCell<GenreCounter>>() {
			@Override
			public ListCell<GenreCounter> call(ListView<GenreCounter> param) {
				return new GenreAnalyseListCell();
			}
		});
	}
	
	public void loadRequests() {
		requestView.getItems().addAll(model.getRequests());
	}
	
	public void setupStageClose() {
		RequestObserver me = this;
		
		prmStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				model.detach(me);
			}
		});
	}
	
	// Control:
	public void refreshView() {
		ArrayList<GenreCounter> genCountList = model.getGenreCounts();
		genreCountView.getItems().clear();
		genreCountView.getItems().addAll(genCountList);
		numberOfRequests.setText(String.valueOf(model.getRequests().size()));
		if (!genCountList.isEmpty())
			mostRequestedGenre.setText(genCountList.get(0).getGenre().getName());
		else
			mostRequestedGenre.setText("none yet");
	}
	
	@Override
	public void updateRequest(Request req) {
		requestView.getItems().add(req);
		refreshView();
	}

}
