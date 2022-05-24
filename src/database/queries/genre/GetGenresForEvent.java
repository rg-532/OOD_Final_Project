package database.queries.genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.interfaces.SelectionStrategy;
import model.entities.simple.Genre;

public class GetGenresForEvent implements SelectionStrategy<Genre> {
	
	private ArrayList<Genre> data;
	
	//params:
	private int eventId;
	
	public GetGenresForEvent(int eventId) {
		data = new ArrayList<>();
		this.eventId = eventId;
	}

	@Override
	public String getTemplate() {
		return
				"SELECT *\r\n"
				+ "FROM genre\r\n"
				+ "WHERE genre_id IN\r\n"
				+ "	(SELECT genre_id\r\n"
				+ "    FROM event_genre\r\n"
				+ "    WHERE event_id = ?)";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		pstmt.setInt(1, eventId);
	}

	@Override
	public void parseInformation(ResultSet rs) throws SQLException {
		while (rs.next()) {
			data.add(new Genre(rs.getInt("genre_id"), rs.getString("genre_name")));
		}
	}
	
	@Override
	public ArrayList<Genre> doOperation() {
		Database db = Database.getInstance();
		db.runSelect(this);
		
		return data;
	}

}
