package com.example.yunplay1.controller;

import com.example.yunplay1.Session;
import com.example.yunplay1.views.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UploadController {
    @FXML
    private Button btnUpload;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnBrowse;

    @FXML
    private void onBtnUploadClick() {

    }

    @FXML
    private void onBtnLogoutClick() {
        try {
            Session.clearSession();
            LoginView loginView = new LoginView();
            Stage loginStage = new Stage();
            loginView.start(loginStage);
            Stage currentPage = (Stage) btnLogout.getScene().getWindow();
            currentPage.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBtnBrowseClick() {

    }
}
