package model.time;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.time.interfaces.TimeObserver;

public class CurrentTime {

	private Timestamp time;
	private ArrayList<TimeObserver> observers;

	public CurrentTime(Timestamp time) {
		this.time = time;
		observers = new ArrayList<>();
	}

	public void setTime(Timestamp time) {
		this.time = time;
		notifyAllObservers();
	}

	public Timestamp getTime() {
		return time;
	}

	public void attach(TimeObserver observer) {
		observers.add(observer);
	}
	
	public void detach(TimeObserver observer) {
		observers.remove(observer);
	}

	public void notifyAllObservers() {
		for (TimeObserver observer : observers) {
			observer.updateTime(this);
		}
	}
	
	public String toString() {
		LocalDateTime ldt = time.toLocalDateTime();
		String weekDay =  ldt.getDayOfWeek().toString();
		weekDay = weekDay.substring(0,1) + weekDay.substring(1).toLowerCase();
		
		String monthDay;
		int day = ldt.getDayOfMonth();
		
		switch (day % 10) {
		case 1:
			monthDay = day + "st";
			break;
		case 2:
			monthDay = day + "nd";
			break;		
		case 3:
			monthDay = day + "rd";
			break;
		default:
			monthDay = day + "th";
		}
		
		String month = ldt.getMonth().toString();
		month = month.substring(0,1) + month.substring(1).toLowerCase();
		
		String hour = String.format("%02d", ldt.getHour());
		String minute = String.format("%02d", ldt.getMinute());
		
		return weekDay + ", " + monthDay + " of " + month + " "
				+ ldt.getYear() + "   " + hour + ":" + minute;
	}

}
