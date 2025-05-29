package com.example.restaurantorderapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddOrder extends AppCompatActivity {

    EditText etRestaurant, etCustomerName, etCustomerSurname;
    int staffId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_order);

        etRestaurant = findViewById(R.id.etRestaurant);
        etCustomerName = findViewById(R.id.etCustomerName);
        etCustomerSurname = findViewById(R.id.etCustomerSurname);

        staffId = getIntent().getIntExtra("STAFF_ID", -1);

        Log.d("AddOrder", "Received STAFF_ID: " + staffId);

        if (staffId == -1) {
            Toast.makeText(this, "Error: Staff ID not passed!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void addOrder(View view) {
        String restaurant = etRestaurant.getText().toString().trim();
        String customerName = etCustomerName.getText().toString().trim();
        String customerSurname = etCustomerSurname.getText().toString().trim();

        if (restaurant.isEmpty() || customerName.isEmpty() || customerSurname.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        new AddOrderTask().execute(customerName, customerSurname, String.valueOf(staffId), restaurant);
    }

    private class AddOrderTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("https://lamp.ms.wits.ac.za/home/s2676309/Order_Create.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                JSONObject json = new JSONObject();
                json.put("Customer_Name", params[0]);
                json.put("Customer_Surname", params[1]);
                json.put("Staff_ID", params[2]);
                json.put("Restaurant_Name", params[3]);

                Log.d("AddOrderTask", "Sending JSON: " + json.toString());

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
                conn.disconnect();

                Log.d("AddOrderTask", "Response: " + response.toString());
                return response.toString();

            } catch (Exception e) {
                Log.e("AddOrderTask", "Exception occurred", e);
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(AddOrder.this, result, Toast.LENGTH_LONG).show();
        }
    }
}
