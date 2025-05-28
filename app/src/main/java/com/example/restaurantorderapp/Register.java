package com.example.restaurantorderapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    TextInputEditText username, name, surname,password;
    String usernameText, nameText, surnameText, passwordText;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username = (TextInputEditText) findViewById(R.id.usernameNewInput);
        name = (TextInputEditText) findViewById(R.id.nameNewInput);
        surname = (TextInputEditText) findViewById(R.id.surnameNewInput);
        password = (TextInputEditText) findViewById(R.id.passwordNewInput);
        btnSignUp = (Button) findViewById(R.id.signUpButton);

        //acts as login button navigation
        TextView existingUser = (TextView) findViewById(R.id.existingUser);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //have to authenticate
                usernameText = username.getText().toString();
                nameText = name.getText().toString();
                surnameText = surname.getText().toString();
                passwordText = password.getText().toString();

                String task = "register";
                BackgroundTask backgroundTask = new BackgroundTask(Register.this);
                backgroundTask.execute(task, usernameText, nameText, surnameText, passwordText);
                finish();

                //need to check the format of info entered by the user if it is valid

                Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                //FIX Android Manifest!!!
                //Intent intent = new Intent(getApplicationContext(), LoginWelcome.class);
                //startActivity(intent);
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
