package model.search.filters.decorators;

import model.entities.simple.*;
import model.search.interfaces.IFilter;

public class GenreFilterDecorator extends FilterDecorator<Genre> {
	
	
	public GenreFilterDecorator(IFilter<Genre> filter) {
		super(filter);
	}
	
	@Override
	public String toSQLCode() {
		StringBuffer sb = new StringBuffer("");
		
		if (!filter.isFilterEmpty()) {
			sb.append("\r\n(genre_id = " + filter.getElements().get(0).getId());
			for (int i = 1; i < filter.getElements().size(); i++)
				sb.append("\r\nOR genre_id = " + filter.getElements().get(i).getId());
			sb.append(")");
		}
		
		return sb.toString();
	}

}
