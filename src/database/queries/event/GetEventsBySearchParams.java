package database.queries.event;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class GetEventsBySearchParams extends GetEventsByName {
	
	//params:
	protected Timestamp time;
	
	public GetEventsBySearchParams(String name, Timestamp time) {
		super(name);
		this.time = time;
	}

	@Override
	public String getTemplate() {
		return
				super.getTemplate() + "\r\n"
				+ "AND ? BETWEEN event_start AND event_end";
	}

	@Override
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		super.editStatement(pstmt);
		pstmt.setTimestamp(2, time);
	}

}
