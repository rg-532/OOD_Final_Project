package controller.filter;

import controller.actions.filter.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.entities.interfaces.Referable;
import model.search.filters.models.FilterModel;
import view.objects.ListCellButton;

public class FilterController<T extends Referable> {

	protected Stage prmStage;
	protected FilterModel<T> model;
	

	@FXML
	protected ListView<T> filterView, worldView;

	
	// Basic:
	public void init(Stage prmStage, FilterModel<T> model) {
		this.prmStage = prmStage;
		this.model = model;
		initLists();
		
		refreshView();
	}
	
	public void initLists() {
		FilterController<T> me = this;

		filterView.setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
			@Override
			public ListCell<T> call(ListView<T> param) {
				return new ListCellButton<T>(new RemoveFromFilterAction<T>(me));
			}
		});

		worldView.setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
			@Override
			public ListCell<T> call(ListView<T> param) {
				return new ListCellButton<T>(new AddToFilterAction<T>(me));
			}
		});
	}
	
	
	// Control:
	public void addToFilter(T element) {
		model.addToFilter(element);
		refreshView();
	}

	public void removeFromFilter(T element) {
		model.removeFromFilter(element);
		refreshView();
	}

	public void refreshView() {
		filterView.getItems().clear();
		filterView.getItems().addAll(model.getFilter().getElements());
		worldView.getItems().clear();
		worldView.getItems().addAll(model.getWorld());
	}

}
