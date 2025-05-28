package com.example.restaurantorderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AddOrder extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_order);

    }
}

// After fixing the code below you can get rid / link it with the one above

/*public class AddOrder {



    private static final String BASE_URL = "http://your-server-domain.com/";

    public interface OrderCallback {
        void onOrdersReceived(List<Order> orders);
        void onError(String message);
    }*/

    public static class Order {
        private int Order_ID;
        private int Staff_ID;
        private String Username;
        private String Order_status;
        private String Order_time;
    }

    public static void insertOrder(Context context, int Staff_ID, String Username, OrderCallback callback) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(BASE_URL + "Create_Order.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);

                    String postData = "staff_id=" + Staff_ID + "&customer_name=" + Username;

                    try (OutputStream os = conn.getOutputStream()) {
                        byte[] input = postData.getBytes(StandardCharsets.UTF_8);
                        os.write(input, 0, input.length);
                    }

                    try (BufferedReader br = new BufferedReader(
                            new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine;
                        while ((responseLine = br.readLine()) != null) {
                            response.append(responseLine.trim());
                        }
                        return response.toString();
                    }
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                // Handle response
            }
        }.execute();
    }

    public static void retrieveOrders(Context context, String searchQuery, OrderCallback callback) {
        new AsyncTask<Void, Void, List<Order>>() {
            @Override
            protected List<Order> doInBackground(Void... voids) {
                List<Order> orders = new ArrayList<>();
                try {
                    URL url = new URL(BASE_URL + "get_orders.php?search=" + searchQuery);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    try (BufferedReader br = new BufferedReader(
                            new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine;
                        while ((responseLine = br.readLine()) != null) {
                            response.append(responseLine.trim());
                        }

                        JSONArray jsonArray = new JSONArray(response.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Order order = new Order();
                            order.setOrderId(jsonObject.getInt("Order_ID"));
                            order.setStaffId(jsonObject.getInt("Staff_ID"));
                            order.setCustomerName(jsonObject.getString("Username"));
                            order.setOrderTime(jsonObject.getString("Order_time"));
                            order.setOrderStatus(jsonObject.getString("Order_status"));
                            orders.add(order);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return orders;
            }

            @Override
            protected void onPostExecute(List<Order> orders) {
                if (!orders.isEmpty()) {
                    callback.onOrdersReceived(orders);
                } else {
                    callback.onError("No orders found");
                }
            }
        }.execute();
    }
}

