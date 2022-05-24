package model.frame;

import database.queries.SelectionContext;
import database.queries.city.GetAllCities;
import database.queries.genre.GetAllGenres;
import model.basis.UserAppModel;
import model.dj.DJManageModel;
import model.entities.pseudo.User;
import model.entities.simple.*;
import model.home.HomeMenuModel;
import model.search.SearchMenuModel;
import model.search.filters.Filter;
import model.search.filters.decorators.CityFilterDecorator;
import model.search.filters.decorators.GenreFilterDecorator;
import model.search.filters.models.FilterModel;
import model.time.CurrentTime;

public class FrameModel extends UserAppModel {
	
	private CityFilterDecorator cityFilter;
	private GenreFilterDecorator genreFilter;
	
	
	public FrameModel(CurrentTime currentTime, User user) {
		super(currentTime, user);
		cityFilter = new CityFilterDecorator(new Filter<City>());
		genreFilter = new GenreFilterDecorator(new Filter<Genre>());
	}
	
	public HomeMenuModel getHomeModel() {
		return new HomeMenuModel(currentTime, user, genreFilter, cityFilter);
	}
	
	public SearchMenuModel getSearchModel() {
		return new SearchMenuModel(currentTime, user, genreFilter, cityFilter);
	}
	
	public DJManageModel getDJManageModel() {
		if (user instanceof DJ)
			return new DJManageModel(currentTime, (DJ)user);
		else
			return null;
	}
	
	public FilterModel<City> getCitiesGeneral() {
		FilterModel<City> back = new FilterModel<>(cityFilter);
		SelectionContext<City> cityCon = new SelectionContext<>(new GetAllCities());
		back.setWorld(cityCon.doOperation());
		return back;
	}
	
	public FilterModel<Genre> getGenresGeneral() {
		FilterModel<Genre> back = new FilterModel<>(genreFilter);
		SelectionContext<Genre> genreCon = new SelectionContext<>(new GetAllGenres());
		back.setWorld(genreCon.doOperation());
		return back;
	}

}
