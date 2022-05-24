package database.queries.genre;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Database;
import database.interfaces.ManipulationStrategy;
import model.entities.simple.DJ;
import model.entities.simple.Genre;

public class AddGenreToDJ implements ManipulationStrategy {
	
	private boolean result;
	
	//params:
	private DJ receive;
	private Genre add;
	
	public AddGenreToDJ(DJ receive, Genre add) {
		this.receive = receive;
		this.add = add;
	}

	@Override
	public String getTemplate() {
		return
				"INSERT INTO dj_genre\r\n"
				+ "VALUES\r\n"
				+ "(?, ?)";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		pstmt.setInt(1, receive.getId());
		pstmt.setInt(2, add.getId());
	}

	@Override
	public void parseInformation(int affected) throws SQLException {
		if (affected != 1)
			result = false;
		else
			result = true;
	}
	
	@Override
	public boolean doOperation() {
		Database db = Database.getInstance();
		db.runUpdate(this);
		
		return result;
	}

}
