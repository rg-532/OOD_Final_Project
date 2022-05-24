package database.queries.city;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.interfaces.SelectionStrategy;
import model.entities.simple.City;

public class GetCityById implements SelectionStrategy<City> {
	
	private ArrayList<City> data;
	
	//params:
	private int id;
	
	public GetCityById(int id) {
		data = new ArrayList<>();
		this.id = id;
	}

	@Override
	public String getTemplate() {
		return
				"SELECT *\r\n"
				+ "FROM city\r\n"
				+ "WHERE city_id = ?";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		pstmt.setInt(1, id);
	}

	@Override
	public void parseInformation(ResultSet rs) throws SQLException {
		while (rs.next()) {
			data.add(new City(rs.getInt("city_id"), rs.getString("city_name")));
		}
	}
	
	@Override
	public ArrayList<City> doOperation() {
		Database db = Database.getInstance();
		db.runSelect(this);
		
		return data;
	}

}
