package model.admin;

import java.sql.Timestamp;
import java.util.ArrayList;

import database.queries.*;
import database.queries.dj.*;
import model.entities.pseudo.*;
import model.entities.simple.*;
import model.frame.*;
import model.time.*;

public class AdminModel {
	
	private CurrentTime currentTime;
	private ClockTimer clock;
	
	public AdminModel() {
		currentTime = new CurrentTime(Timestamp.valueOf("2022-01-01 00:00:00"));
		clock = new ClockTimer(currentTime);
		clock.start();
	}
	
	public CurrentTime getCurrentTime() {
		return currentTime;
	}
	
	public FrameModel getUserView(String username) {
		return new FrameModel(currentTime, new User(username));
	}
	
	public FrameModel getDJView(String name, String password) throws NamePwdMismatchException {
		SelectionContext<DJ> djCon = new SelectionContext<>(new GetDJByNameAndPwd(name, password));
		ArrayList<DJ> res = djCon.doOperation();
		if (res.isEmpty())
			throw new NamePwdMismatchException("Error: DJ Name and Password do not match");
		else
			return new FrameModel(currentTime, res.get(0));
	}
	
	public void setTime(int year, int month, int day, String hour, String minute) {
		clock.setTime(Timestamp.valueOf(year + "-" + month + "-" + day + " "
				+ hour + ":" + minute + ":00"));
	}
	
	public void stopClock() {
		clock.terminate();
	}
	
}
