package model.search.advanced;

import database.queries.SelectionContext;
import database.queries.event.*;
import model.entities.simple.*;
import model.search.basic.*;
import model.search.filters.*;
import model.search.filters.decorators.*;

public class AdvancedEventSearch extends BasicEventSearch {
	
	//Search filters:
	private DJFilterDecorator djs;
	
	
	public AdvancedEventSearch() {
		super();
		djs = new DJFilterDecorator(new Filter<DJ>());
	}
	
	public DJFilterDecorator getDJFilter() {
		return djs;
	}
	
	@Override
	public void executeSearch() {
		SelectionContext<Event> cnt;
		
		if (filterByTime)
			cnt = new SelectionContext<>(new GetEventsForAdvancedSearch(name, time, djs));
		else
			cnt = new SelectionContext<>(new GetEventsForAdvancedSearchNoTime(name, djs));
		
		result = cnt.doOperation();
	}
	
}
