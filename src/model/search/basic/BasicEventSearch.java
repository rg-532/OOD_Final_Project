package model.search.basic;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import database.queries.SelectionContext;
import database.queries.event.*;
import model.entities.simple.*;
import model.search.interfaces.ISearch;

public class BasicEventSearch implements ISearch<Event> {

	protected ArrayList<Event> result;

	// Search params:
	protected String name;
	protected Timestamp time;
	protected boolean filterByTime;

	public BasicEventSearch() {
		result = new ArrayList<>();
		name = "";
		time = Timestamp.valueOf(LocalDateTime.now());
	}

	public void setSearchParams(String name, Timestamp time, boolean filterByTime) {
		this.name = name;
		this.time = time;
		this.filterByTime = filterByTime;
	}

	@Override
	public void executeSearch() {
		SelectionContext<Event> cnt;
		
		if (filterByTime)
			cnt = new SelectionContext<>(new GetEventsBySearchParams(name, time));
		else
			cnt = new SelectionContext<>(new GetEventsByName(name));
		
		result = cnt.doOperation();
	}

	@Override
	public ArrayList<Event> getResult() {
		return result;
	}

}
