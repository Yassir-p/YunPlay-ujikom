package com.example.yunplay1.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UploadView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage thirdStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UploadView.class.getResource("/com/example/yunplay1/addDataView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1260, 827);
        thirdStage.setTitle("Upload");
        thirdStage.setScene(scene);
        thirdStage.centerOnScreen();
        thirdStage.setResizable(false);
        thirdStage.show();
    }
}
