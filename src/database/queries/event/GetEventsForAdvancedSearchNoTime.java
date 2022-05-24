package database.queries.event;

import model.search.filters.decorators.DJFilterDecorator;

public class GetEventsForAdvancedSearchNoTime extends GetEventsByName {
	
	//params:
	//no new params
	
	//filters:
	private DJFilterDecorator djs;
	
	public GetEventsForAdvancedSearchNoTime(String name, DJFilterDecorator djs) {
		super(name);
		this.djs = djs;
	}

	@Override
	public String getTemplate() {
		StringBuffer sb = new StringBuffer(super.getTemplate());
		
		if (!djs.isFilterEmpty()) {
			sb.append("\r\n"
					+ "AND event_id IN\r\n"
					+ "(SELECT event_id\r\n"
					+ "FROM event_dj\r\n"
					+ "WHERE ");
			sb.append(djs.toSQLCode());
			sb.append(")");
		}
		
		return sb.toString();
	}

}
