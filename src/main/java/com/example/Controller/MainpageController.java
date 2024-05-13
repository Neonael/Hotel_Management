package com.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class MainpageController {
    @FXML
    private Button AvailableRoomsbtn;

    @FXML
    private AnchorPane Availablerooms_form;

    @FXML
    private Button Customersbtn;

    @FXML
    private TextField availablerooms_addbutton;

    @FXML
    private Button availablerooms_checkinbutton;

    @FXML
    private TableColumn<?, ?> availablerooms_col_price;

    @FXML
    private TableColumn<?, ?> availablerooms_col_roomNumber;

    @FXML
    private TableColumn<?, ?> availablerooms_col_roomtype;

    @FXML
    private TextField availablerooms_col_search;

    @FXML
    private TableColumn<?, ?> availablerooms_col_status;

    @FXML
    private TextField availablerooms_roomNumber;

    @FXML
    private ComboBox<?> availablerooms_roomType;

    @FXML
    private ComboBox<?> availablerooms_status;

    @FXML
    private AnchorPane customer_form;

    @FXML
    private TableColumn<?, ?> customers_customerCheckIn;

    @FXML
    private TableColumn<?, ?> customers_customerCheckOut;

    @FXML
    private TableColumn<?, ?> customers_customerTotalpayment;

    @FXML
    private TableColumn<?, ?> customers_customerfirstname;

    @FXML
    private TableColumn<?, ?> customers_customerlastname;

    @FXML
    private TableColumn<?, ?> customers_customernumber;

    @FXML
    private TableColumn<?, ?> customers_customerphonenumber;

    @FXML
    private TextField customers_search;

    @FXML
    private Label dashboard_book;

    @FXML
    private Label dashboard_income;

    @FXML
    private Button dashboardbtn;

}
