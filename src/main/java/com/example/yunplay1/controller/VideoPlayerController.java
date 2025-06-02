package com.example.yunplay1.controller;

import com.example.yunplay1.Video;
import com.example.yunplay1.views.HomeView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class VideoPlayerController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnPlay;

    @FXML
    private Slider slider;

    @FXML
    private Button btnRewind;

    @FXML
    private Button btnSkip;

    @FXML
    private Label durasiLabel;

    @FXML
    private MediaView videoView;

    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        Video video = ShowDetailsController.selectedVideo;
        if (video != null) {
            File file = new File(video.getFileVideo());
            if (file.exists()) {
                Media media = new Media(file.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                videoView.setMediaPlayer(mediaPlayer);
                mediaPlayer.setAutoPlay(true);

                Platform.runLater(() -> {
                    Scene scene = videoView.getScene();
                    if (scene != null) {
                        videoView.fitWidthProperty().bind(scene.widthProperty());
                        videoView.fitHeightProperty().bind(scene.heightProperty().subtract(143));
                    }
                });

                mediaPlayer.currentTimeProperty().addListener(((obs, oldTime, newTime) -> {
                    slider.setValue(newTime.toSeconds());
                    durasiLabel.setText((int)slider.getValue() + " / " + (int)media.getDuration().toSeconds());
                }));
                slider.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
                    if (!isChanging) {
                        mediaPlayer.seek(Duration.seconds(slider.getValue()));
                    }
                });
                mediaPlayer.setOnReady(() -> {
                    Duration totalDurasi = media.getDuration();
                    slider.setMax(totalDurasi.toSeconds());
                    durasiLabel.setText(" / " + (int)media.getDuration().toSeconds());
                });
            } else {
                durasiLabel.setText("File video tidak ditemukan!");
            }
        }
    }

    @FXML
    private void onMousePressed() {
        mediaPlayer.seek(Duration.seconds(slider.getValue()));
    }

    @FXML
    private void onBtnPlayClick() {
        if (mediaPlayer == null) return;

        MediaPlayer.Status status = mediaPlayer.getStatus();

        if (status == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            btnPlay.setText("Play");
            btnPlay.setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
        } else {
            mediaPlayer.play();
            btnPlay.setText("Pause");
            btnPlay.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        }
    }

    @FXML
    private void onBtnBackClick() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            HomeView homeView = new HomeView();
            Stage homeStage = new Stage();
            homeView.start(homeStage);
            Stage currentPage = (Stage) btnBack.getScene().getWindow();
            currentPage.close();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "gagal kembali ke halaman beranda", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onBtnSkipClick() {
        if (mediaPlayer != null)
            mediaPlayer.seek(mediaPlayer.getCurrentTime().add(javafx.util.Duration.seconds(10)));
    }

    @FXML
    private void onBtnRewindClick() {
        if (mediaPlayer != null)
            mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(javafx.util.Duration.seconds(10)));
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
