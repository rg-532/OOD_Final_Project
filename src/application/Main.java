package application;
	
import java.io.IOException;

import controller.admin.AdminController;
import controller.helpers.WindowLauncher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.admin.AdminModel;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/admin/AdminView.fxml"));
			Parent root = loader.load();
			
			AdminController ctrl = loader.getController();
			ctrl.init(stage, new AdminModel());
			
			WindowLauncher launcher = new WindowLauncher();
			Scene scene = launcher.getScene(root);
			
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		/*
		SelectionContext<Event> cnt = new SelectionContext<>(
				new GetEventsBySearchParams("", Timestamp.valueOf("2022-01-05 22:00:00")));
		ArrayList<Event> res = cnt.doOperation();
		
		for (Event ev : res)
			System.out.println(ev.getId() + " | " + ev.getName() + " | " + ev.getStart()
			+ " | " + ev.getEnd() + " | " + ev.getClubId());
		
		
		
		AdvancedDJSearch advDJ = new AdvancedDJSearch();
		advDJ.setSearchParams("ta");
		SelectionContext<Club> cnt2 = new SelectionContext<>(new GetClubById(1));
		advDJ.getClubFilter().addToFilter(cnt2.doOperation().get(0));
		advDJ.executeSearch();
		for (DJ dj : advDJ.getResult())
			System.out.println(dj.getId() + " | " + dj.getName());
		
		Event myEvent = null;
		
		if (advDJ.getResult().size() > 0) {
			DJ myDJ = advDJ.getResult().get(0);
			AdvancedEventSearch advEvent = new AdvancedEventSearch();
			advEvent.setSearchParams("du", Timestamp.valueOf("2022-01-05 22:00:00"));
			advEvent.getDJFilter().addToFilter(myDJ);
			advEvent.executeSearch();
			for (Event ev : advEvent.getResult()) {
				System.out.println(ev.getId() + " | " + ev.getName() + " | " + ev.getStart()
				+ " | " + ev.getEnd() + " | " + ev.getClubId());
				myEvent = ev;
			}
		}
		
		/*
		RoomUserModel rum1 = new RoomUserModel(myEvent, "derill");
		RoomUserModel rum2 = new RoomUserModel(myEvent, "jerald");
		
		for (Feedback fb : rum1.getFeedback()) {
			System.out.println(fb.getSender() + "\t\t" + fb.getTime().toString());
			System.out.println(fb.getText());
			System.out.println("------------------------------------------");
		}
		
		for (Feedback fb : rum2.getFeedback()) {
			System.out.println(fb.getSender() + "\t\t" + fb.getTime().toString());
			System.out.println(fb.getText());
			System.out.println("------------------------------------------");
		}
		
		rum1.addFeedback("Yo this event is soo coool!", Timestamp.valueOf("2022-01-05 22:01:00"));
		for (Feedback fb : rum2.getFeedback()) {
			System.out.println(fb.getSender() + "\t\t" + fb.getTime().toString());
			System.out.println(fb.getText());
			System.out.println("------------------------------------------");
		}
		
		rum2.addFeedback("Totally agree!", Timestamp.valueOf("2022-01-05 22:03:00"));
		for (Feedback fb : rum2.getFeedback()) {
			System.out.println(fb.getSender() + "\t\t" + fb.getTime().toString());
			System.out.println(fb.getText());
			System.out.println("------------------------------------------");
		}
		*/
	}
	
}


