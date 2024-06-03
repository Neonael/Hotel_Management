package com.example.Model;

public class RoomData {
    private int roomNumber;
    private String roomType;
    private  String status;
    public int price;

    public RoomData(int roomNumber, String roomType, String status, int price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.status = status;
        this. price = price;

    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoom_type() {
        return roomType;
    }


    public String getStatus(){
        return status;
    }

        
   public int getPrice() {
       return price;
   }



    }


