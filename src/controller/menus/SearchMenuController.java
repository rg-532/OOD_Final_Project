package controller.menus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import controller.actions.room.LaunchEventRoomAction;
import controller.helpers.WindowLauncher;
import controller.interfaces.RoomLauncherController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.entities.simple.*;
import model.search.SearchMenuModel;
import model.time.CurrentTime;
import view.objects.ListCellButton;
import view.objects.MyListCell;

public class SearchMenuController implements RoomLauncherController {
	
	private Stage prmStage;
	private SearchMenuModel model;
	
	@FXML
	private TextField eventNameField, djNameField;

	@FXML
	private DatePicker eventDatePicker;

	@FXML
	private ComboBox<String> eventHourPicker, eventMinutePicker;
	
    @FXML
    private CheckBox isFilterByTime;
	
	@FXML
	private Button eventDJFilterButton, eventSearchButton, djClubFilterButton, djSearchButton;

	@FXML
	private ListView<Event> eventSearchRes;

	@FXML
	private ListView<DJ> djSearchRes;
	
	
	// Basic:
	public void init(Stage prmStage, SearchMenuModel model) {
		this.prmStage = prmStage;
		this.model = model;
		
		initTime();
		initLists();
	}
	
	public void initTime() {
		eventHourPicker.getItems().clear();
		eventHourPicker.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
		eventMinutePicker.getItems().clear();
		eventMinutePicker.getItems().addAll("00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55");
		
		LocalDateTime time = model.getCurrentTime().getTime().toLocalDateTime();
		
		eventHourPicker.setValue(String.format("%02d", time.getHour()));
		eventMinutePicker.setValue(String.format("%02d", (time.getMinute() / 5) * 5));
		eventDatePicker.setValue(time.toLocalDate());
		
		isFilterByTime.setSelected(false);
	}
	
	public void initLists() {
		SearchMenuController me = this;
		
		eventSearchRes.setCellFactory(new Callback<ListView<Event>, ListCell<Event>>() {
			@Override
			public ListCell<Event> call(ListView<Event> param) {
				return new ListCellButton<Event>(new LaunchEventRoomAction(me));
			}
		});

		djSearchRes.setCellFactory(new Callback<ListView<DJ>, ListCell<DJ>>() {
			@Override
			public ListCell<DJ> call(ListView<DJ> param) {
				return new MyListCell<DJ>();
			}
		});
	}
	
	
	// Control:
	public void executeEventSearch() {
		String date = eventDatePicker.getValue().getYear() + "-"
				+ eventDatePicker.getValue().getMonthValue() + "-"
				+ eventDatePicker.getValue().getDayOfMonth();
		
		String time = eventHourPicker.getValue() + ":"
				+ eventMinutePicker.getValue() + ":"
				+ "00";
		
		model.searchEvents(eventNameField.getText(), Timestamp.valueOf(date + " " + time), isFilterByTime.isSelected());
		refreshEventView();
	}
	
	public void executeDJSearch() {
		model.searchDJs(djNameField.getText());
		refreshDJView();
	}
	
	public void refreshEventView() {
		eventSearchRes.getItems().clear();
		for (Event event : model.getEventResult())
			eventSearchRes.getItems().add(event);
	}
	
	public void refreshDJView() {
		djSearchRes.getItems().clear();
		for (DJ dj : model.getDJResult())
			djSearchRes.getItems().add(dj);
	}
	
	public void showEventDJFilter() {
		WindowLauncher launcher = new WindowLauncher();
		launcher.showEventDJFilter(prmStage, model.getDJsForEvents());
	}
	
	public void showDJClubFilter() {
		WindowLauncher launcher = new WindowLauncher();
		launcher.showDJClubFilter(prmStage, model.getClubsForDJs());
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
	
}