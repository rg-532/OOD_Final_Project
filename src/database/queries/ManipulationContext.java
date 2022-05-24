package database.queries;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.interfaces.ManipulationStrategy;

public class ManipulationContext {
	
	private ManipulationStrategy strategy;
	
	public ManipulationContext(ManipulationStrategy strategy) {
		this.strategy = strategy;
	}
	
	public String getTemplate() {
		return strategy.getTemplate();
	}
	
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		strategy.editStatement(pstmt);
	}
	
	public void parseInformation(int affected) throws SQLException {
		strategy.parseInformation(affected);
	}
	
	public boolean doOperation() {
		return strategy.doOperation();
	}
	
}
