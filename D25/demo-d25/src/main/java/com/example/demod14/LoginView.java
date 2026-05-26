package com.example.demod14;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView {
    @FXML
    private TextField sal;

    @FXML
    private TextField etf;

    @FXML
    private TextField epf;

    @FXML
    private TextField fee;
    @FXML
    private TextArea txt;
    @FXML
    private AnchorPane root;
    @FXML
    void nextW(ActionEvent event) throws IOException {

            Stage stage = (Stage) this.root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("cal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);

    }

    @FXML
    void login(ActionEvent event) {

            double sa = Double.parseDouble(sal.getText());
            double et = Double.parseDouble(etf.getText());
            double ep = Double.parseDouble(epf.getText());
            double clubFee = Double.parseDouble(fee.getText());

            double epfAmount = sa * ep / 100;
            double etfAmount = sa * et / 100;

            double finalSal = sa - (epfAmount + etfAmount + clubFee);

            txt.setText(String.valueOf(finalSal));
        }
    }


