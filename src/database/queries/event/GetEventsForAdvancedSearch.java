package database.queries.event;

import java.sql.Timestamp;

import model.search.filters.decorators.DJFilterDecorator;

public class GetEventsForAdvancedSearch extends GetEventsBySearchParams {
	
	//params:
	//no new params
	
	//filters:
	private DJFilterDecorator djs;
	
	public GetEventsForAdvancedSearch(String name, Timestamp time, DJFilterDecorator djs) {
		super(name, time);
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
