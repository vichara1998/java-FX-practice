package com.example.demod14;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class PhoneShop {

    @FXML
    private TextField t1;

    @FXML
    private TextField t2;

    @FXML
    void cus(ActionEvent event) throws IOException{
        Parent historyView = FXMLLoader.load(getClass().getResource("phone-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(historyView);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void history(ActionEvent event) throws IOException {
        Parent historyView = FXMLLoader.load(getClass().getResource("phone4-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(historyView);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void order(ActionEvent event) throws IOException{
        Parent historyView = FXMLLoader.load(getClass().getResource("phone3-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(historyView);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void phone(ActionEvent event) throws IOException{
        Parent historyView = FXMLLoader.load(getClass().getResource("phone2-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(historyView);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
     void lgout(ActionEvent actionEvent) {
        System.exit(0);
    }
}