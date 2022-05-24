package model.sort.event;

import model.sort.interfaces.SortCommand;

import java.util.ArrayList;
import java.util.Collections;

import database.queries.SelectionContext;
import database.queries.club.GetClubById;
import model.entities.factories.CityFactory;
import model.entities.simple.*;

public class SortEventsByCity implements SortCommand<Event> {
	
	private ArrayList<Event> toSort;
	
	//params:
	private ArrayList<City> cities;
	
	
	public SortEventsByCity(ArrayList<Event> toSort, ArrayList<City> cities) {
		this.toSort = toSort;
		this.cities = cities;
	}
	
	@Override
	public int compare(Event o1, Event o2) {
		int score1 = 0;
		int score2 = 0;
		
		SelectionContext<Club> clubCon;
		
		clubCon = new SelectionContext<>(new GetClubById(o1.getClubId()));
		if (cities.contains(CityFactory.getInstance().getCity(clubCon.doOperation().get(0).getCityId())))
			score1 += 1;
		
		clubCon = new SelectionContext<>(new GetClubById(o2.getClubId()));
		if (cities.contains(CityFactory.getInstance().getCity(clubCon.doOperation().get(0).getCityId())))
			score2 += 1;
		
		return score2 - score1;
	}

	@Override
	public void execute() {
		Collections.sort(toSort, this);
	}

}
