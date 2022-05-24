package model.entities.simple;

import model.entities.interfaces.*;

public class Club implements Referable {
	
	private int id;
	private String name;
	private int cityId;
	
	//Search c-tor:
	public Club(int id, String name, int cityId) {
		this.name = name;
		this.id = id;
		this.cityId = cityId;
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

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (!o.getClass().equals(getClass()))
			return false;
		Club c = (Club) o;
		if (id == c.getId())
			return true;
		return false;
	}

}
