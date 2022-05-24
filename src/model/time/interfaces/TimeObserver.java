package model.time.interfaces;

import model.time.CurrentTime;

public interface TimeObserver {
	public void updateTime(CurrentTime time);
}
