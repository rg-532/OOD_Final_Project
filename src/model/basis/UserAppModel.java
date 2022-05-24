package model.basis;

import model.entities.pseudo.User;
import model.time.CurrentTime;

public abstract class UserAppModel extends AppModel {
	
	protected User user;
	
	
	public UserAppModel(CurrentTime currentTime, User user) {
		super(currentTime);
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
}
