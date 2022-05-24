package view.objects;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import model.rooms.GenreCounter;

public class GenreAnalyseListCell extends ListCell<GenreCounter> {

	private HBox hbox = new HBox();
	private Label genre = new Label("(empty)");
	private Label counter = new Label("(empty)");
	private Pane pane = new Pane();
	
	
	public GenreAnalyseListCell() {
		super();
		
		hbox.getChildren().addAll(genre, pane, counter);
		HBox.setHgrow(pane, Priority.ALWAYS);
	}

	@Override
	protected void updateItem(GenreCounter item, boolean empty) {
		super.updateItem(item, empty);
		setText(null); // No text in label of super class
		if (empty) {
			setGraphic(null);
		} else {
			if (item != null) {
				genre.setText(item.getGenre().getName());
				counter.setText(String.valueOf(item.getCounter()));
			} else {
				genre.setText("<null>");
				counter.setText("<null>");
			}

			setGraphic(hbox);
		}
	}

}
