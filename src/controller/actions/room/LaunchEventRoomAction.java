package controller.actions.room;

import controller.actions.interfaces.ButtonAction;
import controller.interfaces.RoomLauncherController;
import javafx.scene.control.Button;
import model.entities.simple.Event;

public class LaunchEventRoomAction implements ButtonAction<Event> {
	
	private Event element;
	private RoomLauncherController controller;
	
	
	public LaunchEventRoomAction(RoomLauncherController controller) {
		this.controller = controller;
	}
	
	@Override
	public void setElement(Event element) {
		this.element = element;
	}

	@Override
	public void editButton(Button button) {
		if (element == null)
			return;
		button.setText("JOIN");
		button.setDisable(false);
		
		if (element.getStart().compareTo(controller.getCurrentTime().getTime()) > 0 ||
				element.getEnd().compareTo(controller.getCurrentTime().getTime()) < 0) {
			
			if (element.getEnd().compareTo(controller.getCurrentTime().getTime()) < 0 &&
					controller.isParticipatingDJ(element)) {
				button.setText("ANALYZE");
			}
			else
				button.setDisable(true);
		}
		
	}

	@Override
	public void act() {
		if (element == null)
			return;
		controller.launchEventRoom(element);
	}
	
}
