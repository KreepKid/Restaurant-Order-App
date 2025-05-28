package com.example.restaurantorderapp;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String usernameText,passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout home = (ConstraintLayout) findViewById(R.id.linearLayout0);

        TextView newUserText = (TextView) findViewById(R.id.newUser);
        TextInputEditText etUsername = (TextInputEditText) findViewById(R.id.staffID);    //Username login TextInput for the user
        TextInputEditText etPassword = (TextInputEditText) findViewById(R.id.staffPassword);  //Password login TextInput for the user
        Button btnSignIn = (Button) findViewById(R.id.btnCustSignIn);   //Button to click when signing-in

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usernameText = etUsername.getText().toString();
                passwordText = etPassword.getText().toString();

                String task = "login";
                BackgroundTask backgroundTask = new BackgroundTask(MainActivity.this);

                etUsername.setText("");
                etPassword.setText("");

                //execute code by passing mentioned params to
                //BackgroundTask class
                backgroundTask.execute(task, usernameText, passwordText);
            }
        });

        //if they clicked on the new user? sign up text
        newUserText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });



    }
}