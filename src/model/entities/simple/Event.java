package model.entities.simple;

import java.sql.Timestamp;

import model.entities.interfaces.*;

public class Event implements Referable {
	
	private int id;
	private String name;
	private Timestamp start, end;
	private int clubId;
	
	//Search c-tor:
	public Event(int id, String name, Timestamp start, Timestamp end, int clubId) {
		this.name = name;
		this.id = id;
		this.start = start;
		this.end = end;
		this.clubId = clubId;
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

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}
	
	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (!o.getClass().equals(getClass()))
			return false;
		Event e = (Event) o;
		if (id == e.getId())
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
}
