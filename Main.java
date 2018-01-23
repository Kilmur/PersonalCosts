package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,453,266);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("Выберите тип затрат и наберите сумму");
			stage.show();
			stage.setOnCloseRequest((WindowEvent we) -> {
	            SampleController.db.closeWindow();
	        });
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
