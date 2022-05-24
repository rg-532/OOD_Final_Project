package model.search.basic;

import java.util.ArrayList;

import database.queries.SelectionContext;
import database.queries.club.GetClubsBySearchParams;
import model.entities.simple.*;
import model.search.interfaces.ISearch;

public class BasicClubSearch implements ISearch<Club> {
	
	private ArrayList<Club> result;
	
	//Search params:
	private String name;
	
	
	public BasicClubSearch() {
		result = new ArrayList<>();
		name = "";
	}
	
	public void setSearchParams(String name) {
		this.name = name;
	}
	
	@Override
	public void executeSearch() {
		SelectionContext<Club> cnt = new SelectionContext<>(new GetClubsBySearchParams(name));
		result = cnt.doOperation();
	}

	@Override
	public ArrayList<Club> getResult() {
		return result;
	}

}
