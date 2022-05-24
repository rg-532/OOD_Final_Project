package model.search.filters.models;

import java.util.ArrayList;

import model.search.filters.commands.*;
import model.search.interfaces.*;
import model.entities.interfaces.*;

public class FilterModel <T extends Referable> {
	
	private IFilter<T> filter;
	
	protected ArrayList<T> world;
	protected ArrayList<FilterCommand<T>> commands;
	
	
	public FilterModel(IFilter<T> filter) {
		this.filter = filter;
		world = new ArrayList<>();
		commands = new ArrayList<>();
	}
	
	public void addToFilter(T element) {
		commands.add(new RemoveFromWorldCommand<T>(world, element));
		commands.add(new AddToFilterCommand<T>(filter, element));
		execute();
	}
	
	public void removeFromFilter(T element) {
		commands.add(new RemoveFromFilterCommand<T>(filter, element));
		commands.add(new AddToWorldCommand<T>(world, element));
		execute();
	}
	
	public void execute() {
		for (FilterCommand<T> fc : commands)
			fc.execute();
		commands.clear();
	}
	
	public void setWorld(ArrayList<T> world) {
		this.world = world;
		for (T t : filter.getElements())
			world.remove(t);
	}
	
	public ArrayList<T> getWorld() {
		return world;
	}
	
	public IFilter<T> getFilter() {
		return filter;
	}
	
}
