package model.search.filters.commands;

import java.util.ArrayList;

import model.entities.interfaces.*;
import model.search.interfaces.FilterCommand;

public class AddToWorldCommand <T extends Referable> implements FilterCommand<T> {
	
	private ArrayList<T> world;
	private T element;
	
	public AddToWorldCommand (ArrayList<T> world, T element) {
		this.world = world;
		this.element = element;
	}
	
	
	@Override
	public void execute() {
		world.add(0, element);
	}

}
