package com.example.restaurantorderapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_user);

        TextInputEditText username = (TextInputEditText) findViewById(R.id.usernameNewInput);
        TextInputEditText name = (TextInputEditText) findViewById(R.id.nameNewInput);
        TextInputEditText surname = (TextInputEditText) findViewById(R.id.surnameNewInput);
        TextInputEditText email = (TextInputEditText) findViewById(R.id.surnameNewInput);
        TextInputEditText password = (TextInputEditText) findViewById(R.id.passwordNewInput);

        TextView existingUser = (TextView) findViewById(R.id.existingUser);

        Button signUp = (Button) findViewById(R.id.signUpButton);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if successful and everything is in order -> take to home page

                //code needed to check user input

                signUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String usernameText, nameText, surnameText, emailText, passwordText;
                        usernameText = username.getText().toString();
                        nameText = name.getText().toString();
                        surnameText = surname.getText().toString();
                        emailText = email.getText().toString();
                        passwordText = password.getText().toString();

                        //need to check the format of info entered by the user if it is valid


                        Intent intent = new Intent(getApplicationContext(), ShopsHomepage.class);
                        startActivity(intent);

                        Toast.makeText(UserSignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        existingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
