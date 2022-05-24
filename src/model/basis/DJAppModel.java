package model.basis;

import model.entities.simple.DJ;
import model.time.CurrentTime;

public abstract class DJAppModel extends AppModel {
	
	protected DJ dj;
	
	
	public DJAppModel(CurrentTime currentTime, DJ dj) {
		super(currentTime);
		this.dj = dj;
	}
	
	public DJ getDJ() {
		return dj;
	}
	
}
