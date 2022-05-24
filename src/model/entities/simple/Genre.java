package model.entities.simple;


import model.entities.interfaces.*;

public class Genre implements Referable {
	
	private int id;
	private String name;
	
	
	public Genre(int id, String name) {
		this.id = id;
		this.name = name;
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
		Genre g = (Genre) o;
		if (id == g.getId())
			return true;
		return false;
	}
	
	public String toString() {
		return name;
	}
	
	public int hashCode() {
		return id;
	}

}
