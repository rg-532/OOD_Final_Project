package model.rooms;

import model.entities.simple.*;

public class EventRoomPackage {
	
	private Event event;
	private FeedbackRoom feedbackRoom;
	private RequestRoom requestRoom;
	
	
	public EventRoomPackage(Event event) {
		this.event = event;
		feedbackRoom = new FeedbackRoom(event);
		requestRoom = new RequestRoom(event);
	}
	
	public Event getEvent() {
		return event;
	}

	public FeedbackRoom getFeedbackRoom() {
		return feedbackRoom;
	}
	
	public RequestRoom getRequestRoom() {
		return requestRoom;
	}
	
}
