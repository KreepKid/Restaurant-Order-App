package com.example.restaurantorderapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    String usernameText, nameText, surnameText, emailText, passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        TextInputEditText username = (TextInputEditText) findViewById(R.id.usernameNewInput);
        TextInputEditText name = (TextInputEditText) findViewById(R.id.nameNewInput);
        TextInputEditText surname = (TextInputEditText) findViewById(R.id.surnameNewInput);
        TextInputEditText password = (TextInputEditText) findViewById(R.id.passwordNewInput);
        Button signUp = (Button) findViewById(R.id.signUpButton);

        //acts as login button navigation
        TextView existingUser = (TextView) findViewById(R.id.existingUser);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //have to authenticate
                usernameText = username.getText().toString();
                nameText = name.getText().toString();
                surnameText = surname.getText().toString();
                passwordText = password.getText().toString();

                String activity = "Register";
                BackgroundTask backgroundWorker = new BackgroundTask(Register.this);
                backgroundWorker.execute(activity, usernameText, nameText, surnameText, passwordText);
                finish();

                //need to check the format of info entered by the user if it is valid

                //Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
            }
        });

        //if they already have an account navigate to signIn page(activity_main.xml)
        existingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
