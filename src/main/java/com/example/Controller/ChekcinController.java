package com.example.Controller;

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

     public Label dashboard_todaysbooking;





    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet resultSet;


    //INSERT WHAT USER INPUTS ON THE CUSTOMERS TABLE
    public void Customercheckin() {


        String CheckIN = "INSERT INTO CUSTOMERS (customer_id,roomType,roomNumber,firstname,lastname,phoneNumber,email,checkIn,checkOut) " +
                "VALUES(?,?,?,?,?,?,?,TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'))";

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
         if (customerNum == null || firstname == null || lastname == null || phonenumber == null ||
                 emailaddress == null || Checkin_Date == null || CheckOut_Date  == null) {

             Alert alert1 = new Alert(Alert.AlertType.ERROR);
             alert1.setTitle("Error Message");
             alert1.setHeaderText(null);
             alert1.setContentText("Complete the Details!");
             alert1.showAndWait();


         } else {

             Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
             alert1.setTitle("CONFIRMATION Message");
             alert1.setHeaderText(null);
             alert1.setContentText("Are you sure?");

             // DISPLAYS THE PROMPT WITH A BUTTON OK
             Optional<ButtonType>option = alert1.showAndWait();

             if(option.get().equals(ButtonType.OK)){

             prepare = connect.prepareStatement(CheckIN);
             // INSERT IT
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

        //////////// UPDATES THE STATUS INTO OCCUPIED IF ITS ALREADY TAKEN
             String sqlEditStatus = "UPDATE Available_room SET status = 'Occupied' WHERE roomNumber = '" + number +"'";
             PreparedStatement prepare = connect.prepareStatement(sqlEditStatus);
             prepare.executeUpdate(sqlEditStatus);



             Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
             alert2.setTitle("INFORMATION Message");
             alert2.setHeaderText(null);
             alert2.setContentText("Successfully Check-in!");
             alert2.showAndWait();




         }else{return;
             }

         }
     }catch(Exception e ){
            e.printStackTrace();
        }

    }







           // ITS ON THE COMBO BOX ON COMBO BOX IT WILL ONLY SHOW THE STATUS THAT IS  AVAILABLE!!

    public void roomTypeList(){
        String listType = "SELECT * FROM Available_room WHERE status = 'Available'";
        connect = dbConnector.getConnection();

        try{

            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(listType);
            resultSet = prepare.executeQuery();
            while (resultSet.next()){

                listData.add(resultSet.getString("room_type"));

            }
                roomType_combo_checkin.setItems(listData);




        }catch (Exception e){e.printStackTrace();};


    }

   //  // ITS ON THE COMBO BOX ON COMBO BOX IT WILL ONLY SHOW THE roomNUMBER THAT IS  AVAILABLE!!
    public void roomNumberList(){
        String listType = "SELECT * FROM Available_room WHERE status = 'Available'";
        connect = dbConnector.getConnection();

        try{

            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(listType);
            resultSet = prepare.executeQuery();
            while (resultSet.next()){

                listData.add(resultSet.getString("roomNumber"));

            }
            roomNumber_combo_checkin.setItems(listData);


        }catch (Exception e){e.printStackTrace();};


    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        roomTypeList();
        roomNumberList();



    }
}
