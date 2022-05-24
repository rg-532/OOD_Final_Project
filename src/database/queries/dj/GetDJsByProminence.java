package database.queries.dj;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.interfaces.SelectionStrategy;
import model.entities.simple.DJ;

public class GetDJsByProminence implements SelectionStrategy<DJ> {
	
	protected ArrayList<DJ> data;
	
	//params: none...
	
	public GetDJsByProminence() {
		data = new ArrayList<>();
	}

	@Override
	public String getTemplate() {
		return
				"SELECT dj.*, counter.count\r\n"
				+ "FROM dj LEFT JOIN"
				+ "		(SELECT event_dj.dj_id, COUNT(event_dj.event_id) AS count\r\n"
				+ "		FROM event_dj\r\n"
				+ "		GROUP BY event_dj.dj_id) AS counter\r\n"
				+ "ON dj.dj_id = counter.dj_id\r\n"
				+ "ORDER BY count DESC";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		//do nothing...
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
