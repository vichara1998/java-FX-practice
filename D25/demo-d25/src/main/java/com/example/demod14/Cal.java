package com.example.demod14;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Cal {

    @FXML
    private TextField epf;

    @FXML
    private TextField etf;

    @FXML
    private TextField fee;

    @FXML
    private TextField sal;

    @FXML
    private AnchorPane root1;

    @FXML
    private TextArea txt;






    @FXML
    public void back(javafx.event.ActionEvent actionEvent) throws IOException{
        Stage stage = (Stage) this.root1.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setScene(scene);
    }
    @FXML
    public void backW(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) this.root1.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setScene(scene);
    }
}