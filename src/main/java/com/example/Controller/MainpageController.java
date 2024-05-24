package com.example.Controller;

import com.example.Model.RoomData;
import com.example.Utilities.dbConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainpageController implements Initializable {


    public TextField availroomnum_textfield;
    public TextField availprice_textfield;
    public ComboBox availroomtype_droptext;
    public ComboBox availroomstatus_droptext;


    public TableView <RoomData> available_tableview;


    public TableColumn<RoomData,String>  avail_roomnum_col;

    public TableColumn<RoomData,String> avail_roomtype_col;
    public TableColumn <RoomData,String>avail_roomstatus_col;
    public TableColumn <RoomData,String> avail_roomprice_col;
    @FXML
    public Label total;

    @FXML
    public Label totalDays;



    private Connection connection;
    private PreparedStatement prepare;

    private ResultSet resultSet;


    public void AvaillabelroomsAdd() {

        connection = dbConnector.getConnection();

        String sql = "INSERT INTO Available_room(roomNumber,room_Type,status,price) VALUES (?,?,?,?)";

        try {

            String roomNumber = availroomnum_textfield.getText();
            String type = (String) availroomtype_droptext.getSelectionModel().getSelectedItem();
            String status = (String) availroomstatus_droptext.getSelectionModel().getSelectedItem();
            String price = availprice_textfield.getText();

            Alert alert;

            if (roomNumber == null || type== null|| status== null  || price.isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill up all the blank");
                alert.showAndWait();


            } else {

                /*CHECK IF THE ROOM NUMBER IS ALREADY TAKEN */

                String check = "SELECT roomNumber FROM Available_room WHERE roomNumber = '" + roomNumber+ "' ";

                  prepare = connection.prepareStatement(check);
                  resultSet = prepare.executeQuery();



                if(resultSet.next()){

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Room #"+ roomNumber+" was already taken");

                    alert.showAndWait();



                }else {

                    prepare = connection.prepareStatement(sql);
                    prepare.setString(1, roomNumber);
                    prepare.setString(2, type);
                    prepare.setString(3, status);
                    prepare.setString(4, price);
                    prepare.executeUpdate();


                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!!");
                    alert.showAndWait();


                    /*IT WILL UPDATE THE DATA IN THE TABLE VIEW */
                    availableRoomsShowData();

                    /*IT WILL CLEAR FIELDS*/
                    AvailableroomClear();
                }
            }

        } catch (Exception e) {
            {
                e.printStackTrace();
            }

        }

    }


    ////////////////////////// LIST IT ON TEXT BOX
    public ObservableList<RoomData> AvailableRoomListData() {

        ObservableList<RoomData> listdata = FXCollections.observableArrayList();

        String sql = ("SELECT * FROM AVAILABLE_ROOM");

        connection = dbConnector.getConnection();

        try {
         RoomData roomD;

            prepare = connection.prepareStatement(sql);
         resultSet = prepare.executeQuery();


            while (resultSet.next()) {
                roomD = new RoomData(

                        resultSet.getInt("roomNumber"),
                        resultSet.getString("room_type"),
                        resultSet.getString("status"),
                        resultSet.getInt("price")
                );
                        listdata.add(roomD);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

            return listdata;
    }

       private ObservableList<RoomData>roomDataList;
        public void availableRoomsShowData(){

            roomDataList = AvailableRoomListData();
            available_tableview.setItems(roomDataList);



        }



    /////////////////// DELETE BUTTON ON AVAILABLE ROOMS

    public void AvailableroomDelete(){

        String roomNumber = availroomnum_textfield.getText();
        String type = (String) availroomtype_droptext.getSelectionModel().getSelectedItem();
        String status = (String) availroomstatus_droptext.getSelectionModel().getSelectedItem();
        String price = availprice_textfield.getText();

             String deletedata = "DELETE FROM Available_room WHERE roomNUmber = '"+roomNumber +"'";

             connection = dbConnector.getConnection();

             Alert alert;

           try{


                   alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Message");
                   alert.setHeaderText(null);
                   alert.setContentText("Are you sure you want to delete it?");
                   alert.showAndWait();

                   Optional<ButtonType> option = alert.showAndWait();

                   if(option.get().equals(ButtonType.OK)){


                       alert = new Alert(Alert.AlertType.INFORMATION);
                       alert.setTitle("Information Message");
                       alert.setHeaderText(null);
                       alert.setContentText("Successfully Deleted!");
                       alert.showAndWait();

                       prepare.getConnection().createStatement();
                       prepare.executeUpdate(deletedata);

                       availableRoomsShowData();

                   }else{
                     return;
                   }

           } catch (SQLException e) {
               throw new RuntimeException(e);
           }


    }




    //////////////*IT WILL CLEAR FIELDS*/
    public void AvailableroomClear(){
        availroomnum_textfield.setText("");
        availroomtype_droptext.getSelectionModel().clearSelection();
        availroomstatus_droptext.getSelectionModel().clearSelection();
        availprice_textfield.setText("");
    }



     public void AvaialbleRoomCheckIn(ActionEvent actionEvent) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Chekcin.fxml"));
         Parent root = loader.load();
         Stage che = new Stage();
         che.setTitle("CheckIn Form");
         che.setScene(new Scene(root, 455, 596));
         che.show();


     }




////////////////// PUT VALUE ON THE COMBO BOX BUTTON on AVAILABLE ROOMS
    private String roomtype[]
            = {"Single Room", "Double Room", "Triple Room", "Quad Room ", "King Room"};

    public void availbaleRoomRoomtype() {

        List<String> listdata = new ArrayList<>();

        for (String data : roomtype) {
            listdata.add(data);
        }
        ObservableList list = FXCollections.observableArrayList(listdata);
        availroomtype_droptext.setItems(list);


    }

    private String Status[]
            = {"Available", "Not Available", "Occupied"};



    public void availableRoomStatus() {

        List<String> listdata = new ArrayList<>();

        for (String data : Status) {
            listdata.add(data);
        }
        ObservableList list = FXCollections.observableArrayList(listdata);
        availroomstatus_droptext.setItems(list);


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        availbaleRoomRoomtype();
        availableRoomStatus();

        // Initialize TableView and TableColumn
        avail_roomnum_col.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        avail_roomtype_col.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        avail_roomstatus_col.setCellValueFactory(new PropertyValueFactory<>("status"));
        avail_roomprice_col.setCellValueFactory(new PropertyValueFactory<>("price"));

        availableRoomsShowData();

    }
}

