package model.entities.simple;

import model.entities.interfaces.*;
import model.entities.pseudo.User;

public class DJ extends User implements Referable {
	
	private int id;

	
	public DJ(int id, String name) {
		super(name);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (!o.getClass().equals(getClass()))
			return false;
		DJ d = (DJ) o;
		if (id == d.getId())
			return true;
		return false;
	}
	
}
