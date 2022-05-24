package model.rooms.interfaces;

import model.entities.pseudo.Request;

public interface RequestObserver {
	public void updateRequest(Request req);
}
