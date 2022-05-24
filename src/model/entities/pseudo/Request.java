package model.entities.pseudo;

import java.sql.Timestamp;

import model.entities.interfaces.*;

public class Request implements Referable {
	
	private int eventId;
	private int id;
	private String song;
	private Timestamp time;
	private String sender;
	private int genreId;
	
	
	public Request(int eventId, int id, String song, Timestamp time, String sender, int genreId) {
		super();
		this.eventId = eventId;
		this.id = id;
		this.song = song;
		this.time = time;
		this.sender = sender;
		this.genreId = genreId;
	}
	
	@Override
	public int getId() {
		return id;
	}

	public int getEventId() {
		return eventId;
	}

	public String getSong() {
		return song;
	}
	
	public void setSong(String song) {
		this.song = song;
	}

	public Timestamp getTime() {
		return time;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public int getGenreId() {
		return genreId;
	}
	
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (!o.getClass().equals(getClass()))
			return false;
		Request e = (Request) o;
		if (id == e.getId() && eventId == e.getEventId())
			return true;
		return false;
	}
	
}
