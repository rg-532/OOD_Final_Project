package database.queries.event;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.interfaces.SelectionStrategy;
import model.entities.simple.Event;

public class GetEventsForDJ implements SelectionStrategy<Event> {
	
	private ArrayList<Event> data;
	
	//params:
	private int djId;
	
	public GetEventsForDJ(int djId) {
		data = new ArrayList<>();
		this.djId = djId;
	}

	@Override
	public String getTemplate() {
		return
				"SELECT *\r\n"
				+ "FROM event\r\n"
				+ "WHERE event_id IN\r\n"
				+ "	(SELECT event_id\r\n"
				+ "    FROM event_dj\r\n"
				+ "    WHERE dj_id = ?)";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		pstmt.setInt(1, djId);
	}

	@Override
	public void parseInformation(ResultSet rs) throws SQLException {
		while (rs.next()) {
			data.add(new Event(rs.getInt("event_id"), rs.getString("event_name"), rs.getTimestamp("event_start"),
					rs.getTimestamp("event_end"), rs.getInt("club_id")));
		}
	}
	
	@Override
	public ArrayList<Event> doOperation() {
		Database db = Database.getInstance();
		db.runSelect(this);
		
		return data;
	}

}
