package model.rooms;

import java.util.ArrayList;

import database.queries.SelectionContext;
import database.queries.dj.GetDJsForEvent;
import model.basis.UserAppModel;
import model.entities.pseudo.User;
import model.entities.simple.DJ;
import model.entities.simple.Event;
import model.time.CurrentTime;

public class EventRoomModel extends UserAppModel {
	
	private Event event;
	private EventRoomPackage pack;
	
	
	public EventRoomModel(CurrentTime currentTime, User user, Event event) {
		super(currentTime, user);
		this.event = event;
		pack = EventRoomFactory.getInstance().getRoomPackage(event);
	}
	
	public Event getEvent() {
		return event;
	}
	
	public FeedbackRoomModel getFeedbackModel() {
		return new FeedbackRoomModel(currentTime, user, pack.getFeedbackRoom());
	}
	
	public RequestRoomModel getRequestModel() {
		return new RequestRoomModel(currentTime, user, pack.getRequestRoom());
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
	
	public boolean isEventOver() {
		return currentTime.getTime().compareTo(event.getEnd()) > 0;
	}
	
}
