package model.sort.interfaces;

import java.util.Comparator;

public interface SortCommand<T> extends Comparator<T> {
	public void execute();
}
