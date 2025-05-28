package com.example.yunplay1.controller;

import com.example.yunplay1.views.DashboardView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EditController {
    @FXML
    private Button btnBrowse;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnUpdate;

    @FXML
    private javafx.scene.image.ImageView btnBack;

    @FXML
    private void onBtnBrowseClick() {

    }

    @FXML
    private void onBtnUpdateClick() {

    }

    @FXML
    private void onBtnLogoutClick() {

    }

    @FXML
    private void onBtnBackClick() {
        try {
            DashboardView dashboardView = new DashboardView();
            Stage dasboardStage = new Stage();
            dashboardView.start(dasboardStage);
            Stage currentPage = (Stage) btnBack.getScene().getWindow();
            currentPage.close();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "gagal kembali ke dashboard", Alert.AlertType.ERROR);
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
