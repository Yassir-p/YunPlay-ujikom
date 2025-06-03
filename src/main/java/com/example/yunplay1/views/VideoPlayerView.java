package com.example.yunplay1.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VideoPlayerView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VideoPlayerView.class.getResource("/com/example/yunplay1/videoPlayerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1260, 827);
        stage.setScene(scene);
        stage.setTitle("YunPlay");
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }
}
