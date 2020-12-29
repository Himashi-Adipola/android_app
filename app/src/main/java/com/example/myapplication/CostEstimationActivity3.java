package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CostEstimationActivity3 extends AppCompatActivity implements View.OnClickListener{
    Button Enter;
    ListView vehicleList;
    List<String> vehicles;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_estimation3);
        vehicles = new ArrayList<>();
        sharedPreferences = getSharedPreferences("myData", MODE_PRIVATE);
        Intent intent = getIntent();
        vehicles = intent.getStringArrayListExtra("vehicles");

        vehicleList = findViewById(R.id.vehicle_list);
        VehicleListAdapter vehicleListAdapter = new VehicleListAdapter(this,vehicles);
        vehicleList.setAdapter(vehicleListAdapter);

        vehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),  vehicles.get(position), Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("vehicleno",vehicles.get(position));
                editor.apply();
                startActivity( new Intent(parent.getContext(),ImageUploadActivity.class));
            }
        });

    }



    @Override
    public void onClick(View view) {


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}