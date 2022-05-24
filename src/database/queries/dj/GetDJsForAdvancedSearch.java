package database.queries.dj;

import java.util.ArrayList;

import database.Database;
import model.entities.simple.DJ;
import model.search.filters.decorators.ClubFilterDecorator;

public class GetDJsForAdvancedSearch extends GetDJsBySearchParams {
	
	//params:
	//no new params
	
	//filters:
	private ClubFilterDecorator clubs;
	
	public GetDJsForAdvancedSearch(String name, ClubFilterDecorator clubs) {
		super(name);
		this.clubs = clubs;
	}

	@Override
	public String getTemplate() {
		StringBuffer sb = new StringBuffer(super.getTemplate());
		
		if (!clubs.isFilterEmpty()) {
			sb.append("\r\n"
					+ "AND dj_id IN\r\n"
					+ "(SELECT dj_id\r\n"
					+ "FROM event_dj JOIN event\r\n"
					+ "ON event_dj.event_id = event.event_id\r\n"
					+ "WHERE ");
			sb.append(clubs.toSQLCode());
			sb.append(")");
		}
		
		return sb.toString();
	}
	
	@Override
	public ArrayList<DJ> doOperation() {
		Database db = Database.getInstance();
		db.runSelect(this);
		
		return data;
	}

}
