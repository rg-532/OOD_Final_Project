package database.queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.interfaces.SelectionStrategy;
import model.entities.interfaces.*;

public class SelectionContext <T extends Referable> {
	
	private SelectionStrategy<T> strategy;
	
	public SelectionContext(SelectionStrategy<T> strategy) {
		this.strategy = strategy;
	}
	
	public String getTemplate() {
		return strategy.getTemplate();
	}
	
	public void editStatement(PreparedStatement pstmt) throws SQLException {
		strategy.editStatement(pstmt);
	}
	
	public void parseInformation(ResultSet rs) throws SQLException {
		strategy.parseInformation(rs);
	}
	
	public ArrayList<T> doOperation() {
		return strategy.doOperation();
	}
	
}
