package model.search.interfaces;

import java.util.ArrayList;

import model.entities.interfaces.*;

public interface ISearch <T extends Referable> {
	
	void executeSearch();
	ArrayList<T> getResult();
	
}
