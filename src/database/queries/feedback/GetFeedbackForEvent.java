package database.queries.feedback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.interfaces.SelectionStrategy;
import model.entities.pseudo.Feedback;

public class GetFeedbackForEvent implements SelectionStrategy<Feedback> {
	
	private ArrayList<Feedback> data;
	
	//params:
	private int eventId;
	
	public GetFeedbackForEvent(int eventId) {
		data = new ArrayList<>();
		this.eventId = eventId;
	}

	@Override
	public String getTemplate() {
		return
				"SELECT *\r\n"
				+ "FROM feedback\r\n"
				+ "WHERE event_id = ?";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		pstmt.setInt(1, eventId);
	}

	@Override
	public void parseInformation(ResultSet rs) throws SQLException {
		while (rs.next()) {
			data.add(new Feedback(rs.getInt("event_id"), rs.getInt("feedback_id"), rs.getString("feedback_text"),
					rs.getTimestamp("feedback_time"), rs.getString("feedback_sender")));
		}
	}
	
	@Override
	public ArrayList<Feedback> doOperation() {
		Database db = Database.getInstance();
		db.runSelect(this);
		
		return data;
	}

}
