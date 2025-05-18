package com.example.restaurantorderapp;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout home = (ConstraintLayout) findViewById(R.id.linearLayout0);

        Button sign = (Button) findViewById(R.id.signInButton);
        TextView newUserText = (TextView) findViewById(R.id.newUser);

        TextInputEditText username = (TextInputEditText) findViewById(R.id.username);
        TextInputEditText password = (TextInputEditText) findViewById(R.id.password);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameText = username.toString();
                String passwordText = password.toString();

                //need to check usernameText on the database then if found, use intent

                Intent intent = new Intent(getApplicationContext(),user.class);
                startActivity(intent);

            }
        });

        //if they clicked on the new user? sign up text

        newUserText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), UserSignUp.class);
                startActivity(intent);

            }
        });



    }
}