package com.example.restaurantorderapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StaffLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_main);

        TextInputEditText staffID = (TextInputEditText) findViewById(R.id.staffID);
        TextInputEditText staffPassword = (TextInputEditText) findViewById(R.id.staffPassword);
        Button staffLoginButton = (Button) findViewById(R.id.staffLogInButton);

        staffLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StaffDashboard.class);
                startActivity(intent);
            }
        });
     }
}
