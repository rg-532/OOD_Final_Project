package model.search.filters.commands;

import model.entities.interfaces.*;
import model.search.interfaces.FilterCommand;
import model.search.interfaces.IFilter;

public class AddToFilterCommand <T extends Referable> implements FilterCommand<T> {
	
	private IFilter<T> filter;
	private T element;
	
	public AddToFilterCommand (IFilter<T> filter, T element) {
		this.filter = filter;
		this.element = element;
	}
	
	
	@Override
	public void execute() {
		filter.addToFilter(element);
	}

}
