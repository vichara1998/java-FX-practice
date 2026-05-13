package com.example.demod14;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadController implements Initializable {
    @FXML
    private TableView<PhoneTbl> table;

    @FXML
    void loadAll(ActionEvent event) {

    }

    public ArrayList<PhoneTbl> loadData() {

        //Enter data


        try {
            //Driver class loaded to the ram
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Create a connection with the given server and database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_phone", "root", "9801");

            //Create Dynamic query
            PreparedStatement stm = connection.prepareStatement("select * from phone");


            ResultSet resultSet = stm.executeQuery();
            //Create an Array
            ArrayList<PhoneTbl> phoneT = new ArrayList<>();

            while (resultSet.next()) {
                phoneT.add(new PhoneTbl(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDouble(4),
                        resultSet.getInt(5)));

            }
            return phoneT;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("load");
        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("pid"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qnt"));

        ArrayList<PhoneTbl> phoneT = loadData();
        table.setItems(FXCollections.observableArrayList(phoneT));

    }
}


