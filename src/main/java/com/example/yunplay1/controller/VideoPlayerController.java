package com.example.yunplay1.controller;

import com.example.yunplay1.Video;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class VideoPlayerController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnFullscreen;

    @FXML
    private Button btnPause;

    @FXML
    private Button btnPlay;

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

                // Update durasi label saat tersedia
                mediaPlayer.setOnReady(() -> {
                    double totalSeconds = mediaPlayer.getTotalDuration().toSeconds();
                    durasiLabel.setText(String.format("Duration: %.0f seconds", totalSeconds));
                });
            } else {
                durasiLabel.setText("File video tidak ditemukan");
            }
        }
    }

    @FXML
    private void onBtnPlayClick() {
        if (mediaPlayer != null) mediaPlayer.play();
    }

    @FXML
    private void onBtnPauseClick() {
        if (mediaPlayer != null) mediaPlayer.pause();
    }

    @FXML
    private void onBtnBackClick() {

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

    @FXML
    private void onBtnFullscreenClick() {

    }
}
