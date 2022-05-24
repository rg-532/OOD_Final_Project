package controller.filter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import model.entities.simple.DJ;
import model.search.filters.models.DJFilterModel;

public class DJFilterController extends FilterController<DJ> {

	private DJFilterModel model;
	
	
	@FXML
	protected TextField djNameField;
	
	@FXML
	protected Button searchButton;

	// Basic:
	public void init(Stage prmStage, DJFilterModel model) {
		super.init(prmStage, model);
		this.model = model;
	}

	// Control:
	public void setWorld() {
		String name = djNameField.getText();
		model.searchDJs(name);
		refreshView();
	}

}
