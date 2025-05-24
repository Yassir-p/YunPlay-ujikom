package com.example.yunplay1.controller;


import com.example.yunplay1.Koneksi;
import com.example.yunplay1.Session;
import com.example.yunplay1.views.DashboardView;
import com.example.yunplay1.views.HomeView;
import com.example.yunplay1.views.RegisterView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPass;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnSignUp;

    @FXML
    private void onBtnLoginClick() {
        String username = txtUser.getText().trim();
        String password = txtPass.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Username dan password tidak boleh kosong", Alert.AlertType.ERROR);
            return;
        }

        try {
            Connection conn = Koneksi.getKonek();
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String uName = rs.getString("username");
                String role = rs.getString("role");

                Session.setUser(id, fullName, uName, role);

                showAlert("Sukses", "Login berhasil, selamat datang " + username, Alert.AlertType.INFORMATION);
                Stage newStage = new Stage();
                if (role.equalsIgnoreCase("admin")) {
                    DashboardView dashboardView = new DashboardView();
                    dashboardView.start(newStage);
                } else {
                    HomeView homeView = new HomeView();
                    homeView.start(newStage);
                }

                Stage currentStage = (Stage) btnSignIn.getScene().getWindow();
                currentStage.close();

            } else {
                showAlert("Error", "Username atau password salah", Alert.AlertType.ERROR);
            }

            rs.close();
            stmt.close();
        } catch (Exception ex) {
            showAlert("Database Error", "Koneksi ke database gagal: " + ex.getMessage(), Alert.AlertType.ERROR);
            ex.printStackTrace();
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }


    @FXML
    private void onBtnRegisClick() {
        try {
            RegisterView registerView = new RegisterView();
            Stage registerStage = new Stage();
            registerView.start(registerStage);
            Stage currentPage = (Stage) btnSignUp.getScene().getWindow();
            currentPage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
