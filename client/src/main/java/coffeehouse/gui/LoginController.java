package coffeehouse.gui;

import coffeehouse.ClientApplication;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class LoginController {
	@FXML
	private TextField serverIpField;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private Button loginButton;

	private ClientApplication clientApp;

	private void showError(Exception e)
	{
		e.printStackTrace();

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Unknown error!");
		alert.setHeaderText("An error occurred connecting to server.");
		alert.setContentText(e + " thrown while attempting to login to server. See logs for details.");

		e.printStackTrace();

		alert.showAndWait();
	}

	private void showError(UnknownHostException e)
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Unknown Address!");
		alert.setHeaderText("Please enter a valid server address.");
		alert.setContentText(e.getLocalizedMessage());

		alert.showAndWait();
	}

	@FXML
	public void initialize() {
		loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String ip = serverIpField.getText();
				String username = usernameField.getText();

				String components[] = ip.split(":");

				int port = 3000;

				try {

					if(components.length > 2)
					{
						throw new UnknownHostException();
					}
					else if(components.length == 2) {
						try {
							port = Integer.parseUnsignedInt(components[1]);
						} catch (NumberFormatException e) {
							throw new UnknownHostException("Invalid port");
						}
						if (port > 65535) {
							throw new UnknownHostException("Invalid port");
						}
						ip = components[0];
					}

					InetAddress addr = InetAddress.getByName(ip);

					clientApp.login(addr.getHostAddress(), port, username);
				} catch(UnknownHostException e) {
					showError(e);
				} catch(Exception e) {
					showError(e);
				}
			}
		});
	}

	public void setClientApp(ClientApplication clientApp) {
		this.clientApp = clientApp;
	}


}
