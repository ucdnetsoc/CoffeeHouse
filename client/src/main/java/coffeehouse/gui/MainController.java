package coffeehouse.gui;

import coffeehouse.ClientApplication;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.UnknownHostException;

public class MainController {

    private ClientApplication clientApp;

    @FXML
    public void initialize() {

    }

    public void setClientApp(ClientApplication clientApp) {
        this.clientApp = clientApp;
    }
}
