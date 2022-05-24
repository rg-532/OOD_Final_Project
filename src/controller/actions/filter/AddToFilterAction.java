package controller.actions.filter;

import controller.filter.FilterController;
import javafx.scene.control.Button;
import model.entities.interfaces.Referable;

public class AddToFilterAction<T extends Referable> extends FilterAction<T> {

	public AddToFilterAction(FilterController<T> controller) {
		super(controller);
	}

	@Override
	public void act() {
		controller.addToFilter(element);
	}

	@Override
	public void editButton(Button button) {
		button.setText("ADD");
	}

}
