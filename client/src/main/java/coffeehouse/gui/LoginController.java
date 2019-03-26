package coffeehouse.gui;

import coffeehouse.ClientApplication;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class LoginController {
	@FXML
	private TextField serverIpField;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private Button loginButton;

	private ClientApplication clientApp;

	@FXML
	public void initialize() {
		loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String ip = serverIpField.getText();
				String username = usernameField.getText();

				// TODO: Check if ip and username are valid format

				int port = 3000;

				// TODO: if ip contains port (e.g. 192.168.0.10:3000) use that instead

				loginButton.setText("Clicked"); // Something to show that this was run

				clientApp.login(ip, port, username);
			}
		});
	}

	public void setClientApp(ClientApplication clientApp) {
		this.clientApp = clientApp;
	}


}
