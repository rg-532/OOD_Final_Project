package controller.filter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.simple.Club;
import model.search.filters.models.ClubFilterModel;

public class ClubFilterController extends FilterController<Club> {

	private ClubFilterModel model;
	
	
	@FXML
	protected TextField clubNameField;
	
	@FXML
	protected Button searchButton;

	// Basic:
	public void init(Stage prmStage, ClubFilterModel model) {
		super.init(prmStage, model);
		this.model = model;
	}

	// Control:
	public void setWorld() {
		String name = clubNameField.getText();
		model.searchClubs(name);
		refreshView();
	}

}
