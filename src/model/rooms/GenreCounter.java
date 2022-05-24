package model.rooms;

import model.entities.simple.Genre;

public class GenreCounter implements Comparable<GenreCounter>{
	
	private Genre genre;
	private int counter;
	
	
	public GenreCounter(Genre genre) {
		this.genre = genre;
		counter = 0;
	}
	
	public Genre getGenre() {
		return genre;
	}
	
	public int getCounter() {
		return counter;
	}
	
	public void add() {
		counter++;
	}

	@Override
	public int compareTo(GenreCounter o) {
		return counter - o.getCounter();
	}
	
}
