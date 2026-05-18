package com.example.demod14;

import com.example.demod14.dto.PhoneDto;
import com.example.demod14.model.PhoneModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadController implements Initializable {

    @FXML
    private TableView<PhoneTbl> table;

    @FXML
    void loadData(ActionEvent event) {
        loadTable();
    }

    private void loadTable() {

        ArrayList<PhoneDto> allPhone = PhoneModel.getAllPhone();

        ArrayList<PhoneTbl> phoneTms = new ArrayList<>();

        for (PhoneDto dto : allPhone) {
            phoneTms.add(new PhoneTbl(
                    dto.getPid(),
                    dto.getBrand(),
                    dto.getModel(),
                    dto.getPrice(),
                    dto.getQnt()
            ));
        }


        table.setItems(FXCollections.observableArrayList(phoneTms));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("pid"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qnt"));


        loadTable();
    }
}