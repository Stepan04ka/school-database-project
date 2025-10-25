package com.project.ui.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(loadFXML("mainfile"), 640, 480);
        stage.setTitle("Приложение для изменения информации о компьютерах");
        stage.setResizable(true);

        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFMXL(String fxml) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlloader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}