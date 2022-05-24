package controller.frame;

import java.io.IOException;

import controller.helpers.WindowLauncher;
import controller.menus.HomeMenuController;
import controller.menus.SearchMenuController;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.frame.FrameModel;
import model.time.CurrentTime;
import model.time.interfaces.TimeObserver;

public class UserFrameController implements TimeObserver {
	
	protected Stage prmStage;
	protected FrameModel model;
	
	@FXML
	protected Button cityFilterButton, genreFilterButton, homeMenuButton, searchMenuButton;

	@FXML
	protected AnchorPane menuPane;

	@FXML
	protected Label usernameLabel, timeLabel;
	
	
	// Basic:
	public void init(Stage prmStage, FrameModel model) {
		this.prmStage = prmStage;
		this.model = model;
		updateTime(model.getCurrentTime());
		model.getCurrentTime().attach(this);
		
		usernameLabel.setText(model.getUser().getName());
		loadHome();
		setupStageClose();
	}
	
	
	// Control:
	public void loadHome() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/menus/HomeMenuView.fxml"));
			GridPane root = loader.load();

			HomeMenuController ctrl = loader.getController();
			ctrl.init(prmStage, model.getHomeModel());

			menuPane.getChildren().clear();
			menuPane.getChildren().add(root);
			root.prefHeightProperty().bind(menuPane.heightProperty());
			root.prefWidthProperty().bind(menuPane.widthProperty());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadSearch() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/menus/SearchMenuView.fxml"));
			TabPane root = loader.load();

			SearchMenuController ctrl = loader.getController();
			ctrl.init(prmStage, model.getSearchModel());

			menuPane.getChildren().clear();
			menuPane.getChildren().add(root);
			
			root.prefHeightProperty().bind(menuPane.heightProperty());
			root.prefWidthProperty().bind(menuPane.widthProperty());
			root.tabMinWidthProperty().bind(Bindings.createDoubleBinding(
					() -> (menuPane.getWidth() - 21.3)/2, menuPane.widthProperty()));
			root.setTabMinHeight(50);
			root.setTabMaxHeight(50);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showCityFilter() {
		WindowLauncher launcher = new WindowLauncher();
		launcher.showGeneralCityFilter(prmStage, model.getCitiesGeneral());
	}
	
	public void showGenreFilter() {
		WindowLauncher launcher = new WindowLauncher();
		launcher.showGeneralGenreFilter(prmStage, model.getGenresGeneral());
	}

	@Override
	public void updateTime(CurrentTime time) {
		timeLabel.setText(time.toString());
	}
	
	public void setupStageClose() {
		TimeObserver me = this;
		
		prmStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				model.getCurrentTime().detach(me);
			}
		});
	}

}
