package com.example.Model;

public class RoomData {
    private int roomNumber;
    private String room_type;
    private  String status;
    public int price;

    public RoomData(int roomNumber, String room_type, String status, int price) {
        this.roomNumber = roomNumber;
        this.room_type = room_type;
        this.status = status;
        this. price = price;

    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoom_type() {
        return room_type;
    }


    public String getStatus(){
        return status;
    }

        
   public int getPrice() {
       return price;
   }



    }


