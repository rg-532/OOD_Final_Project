package view.objects;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import model.entities.interfaces.Referable;
import controller.actions.interfaces.ButtonAction;
import controller.helpers.EntityTransformer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class ListCellButton<T extends Referable> extends ListCell<T> {

	private HBox hbox = new HBox();
	private Label label = new Label("(empty)");
	private Pane pane = new Pane();
	private Button button = new Button(":)");

	private ButtonAction<T> action;

	
	public ListCellButton(ButtonAction<T> action) {
		super();
		
		this.action = action;

		hbox.getChildren().addAll(label, pane, button);
		hbox.setAlignment(Pos.CENTER);
		HBox.setHgrow(pane, Priority.ALWAYS);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				action.act();
			}
		});
	}

	@Override
	protected void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);
		setText(null); // No text in label of super class
		if (empty) {
			setGraphic(null);
		} else {
			if (item != null) {
				action.setElement(item);
				action.editButton(button);
				label.setText(EntityTransformer.toListShow(item));
			} else {
				label.setText("<null>");
			}

			setGraphic(hbox);
		}
	}

}
