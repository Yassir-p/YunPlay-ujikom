package com.example.yunplay1.controller;

import com.example.yunplay1.views.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static com.example.yunplay1.Session.clearSession;

public class HomeController {
    @FXML
    private Button btnLogout;

    @FXML
    private Button btnPlay;

    @FXML
    private void onBtnLogoutClick() {
        try {
            clearSession();
            LoginView loginView = new LoginView();
            Stage loginStage = new Stage();
            loginView.start(loginStage);
            Stage currentPage = (Stage) btnLogout.getScene().getWindow();
            currentPage.close();
            showAlert("Logout", "Anda berhasil logout", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBtnPlayClick() {

    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
