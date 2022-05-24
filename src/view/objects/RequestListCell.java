package view.objects;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.entities.factories.GenreFactory;
import model.entities.pseudo.Request;

public class RequestListCell extends ListCell<Request> {

	private HBox hbox = new HBox();
	private VBox vbox = new VBox();
	private Label name = new Label("(empty)");
	private Label time = new Label("(empty)");
	private Label request = new Label("(empty)");
	private Pane pane = new Pane();
	
	
	public RequestListCell() {
		super();
		
		hbox.getChildren().addAll(name, pane, time);
		HBox.setHgrow(pane, Priority.ALWAYS);
		vbox.getChildren().addAll(hbox, request);
		request.setWrapText(true);
	}

	@Override
	protected void updateItem(Request item, boolean empty) {
		super.updateItem(item, empty);
		setText(null); // No text in label of super class
		if (empty) {
			setGraphic(null);
		} else {
			if (item != null) {
				name.setText(item.getSender());
				time.setText(item.getTime().toString());
				request.setText(item.getSong() + " ("+ GenreFactory.getInstance().getGenre(item.getGenreId()) + ")");
			} else {
				name.setText("<null>");
				time.setText("<null>");
				request.setText("<null>");
			}

			setGraphic(vbox);
		}
	}

}
