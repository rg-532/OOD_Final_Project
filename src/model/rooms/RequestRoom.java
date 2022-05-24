package model.rooms;

import java.util.ArrayList;

import database.queries.*;
import database.queries.request.*;
import model.entities.pseudo.*;
import model.entities.simple.*;
import model.rooms.interfaces.*;

public class RequestRoom {
	
	private Event event;
	private ArrayList<Request> requests;
	
	private ArrayList<RequestObserver> observers;
	
	
	public RequestRoom(Event event) {
		this.event = event;
		generateRequests();
		observers = new ArrayList<>();
	}

	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		this.event = event;
	}


	public ArrayList<Request> getRequests() {
		return requests;
	}

	public void generateRequests() {
		SelectionContext<Request> cnt = new SelectionContext<>(new GetRequestsForEvent(event.getId()));
		requests = cnt.doOperation();
	}
	
	public synchronized void addRequest(Request req) {
		ManipulationContext cnt = new ManipulationContext(new AddRequestToEvent(req));
		if (!cnt.doOperation())
			throw new RuntimeException("SQL REQUEST INSERTION ERROR");
		
		requests.add(req);
		notifyAllObservers(req);
	}
	
	public synchronized void attach(RequestObserver observer) {
		observers.add(observer);
	}
	
	public synchronized void detach(RequestObserver observer) {
		observers.remove(observer);
	}
	
	public void notifyAllObservers(Request req) {
		for (RequestObserver observer : observers)
			observer.updateRequest(req);
	}
	
}
