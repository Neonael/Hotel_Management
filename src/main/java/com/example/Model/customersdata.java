package com.example.Model;

import java.util.Date;

public class customersdata {

    private Integer CustomerNum;
    private String firstname;
    private String lastname;
    private String PhoneNum;
    private Date CheckIn;
    private Date CheckOut;

    public customersdata(Integer CustomerNum , String firstname, String lastname, String PhoneNum, Date CheckIn, Date CheckOut){
       this.CustomerNum = CustomerNum;
       this.firstname = firstname;
       this.lastname = lastname;
       this.PhoneNum = PhoneNum;
       this.CheckIn = CheckIn;
       this.CheckOut = CheckOut;
    }


    public Integer getCustomerNum(){
        return CustomerNum;
    }

    public String getFirstname(){
        return firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public String getPhoneNum(){
        return PhoneNum;
    }

    public Date getCheckIn(){
        return CheckIn;
    }

    public Date getCheckOut(){
        return CheckOut;
    }










}
