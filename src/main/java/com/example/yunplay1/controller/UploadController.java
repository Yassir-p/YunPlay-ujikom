package com.example.yunplay1.controller;

import com.example.yunplay1.Koneksi;
import com.example.yunplay1.Session;
import com.example.yunplay1.views.DashboardView;
import com.example.yunplay1.views.LoginView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.yunplay1.Session.clearSession;

public class UploadController {

    String path;

    @FXML
    private Button btnUpload;

    @FXML
    private Button btnLogout;

    @FXML
    private Text previewLink;

    @FXML
    private Button btnBrowse;

    @FXML
    private void onBtnUploadClick() {
        if (path == null || path.isEmpty()) {
            showAlert("Error", "Silakan pilih file video terlebih dahulu", Alert.AlertType.ERROR);
            return;
        }

        File videoFile = new File(path);
        try (FileInputStream fis = new FileInputStream(videoFile)) {
            Connection conn = Koneksi.getKonek(); // Panggil koneksi dari Koneksi.java
            String sql = "INSERT INTO video (nama_video, link_video) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, videoFile.getName());
            ps.setBinaryStream(2, fis, (int) videoFile.length());
            ps.executeUpdate();

            showAlert("Sukses", "Video berhasil diupload ke database", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Gagal upload video: " + e.getMessage(), Alert.AlertType.ERROR);
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

    @FXML
    private void onBtnBrowseClick() {
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = jf.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File f = jf.getSelectedFile();
            path = f.getAbsolutePath();
            previewLink.setText(f.getName()); // menampilkan nama video
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
