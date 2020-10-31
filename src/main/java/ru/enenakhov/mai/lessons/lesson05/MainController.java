package ru.enenakhov.mai.lessons.lesson05;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;

import java.util.Random;

public class MainController {

    @FXML
    Button myButton;

    @FXML
    AreaChart<String, Integer> chart;

    @FXML
    WebView webBrowser;

    private final XYChart.Series<String, Integer> series = new XYChart.Series<>();

    @FXML
    private void onButtonAction() {
        myButton.setText(new StringBuilder(myButton.getText()).reverse().toString());
    }

    @FXML
    private void onMouseClick() {
        Random randomX = new Random(System.currentTimeMillis());
        Random randomY = new Random(System.currentTimeMillis());
        String x = Integer.toString(randomX.nextInt() / 10000);
        int y = randomY.nextInt();
        series.getData().add(new XYChart.Data<>(x, y / 10000));
        chart.getData().add(series);
    }

    @FXML
    private void getWebPage() {
        webBrowser.getEngine().load("http://yandex.ru");
    }

    @FXML
    private void onMouseMoved() {
        myButton.setText("Mouse Moved");
    }
}
