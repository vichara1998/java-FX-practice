package com.example.demod14;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.*;

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
        String id = txtSearch.getText();

        try {
            //Driver class loaded to the ram
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Create a connection with the given server and database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_phone",
                    "root", "9801");

            //Create Dynamic query
            PreparedStatement stm = connection.prepareStatement("Select * from phone where Phoneid=?");
            stm.setObject(1, id);

            ResultSet resultSet = stm.executeQuery();

            while (resultSet.next()) {
                txtBrand.setText(resultSet.getString(2));
                txtModel.setText(resultSet.getString(3));
                txtPrice.setText(String.valueOf(resultSet.getDouble(4)));
                txtQt.setText(String.valueOf(resultSet.getInt(5)));
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Duplicate Error");
                alert.setContentText("ID already exists! Please use a different ID.");
                alert.show();


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database Error");
                alert.setContentText("Something went wrong: " + e.getMessage());
                alert.show();

            }
        }
    }

    private void cleanInput() {

    }


    @FXML
    void update(ActionEvent event) {
        int id = Integer.parseInt(txtSearch.getText());
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
            PreparedStatement stm = connection.prepareStatement("Update  phone set Brand=?, Model=?,Price=?,Quantity=? where Phoneid=?");
            stm.setObject(1, brand);
            stm.setObject(2, model);
            stm.setObject(3, price);
            stm.setObject(4, qt);
            stm.setObject(5, id);

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


        txtBrand.clear();
        txtModel.clear();
        txtPrice.clear();
        txtQt.clear();
        txtSearch.clear();
    }
}
