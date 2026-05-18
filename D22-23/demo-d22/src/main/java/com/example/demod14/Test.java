package com.example.demod14;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Test {
    @FXML
    private TextField t1;

    @FXML
    private TextField t2;

    @FXML
    void cancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void login(ActionEvent event) {

    }
}
