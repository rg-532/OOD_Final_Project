package model.rooms;

import java.util.ArrayList;

import database.queries.SelectionContext;
import database.queries.genre.GetAllGenres;
import model.basis.RoomModel;
import model.entities.pseudo.Request;
import model.entities.pseudo.User;
import model.entities.simple.Genre;
import model.rooms.interfaces.RequestObserver;
import model.time.CurrentTime;

public class RequestRoomModel extends RoomModel {
	
	private RequestRoom room;
	
	
	public RequestRoomModel(CurrentTime currentTime, User user, RequestRoom room) {
		super(currentTime, user, room.getEvent());
		this.room = room;
	}
	
	public ArrayList<Request> getRequests() {
		return room.getRequests();
	}
	
	public ArrayList<Genre> getGenres() {
		SelectionContext<Genre> genreCon = new SelectionContext<Genre>(new GetAllGenres());
		return genreCon.doOperation();
	}
	
	public void addRequest(String song, Genre genre) {
		Request add = new Request(event.getId(), -1, song, currentTime.getTime(), getUser().getName(), genre.getId());
		room.addRequest(add);
	}
	
	public ArrayList<GenreCounter> getGenreCounts() {
		GenreAnalyzer gan = new GenreAnalyzer(getRequests());
		return gan.getResult();
	}
	
	public void attach(RequestObserver observer) {
		room.attach(observer);
	}
	
	public void detach(RequestObserver observer) {
		room.detach(observer);
	}
	
}
