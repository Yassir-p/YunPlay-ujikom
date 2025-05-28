package com.example.yunplay1;

public class Video {
    private int id;
    private String namaVideo;
    private String fileVideo;

    public Video(int id, String namaVideo, String fileVideo) {
        this.id = id;
        this.namaVideo = namaVideo;
        this.fileVideo = fileVideo;
    }

    public int getId() {
        return id;
    }

    public String getNamaVideo() {
        return namaVideo;
    }

    public String getFileVideo() {
        return fileVideo;
    }

    public void setNamaVideo(String namaVideo) {
        this.namaVideo = namaVideo;
    }

    public void setFileVideo(String fileVideo) {
        this.fileVideo = fileVideo;
    }
}
