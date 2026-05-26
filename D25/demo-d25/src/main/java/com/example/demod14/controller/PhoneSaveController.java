package com.example.demod14.controller;

import com.example.demod14.dto.PhoneDto;
import com.example.demod14.model.PhoneModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;



public class PhoneSaveController {

    @FXML
    private TextField txtBrand;


    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;


    @FXML
    private TextField txtQt;

    @FXML
    void cancle(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void save(ActionEvent event) {
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qt = Integer.parseInt(txtQt.getText());


        //Save data

        PhoneModel.savePhone(new PhoneDto(brand,model,price,qt));

    }
    public void cleanInput(){

        txtBrand.clear();
        txtModel.clear();
        txtPrice.clear();
        txtQt.clear();
    }
}


