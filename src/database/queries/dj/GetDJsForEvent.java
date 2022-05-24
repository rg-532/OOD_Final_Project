package database.queries.dj;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.interfaces.SelectionStrategy;
import model.entities.simple.DJ;

public class GetDJsForEvent implements SelectionStrategy<DJ> {
	
	private ArrayList<DJ> data;
	
	//params:
	private int eventId;
	
	public GetDJsForEvent(int eventId) {
		data = new ArrayList<>();
		this.eventId = eventId;
	}

	@Override
	public String getTemplate() {
		return
				"SELECT *\r\n"
				+ "FROM dj\r\n"
				+ "WHERE dj_id IN\r\n"
				+ "	(SELECT dj_id\r\n"
				+ "    FROM event_dj\r\n"
				+ "    WHERE event_id = ?)";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		pstmt.setInt(1, eventId);
	}

	@Override
	public void parseInformation(ResultSet rs) throws SQLException {
		while (rs.next()) {
			data.add(new DJ(rs.getInt("dj_id"), rs.getString("dj_name")));
		}
	}
	
	@Override
	public ArrayList<DJ> doOperation() {
		Database db = Database.getInstance();
		db.runSelect(this);
		
		return data;
	}

}
