package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utilities.Log;
import utilities.Themes;

import java.net.URI;
import java.net.URL;

public class Main extends Application {

    public static String THEME = "";

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Paint Revolution");

        URL resource = getClass().getResource("/fxml/main.fxml");
        Log.i(resource.toString());
        Parent root = FXMLLoader.load(resource);
        // add styles to main.fxml
        root.getStylesheets().add(THEME);

        // create a window
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        THEME = Themes.THEME_LIGHT;
        if(args.length > 0){
            Log.i("ARGS: " + args[0]);
            THEME = args[0];
        }
        launch(args);
    }
}
