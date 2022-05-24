package model.rooms;

import java.util.ArrayList;

import model.basis.RoomModel;
import model.entities.pseudo.Feedback;
import model.entities.pseudo.User;
import model.rooms.interfaces.FeedbackObserver;
import model.time.CurrentTime;

public class FeedbackRoomModel extends RoomModel {
	
	private FeedbackRoom room;
	
	
	public FeedbackRoomModel(CurrentTime currentTime, User user, FeedbackRoom room) {
		super(currentTime, user, room.getEvent());
		this.room = room;
	}
	
	public ArrayList<Feedback> getFeedback() {
		return room.getFeedback();
	}
	
	public void addFeedback(String text) {
		Feedback add = new Feedback(event.getId(), -1, text, currentTime.getTime(), getUser().getName());
		room.addFeedback(add);
	}
	
	public void attach(FeedbackObserver observer) {
		room.attach(observer);
	}
	
	public void detach(FeedbackObserver observer) {
		room.detach(observer);
	}
	
}
