package com.example.restaurantorderapp;

import android.widget.Button;
import android.widget.RatingBar;

import java.sql.Timestamp;

public class ListItem {

    private int orderID;
    private String customerName;
    private String customerSurname;
    private int staffID;
    private String restaurantName;
    private Timestamp time;
    private  String status;

    public ListItem(int orderID, String customerName, String customerSurname, int staffID, String restaurantName, Timestamp time, String status){
        this.orderID = orderID;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.staffID = staffID;
        this.restaurantName = restaurantName;
        this.time = time;
        this.status = status;
    }

    public ListItem(int order_id, String customer_name, String customer_surname, int staff_id, String restaurant_name, Timestamp time ,int status) {
    }

    public int getOrderID(){
        return orderID;
    }
        public String getCustomerName(){
        return customerName;
    }
    public String getCustomerSurname() {
        return customerSurname;
    }

    public int getStaffID() {
        return staffID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }
}