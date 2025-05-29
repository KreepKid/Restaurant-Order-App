package com.example.restaurantorderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class StaffDashboard extends AppCompatActivity {

    int staffId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_dashboard);

        staffId = getIntent().getIntExtra("STAFF_ID", -1);

        if (staffId == -1) {
            Toast.makeText(this, "Error: Staff ID not passed!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        TextView staffProfile = findViewById(R.id.staffProfile);
        TextView createOrder = findViewById(R.id.staffCreateOrder);
        TextView orderHistory = findViewById(R.id.staffOrderHistory);

        staffProfile.setOnClickListener(view -> startWithStaffId(StaffProfile.class));
        createOrder.setOnClickListener(view -> startWithStaffId(AddOrder.class));
        orderHistory.setOnClickListener(view -> startWithStaffId(StaffOrderHistory.class));
    }

    private void startWithStaffId(Class<?> destination) {
        Intent intent = new Intent(getApplicationContext(), destination);
        intent.putExtra("STAFF_ID", staffId);
        startActivity(intent);
    }

}
