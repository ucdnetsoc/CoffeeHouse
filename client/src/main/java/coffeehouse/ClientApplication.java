package coffeehouse;

import coffeehouse.net.Client;
import coffeehouse.util.IOUtils;
import javafx.application.Application;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ClientApplication extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
        Parent root;
        try {
            URL resourceUrl = getClass().getResource("/coffeehouse/gui/Login.fxml");
            root = FXMLLoader.load(resourceUrl);
        } catch(Exception e) {
            System.err.println("Exception while fetching Login.fxml");
            e.printStackTrace();
            return;
        }

		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}
}
