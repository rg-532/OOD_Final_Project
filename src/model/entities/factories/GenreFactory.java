package model.entities.factories;

import java.util.HashMap;

import database.queries.SelectionContext;
import database.queries.genre.GetGenreById;
import model.entities.simple.Genre;

public class GenreFactory {
	
	private static GenreFactory _instance;
	
	private HashMap<Integer, Genre> genreMap;
	
	
	private GenreFactory() {
		genreMap = new HashMap<>();
	}
	
	public static GenreFactory getInstance() {
		if (_instance == null)
			_instance = new GenreFactory();
		return _instance;
	}
	
	public synchronized Genre getGenre(int id) {
		Genre back = genreMap.get(id);
		
		if (back == null) {
			SelectionContext<Genre> cnt = new SelectionContext<>(new GetGenreById(id));
			back = cnt.doOperation().get(0);
			genreMap.put(id, back);
		}
		
		return back;
	}
}
