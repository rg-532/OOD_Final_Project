package database.interfaces;

import java.sql.SQLException;

public interface ManipulationStrategy extends QueryStrategy {
	public void parseInformation(int affected)  throws SQLException;
	public boolean doOperation();
}
