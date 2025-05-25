package com.example.yunplay1.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowDetailsView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage thirdStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShowDetailsView.class.getResource("/com/example/yunplay1/showDataView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1260, 827);
        thirdStage.show();
        thirdStage.setTitle("Edit");
        thirdStage.setScene(scene);
        thirdStage.centerOnScreen();
        thirdStage.setScene(scene);
        thirdStage.setResizable(false);
    }
}
