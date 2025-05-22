module com.example.yunplay1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;
    requires java.sql;

    opens com.example.yunplay1 to javafx.fxml;
    exports com.example.yunplay1;
    exports com.example.yunplay1.controller;
    opens com.example.yunplay1.controller to javafx.fxml;
    exports com.example.yunplay1.views;
    opens com.example.yunplay1.views to javafx.fxml;
}