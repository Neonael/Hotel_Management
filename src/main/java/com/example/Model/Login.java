package com.example.Model;
public class Login {
    private String username;
    private String password;



    public Login(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public Login() {
    }



    public String getUserID() {
        return username;
    }

    public void setUserID(String userID) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }



    }





