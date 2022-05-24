package database.queries.dj;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.interfaces.SelectionStrategy;
import model.entities.simple.DJ;

public class GetDJByNameAndPwd implements SelectionStrategy<DJ> {
	
	protected ArrayList<DJ> data;
	
	//params:
	private String name;
	private String password;
	
	public GetDJByNameAndPwd(String name, String password) {
		data = new ArrayList<>();
		this.name = name;
		this.password = password;
	}

	@Override
	public String getTemplate() {
		return
				"SELECT *\r\n"
				+ "FROM dj\r\n"
				+ "WHERE dj_name = ?\r\n"
				+ "AND dj_password = ?";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, name);
		pstmt.setString(2, password);
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
