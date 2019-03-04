package coffeehouse;

import coffeehouse.net.Client;
import coffeehouse.util.IOUtils;
import javafx.application.Application;
import java.net.UnknownHostException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ClientApplication extends Application {
	public static void main(String[] args) {
		//if (args.length < 2) {
		//	System.err.println("Insufficient arguments provided, usage: program <ip> <port>");
		//	return;
		//}


		String ip = "127.0.0.1";
		int port = Integer.parseInt("3000");
		
		Client client;
		
		try {
			client = new Client(ip, port);
		} catch (UnknownHostException e) {
			System.err.println("Host not recognised.");
			return;
		}

		new ConsoleInputLoop(client).run();		
		
		launch(args);
		IOUtils.closeQuietly(client);

	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Hello World!");
		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});

		StackPane root = new StackPane();
		root.getChildren().add(btn);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}
}
