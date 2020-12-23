package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class VehicleListAdapter extends ArrayAdapter {

    private List<String> vehicleList;
    private Activity context;

    public VehicleListAdapter(Activity context,List<String> vehicleList) {
        super(context, R.layout.list_vehicle, vehicleList);
        this.context = context;
        this.vehicleList = vehicleList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();

        if(convertView==null)
            row = inflater.inflate(R.layout.list_vehicle, null, true);

            TextView txtVehicle = (TextView) row.findViewById(R.id.vehicle_number);

            txtVehicle.setText(vehicleList.get(position));


        return  row;
    }

}
