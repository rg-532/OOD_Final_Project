package database.queries.request;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Database;
import database.interfaces.ManipulationStrategy;
import database.queries.SelectionContext;
import model.entities.pseudo.Request;

public class AddRequestToEvent implements ManipulationStrategy {
	
	private boolean result;
	
	//params:
	Request add;
	
	public AddRequestToEvent(Request add) {
		this.add = add;
	}

	@Override
	public String getTemplate() {
		return
				"INSERT INTO request\r\n"
				+ "VALUES\r\n"
				+ "(?, ?, ?, ?, ?, ?)";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		SelectionContext<Request> cnt = new SelectionContext<>(new GetRequestsForEvent(add.getEventId()));
		int id = cnt.doOperation().size() + 1;
		pstmt.setInt(1, add.getEventId());
		pstmt.setInt(2, id);
		pstmt.setString(3, add.getSong());
		pstmt.setTimestamp(4, add.getTime());
		pstmt.setString(5, add.getSender());
		pstmt.setInt(6, add.getGenreId());
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
