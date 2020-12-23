package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.apiOps.driverHistory.DriverHistoryOps;
import com.example.myapplication.model.DriverHistory;

import java.util.List;

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

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_history);

        List<DriverHistory> driverHistory = DriverHistoryOps.getDriverHistory();
        listView = findViewById(R.id.driver_history_list);
        DriverHistoryAdapter driverHistoryAdapter = new DriverHistoryAdapter(this,driverHistory);
        listView.setAdapter(driverHistoryAdapter);

//        txtName = (TextView) findViewById(R.id.txtName);
//        txtName.setText(driverHistory.get(0).getfName() + " " + driverHistory.get(0).getlName());
//        txtDOB = (TextView) findViewById(R.id.txtDOB);
//        txtDOB.setText(driverHistory.get(0).getpDOB().getYear() + "/" + driverHistory.get(0).getpDOB().getMonth() + "/" + driverHistory.get(0).getpDOB().getDate());
//        txtAddress = (TextView) findViewById(R.id.txtAddress);
//        txtAddress.setText(driverHistory.get(0).getpAddress());
//        txtContact = (TextView) findViewById(R.id.txtContact);
//        txtContact.setText(String.valueOf(driverHistory.get(0).getpContactNo()));
//        txtVehNo = (TextView) findViewById(R.id.txtVehNo);
//        txtVehNo.setText(driverHistory.get(0).getVehicleNumber());
//        txtPlace = (TextView) findViewById(R.id.txtPlace);
//        txtPlace.setText(driverHistory.get(0).getPlace());
//        txtAgentID = (TextView) findViewById(R.id.txtAgentID);
//        txtAgentID.setText(String.valueOf(driverHistory.get(0).getAgId()));
//        txtAccept = (TextView) findViewById(R.id.txtAccept);
//        txtAccept.setText(String.valueOf(driverHistory.get(0).getIsAccepted()));
//        txtReportID = (TextView) findViewById(R.id.txtReportID);
//        txtReportID.setText(String.valueOf(driverHistory.get(0).getrId()));
//        txtReportDate = (TextView) findViewById(R.id.txtReportDate);
//        txtReportDate.setText(driverHistory.get(0).getrDate().toString());
//        txtReportCost = (TextView) findViewById(R.id.txtReportCost);
//        txtReportCost.setText(String.valueOf(driverHistory.get(0).getrCost()));
//        txtDescription = (TextView) findViewById(R.id.txtDescription);
//        txtDescription.setText(driverHistory.get(0).getrDescription());
    }
}