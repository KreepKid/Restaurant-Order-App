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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout home = (ConstraintLayout) findViewById(R.id.linearLayout0);

        TextView newUserText = (TextView) findViewById(R.id.newUser);
        TextInputEditText Username = (TextInputEditText) findViewById(R.id.staffID);    //Username login TextInput for the user
        TextInputEditText Password = (TextInputEditText) findViewById(R.id.staffPassword);  //Password login TextInput for the user
        Button signIn = (Button) findViewById(R.id.staffLogInButton);   //Button to click when signing-in

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameText = Username.getText().toString();
                String passwordText = Password.getText().toString();

                //need to check usernameText on the database then if found, use intent

                Intent intent = new Intent(getApplicationContext(),UserSignUp.class);
                startActivity(intent);

            }
        });

        //if they clicked on the new user? sign up text

        /*newUserText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), UserSignUp.class);
                startActivity(intent);

            }
        });*/



    }
}