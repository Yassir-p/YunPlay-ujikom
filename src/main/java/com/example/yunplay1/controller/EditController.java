package com.example.yunplay1.controller;

import com.example.yunplay1.Koneksi;
import com.example.yunplay1.views.DashboardView;
import com.example.yunplay1.views.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;
import java.io.File;

import static com.example.yunplay1.Session.clearSession;

public class EditController {
    String path;

    @FXML
    private Button btnBrowse;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtNamaVideo;

    @FXML
    private Text previewLink;

    @FXML
    private javafx.scene.image.ImageView btnBack;

    @FXML
    public void initialize() {
        if (ShowDetailsController.selectedVideo != null) {
            txtNamaVideo.setText(ShowDetailsController.selectedVideo.getNamaVideo());
            previewLink.setText(ShowDetailsController.selectedVideo.getFileVideo());
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

    @FXML
    private void onBtnUpdateClick() {
        String namaBaru = txtNamaVideo.getText();
        String fileBaru = previewLink.getText();

        if (namaBaru.isEmpty() || fileBaru.isEmpty()) {
            showAlert("Error", "Nama dan file tidak boleh kosong", Alert.AlertType.ERROR);
            return;
        }

        if (path == null || path.isEmpty()) {
            showAlert("Error", "Silakan pilih file video terlebih dahulu", Alert.AlertType.ERROR);
            return;
        }

        try {
            Connection conn = Koneksi.getKonek();
            String query = "UPDATE video SET nama_video = ?, link_video = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, namaBaru);
            ps.setString(2, fileBaru);
            ps.setInt(3, ShowDetailsController.selectedVideo.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                showAlert("Sukses", "Data berhasil diupdate", Alert.AlertType.INFORMATION);
                try {
                    DashboardView dashboardView = new DashboardView();
                    Stage dasboardStage = new Stage();
                    dashboardView.start(dasboardStage);
                    Stage currentPage = (Stage) btnUpdate.getScene().getWindow();
                    currentPage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error", "gagal kembali ke dashboard", Alert.AlertType.ERROR);
                }
            } else {
                showAlert("Gagal", "Data gagal diupdate", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
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
