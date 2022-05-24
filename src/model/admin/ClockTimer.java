package model.admin;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import model.time.CurrentTime;

public class ClockTimer {
	
	private CurrentTime currentTime;
	private Timer timer;
	
	public ClockTimer(CurrentTime currentTime) {
		this.currentTime = currentTime;
		timer = new Timer();

	}
	
	public void setTime(Timestamp time) {
		currentTime.setTime(time);
	}
	
	public void start() {
	    timer.scheduleAtFixedRate(new TimerTask() {
	        @Override
	        public void run() {
	        	Platform.runLater(new Runnable() {
	                @Override
	                public void run() {
	                    increment();
	                }
	            });
	        }
	    }, 0, 1000);
	}
	
	public void increment() {
		LocalDateTime now = currentTime.getTime().toLocalDateTime();
		now = now.plusMinutes(1);
		currentTime.setTime(Timestamp.valueOf(now));
	}
	
	public void terminate() {
		timer.cancel();
	}
	
}
