package com.example.restaurantorderapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StaffLogin extends AppCompatActivity {

    TextInputEditText staffID, staffPassword;
    Button btnStaffSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_main);

        staffID = findViewById(R.id.staffID);
        staffPassword = findViewById(R.id.staffPassword);
        btnStaffSignIn = findViewById(R.id.btnStaffSignIn);

        btnStaffSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = staffID.getText().toString().trim();
                String password = staffPassword.getText().toString().trim();

                if (id.isEmpty() && password.isEmpty()) {
                    Toast.makeText(StaffLogin.this, "Please enter Staff ID and Password", Toast.LENGTH_SHORT).show();
                }
                else if (id.isEmpty()) {
                    Toast.makeText(StaffLogin.this, "Please enter Staff ID", Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty()){
                    Toast.makeText(StaffLogin.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                }else {
                    new StaffLoginTask().execute(id, password);
                }
            }
        });
    }

    private class StaffLoginTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("https://lamp.ms.wits.ac.za/home/s2676309/Staff_Login.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conn.setDoOutput(true);

                JSONObject json = new JSONObject();
                json.put("Staff_ID", Integer.parseInt(params[0]));
                json.put("Password", params[1]);

                OutputStream os = conn.getOutputStream();
                os.write(json.toString().getBytes("UTF-8"));
                os.close();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                return response.toString();

            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject response = new JSONObject(result);
                if (response.getString("status").equals("success")) {
                    String name = response.getString("Name");
                    Toast.makeText(StaffLogin.this, "Welcome " + name, Toast.LENGTH_LONG).show();

                    // We vayy to Staff Dashboard
                    Intent intent = new Intent(StaffLogin.this, StaffDashboard.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(StaffLogin.this, response.getString("message"), Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(StaffLogin.this, "Login failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
