package controller.menus;

import controller.actions.room.LaunchEventRoomAction;
import controller.filter.FilterController;
import controller.helpers.WindowLauncher;
import controller.interfaces.RoomLauncherController;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.dj.DJManageModel;
import model.entities.simple.Event;
import model.entities.simple.Genre;
import model.time.CurrentTime;
import view.objects.ListCellButton;

public class DJManageController extends FilterController<Genre> implements RoomLauncherController {

	private DJManageModel model;

	@FXML
	private ListView<Event> eventListView;

	// Basic:
	public void init(Stage prmStage, DJManageModel model) {
		this.model = model;
		super.init(prmStage, model.getDJGenres());
	}
	
	@Override
	public void initLists() {
		DJManageController me = this;

		eventListView.setCellFactory(new Callback<ListView<Event>, ListCell<Event>>() {
			@Override
			public ListCell<Event> call(ListView<Event> param) {
				return new ListCellButton<Event>(new LaunchEventRoomAction(me));
			}
		});

		super.initLists();
	}
	
	// Control:
	@Override
	public void addToFilter(Genre element) {
		model.addToFilter(element);
		refreshView();
	}
	
	@Override
	public void removeFromFilter(Genre element) {
		model.removeFromFilter(element);
		refreshView();
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
	
	@Override
	public void refreshView() {
		super.refreshView();
		eventListView.getItems().clear();
		eventListView.getItems().addAll(model.getDJEvents());
	}

}
