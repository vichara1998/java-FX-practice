package com.example.demod14.model;

import com.example.demod14.db.DBConnection;
import com.example.demod14.dto.OrderDetailDto;
import com.example.demod14.dto.PhoneDto;
import javafx.scene.control.Alert;

import java.sql.Connection;

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

    public static int updatePhone(PhoneDto phoneDto) throws SQLException, ClassNotFoundException {

        int result = 0;

        Connection connection = DBConnection.getDBConnection().getConnection();

        PreparedStatement stm = connection.prepareStatement(
                "UPDATE phone SET Brand=?, Model=?, Price=?, Quantity=? WHERE Phoneid=?"
        );

        stm.setObject(1, phoneDto.getBrand());
        stm.setObject(2, phoneDto.getModel());
        stm.setObject(3, phoneDto.getPrice());
        stm.setObject(4, phoneDto.getQnt());
        stm.setObject(5, phoneDto.getPid());

        result = stm.executeUpdate();

        return result;
    }

    public static void deletePhone(int pid) throws SQLException, ClassNotFoundException {

        Connection conn = DBConnection.getDBConnection().getConnection();
        System.out.println("Connected successfully!");
    }

    public static PhoneDto searchPhone(String pid) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        // Assign to instance variable (FIXED)
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/javafx_phone",
                "root",
                "9801"
        );
        System.out.println("Connected successfully!");
        PreparedStatement stm = connection.prepareStatement(
                "SELECT * FROM phone WHERE Phoneid=?"
        );

        stm.setObject(1, pid);

        ResultSet resultSet = stm.executeQuery();

        PhoneDto phoneDto = new PhoneDto();
        while (resultSet.next()) {
            phoneDto.setBrand(resultSet.getString(2));
            phoneDto.setModel(resultSet.getString(3));
            phoneDto.setPrice((resultSet.getDouble(4)));
            phoneDto.setQnt(resultSet.getInt(5));
        }


        return phoneDto;

    }

    public static ArrayList<PhoneDto> getAllPhone() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDBConnection().getConnection();
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


    }

    public static boolean makeOrder(String orderId, double subTotal, String orderDate, ArrayList<OrderDetailDto> orderDetailDtos) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDBConnection().getConnection();
        connection.setAutoCommit(false);

        PreparedStatement stm = connection.prepareStatement("Insert Into orders (oid,order_date,amount) values(?,?,?)");
        stm.setObject(1, orderId);
        stm.setObject(2, orderDate);
        stm.setObject(3, subTotal);

        int i = stm.executeUpdate();

        if (i > 0) {
            for (OrderDetailDto dto : orderDetailDtos) {
                PreparedStatement stm2 = connection.prepareStatement("Insert Into order_details (order_id,phone_id,qty,amount) values(?,?,?,?)");
                stm2.setObject(1, orderId);
                stm2.setObject(2, dto.getId());
                stm2.setObject(3, dto.getQnt());
                stm2.setObject(4, dto.getTotal());

                int isAddedOrderDetails = stm2.executeUpdate();

                if (isAddedOrderDetails > 0) {
                    continue;
                } else {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.rollback();
            connection.setAutoCommit(true);
            return true;
        } else {
            connection.rollback();
            connection.setAutoCommit(true);
            return true;
        }


    }
}

