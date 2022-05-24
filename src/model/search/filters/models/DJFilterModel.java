package model.search.filters.models;

import model.entities.simple.*;
import model.search.basic.BasicDJSearch;
import model.search.interfaces.IFilter;

public class DJFilterModel extends FilterModel<DJ> {

	private BasicDJSearch search;

	public DJFilterModel(IFilter<DJ> filter) {
		super(filter);
		search = new BasicDJSearch();
	}

	public void searchDJs(String name) {
		search.setSearchParams(name);
		search.executeSearch();
		super.setWorld(search.getResult());
	}

}
