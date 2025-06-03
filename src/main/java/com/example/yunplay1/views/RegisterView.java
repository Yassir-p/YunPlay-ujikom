package com.example.yunplay1.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterView extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage secondStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterView.class.getResource("/com/example/yunplay1/registerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1260, 827);
        secondStage.setTitle("YunPlay");
        secondStage.centerOnScreen();
        secondStage.setScene(scene);
        secondStage.setResizable(false);
        secondStage.show();
    }
}
