package coffeehouse;

import coffeehouse.gui.LoginController;
import coffeehouse.gui.MainController;
import coffeehouse.net.AuthPacket;
import coffeehouse.net.Client;
import coffeehouse.util.IOUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.UnknownHostException;

public class ClientApplication extends Application {

    private Client client;
    private Stage primaryStage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

	    this.primaryStage = primaryStage;

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

	public void logout() {
        IOUtils.closeQuietly(client);
        client = null;
    }

	public void login(String ip, int port, String username) throws UnknownHostException, IOException {
        if(client != null)
        {
            logout();
        }

        client = new Client(ip, port);

        client.sendPacket(new AuthPacket(username));

        MainController controller;
        Parent root;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gui/Main.fxml"));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();
        } catch(Exception e) {
            System.err.println("Exception while fetching Main.fxml");
            e.printStackTrace();
            return;
        }

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
