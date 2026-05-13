module com.example.demod14 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;



    opens com.example.demod14 to javafx.fxml;
    exports com.example.demod14;
}