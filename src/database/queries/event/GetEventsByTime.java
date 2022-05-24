package database.queries.event;

import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.interfaces.SelectionStrategy;
import model.entities.simple.Event;

public class GetEventsByTime implements SelectionStrategy<Event> {
	
	protected ArrayList<Event> data;
	
	//params:
	protected Timestamp time;
	
	public GetEventsByTime(Timestamp time) {
		data = new ArrayList<>();
		this.time = time;
	}

	@Override
	public String getTemplate() {
		return
				"SELECT *\r\n"
				+ "FROM event\r\n"
				+ "WHERE ? BETWEEN event_start AND event_end";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		pstmt.setTimestamp(1, time);
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
