package model.entities.pseudo;

import java.sql.Timestamp;

import model.entities.interfaces.*;

public class Feedback implements Referable {
	
	private int eventId;
	private int id;
	private String text;
	private Timestamp time;
	private String sender;
	
	
	public Feedback(int eventId, int id, String text, Timestamp time, String sender) {
		super();
		this.eventId = eventId;
		this.id = id;
		this.text = text;
		this.time = time;
		this.sender = sender;
	}
	
	@Override
	public int getId() {
		return id;
	}

	public int getEventId() {
		return eventId;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
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
	
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (!o.getClass().equals(getClass()))
			return false;
		Feedback e = (Feedback) o;
		if (id == e.getId() && eventId == e.getEventId())
			return true;
		return false;
	}
	
}
