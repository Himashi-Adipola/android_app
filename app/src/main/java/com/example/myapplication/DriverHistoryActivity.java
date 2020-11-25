package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.apiOps.driverHistory.DriverHistoryOps;
import com.example.myapplication.model.DriverHistory;

public class DriverHistoryActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtDOB;
    private TextView txtAddress;
    private TextView txtContact;
    private TextView txtVehNo;
    private TextView txtPlace;
    private TextView txtAgentID;
    private TextView txtAccept;
    private TextView txtReportID;
    private TextView txtReportDate;
    private TextView txtReportCost;
    private TextView txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DriverHistory driverHistory = DriverHistoryOps.getDriverHistory();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_history);

        txtName = (TextView) findViewById(R.id.txtName);
        txtName.setText(driverHistory.getfName() + " " + driverHistory.getlName());
        txtDOB = (TextView) findViewById(R.id.txtDOB);
        txtDOB.setText(driverHistory.getpDOB().getYear() + "/" + driverHistory.getpDOB().getMonth() + "/" + driverHistory.getpDOB().getDate());
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtAddress.setText(driverHistory.getpAddress());
        txtContact = (TextView) findViewById(R.id.txtContact);
        txtContact.setText(String.valueOf(driverHistory.getpContactNo()));
        txtVehNo = (TextView) findViewById(R.id.txtVehNo);
        txtVehNo.setText(driverHistory.getVehicleNumber());
        txtPlace = (TextView) findViewById(R.id.txtPlace);
        txtPlace.setText(driverHistory.getPlace());
        txtAgentID = (TextView) findViewById(R.id.txtAgentID);
        txtAgentID.setText(String.valueOf(driverHistory.getAgId()));
        txtAccept = (TextView) findViewById(R.id.txtAccept);
        txtAccept.setText(String.valueOf(driverHistory.getIsAccepted()));
        txtReportID = (TextView) findViewById(R.id.txtReportID);
        txtReportID.setText(String.valueOf(driverHistory.getrId()));
        txtReportDate = (TextView) findViewById(R.id.txtReportDate);
        txtReportDate.setText(driverHistory.getrDate().getYear() + "/" + driverHistory.getrDate().getMonth() + "/" + driverHistory.getrDate().getDate());
        txtReportCost = (TextView) findViewById(R.id.txtReportCost);
        txtReportCost.setText(String.valueOf(driverHistory.getrCost()));
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtDescription.setText(driverHistory.getrDescription());
    }
}