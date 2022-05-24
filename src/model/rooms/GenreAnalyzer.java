package model.rooms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import model.entities.factories.GenreFactory;
import model.entities.pseudo.Request;
import model.entities.simple.Genre;

public class GenreAnalyzer {
	
	private HashMap<Genre, GenreCounter> genreMap;
	private ArrayList<GenreCounter> genreList;
	
	public GenreAnalyzer(ArrayList<Request> requests) {
		genreMap = new HashMap<>();
		genreList = new ArrayList<>();
		GenreCounter counter;
		Genre genre;
		
		for (Request req : requests) {
			genre = GenreFactory.getInstance().getGenre(req.getGenreId());
			counter = genreMap.get(genre);
			
			if (counter == null) {
				genre = GenreFactory.getInstance().getGenre(req.getGenreId());
				counter = new GenreCounter(genre);
				genreList.add(counter);
				genreMap.put(genre, counter);
			}
			
			counter.add();
		}
		
		Collections.sort(genreList);
	}
	
	public ArrayList<GenreCounter> getResult() {
		return genreList;
	}
	
}
