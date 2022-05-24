package database.queries.genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.interfaces.SelectionStrategy;
import model.entities.simple.Genre;

public class GetGenreById implements SelectionStrategy<Genre> {
	
	private ArrayList<Genre> data;
	
	//params:
	int id;
	
	public GetGenreById(int id) {
		data = new ArrayList<>();
		this.id = id;
	}

	@Override
	public String getTemplate() {
		return
				"SELECT *\r\n"
				+ "FROM genre\r\n"
				+ "WHERE genre_id = ?";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		pstmt.setInt(1, id);
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
