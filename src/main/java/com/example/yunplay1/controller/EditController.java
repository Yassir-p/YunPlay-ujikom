package com.example.yunplay1.controller;

import com.example.yunplay1.Koneksi;
import com.example.yunplay1.views.DashboardView;
import com.example.yunplay1.views.LoginView;
import com.example.yunplay1.views.ShowDetailsView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.io.File;
import java.util.Arrays;
import java.util.List;

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
    private static final List<String> VALIDASI_VIDEO = Arrays.asList("mp4", "avi", "mov", "mkv", "webm", "wmv");

    @FXML
    public void initialize() {
        if (ShowDetailsController.selectedVideo != null) {
            txtNamaVideo.setText(ShowDetailsController.selectedVideo.getNamaVideo());
            previewLink.setText(ShowDetailsController.selectedVideo.getFileVideo());
            path = ShowDetailsController.selectedVideo.getFileVideo();
        } else {
            showAlert("Error", "Data video tidak tersedia", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onBtnBrowseClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih Video");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mov", "*.mkv", "*.webm", "*.wmv")
        );

        File pilihFile = fileChooser.showOpenDialog(null);
        if (pilihFile != null) {
            String ext = getFileExtension(pilihFile.getName());
            if (!VALIDASI_VIDEO.contains(ext.toLowerCase())) {
                showAlert("Error", "Hanya format file mp4, avi, mov, mkv, webm dan wmv yang diperbolehkan", Alert.AlertType.ERROR);
                return;
            }
            path = pilihFile.getAbsolutePath();
            previewLink.setText(pilihFile.getName());
        }
    }

    @FXML
    private void onBtnUpdateClick() {
        if (ShowDetailsController.selectedVideo == null) {
            showAlert("Error", "Data video tidak ditemukan", Alert.AlertType.ERROR);
            return;
        }

        String namaBaru = txtNamaVideo.getText().trim();
        if (namaBaru.isEmpty()) {
            showAlert("Error", "Silahkan isi nama video terlebih dahulu", Alert.AlertType.ERROR);
            return;
        }

        if (path == null || path.isEmpty()) {
            showAlert("Error", "Silakan pilih file video terlebih dahulu", Alert.AlertType.ERROR);
            return;
        }

        File fileBaru = new File(path);
        try {
            Connection conn = Koneksi.getKonek();
            String query = "UPDATE video SET nama_video = ?, link_video = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, namaBaru);
            ps.setString(2, fileBaru.getAbsolutePath());
            ps.setInt(3, ShowDetailsController.selectedVideo.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                showAlert("Sukses", "Video berhasil diperbarui", Alert.AlertType.INFORMATION);
                ShowDetailsView showDetailsView = new ShowDetailsView();
                Stage detailsStage = new Stage();
                showDetailsView.start(detailsStage);
                Stage currentPage = (Stage) btnUpdate.getScene().getWindow();
                currentPage.close();
            } else {
                showAlert("Gagal", "Gagal memperbarui data", Alert.AlertType.ERROR);
            }

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onBtnLogoutClick() {
        try {
            clearSession();
            showAlert("Logout", "Anda berhasil logout", Alert.AlertType.INFORMATION);
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

    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex > 0 && dotIndex < filename.length() - 1) ? filename.substring(dotIndex + 1) : "";
    }
}