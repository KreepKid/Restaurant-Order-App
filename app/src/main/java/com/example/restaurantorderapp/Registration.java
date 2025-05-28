package com.example.restaurantorderapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_user);

        TextInputEditText UsermameIn = (TextInputEditText) findViewById(R.id.usernameNewInput);
        TextInputEditText NameIn = (TextInputEditText) findViewById(R.id.nameNewInput);
        TextInputEditText SurnameIn = (TextInputEditText) findViewById(R.id.surnameNewInput);
        TextInputEditText PasswordIn = (TextInputEditText) findViewById(R.id.passwordNewInput);

        Button signUpButton = (Button) findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            String usernameText = UsermameIn.getText().toString().trim();
            String nameText = NameIn.getText().toString().trim();
            String surnameText = SurnameIn.getText().toString().trim();
            String passwordText = PasswordIn.getText().toString().trim();

            if(inputValidate(usernameText, nameText, surnameText, passwordText)){
                new RegisterTask().execute(usernameText, nameText, surnameText, passwordText);
            }
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ShopsHomepage.class);
                startActivity(intent);

            }
        });
        private boolean validateInputs(String... inputs) {
            for (String input : inputs) {
                if (input.isEmpty()) {
                    Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            return true;
        }

        private class RegisterTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String username = params[0];
                String name = params[1];
                String surname = params[2];
                String password = params[3];
                String serverUrl = "http://lamp.ms.wits.ac.za/Register.php";

                try {
                    // Create JSON payload
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("Username", username);
                    jsonParam.put("Name", name);
                    jsonParam.put("Surname", surname);
                    jsonParam.put("Password", password);

                    // Configure HTTP POST request
                    URL url = new URL(serverUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

                    // Send JSON data
                    try (OutputStream os = conn.getOutputStream()) {
                        byte[] input = jsonParam.toString().getBytes(StandardCharsets.UTF_8);
                        os.write(input, 0, input.length);
                    }

                    // Check response
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        // Parse JSON response
                        try (BufferedReader br = new BufferedReader(
                                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                            StringBuilder response = new StringBuilder();
                            String line;
                            while ((line = br.readLine()) != null) {
                                response.append(line.trim());
                            }
                            return response.toString();
                        }
                    } else {
                        return "Error: " + conn.getResponseCode();
                    }
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            }

        protected void onPostExecute(String result) {
            try {
                JSONObject jsonResponse = new JSONObject(result);
                if (jsonResponse.getString("status").equals("success")) {
                    Toast.makeText(Registration.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                    finish(); // Return to login screen
                } else {
                    String error = jsonResponse.getString("message");
                    Toast.makeText(Registration.this, error, Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(Registration.this, "Registration failed. Try again.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
