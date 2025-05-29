package com.example.restaurantorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.content.Context;

public class LoginWelcome extends AppCompatActivity {
    TextView name,surname;
    SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loggin_welcome);

        name = (TextView) findViewById(R.id.textCustomerName);
        surname = (TextView) findViewById(R.id.textCustomerSurname);
        preferences =  this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        String custName = preferences.getString("name","Error, name not found");
        String custSurname = preferences.getString("surname","Error, surname not found");

        name.setText(custName);
        surname.setText(custSurname);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), CustomerDashboard.class);
                startActivity(intent);
                finish();
            }
        }, 5500);
    }
}
