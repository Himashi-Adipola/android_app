package com.example.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.model.DriverHistory;

import java.io.File;
import java.util.List;

public class DriverHistoryAdapter extends ArrayAdapter {

    private List<DriverHistory> driverHistories;

    private Activity context;


    public DriverHistoryAdapter(Activity context, List<DriverHistory> driverHistories) {
        super(context, R.layout.list_driver_history, driverHistories);
        this.context = context;
        this.driverHistories = driverHistories;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();

        if(convertView==null)
            row = inflater.inflate(R.layout.list_driver_history, null, true);

        TextView title = row.findViewById(R.id.textVehicleNo);
        title.setText("Vehicle No :"+driverHistories.get(position).getVehicleNumber());

        TextView txtName = (TextView) row.findViewById(R.id.txtName);
        txtName.setText(driverHistories.get(position).getfName() + " " + driverHistories.get(position).getlName());
        TextView txtDOB = (TextView) row.findViewById(R.id.txtDOB);
        txtDOB.setText(driverHistories.get(position).getpDOB().toString());
        TextView txtAddress = (TextView) row.findViewById(R.id.txtAddress);
        txtAddress.setText(driverHistories.get(position).getpAddress());
        TextView txtContact = (TextView) row.findViewById(R.id.txtContact);
        txtContact.setText(String.valueOf(driverHistories.get(position).getpContactNo()));
        TextView txtVehNo = (TextView) row.findViewById(R.id.txtVehNo);
        txtVehNo.setText(driverHistories.get(position).getVehicleNumber());
        TextView txtPlace = (TextView) row.findViewById(R.id.txtPlace);
        txtPlace.setText(driverHistories.get(position).getPlace());
        TextView txtAgentID = (TextView) row.findViewById(R.id.txtAgentID);
        txtAgentID.setText(String.valueOf(driverHistories.get(position).getAgId()));
        TextView txtAccept = (TextView) row.findViewById(R.id.txtAccept);
        txtAccept.setText(String.valueOf(driverHistories.get(position).getIsAccepted()));
        TextView txtReportID = (TextView) row.findViewById(R.id.txtReportID);
        txtReportID.setText(String.valueOf(driverHistories.get(position).getrId()));
        TextView txtReportDate = (TextView) row.findViewById(R.id.txtReportDate);
        txtReportDate.setText(driverHistories.get(position).getrDate().toString());
        TextView txtReportCost = (TextView) row.findViewById(R.id.txtReportCost);
        txtReportCost.setText(String.valueOf(driverHistories.get(position).getrCost()));
        TextView txtDescription = (TextView) row.findViewById(R.id.txtDescription);
        txtDescription.setText(driverHistories.get(position).getrDescription());


        return  row;
    }

}
