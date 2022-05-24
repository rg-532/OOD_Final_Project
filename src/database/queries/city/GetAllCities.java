package database.queries.city;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.interfaces.SelectionStrategy;
import model.entities.simple.City;

public class GetAllCities implements SelectionStrategy<City> {
	
	private ArrayList<City> data;
	
	//params:
	//none
	
	public GetAllCities() {
		data = new ArrayList<>();
	}

	@Override
	public String getTemplate() {
		return
				"SELECT *\r\n"
				+ "FROM city";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		//do nothing...
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
