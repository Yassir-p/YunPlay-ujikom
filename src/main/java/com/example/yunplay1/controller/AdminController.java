package com.example.yunplay1.controller;

import com.example.yunplay1.views.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static com.example.yunplay1.Session.clearSession;

public class AdminController {
    @FXML
    private Button btnAddData;

    @FXML
    private Button btnShowData;

    @FXML
    private Button btnLogout;

    @FXML
    private void onBtnAddClick() {
        try {
            UploadView uploadView = new UploadView();
            Stage uploadStage = new Stage();
            uploadView.start(uploadStage);
            Stage currentPage = (Stage) btnAddData.getScene().getWindow();
            currentPage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBtnShowDataClick() {
        try {
            ShowDetailsView showDetailsView = new ShowDetailsView();
            Stage showDetails = new Stage();
            showDetailsView.start(showDetails);
            Stage currentPage = (Stage) btnShowData.getScene().getWindow();
            currentPage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
