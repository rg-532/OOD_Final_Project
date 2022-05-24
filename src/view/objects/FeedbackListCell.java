package view.objects;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.entities.pseudo.Feedback;

public class FeedbackListCell extends ListCell<Feedback> {

	private HBox hbox = new HBox();
	private VBox vbox = new VBox();
	private Label name = new Label("(empty)");
	private Label time = new Label("(empty)");
	private Label feedback = new Label("(empty)");
	private Pane pane = new Pane();
	
	
	public FeedbackListCell() {
		super();
		
		hbox.getChildren().addAll(name, pane, time);
		HBox.setHgrow(pane, Priority.ALWAYS);
		vbox.getChildren().addAll(hbox, feedback);
		feedback.setWrapText(true);
	}

	@Override
	protected void updateItem(Feedback item, boolean empty) {
		super.updateItem(item, empty);
		setText(null); // No text in label of super class
		if (empty) {
			setGraphic(null);
		} else {
			if (item != null) {
				name.setText(item.getSender());
				time.setText(item.getTime().toString());
				feedback.setText(item.getText());
			} else {
				name.setText("<null>");
				time.setText("<null>");
				feedback.setText("<null>");
			}

			setGraphic(vbox);
		}
	}

}
