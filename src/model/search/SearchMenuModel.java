package model.search;

import java.sql.Timestamp;
import java.util.ArrayList;

import model.search.advanced.*;
import model.basis.UserRoomLauncherModel;
import model.entities.pseudo.User;
import model.entities.simple.*;
import model.search.filters.models.ClubFilterModel;
import model.search.filters.models.DJFilterModel;
import model.search.interfaces.IFilter;
import model.sort.Sorter;
import model.sort.event.SortEventsByCity;
import model.sort.event.SortEventsByGenres;
import model.sort.event.SortEventsByTime;
import model.time.CurrentTime;

public class SearchMenuModel extends UserRoomLauncherModel {
	
	private AdvancedEventSearch advEvent;
	private ArrayList<Event> resEvent;
	private AdvancedDJSearch advDJ;
	private ArrayList<DJ> resDJ;
	
	private IFilter<Genre> genres;
	private IFilter<City> cities;
	
	
	public SearchMenuModel(CurrentTime currentTime, User user, IFilter<Genre> genres, IFilter<City> cities) {
		super(currentTime, user);
		advEvent = new AdvancedEventSearch();
		resEvent = new ArrayList<>();
		advDJ = new AdvancedDJSearch();
		resDJ = new ArrayList<>();
		
		this.genres = genres;
		this.cities = cities;
	}
	
	public void searchEvents(String name, Timestamp time, boolean filterByTime) {
		advEvent.setSearchParams(name, time, filterByTime);
		advEvent.executeSearch();
		resEvent = advEvent.getResult();
	}
	
	public void searchDJs(String name) {
		advDJ.setSearchParams(name);
		advDJ.executeSearch();
		resDJ = advDJ.getResult();
	}
	
	public DJFilterModel getDJsForEvents() {
		return new DJFilterModel(advEvent.getDJFilter().getFilter());
	}
	
	public ClubFilterModel getClubsForDJs() {
		return new ClubFilterModel(advDJ.getClubFilter().getFilter());
	}
	
	public ArrayList<Event> getEventResult() {
		Sorter<Event> sort = new Sorter<>();
		sort.addSort(new SortEventsByTime(resEvent, currentTime.getTime()));
		sort.addSort(new SortEventsByGenres(resEvent, genres.getElements()));
		sort.addSort(new SortEventsByCity(resEvent, cities.getElements()));
		sort.execute();
		
		return resEvent;
	}
	
	public ArrayList<DJ> getDJResult() {
		return resDJ;
	}
	
}
