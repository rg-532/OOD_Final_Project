package model.search.advanced;

import database.queries.SelectionContext;
import database.queries.dj.GetDJsForAdvancedSearch;
import model.entities.simple.*;
import model.search.basic.*;
import model.search.filters.*;
import model.search.filters.decorators.*;

public class AdvancedDJSearch extends BasicDJSearch {
	
	//Search filters:
	private ClubFilterDecorator clubs;
	
	
	public AdvancedDJSearch() {
		super();
		clubs = new ClubFilterDecorator(new Filter<Club>());
	}
	
	public ClubFilterDecorator getClubFilter() {
		return clubs;
	}
	
	@Override
	public void executeSearch() {
		SelectionContext<DJ> cnt = new SelectionContext<>(new GetDJsForAdvancedSearch(name, clubs));
		result = cnt.doOperation();
	}
	
}
