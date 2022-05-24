package view.objects;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import model.entities.interfaces.Referable;
import controller.helpers.EntityTransformer;

public class MyListCell<T extends Referable> extends ListCell<T> {

	private HBox hbox = new HBox();
	private Label label = new Label("(empty)");
	private Pane pane = new Pane();
	
	
	public MyListCell() {
		super();
		
		hbox.getChildren().addAll(label, pane);
		HBox.setHgrow(pane, Priority.ALWAYS);
	}

	@Override
	protected void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);
		setText(null); // No text in label of super class
		if (empty) {
			setGraphic(null);
		} else {
			label.setText(item != null
					? EntityTransformer.toListShow(item)
					: "<null>");

			setGraphic(hbox);
		}
	}

}
