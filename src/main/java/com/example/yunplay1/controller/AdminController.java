package com.example.yunplay1.controller;

import com.example.yunplay1.views.DashboardView;
import com.example.yunplay1.views.RegisterView;
import com.example.yunplay1.views.UploadView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminController {
    @FXML
    private Button btnAddData;

    @FXML
    private Button btnShowData;

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
            DashboardView dashboardView = new DashboardView();
            Stage DashboardStage = new Stage();
            dashboardView.start(DashboardStage);
            Stage currentPage = (Stage) btnShowData.getScene().getWindow();
            currentPage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
