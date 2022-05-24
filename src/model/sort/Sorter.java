package model.sort;

import java.util.ArrayList;

import model.sort.interfaces.SortCommand;

public class Sorter<T> {
	
	private ArrayList<SortCommand<T>> toExecute;
	
	
	public Sorter() {
		toExecute = new ArrayList<>();
	}
	
	public void addSort(SortCommand<T> sortCom) {
		toExecute.add(0, sortCom);
	}
	
	public void execute() {
		for (SortCommand<T> sortCom : toExecute)
			sortCom.execute();
		toExecute.clear();
	}
	
}
