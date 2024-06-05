package com.example.Model;

public class RoomData {
    private String roomNumber;
    private String roomType;
    private  String status;
    public int price;

    public RoomData(String roomNumber, String roomType, String status, int price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.status = status;
        this. price = price;

    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }


    public String getStatus(){
        return status;
    }

        
   public int getPrice() {
       return price;
   }



    }


