package database.queries.request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.interfaces.SelectionStrategy;
import model.entities.pseudo.Request;

public class GetRequestsForEvent implements SelectionStrategy<Request> {
	
	private ArrayList<Request> data;
	
	//params:
	int eventId;
	
	public GetRequestsForEvent(int eventId) {
		data = new ArrayList<>();
		this.eventId = eventId;
	}

	@Override
	public String getTemplate() {
		return
				"SELECT *\r\n"
				+ "FROM request\r\n"
				+ "WHERE event_id = ?";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		pstmt.setInt(1, eventId);
	}

	@Override
	public void parseInformation(ResultSet rs) throws SQLException {
		while (rs.next()) {
			data.add(new Request(rs.getInt("event_id"), rs.getInt("request_id"), rs.getString("request_song"),
					rs.getTimestamp("request_time"), rs.getString("request_sender"), rs.getInt("genre_id")));
		}
	}
	
	@Override
	public ArrayList<Request> doOperation() {
		Database db = Database.getInstance();
		db.runSelect(this);
		
		return data;
	}

}
