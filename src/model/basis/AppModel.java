package model.basis;

import model.time.CurrentTime;

public abstract class AppModel {
	
	protected CurrentTime currentTime;
	
	
	public AppModel(CurrentTime currentTime) {
		this.currentTime = currentTime;
	}
	
	public CurrentTime getCurrentTime() {
		return currentTime;
	}
	
}
