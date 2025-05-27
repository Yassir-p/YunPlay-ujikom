package com.example.yunplay1.controller;

import com.example.yunplay1.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBtnBrowseClick() {

    }
}
