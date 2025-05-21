package com.example.yunplay1.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("/com/example/yunplay1/loginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1260, 827);
        stage.setTitle("Login");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}