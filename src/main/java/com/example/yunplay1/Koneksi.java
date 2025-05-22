package com.example.yunplay1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Connection conn;
    public static Connection getKonek() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/yunplay";
                String user = "root";
                String pass = "";
                conn = DriverManager.getConnection(url,user,pass);
                System.out.println("Berhasil koneksi");
            } catch (SQLException ex) {
                System.err.println("Gagal konek"+ ex.getMessage());
            }
        }
        return conn;
    }
    public static void main(String[] args) {
        Koneksi k = new Koneksi();
        k.getKonek();
    }
}