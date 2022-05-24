package controller.actions.filter;

import controller.filter.FilterController;
import javafx.scene.control.Button;
import model.entities.interfaces.Referable;

public class RemoveFromFilterAction <T extends Referable> extends FilterAction<T> {
	
	public RemoveFromFilterAction(FilterController<T> controller) {
		super(controller);
	}
	
	@Override
	public void act() {
		controller.removeFromFilter(element);
	}

	@Override
	public void editButton(Button button) {
		button.setText("REMOVE");
	}

}
