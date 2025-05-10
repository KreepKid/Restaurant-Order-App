package com.example.restaurantorderapp;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout home = (ConstraintLayout) findViewById(R.id.linearLayout0);
        //setContentView(R.id.linearLayout0);

        Button sign = (Button) findViewById(R.id.signInButton);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.user);

                ImageView proceed = (ImageView) findViewById(R.id.imageView11);

                proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        setContentView(R.layout.post_user);

                    }
                });
            }
        });



    }
}