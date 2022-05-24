package model.sort.event;

import model.sort.interfaces.SortCommand;

import java.util.ArrayList;
import java.util.Collections;

import database.queries.SelectionContext;
import database.queries.genre.GetGenresForEvent;
import model.entities.simple.*;

public class SortEventsByGenres implements SortCommand<Event> {
	
	private ArrayList<Event> toSort;
	
	//params:
	private ArrayList<Genre> genres;
	
	
	public SortEventsByGenres(ArrayList<Event> toSort, ArrayList<Genre> genres) {
		this.toSort = toSort;
		this.genres = genres;
	}
	
	@Override
	public int compare(Event o1, Event o2) {
		int score1 = 0;
		int score2 = 0;
		
		SelectionContext<Genre> genreCon;
		ArrayList<Genre> sortBy;
		
		
		genreCon = new SelectionContext<>(new GetGenresForEvent(o1.getId()));
		sortBy = genreCon.doOperation();
		for (Genre genre : sortBy)
			if (genres.contains(genre))
				score1 += 1;
		
		genreCon = new SelectionContext<>(new GetGenresForEvent(o2.getId()));
		sortBy = genreCon.doOperation();
		for (Genre genre : sortBy)
			if (genres.contains(genre))
				score2 += 1;
		
		return score2 - score1;
	}

	@Override
	public void execute() {
		Collections.sort(toSort, this);
	}

}
