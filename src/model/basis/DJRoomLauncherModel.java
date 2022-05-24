package model.basis;

import java.util.ArrayList;

import database.queries.SelectionContext;
import database.queries.dj.GetDJsForEvent;
import model.entities.simple.DJ;
import model.entities.simple.Event;
import model.rooms.EventRoomModel;
import model.time.CurrentTime;

public abstract class DJRoomLauncherModel extends DJAppModel {
	
	public DJRoomLauncherModel(CurrentTime currentTime, DJ dj) {
		super(currentTime, dj);
	}
	
	public boolean isParticipatingDJ(Event event) {
		SelectionContext<DJ> djCon = new SelectionContext<>(new GetDJsForEvent(event.getId()));
		ArrayList<DJ> res = djCon.doOperation();
		return res.contains(dj);
	}
	
	public EventRoomModel getEventRoom(Event event) {
		return new EventRoomModel(currentTime, dj, event);
	}
	
}
