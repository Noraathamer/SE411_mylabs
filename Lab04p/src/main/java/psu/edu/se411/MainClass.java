package psu.edu.se411;
import javafx.application.*;
import javafx.stage.Stage;

public class MainClass extends Application {

	public static void main(String[] args) {
		  launch();
		}

		@Override
		public void start(Stage primaryStage) {
		  try {
		    primaryStage.setTitle("My Project");

		    primaryStage.show();

		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		}
}