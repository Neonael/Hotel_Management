package com.example.Controller;

import com.example.Model.CustomerNumberData;
import com.example.Utilities.dbConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class ChekcinController implements Initializable {
    public TextField firstname;
    public TextField lastname;
    public TextField phonenumber;
    public TextField emailaddress;
    public DatePicker Checkin_Date;
    public DatePicker CheckOut_Date;
    public Button ResetButton;
    public Button ReceiptButton;
    public Button CheckInButton;
    public Label CustomerNumber;
    public ComboBox roomType_combo_checkin;
    public ComboBox roomNumber_combo_checkin;
    public Label TotalDays;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet resultSet;



    public void Customercheckin() {

        String CheckIN = "INSERT INTO CUSTOMERS (customer_id,roomType,roomNumber,firstname,lastname,phoneNumber,email,checkIn,checkOut) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";

        connect = dbConnector.getConnection();
     try {
         String customerNum = CustomerNumber.getText();
         String type = (String) roomType_combo_checkin.getSelectionModel().getSelectedItem();
         String number = (String) roomNumber_combo_checkin.getSelectionModel().getSelectedItem();
         String firstN = firstname.getText();
         String lastN = lastname.getText();
         String PNumber = phonenumber.getText();
         String mail = emailaddress.getText();
         String checkInDate = String.valueOf(Checkin_Date.getValue());
         String checkOutDate = String.valueOf(CheckOut_Date.getValue());
         Alert alert;
         // Check if there is empty field
         if (customerNum == null || firstname == null || lastname == null || phonenumber == null || emailaddress == null
                 || Checkin_Date == null || CheckOut_Date == null) {
             Alert alert1 = new Alert(Alert.AlertType.ERROR);
             alert1.setTitle("Error Message");
             alert1.setHeaderText(null);
             alert1.setContentText("Complete the details!" + "Thanks!");
             alert1.showAndWait();

         } else {

             Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
             alert1.setTitle("CONFIRMATION Message");
             alert1.setHeaderText(null);
             alert1.setContentText("Are you sure?");
             alert1.showAndWait();
             Optional<ButtonType>option = alert1.showAndWait();

             if(option.get().equals(ButtonType.OK)){


             }else{return;}


             prepare = connect.prepareStatement(CheckIN);

             prepare.setString(1, customerNum);
             prepare.setString(2, type);
             prepare.setString(3, number);
             prepare.setString(4, firstN);
             prepare.setString(5, lastN);
             prepare.setString(6, PNumber);
             prepare.setString(7, mail);
             prepare.setString(8, checkInDate);
             prepare.setString(9, checkOutDate);

             prepare.executeUpdate();

             Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
             alert2.setTitle("INFORMATION Message");
             alert2.setHeaderText(null);
             alert2.setContentText("Successfully Check-in!");
             alert2.showAndWait();



         }
     }catch(Exception e ){
            e.printStackTrace();
        }

    }



    public void totalDays(){
        if (CheckOut_Date.getValue().compareTo(Checkin_Date.getValue()) < 0 ){
            Alert alert;
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Message");
            alert1.setHeaderText(null);
            alert1.setContentText("Invalid Check-Out Date!");
            alert1.showAndWait();


        }else {
         CustomerNumberData.Totaldays = CheckOut_Date.getValue().compareTo(Checkin_Date.getValue());
         //
        }
    }

    public void displayTotal(){

        String totalID = String.valueOf(CustomerNumberData.Totaldays);
        TotalDays.setText(totalID);
    }




    public void CustomerNumber() {

        String customerNum = "SELECT customer_id FROM CUSTOMERS";

        connect = dbConnector.getConnection();

       try {
           prepare = connect.prepareStatement(customerNum);

           while (resultSet.next()){
               CustomerNumberData.customerNum = resultSet.getInt("customer_id");
           }


       }catch (Exception e ){e.printStackTrace();}
    }


    public void displayCustomerNumber(){
        CustomerNumber();
        CustomerNumber.setText(String.valueOf( CustomerNumberData.customerNum + 1));

    }






    public void roomTypeList(){
        String listType = "SELECT * FROM Available_rooms WHERE status = 'Available'";
        connect = dbConnector.getConnection();

        try{

            ObservableList listData = FXCollections.observableArrayList(listType);
            prepare = connect.prepareStatement(listType);
            resultSet = prepare.executeQuery();
            while (resultSet.next()){

                listData.add(resultSet.getString("room_type"));

            }
                roomType_combo_checkin.setItems(listData);




        }catch (Exception e){e.printStackTrace();};


    }







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        displayCustomerNumber();
        roomTypeList();


    }
}
