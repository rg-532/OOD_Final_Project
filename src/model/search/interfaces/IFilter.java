package model.search.interfaces;

import java.util.ArrayList;

import model.entities.interfaces.*;

public interface IFilter<T extends Referable> {
	public void addToFilter(T add);
	public void removeFromFilter(T remove);
	public ArrayList<T> getElements();
	public void truncateFilter();
	public boolean isFilterEmpty();
}
