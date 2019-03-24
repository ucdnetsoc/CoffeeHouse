package coffeehouse.gui;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class UserDisplay extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField username= new TextField();
        TextField password= new TextField();
        BorderPane root = new BorderPane();
        Text passwordText = new Text();
        Text usernameText = new Text();
        Button enterButton = new Button();
        usernameText.setFont(Font.font("Arial Black", FontWeight.BLACK, 12));
        passwordText.setFont(Font.font("Arial Black", FontWeight.BLACK, 12));
        usernameText.setText("Username");
        enterButton.setText("Enter");
        usernameText.setTextAlignment(TextAlignment.LEFT);
        passwordText.setTextAlignment(TextAlignment.CENTER);
        HBox centerScreen = new HBox();
        centerScreen.setAlignment(Pos.CENTER);
        passwordText.setText("Password");
        HBox.setHgrow(centerScreen, Priority.ALWAYS);
        centerScreen.getChildren().add(usernameText);
        centerScreen.getChildren().add(username);
        centerScreen.getChildren().add(passwordText);
        centerScreen.getChildren().add(password);
        centerScreen.getChildren().add(enterButton);
        centerScreen.setId("Password");
        root.setCenter(centerScreen);
        Scene scene = new Scene(root, 500, 300);
        primaryStage.setScene(scene);
        enterButton.setOnAction(value ->  {
            String user= username.getText();
            String pass= password.getText();
            username.setText("");
            password.setText("");
            Charset charset = Charset.forName("US-ASCII");
            Path file = Paths.get("details.txt");
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                writer.write("\n"+user+" "+pass);
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }

        });

        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);

    }
}