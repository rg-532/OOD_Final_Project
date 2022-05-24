package model.rooms;

import java.util.ArrayList;

import database.queries.*;
import database.queries.feedback.*;
import model.entities.pseudo.*;
import model.entities.simple.*;
import model.rooms.interfaces.*;

public class FeedbackRoom {
	
	private Event event;
	private ArrayList<Feedback> feedback;
	
	private ArrayList<FeedbackObserver> observers;
	
	
	public FeedbackRoom(Event event) {
		this.event = event;
		generateFeedback();
		observers = new ArrayList<>();
	}

	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		this.event = event;
	}


	public ArrayList<Feedback> getFeedback() {
		return feedback;
	}


	public void generateFeedback() {
		SelectionContext<Feedback> cnt = new SelectionContext<>(new GetFeedbackForEvent(event.getId()));
		feedback = cnt.doOperation();
	}
	
	public synchronized void addFeedback(Feedback fb) {
		ManipulationContext cnt = new ManipulationContext(new AddFeedbackToEvent(fb));
		if (!cnt.doOperation())
			throw new RuntimeException("SQL FEEDBACK INSERTION ERROR");
		
		feedback.add(fb);
		notifyAllObservers(fb);
	}
	
	public synchronized void attach(FeedbackObserver observer) {
		observers.add(observer);
	}
	
	public synchronized void detach(FeedbackObserver observer) {
		observers.remove(observer);
	}
	
	public void notifyAllObservers(Feedback fb) {
		for (FeedbackObserver observer : observers)
			observer.updateFeedback(fb);
	}
	
}
