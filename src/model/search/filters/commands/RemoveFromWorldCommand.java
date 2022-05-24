package model.search.filters.commands;

import java.util.ArrayList;

import model.entities.interfaces.*;
import model.search.interfaces.FilterCommand;

public class RemoveFromWorldCommand <T extends Referable> implements FilterCommand<T> {
	
	private ArrayList<T> world;
	private T element;
	
	public RemoveFromWorldCommand (ArrayList<T> world, T element) {
		this.world = world;
		this.element = element;
	}
	
	
	@Override
	public void execute() {
		world.remove(element);
	}

}
