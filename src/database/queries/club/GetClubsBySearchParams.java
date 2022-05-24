package database.queries.club;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.interfaces.SelectionStrategy;
import model.entities.simple.Club;

public class GetClubsBySearchParams implements SelectionStrategy<Club> {
	
	private ArrayList<Club> data;
	
	//params:
	private String name;
	
	public GetClubsBySearchParams(String name) {
		data = new ArrayList<>();
		this.name = name;
	}

	@Override
	public String getTemplate() {
		return
				"SELECT *\r\n"
				+ "FROM club\r\n"
				+ "WHERE club_name LIKE ?";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, "%" + name + "%");
	}

	@Override
	public void parseInformation(ResultSet rs) throws SQLException {
		while (rs.next()) {
			data.add(new Club(rs.getInt("club_id"), rs.getString("club_name"), rs.getInt("city_id")));
		}
	}
	
	@Override
	public ArrayList<Club> doOperation() {
		Database db = Database.getInstance();
		db.runSelect(this);
		
		return data;
	}

}
