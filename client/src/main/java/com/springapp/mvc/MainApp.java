package com.springapp.mvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String xmlFile = "/fxml/stores.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(xmlFile));
        Scene scene = new Scene(rootNode, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Choose store");
        primaryStage.show();
    }
}
