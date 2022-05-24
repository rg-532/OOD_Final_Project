package model.home;

import java.util.ArrayList;

import database.queries.SelectionContext;
import database.queries.dj.GetDJsByProminence;
import database.queries.event.GetEventsByTime;
import model.basis.UserRoomLauncherModel;
import model.entities.pseudo.User;
import model.entities.simple.City;
import model.entities.simple.DJ;
import model.entities.simple.Event;
import model.entities.simple.Genre;
import model.search.interfaces.IFilter;
import model.sort.Sorter;
import model.sort.event.SortEventsByCity;
import model.sort.event.SortEventsByGenres;
import model.sort.event.SortEventsByTime;
import model.time.CurrentTime;

public class HomeMenuModel extends UserRoomLauncherModel {
	
	private ArrayList<Event> events;
	private ArrayList<DJ> djs;
	
	private IFilter<Genre> genres;
	private IFilter<City> cities;
	
	
	public HomeMenuModel(CurrentTime currentTime, User user, IFilter<Genre> genres, IFilter<City> cities) {
		super(currentTime, user);
		initEvents();
		initDJs();
		
		this.genres = genres;
		this.cities = cities;
	}
	
	public void initEvents() {
		SelectionContext<Event> eventCon = new SelectionContext<>(new GetEventsByTime(currentTime.getTime()));
		events = eventCon.doOperation();
	}
	
	public void initDJs() {
		SelectionContext<DJ> djCon = new SelectionContext<>(new GetDJsByProminence());
		ArrayList<DJ> temp = djCon.doOperation();
		if (temp.size() < 5)
			djs = temp;
		else {
			djs = new ArrayList<>();
			for (int i=0; i<5; i++)
				djs.add(temp.get(i));
		}
	}
	
	public ArrayList<Event> getEvents() {
		Sorter<Event> sort = new Sorter<>();
		sort.addSort(new SortEventsByTime(events, currentTime.getTime()));
		sort.addSort(new SortEventsByGenres(events, genres.getElements()));
		sort.addSort(new SortEventsByCity(events, cities.getElements()));
		sort.execute();
		
		return events;
	}
	
	public ArrayList<DJ> getDJs() {
		return djs;
	}
	
}
