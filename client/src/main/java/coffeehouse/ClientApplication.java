package coffeehouse;

import coffeehouse.gui.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApplication extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
        Parent root;
        LoginController controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gui/Login.fxml"));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();
        } catch(Exception e) {
            System.err.println("Exception while fetching Login.fxml");
            e.printStackTrace();
            return;
        }
        if(controller != null)
            controller.setClientApp(this);
        else
            System.err.println("controller is null");

		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

	public void login(String ip, int port, String username)
    {
        // TODO: implement
    }
}
