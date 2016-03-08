package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utilities.Log;

import java.net.URI;
import java.net.URL;

public class Main extends Application {

    private static final String THEME_BLACK = "/css/styles_black.css";
    private static final String THEME_LIGHT = "/css/styles_light.css";
    private static final String THEME_BLUE = "/css/styles_blue.css";
    Button button;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Paint Revolution");

        URL resource = getClass().getResource("/fxml/main.fxml");
        Log.i(resource.toString());
        Parent root = FXMLLoader.load(resource);
        // add styles to main.fxml
        root.getStylesheets().add(THEME_BLUE);

        // create a window
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
