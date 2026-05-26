package com.example.demod14;

import com.example.demod14.dto.PhoneDto;
import com.example.demod14.model.PhoneModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class UpdateView {

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQt;

    @FXML
    private TextField txtSearch;

    @FXML
    void cancle(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void search(ActionEvent event) {

        try {
            int id = Integer.parseInt(txtSearch.getText());

            PhoneDto dto = PhoneModel.searchPhone(String.valueOf(id));

            if (dto != null) {
                txtBrand.setText(dto.getBrand());
                txtModel.setText(dto.getModel());
                txtPrice.setText(String.valueOf(dto.getPrice()));
                txtQt.setText(String.valueOf(dto.getQnt()));
            } else {
                new Alert(Alert.AlertType.ERROR, "No record found!").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID!").show();
        }
    }

    @FXML
    void update(ActionEvent event) {

        try {
            int id = Integer.parseInt(txtSearch.getText());
            String brand = txtBrand.getText();
            String model = txtModel.getText();
            double price = Double.parseDouble(txtPrice.getText());
            int qt = Integer.parseInt(txtQt.getText());

            PhoneDto dto = new PhoneDto(id, brand, model, price, qt);

            int result = PhoneModel.updatePhone(dto);

            if (result > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
                cleanInput();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update Failed").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Invalid input!").show();
        }
    }

    private void cleanInput() {
        txtSearch.clear();
        txtBrand.clear();
        txtModel.clear();
        txtPrice.clear();
        txtQt.clear();
    }
}