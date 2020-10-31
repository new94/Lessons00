package ru.enenakhov.mai.lessons.lesson05;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Components
        Label helloWorldLabel = new Label("Hello World!");
        Button okButton = new Button("OK!");

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                okButton.setText("No ok!");
            }
        });

        Button noOkButton = new Button("No ok!");
        noOkButton.setOnAction((event) -> {
            String text = okButton.getText();
            okButton.setText(noOkButton.getText());
            noOkButton.setText(text);
        });

        //Containers
        VBox container = new VBox();
        container.getChildren().addAll(helloWorldLabel, okButton, noOkButton);

        //Scenes
        Scene primaryScene = new Scene(container);
        primaryStage.setScene(primaryScene);

        primaryStage.show();
    }
}
