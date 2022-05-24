package model.search.filters.decorators;

import java.util.ArrayList;

import model.entities.interfaces.Referable;
import model.search.interfaces.IFilter;

public abstract class FilterDecorator <T extends Referable> implements IFilter<T> {
	
	protected IFilter<T> filter;
	
	
	public FilterDecorator(IFilter<T> filter) {
		this.filter = filter;
	}
	
	@Override
	public void addToFilter(T add) {
		filter.addToFilter(add);
	}

	@Override
	public void removeFromFilter(T remove) {
		filter.removeFromFilter(remove);
	}

	@Override
	public ArrayList<T> getElements() {
		return filter.getElements();
	}

	@Override
	public void truncateFilter() {
		filter.truncateFilter();
	}

	@Override
	public boolean isFilterEmpty() {
		return filter.isFilterEmpty();
	}
	
	public IFilter<T> getFilter() {
		return filter;
	}
	
	public abstract String toSQLCode();

}
