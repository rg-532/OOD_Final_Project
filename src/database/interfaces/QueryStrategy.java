package database.interfaces;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryStrategy {
	public String getTemplate();
	public void editStatement(PreparedStatement pstmt) throws SQLException;
}
