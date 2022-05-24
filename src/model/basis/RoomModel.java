package model.basis;

import java.util.ArrayList;

import database.queries.SelectionContext;
import database.queries.dj.GetDJsForEvent;
import model.entities.pseudo.User;
import model.entities.simple.DJ;
import model.entities.simple.Event;
import model.time.CurrentTime;

public abstract class RoomModel extends UserAppModel {
	
	protected Event event;
	
	
	public RoomModel(CurrentTime currentTime, User user, Event event) {
		super(currentTime, user);
		this.event = event;
	}
	
	public boolean isParticipatingDJ() {
		if (user instanceof DJ) {
			DJ dj = (DJ) user;
			SelectionContext<DJ> djCon = new SelectionContext<>(new GetDJsForEvent(event.getId()));
			ArrayList<DJ> res = djCon.doOperation();
			return res.contains(dj);
		}
		else
			return false;
	}
	
}
