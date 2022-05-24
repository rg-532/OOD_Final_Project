package database.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entities.interfaces.*;

public interface SelectionStrategy<T extends Referable> extends QueryStrategy {
	public void parseInformation(ResultSet rs)  throws SQLException;
	public ArrayList<T> doOperation();
}
