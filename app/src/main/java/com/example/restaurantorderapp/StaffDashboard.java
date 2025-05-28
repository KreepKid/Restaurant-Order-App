package com.example.restaurantorderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StaffDashboard extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_dashboard);

        TextView staffProfile = (TextView) findViewById(R.id.staffProfile);
        TextView createOrder = (TextView) findViewById(R.id.staffCreateOrder);
        TextView orderHistory = (TextView) findViewById(R.id.staffOrderHistory);

        staffProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StaffProfile.class);
                startActivity(intent);
            }
        });

        createOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddOrder.class);
                startActivity(intent);
            }
        });

        orderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StaffOrderHistory.class);
                startActivity(intent);
            }
        });


    }
}
