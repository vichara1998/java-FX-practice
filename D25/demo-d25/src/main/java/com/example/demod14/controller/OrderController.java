package com.example.demod14.controller;

import com.example.demod14.dto.OrderDetailDto;
import com.example.demod14.dto.PhoneDto;
import com.example.demod14.model.PhoneModel;
import com.example.demod14.tm.OrderDetailTM;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    private TableView<OrderDetailTM> table;
    @FXML
    private TextArea lblTotal;
    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQnt;

    @FXML
    private TextField txtQntOnHand;

    ArrayList<OrderDetailTM> orderDetailTMS;

    private double subTotal =0.0;

    @FXML
    void addToCart(ActionEvent event) {
        double total = Double.parseDouble(txtPrice.getText()) * Integer.parseInt(txtQnt.getText());
        OrderDetailTM orderDetailTM = new OrderDetailTM(Integer.parseInt(txtId.getText()), txtBrand.getText(),
                txtModel.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtQnt.getText()),
                total);

        orderDetailTMS.add(orderDetailTM);
        table.setItems(FXCollections.observableArrayList(orderDetailTMS));

        subTotal += total;
        lblTotal.setText(String.valueOf(subTotal));

    }

    @FXML
    void order(ActionEvent event) {
        DateFormat dateFormat =new SimpleDateFormat("yyyy/MM/dd");
        Date date =new Date();
       String orderDate=dateFormat.format(date);

       //convert TM list to DTO list
        ArrayList<OrderDetailDto> list = new ArrayList<>();
        for(OrderDetailTM tm : orderDetailTMS){
            list.add(new OrderDetailDto(tm.getId(),tm.getBrand(),tm.getModel(),tm.getPrice(),tm.getQnt(),tm.getTotal()));
        }
        try {
            PhoneModel.makeOrder(String.valueOf(txtOrderId),subTotal,orderDate,list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void searchById(ActionEvent event) throws SQLException, ClassNotFoundException {
        String pid = txtId.getText();
        PhoneDto phoneDto = PhoneModel.searchPhone(pid);
        txtBrand.setText(phoneDto.getBrand());
        txtModel.setText(phoneDto.getModel());
        txtPrice.setText(String.valueOf(phoneDto.getPrice()));
        txtQntOnHand.setText(String.valueOf(phoneDto.getQnt()));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qnt"));
        table.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));

        orderDetailTMS =new ArrayList<>();
    }


}
