package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Appsu extends Application {


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Appsu.class.getResource("signup.fxml"));

        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane, 500, 650);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
