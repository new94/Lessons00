package ru.enenakhov.mai.lessons.lesson05;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Components
        Label myLabel = new Label("Hello World");
        Button okButton = new Button("OK");
        Button notOkButton = new Button("Not OK");

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = notOkButton.getText();
                notOkButton.setText(okButton.getText());
                okButton.setText(text);
            }
        });

        notOkButton.setOnAction(event -> {
            String text = okButton.getText();
            okButton.setText(notOkButton.getText());
            notOkButton.setText(text);
        });

        //Layout containers
        VBox verticalContainer = new VBox();
        verticalContainer.getChildren().addAll(myLabel,okButton, notOkButton);

        //Scenes
        Scene primaryScene = new Scene(verticalContainer);

        //Frame/Stage/Window
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
}
