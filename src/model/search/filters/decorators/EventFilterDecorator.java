package model.search.filters.decorators;

import model.entities.simple.*;
import model.search.interfaces.IFilter;

public class EventFilterDecorator extends FilterDecorator<Event> {
	

	public EventFilterDecorator(IFilter<Event> filter) {
		super(filter);
	}

	@Override
	public String toSQLCode() {
		StringBuffer sb = new StringBuffer("");
		
		if (!filter.isFilterEmpty()) {
			sb.append("\r\n(event_id = " + filter.getElements().get(0).getId());
			for (int i = 1; i < filter.getElements().size(); i++)
				sb.append("\r\nOR event_id = " + filter.getElements().get(i).getId());
			sb.append(")");
		}
		
		return sb.toString();
	}

}
