package com.example.demod14.model;

import com.example.demod14.PhoneTbl;
import com.example.demod14.dto.PhoneDto;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

public class PhoneModel {
    public static void savePhone(PhoneDto phoneDto) {
        try {
            //Driver class loaded to the ram
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Create a connection with the given server and database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_phone", "root", "9801");

            //Create Dynamic query
            PreparedStatement stm = connection.prepareStatement("Insert Into Phone (Brand,Model,Price,Quantity) values(?,?,?,?)");
            stm.setObject(1, phoneDto.getBrand());
            stm.setObject(2, phoneDto.getModel());
            stm.setObject(3, phoneDto.getPrice());
            stm.setObject(4, phoneDto.getQnt());
            int i = stm.executeUpdate();
            if (i > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert!!");
                alert.setContentText("Data added Successfully!");
                alert.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database Error");
                alert.setContentText("Something went wrong ");
                alert.show();

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

    public static int updatePhone(PhoneDto phoneDto) {

        int result = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javafx_phone",
                    "root",
                    "9801"
            );

            PreparedStatement stm = connection.prepareStatement(
                    "UPDATE phone SET Brand=?, Model=?, Price=?, Quantity=? WHERE Phoneid=?"
            );

            stm.setObject(1, phoneDto.getBrand());
            stm.setObject(2, phoneDto.getModel());
            stm.setObject(3, phoneDto.getPrice());
            stm.setObject(4, phoneDto.getQnt());
            stm.setObject(5, phoneDto.getPid());

            result = stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void deletePhone() {
    }

    public static PhoneDto searchPhone(int pid) {

        PhoneDto phoneDto = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javafx_phone",
                    "root",
                    "9801"
            );

            PreparedStatement stm = connection.prepareStatement(
                    "SELECT * FROM phone WHERE Phoneid=?"
            );

            stm.setObject(1, pid);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                phoneDto = new PhoneDto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return phoneDto;
    }

    public static ArrayList<PhoneDto> getAllPhone() {
        try {
            //Driver class loaded to the ram
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Create a connection with the given server and database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_phone", "root", "9801");

            //Create Dynamic query
            PreparedStatement stm = connection.prepareStatement("select * from phone");


            ResultSet resultSet = stm.executeQuery();
            //Create an Array
            ArrayList<PhoneDto> phoneDtos = new ArrayList<>();

            while (resultSet.next()) {
                phoneDtos.add(new PhoneDto(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDouble(4),
                        resultSet.getInt(5)));
            }

            return phoneDtos;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }



    }
}

