package model.search.filters.models;

import model.entities.simple.*;
import model.search.basic.BasicClubSearch;
import model.search.interfaces.IFilter;

public class ClubFilterModel extends FilterModel<Club> {
	
	private BasicClubSearch search;
	
	
	public ClubFilterModel(IFilter<Club> filter) {
		super(filter);
		search = new BasicClubSearch();
	}
	
	public void searchClubs(String name) {
		search.setSearchParams(name);
		search.executeSearch();
		super.setWorld(search.getResult());
	}
	
}
