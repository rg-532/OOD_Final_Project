package model.search.interfaces;

import model.entities.interfaces.*;

public interface FilterCommand <T extends Referable> {
	public void execute();
}
