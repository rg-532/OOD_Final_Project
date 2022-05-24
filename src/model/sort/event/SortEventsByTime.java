package model.sort.event;

import model.sort.interfaces.SortCommand;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

import model.entities.simple.*;

public class SortEventsByTime implements SortCommand<Event> {
	
	private ArrayList<Event> toSort;
	
	//params:
	private Timestamp now;
	
	
	public SortEventsByTime(ArrayList<Event> toSort, Timestamp now) {
		this.toSort = toSort;
		this.now = now;
	}
	
	@Override
	public int compare(Event o1, Event o2) {
		if (now.compareTo(o1.getStart()) >= 0 && now.compareTo(o1.getEnd()) <= 0) {
			if (now.compareTo(o2.getStart()) >= 0 && now.compareTo(o2.getEnd()) <= 0)
				return -(o1.getEnd().compareTo(o2.getEnd()));
				
			return -1;
		}
		
		if (now.compareTo(o1.getStart()) < 0) {
			if (now.compareTo(o2.getStart()) < 0)
				return o1.getStart().compareTo(o2.getStart());
			
			if (now.compareTo(o2.getStart()) >= 0 && now.compareTo(o2.getEnd()) <= 0)
				return 1;
			
			return -1;
		}
		
		if (now.compareTo(o2.getEnd()) > 0)
			return -(o1.getEnd().compareTo(o2.getEnd()));
		
		return 1;
	}

	@Override
	public void execute() {
		Collections.sort(toSort, this);
	}

}
