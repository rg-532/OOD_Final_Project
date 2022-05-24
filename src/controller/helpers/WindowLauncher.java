package controller.helpers;

import java.io.File;
import java.io.IOException;

import controller.admin.LogInController;
import controller.filter.*;
import controller.frame.DJFrameController;
import controller.frame.UserFrameController;
import controller.rooms.EventRoomController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.admin.AdminModel;
import model.entities.simple.*;
import model.frame.FrameModel;
import model.rooms.*;
import model.search.filters.models.*;

public class WindowLauncher {
	
	// Admin Controller + Threads:
	public Stage showLogInView(AdminModel model) {
		try {
			Stage stage = new Stage();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/admin/UserLogInView.fxml"));
			Parent root = loader.load();

			LogInController ctrl = loader.getController();
			ctrl.init(stage, model);

			Scene scene = getScene(root);
			stage.setScene(scene);
			stage.show();
			return stage;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Log In Controller:
	public Stage showUserAppView(FrameModel model) {
		try {
			Stage stage = new Stage();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/frame/UserFrameView.fxml"));
			Parent root = loader.load();

			UserFrameController ctrl = loader.getController();
			ctrl.init(stage, model);

			Scene scene = getScene(root);
			stage.setScene(scene);
			stage.show();
			return stage;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Stage showDJAppView(FrameModel model) {
		try {
			Stage stage = new Stage();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/frame/DJFrameView.fxml"));
			Parent root = loader.load();

			DJFrameController ctrl = loader.getController();
			ctrl.init(stage, model);

			Scene scene = getScene(root);
			stage.setScene(scene);
			stage.show();
			return stage;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// User Frame Controller:
	public void showGeneralCityFilter(Stage prmStage, FilterModel<City> model) {
		try {
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(prmStage);

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/filter/GeneralFilterView.fxml"));
			Parent root = loader.load();

			FilterController<City> ctrl = loader.getController();
			ctrl.init(stage, model);

			Scene scene = getScene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showGeneralGenreFilter(Stage prmStage, FilterModel<Genre> model) {
		try {
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(prmStage);

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/filter/GeneralFilterView.fxml"));
			Parent root = loader.load();

			FilterController<Genre> ctrl = loader.getController();
			ctrl.init(stage, model);

			Scene scene = getScene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Search Menu Controller:
	public void showEventDJFilter(Stage prmStage, DJFilterModel model) {
		try {
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(prmStage);

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/filter/DJFilterView.fxml"));
			Parent root = loader.load();

			DJFilterController ctrl = loader.getController();
			ctrl.init(stage, model);

			Scene scene = getScene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showDJClubFilter(Stage prmStage, ClubFilterModel model) {
		try {
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(prmStage);

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/filter/ClubFilterView.fxml"));
			Parent root = loader.load();

			ClubFilterController ctrl = loader.getController();
			ctrl.init(stage, model);

			Scene scene = getScene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage showEventRoom(Stage prmStage, EventRoomModel model) {
		try {
			Stage stage = new Stage();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/rooms/EventRoomView.fxml"));
			Parent root = loader.load();

			EventRoomController ctrl = loader.getController();
			ctrl.init(stage, model);

			Scene scene = getScene(root);
			stage.setScene(scene);
			
			stage.show();
			return stage;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Scene getScene(Parent root) {
		Scene scene = new Scene(root);
		File f = new File("application.css");
		scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
		return scene;
	}

}
