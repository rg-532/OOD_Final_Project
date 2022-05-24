package controller.helpers;

import java.util.ArrayList;

import database.queries.SelectionContext;
import database.queries.club.GetClubById;
import database.queries.dj.GetDJsForEvent;
import database.queries.genre.GetGenresForDJ;
import database.queries.genre.GetGenresForEvent;
import model.entities.factories.CityFactory;
import model.entities.interfaces.Referable;
import model.entities.simple.*;

public class EntityTransformer {

	public static String toListShow(Referable ref) {
		switch (ref.getClass().getSimpleName()) {
		case "City":
			return cityToListShow((City) ref);
		case "Club":
			return clubToListShow((Club) ref);
		case "DJ":
			return djToListShow((DJ) ref);
		case "Event":
			return eventToListShow((Event) ref);
		case "Genre":
			return genreToListShow((Genre) ref);
		default:
			return null;
		}
	}
	
	public static String cityToListShow(City city) {
		return city.getName();
	}
	
	public static String clubToListShow(Club club) {
		return club.getName() + " Club in " + CityFactory.getInstance().getCity(club.getCityId()).getName();
	}

	public static String djToListShow(DJ dj) {
		SelectionContext<Genre> sc = new SelectionContext<>(new GetGenresForDJ(dj.getId()));
		StringBuffer sb = new StringBuffer("DJ " + dj.getName() + "\n");
		
		ArrayList<Genre> genres = sc.doOperation();
		if (genres.isEmpty())
			sb.append("No genres defined yet.\n");
		else {
			sb.append("Genres: ");
			for (int i=0; i<genres.size()-1; i++)
				sb.append(genres.get(i).getName() + ", ");
			sb.append(genres.get(genres.size()-1).getName() + ".");
		}
		
		return sb.toString();
	}
	
	public static String eventToListShow(Event event) {
		SelectionContext<Club> clubSC= new SelectionContext<>(new GetClubById(event.getClubId()));
		SelectionContext<Genre> genreSC = new SelectionContext<>(new GetGenresForEvent(event.getId()));
		SelectionContext<DJ> djSC = new SelectionContext<>(new GetDJsForEvent(event.getId()));
		
		StringBuffer sb = new StringBuffer(event.getName() + " in " + clubToListShow(clubSC.doOperation().get(0)) + "\n");
		sb.append("Starts on: " + event.getStart().toString() + "  Ends on: " + event.getEnd() + "\n");
		
		ArrayList<Genre> genres = genreSC.doOperation();
		if (genres.isEmpty())
			sb.append("No genres defined yet.\n");
		else {
			sb.append("Genres: ");
			for (int i=0; i<genres.size()-1; i++)
				sb.append(genres.get(i).getName() + ", ");
			sb.append(genres.get(genres.size()-1).getName() + ".\n");
		}
		
		ArrayList<DJ> djs = djSC.doOperation();
		if (djs.isEmpty())
			sb.append("No djs defined yet.\n");
		else {
			sb.append("DJs: ");
			for (int i=0; i<djs.size()-1; i++)
				sb.append(djs.get(i).getName() + ", ");
			sb.append(djs.get(djs.size()-1).getName() + ".");
		}
		
		return sb.toString();
	}
	
	public static String genreToListShow(Genre genre) {
		return genre.getName();
	}

}
