package model.search.filters.decorators;

import model.entities.simple.*;
import model.search.interfaces.IFilter;

public class ClubFilterDecorator extends FilterDecorator<Club> {
	
	
	public ClubFilterDecorator(IFilter<Club> filter) {
		super(filter);
	}

	@Override
	public String toSQLCode() {
		StringBuffer sb = new StringBuffer("");
		
		if (!filter.isFilterEmpty()) {
			sb.append("\r\n(club_id = " + filter.getElements().get(0).getId());
			for (int i = 1; i < filter.getElements().size(); i++)
				sb.append("\r\nOR club_id = " + filter.getElements().get(i).getId());
			sb.append(")");
		}
		
		return sb.toString();
	}

}
