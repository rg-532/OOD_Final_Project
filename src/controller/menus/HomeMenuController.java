package controller.menus;

import controller.actions.room.LaunchEventRoomAction;
import controller.helpers.WindowLauncher;
import controller.interfaces.RoomLauncherController;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.entities.simple.DJ;
import model.entities.simple.Event;
import model.home.HomeMenuModel;
import model.time.CurrentTime;
import view.objects.ListCellButton;
import view.objects.MyListCell;

public class HomeMenuController implements RoomLauncherController {
	
	private Stage prmStage;
	private HomeMenuModel model;
	
    @FXML
    private ListView<Event> eventListView;
    
    @FXML
    private ListView<DJ> djListView;
    
    
	// Basic:
	public void init(Stage prmStage, HomeMenuModel model) {
		this.prmStage = prmStage;
		this.model = model;
		
		initLists();
		refreshView();
	}

	public void initLists() {
		HomeMenuController me = this;
		
		eventListView.setCellFactory(new Callback<ListView<Event>, ListCell<Event>>() {
			@Override
			public ListCell<Event> call(ListView<Event> param) {
				return new ListCellButton<Event>(new LaunchEventRoomAction(me));
			}
		});
		
		djListView.setCellFactory(new Callback<ListView<DJ>, ListCell<DJ>>() {
			@Override
			public ListCell<DJ> call(ListView<DJ> param) {
				return new MyListCell<DJ>();
			}
		});

	}
	
	@Override
	public void launchEventRoom(Event event) {
		WindowLauncher launcher = new WindowLauncher();
		launcher.showEventRoom(prmStage, model.getEventRoom(event));	
	}

	@Override
	public CurrentTime getCurrentTime() {
		return model.getCurrentTime();
	}

	@Override
	public boolean isParticipatingDJ(Event event) {
		return model.isParticipatingDJ(event);
	}
	
	public void refreshView() {
		eventListView.getItems().clear();
		eventListView.getItems().addAll(model.getEvents());
		djListView.getItems().clear();
		djListView.getItems().addAll(model.getDJs());
	}
	
}
