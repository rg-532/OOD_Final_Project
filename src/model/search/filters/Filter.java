package model.search.filters;

import java.util.ArrayList;

import model.entities.interfaces.*;
import model.search.interfaces.*;

public class Filter <T extends Referable> implements IFilter<T> {
	
	private ArrayList<T> filters;
	
	
	public Filter() {
		filters = new ArrayList<>();
	}
	
	@Override
	public void addToFilter(T add) {
		filters.add(add);
	}

	@Override
	public void removeFromFilter(T remove) {
		filters.remove(remove);
	}
	
	@Override
	public ArrayList<T> getElements() {
		return filters;
	}

	@Override
	public void truncateFilter() {
		filters.clear();
	}

	@Override
	public boolean isFilterEmpty() {
		if (filters.isEmpty())
			return true;
		return false;
	}

}
