package model.basis;

import java.util.ArrayList;

import database.queries.SelectionContext;
import database.queries.dj.GetDJsForEvent;
import model.entities.pseudo.User;
import model.entities.simple.DJ;
import model.entities.simple.Event;
import model.rooms.EventRoomModel;
import model.time.CurrentTime;

public abstract class UserRoomLauncherModel extends UserAppModel {
	
	public UserRoomLauncherModel(CurrentTime currentTime, User user) {
		super(currentTime, user);
	}
	
	public boolean isParticipatingDJ(Event event) {
		if (user instanceof DJ) {
			DJ dj = (DJ) user;
			SelectionContext<DJ> djCon = new SelectionContext<>(new GetDJsForEvent(event.getId()));
			ArrayList<DJ> res = djCon.doOperation();
			return res.contains(dj);
		}
		else
			return false;
	}
	
	public EventRoomModel getEventRoom(Event event) {
		return new EventRoomModel(currentTime, user, event);
	}
	
}
