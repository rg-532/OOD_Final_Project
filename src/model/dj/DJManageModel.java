package model.dj;

import java.util.ArrayList;

import database.queries.ManipulationContext;
import database.queries.SelectionContext;
import database.queries.event.GetEventsForDJ;
import database.queries.genre.*;
import model.basis.DJRoomLauncherModel;
import model.entities.simple.*;
import model.search.filters.Filter;
import model.search.filters.models.FilterModel;
import model.sort.Sorter;
import model.sort.event.SortEventsByTimeDJ;
import model.time.CurrentTime;

public class DJManageModel extends DJRoomLauncherModel {
	
	private FilterModel<Genre> djGenres;
	private ArrayList<Event> djEvents;
	
	public DJManageModel(CurrentTime currentTime, DJ dj) {
		super(currentTime, dj);
		djGenres = new FilterModel<>(new Filter<Genre>());
		initDJGenres();
		initDJEvents();
	}
	
	public void initDJGenres() {
		SelectionContext<Genre> gCon = new SelectionContext<>(new GetAllGenres());
		djGenres.setWorld(gCon.doOperation());
		gCon = new SelectionContext<>(new GetGenresForDJ(dj.getId()));
		for (Genre g : gCon.doOperation())
			djGenres.addToFilter(g);
	}
	
	public void initDJEvents() {
		SelectionContext<Event> eCon = new SelectionContext<>(new GetEventsForDJ(dj.getId()));
		djEvents = eCon.doOperation();
	}
	
	public void addToFilter(Genre genre) {
		ManipulationContext cnt = new ManipulationContext(new AddGenreToDJ(dj, genre));
		if (!cnt.doOperation())
			throw new RuntimeException("SQL DJ_GENRE INSERTION ERROR");
		djGenres.addToFilter(genre);
	}
	
	public void removeFromFilter(Genre genre) {
		ManipulationContext cnt = new ManipulationContext(new RemoveGenreFromDJ(dj, genre));
		if (!cnt.doOperation())
			throw new RuntimeException("SQL DJ_GENRE DELETION ERROR");
		djGenres.removeFromFilter(genre);
	}
	
	public FilterModel<Genre> getDJGenres() {
		return djGenres;
	}
	
	public ArrayList<Event> getDJEvents() {
		Sorter<Event> sort = new Sorter<>();
		sort.addSort(new SortEventsByTimeDJ(djEvents, currentTime.getTime()));
		sort.execute();
		
		return djEvents;
	}

}
