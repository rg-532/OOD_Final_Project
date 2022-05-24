package model.entities.simple;

import model.entities.interfaces.*;

public class City implements Referable {
	
	private int id;
	private String name;
	
	//Search c-tor:
	public City(int id, String name) {
		this.name = name;
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (!o.getClass().equals(getClass()))
			return false;
		City c = (City) o;
		if (id == c.getId())
			return true;
		return false;
	}
	
}
