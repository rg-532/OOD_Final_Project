package model.rooms;

import java.util.HashMap;

import model.entities.simple.Event;

public class EventRoomFactory {
	
	private static EventRoomFactory _instance;
	
	private HashMap<Event, EventRoomPackage> roomMap;
	
	
	private EventRoomFactory() {
		roomMap = new HashMap<>();
	}
	
	public static EventRoomFactory getInstance() {
		if (_instance == null)
			_instance = new EventRoomFactory();
		return _instance;
	}
	
	public synchronized EventRoomPackage getRoomPackage(Event event) {
		EventRoomPackage back = roomMap.get(event);
		
		if (back == null) {
			back = new EventRoomPackage(event);
			roomMap.put(event, back);
			//System.out.println("nyan!");
		}
		
		return back;
	}
	
}
