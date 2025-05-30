package com.example.yunplay1.controller;

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

    @FXML
    public void initialize() {
        if (ShowDetailsController.selectedVideo != null) {
            String filePath = ShowDetailsController.selectedVideo.getFileVideo();
            Media media = new Media(new File(filePath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            videoView.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
        } else {
            System.out.println("Tidak ada video dipilih.");
        }
    }

    @FXML
    private void onBtnPlayClick() {

    }

    @FXML
    private void onBtnPauseClick() {

    }

    @FXML
    private void onBtnBackClick() {

    }

    @FXML
    private void onBtnSkipClick() {

    }

    @FXML
    private void onBtnRewindClick() {

    }

    @FXML
    private void onBtnFullscreenClick() {

    }
}
