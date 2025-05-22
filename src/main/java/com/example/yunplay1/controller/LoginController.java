package com.example.yunplay1.controller;


import com.example.yunplay1.views.RegisterView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPass;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private void onBtnLoginClick() {
        String username = txtUser.getText();
        String password = txtPass.getText();

    }

    @FXML
    private void onBtnRegisClick() {
        try {
            RegisterView registerView = new RegisterView();
            Stage registerStage = new Stage();
            Stage currentPage = (Stage) btnSignUp.getScene().getWindow();
            currentPage.close();
            registerView.start(registerStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
