package model.entities.factories;

import java.util.HashMap;

import database.queries.SelectionContext;
import database.queries.city.GetCityById;
import model.entities.simple.City;

public class CityFactory {
	
	private static CityFactory _instance;
	
	private HashMap<Integer, City> cityMap;
	
	
	private CityFactory() {
		cityMap = new HashMap<>();
	}
	
	public static CityFactory getInstance() {
		if (_instance == null)
			_instance = new CityFactory();
		return _instance;
	}
	
	public synchronized City getCity(int id) {
		City back = cityMap.get(id);
		
		if (back == null) {
			SelectionContext<City> cnt = new SelectionContext<>(new GetCityById(id));
			back = cnt.doOperation().get(0);
			cityMap.put(id, back);
		}
		
		return back;
	}
}
