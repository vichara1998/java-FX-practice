package com.example.demod14;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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


