package com.example.restaurantorderapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ListItem listItem = listItems.get(position);

        holder.textViewOrderID.setText(listItem.getOrderID());
        holder.textViewCustomerName.setText(listItem.getCustomerName());
        holder.textViewCustomerSurname.setText(listItem.getCustomerSurname());
        holder.textViewStaffID.setText(listItem.getStaffID());
        holder.textViewRestaurantName.setText(listItem.getRestaurantName());
        holder.textViewTime.setText((listItem.getTime()).toString());
        holder.textViewStatus.setText(listItem.getStatus());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView textViewOrderID;
        public TextView textViewCustomerName;
        public TextView textViewCustomerSurname;
        public TextView textViewStaffID;
        public TextView textViewRestaurantName;
        public TextView textViewTime;
        public TextView textViewStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewOrderID = (TextView) itemView.findViewById(R.id.textViewOrderID);
            textViewCustomerName = (TextView) itemView.findViewById(R.id.textViewCustomerName);
            textViewCustomerSurname = (TextView) itemView.findViewById(R.id.textViewCustomerSurname);
            textViewStaffID = (TextView) itemView.findViewById(R.id.textViewStaffID);
            textViewRestaurantName = (TextView) itemView.findViewById(R.id.textViewRestaurantName);
            textViewTime = (TextView) itemView.findViewById(R.id.textViewTime);
            textViewStatus = (TextView) itemView.findViewById(R.id.textViewStatus);
        }
    }
}
