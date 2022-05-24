package model.search.basic;

import java.util.ArrayList;

import database.queries.SelectionContext;
import database.queries.dj.GetDJsBySearchParams;
import model.entities.simple.*;
import model.search.interfaces.ISearch;

public class BasicDJSearch implements ISearch<DJ> {
	
	protected ArrayList<DJ> result;
	
	//Search params:
	protected String name;
	
	
	public BasicDJSearch() {
		result = new ArrayList<>();
		name = "";
	}
	
	public void setSearchParams(String name) {
		this.name = name;
	}
	
	@Override
	public void executeSearch() {
		SelectionContext<DJ> cnt = new SelectionContext<>(new GetDJsBySearchParams(name));
		result = cnt.doOperation();
	}

	@Override
	public ArrayList<DJ> getResult() {
		return result;
	}

}
