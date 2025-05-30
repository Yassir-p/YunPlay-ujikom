package com.example.yunplay1.controller;

import com.example.yunplay1.Koneksi;
import com.example.yunplay1.Video;
import com.example.yunplay1.views.LoginView;
import com.example.yunplay1.views.VideoPlayerView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.example.yunplay1.Session.clearSession;

public class HomeController {
    @FXML
    private Button btnLogout;

    @FXML
    private Button btnPlay;

    @FXML
    private ListView<Video> listView;

    @FXML
    public void initialize() {
        loadVideos();
    }

    private void loadVideos() {
        ObservableList<Video> videos = FXCollections.observableArrayList();

        try {
            Connection conn = Koneksi.getKonek();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM video");

            while (rs.next()) {
                int id = rs.getInt("id");
                String namaVideo = rs.getString("nama_video");
                String fileVideo = rs.getString("link_video");
                videos.add(new Video(id, namaVideo, fileVideo));
            }
            listView.setItems(videos);
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
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
    private void onBtnPlayClick() {
        Video selectedVideo = listView.getSelectionModel().getSelectedItem();
        if (selectedVideo == null) {
            showAlert("Peringatan", "Pilih salah satu video terlebih dahulu", Alert.AlertType.WARNING);
            return;
        }

        try {
            ShowDetailsController.selectedVideo = selectedVideo; // menyimpan video ke session
            VideoPlayerView videoPlayerView = new VideoPlayerView();
            Stage playerStage = new Stage();
            videoPlayerView.start(playerStage);
            Stage currentStage = (Stage) btnPlay.getScene().getWindow();
            currentStage.close();
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
}
