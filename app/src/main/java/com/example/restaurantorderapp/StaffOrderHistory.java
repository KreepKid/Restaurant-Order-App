package com.example.restaurantorderapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class StaffOrderHistory extends AppCompatActivity {

    private static final String URL_DATA = "https://lamp.ms.wits.ac.za/home/s2676309/order_retrieval.php";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;

    int staffID;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_order_history);

        staffID = getIntent().getIntExtra("STAFF_ID", -1);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        loadRecyclerViewData();
    }

    private void loadRecyclerViewData(){
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading data...");
            progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONArray array = new JSONArray(s);

                            for(int i = 0; i < array.length(); ++i){
                                JSONObject o = array.getJSONObject(i);
                                ListItem item = new ListItem(
                                        o.getInt("Order_ID"),
                                        o.getString("Customer_Name"),
                                        o.getString("Customer_Surname"),
                                        Integer.parseInt(o.getString("Staff_ID")),
                                        o.getString("Restaurant_Name"),
                                        Timestamp.valueOf(o.getString("Order_Time")),
                                        o.getString("Status")
                                );
                                listItems.add(item);
                            }
                            adapter = new MyAdapter(listItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        }
                            catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

        //RequestQueue StringRequest = Volley.newRequestQueue(this);
        //RequestQueue.add(StringRequest);
    }
}
