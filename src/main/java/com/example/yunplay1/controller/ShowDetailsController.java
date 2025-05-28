package com.example.yunplay1.controller;

import com.example.yunplay1.Session;
import com.example.yunplay1.Video;
import com.example.yunplay1.views.DashboardView;
import com.example.yunplay1.views.EditView;
import com.example.yunplay1.views.LoginView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import com.example.yunplay1.Koneksi;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ShowDetailsController implements Initializable {
    @FXML
    private Button btnLogout;

    @FXML
    private Button btnEdit;

    @FXML
    private javafx.scene.image.ImageView btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    public static Video selectedVideo;

    @FXML
    private TableView<Video> tableVideo;

    @FXML
    private TableColumn<Video, Integer> idColumn;

    @FXML
    private TableColumn<Video, String> namaColumn;

    @FXML
    private TableColumn<Video, String> fileColumn;

    private ObservableList<Video> videoList = FXCollections.observableArrayList();

    @FXML
    private void onBtnEditClick() {
        selectedVideo = tableVideo.getSelectionModel().getSelectedItem();
        if (selectedVideo == null) {
            showAlert("Peringatan", "Pilih data terlebih dahulu!", Alert.AlertType.WARNING);
            return;
        }

        try {
            EditView editView = new EditView();
            Stage stage = new Stage();
            editView.start(stage);
            stage.show();
            Stage current = (Stage) btnEdit.getScene().getWindow();
            current.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBtnDeleteClick() {
        Video selected = tableVideo.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Peringatan", "Pilih data yang ingin dihapus!", Alert.AlertType.WARNING);
            return;
        }

        try {
            Connection conn = Koneksi.getKonek();
            String query = "DELETE FROM video WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, selected.getId());
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                showAlert("Sukses", "Data berhasil dihapus.", Alert.AlertType.INFORMATION);
                videoList.remove(selected);
            } else {
                showAlert("Gagal", "Data gagal dihapus.", Alert.AlertType.ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Terjadi kesalahan saat menghapus data.", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        namaColumn.setCellValueFactory(new PropertyValueFactory<>("namaVideo"));
        fileColumn.setCellValueFactory(new PropertyValueFactory<>("fileVideo"));
        loadData();
    }

    private void loadData() {
        videoList.clear();
        try {
            Connection conn = Koneksi.getKonek();
            String query = "SELECT * FROM video";
            PreparedStatement ps = conn.prepareStatement(query);
            var rs = ps.executeQuery();
            while (rs.next()) {
                videoList.add(new Video(
                        rs.getInt("id"),
                        rs.getString("nama_video"),
                        rs.getString("link_video")
                ));
            }
            tableVideo.setItems(videoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBtnLogoutClick() {
        try {
            Session.clearSession();
            LoginView loginView = new LoginView();
            Stage loginStage = new Stage();
            loginView.start(loginStage);
            loginStage.show();
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
            dasboardStage.show();
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
