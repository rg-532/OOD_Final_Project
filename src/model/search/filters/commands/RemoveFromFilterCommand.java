package model.search.filters.commands;

import model.entities.interfaces.*;
import model.search.interfaces.FilterCommand;
import model.search.interfaces.IFilter;

public class RemoveFromFilterCommand <T extends Referable> implements FilterCommand<T> {
	
	private IFilter<T> filter;
	private T element;	
	
	
	public RemoveFromFilterCommand (IFilter<T> filter, T element) {
		this.filter = filter;
		this.element = element;
	}
	
	
	@Override
	public void execute() {
		filter.removeFromFilter(element);
	}

}
