package com.example.yunplay1;

public class Session {
    private static int id;
    private static String fullName;
    private static String username;
    private static String role;
    private static Video selectedVideo;

    public static void setUser(int userId, String name, String uName, String userRole) {
        id = userId;
        fullName = name;
        username = uName;
        role = userRole;
    }

    public static int getId() {
        return id;
    }

    public static String getFullName() {
        return fullName;
    }

    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }

    public static void clearSession() {
        id = 0;
        fullName = null;
        username = null;
        role = null;
    }

    public static void setSelectedVideo(Video video) {
        selectedVideo = video;
    }

    public static Video getSelectedVideo() {
        return selectedVideo;
    }
}