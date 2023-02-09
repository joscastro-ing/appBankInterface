package com.group1.controller;

import com.group1.HelloApplication;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class ChangePage {
    public static void loadStage(String url, Event event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(url));
            Scene scene = new Scene(fxmlLoader.load());
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    Platform.exit();
                }
            });
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
