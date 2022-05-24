package controller.actions.interfaces;

import javafx.scene.control.Button;

public interface ButtonAction<T> {
	
	public void setElement(T element);
	public void editButton(Button button);
	public void act();
	
}
