package controller.interfaces;

import model.entities.simple.Event;
import model.time.CurrentTime;

public interface RoomLauncherController{
	
	public void launchEventRoom(Event event);
	public CurrentTime getCurrentTime();
	public boolean isParticipatingDJ(Event event);
	
}
