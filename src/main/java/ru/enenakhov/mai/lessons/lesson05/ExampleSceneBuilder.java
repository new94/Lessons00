package ru.enenakhov.mai.lessons.lesson05;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class ExampleSceneBuilder extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ExampleSceneBuilder.class.getResource("/example.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
    }
}
