package com.example.demod14;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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


        try {
            //Driver class loaded to the ram
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Create a connection with the given server and database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_phone", "root", "9801");

            //Create Dynamic query
            PreparedStatement stm = connection.prepareStatement("Insert Into Phone (Brand,Model,Price,Quantity) values(?,?,?,?)");
            stm.setObject(1, brand);
            stm.setObject(2, model);
            stm.setObject(3, price);
            stm.setObject(4, qt);
            int i = stm.executeUpdate();
            if (i > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert!!");
                alert.setContentText("Data added Successfully!");
                alert.show();
                cleanInput();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database Error");
                alert.setContentText("Something went wrong ");
                alert.show();
                cleanInput();
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Duplicate Error");
                alert.setContentText("ID already exists! Please use a different ID.");
                alert.show();
                cleanInput();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database Error");
                alert.setContentText("Something went wrong: " + e.getMessage());
                alert.show();
                cleanInput();
            }
        }
    }
    public void cleanInput(){

        txtBrand.clear();
        txtModel.clear();
        txtPrice.clear();
        txtQt.clear();
    }
}


