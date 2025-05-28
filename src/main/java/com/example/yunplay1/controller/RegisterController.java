package com.example.yunplay1.controller;

import com.example.yunplay1.Koneksi;
import com.example.yunplay1.views.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterController {
    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPass;

    @FXML
    private void onBtnLoginClick() {
        try {
            LoginView loginView = new LoginView();
            Stage loginStage = new Stage();
            loginView.start(loginStage);
            loginStage.show();
            Stage currentPage = (Stage) btnSignIn.getScene().getWindow();
            currentPage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBtnRegisClick() {
        String fullName = txtName.getText().trim();
        String username = txtUser.getText().trim();
        String password = txtPass.getText().trim();
        if (fullName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Field tidak boleh kosong", Alert.AlertType.ERROR);
            return;
        }
        try {
            Connection conn = Koneksi.getKonek();
            String query = "INSERT INTO users (full_name, username, password) VALUES (?, ?, ?)";
            PreparedStatement up = conn.prepareStatement(query);
            up.setString(1, fullName);
            up.setString(2, username);
            up.setString(3, password);
            int RowsAffected = up.executeUpdate();
            if (RowsAffected > 0) {
                showAlert("Sukses", "Berhasil Sign Up " + fullName, Alert.AlertType.INFORMATION);
                clearField();
            } else {
                showAlert("ERROR","Gagal Sign Up", Alert.AlertType.ERROR);
            }
            up.close();
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

    private void clearField() {
        txtName.clear();
        txtUser.clear();
        txtPass.clear();
    }
}
