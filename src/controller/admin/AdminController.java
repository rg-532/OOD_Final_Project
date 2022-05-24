package controller.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;

import controller.helpers.WindowLauncher;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.admin.AdminModel;
import model.time.CurrentTime;
import model.time.interfaces.TimeObserver;

public class AdminController implements TimeObserver {
	
	private Stage prmStage;
	private AdminModel model;
	
    @FXML
    private Button getInstanceButton, setTimeButton;

    @FXML
    private DatePicker setDatePicker;

    @FXML
    private ComboBox<String> setHourPicker, setMinutePicker;
    
    @FXML
    private Label timeLabel;
    
    
    // Basic:
    public void init(Stage prmStage, AdminModel model) {
		this.prmStage = prmStage;
		this.model = model;
		initTime();
		updateTime(model.getCurrentTime());
		
		model.getCurrentTime().attach(this);
		setupStageClose();
    }
    
	public void initTime() {
		setHourPicker.getItems().clear();
		setHourPicker.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
		setMinutePicker.getItems().clear();
		setMinutePicker.getItems().addAll("00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55");
		
		LocalDateTime time = model.getCurrentTime().getTime().toLocalDateTime();
		
		setHourPicker.setValue(String.format("%02d", time.getHour()));
		setMinutePicker.setValue(String.format("%02d", (time.getMinute() / 5) * 5));
		setDatePicker.setValue(time.toLocalDate());
	}
    
    
    // Control:
    public void getAppInstance() {
		WindowLauncher launcher = new WindowLauncher();
		launcher.showLogInView(model);
    }
    
    public void setTime() {
    	LocalDate date = setDatePicker.getValue();
    	model.setTime(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth(),
    			setHourPicker.getValue(), setMinutePicker.getValue());
    }
    
	@Override
	public void updateTime(CurrentTime time) {
		timeLabel.setText(time.toString());
	}
	
	public void setupStageClose() {
		prmStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				model.stopClock();
			}
		});
	}

}
