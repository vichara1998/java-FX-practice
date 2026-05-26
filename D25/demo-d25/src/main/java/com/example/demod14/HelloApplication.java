package com.example.demod14;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PhoneShop.class.getResource("order-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Table !");
        stage.setScene(scene);
        stage.show();
    }

}
