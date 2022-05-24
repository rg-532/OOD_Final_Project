package controller.actions.filter;

import controller.actions.interfaces.ButtonAction;
import controller.filter.FilterController;
import model.entities.interfaces.Referable;

public abstract class FilterAction <T extends Referable> implements ButtonAction<T> {
	
	protected FilterController<T> controller;
	protected T element;
	
	public FilterAction(FilterController<T> controller) {
		this.controller = controller;
	}
	
	@Override
	public void setElement(T element) {
		this.element = element;
	}

}
