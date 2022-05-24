package database.queries.genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.interfaces.SelectionStrategy;
import model.entities.simple.Genre;

public class GetAllGenres implements SelectionStrategy<Genre> {
	
	private ArrayList<Genre> data;
	
	//params:
	//none
	
	public GetAllGenres() {
		data = new ArrayList<>();
	}

	@Override
	public String getTemplate() {
		return
				"SELECT *\r\n"
				+ "FROM genre";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		//do nothing...
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
