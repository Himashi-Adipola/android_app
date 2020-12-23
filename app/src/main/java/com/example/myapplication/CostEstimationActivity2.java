package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.apiOps.LoginAuth;
import com.example.myapplication.apiOps.UserClient;
import com.example.myapplication.model.UserList;
import com.example.myapplication.model.Vehicle;
import com.example.myapplication.model.VehicleList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CostEstimationActivity2 extends AppCompatActivity implements View.OnClickListener{
    Button Enter;
    EditText nic;
    EditText place;
    DatePicker datePicker;
    EditText description;
    ArrayList<String> vehicles;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_estimation2);
        vehicles = new ArrayList<>();
        init();
    }

    private void init(){
        Enter =(Button) findViewById(R.id.button7);
        nic = findViewById(R.id.editTextTextPersonName2);
        place = findViewById(R.id.textPlace);
        datePicker = findViewById(R.id.accDate);
        description = findViewById(R.id.txtdiscription);
       // CreateAccountLink = (TextView) findViewById(R.id.tRegisterLink);
        sharedPreferences = getSharedPreferences("myData", MODE_PRIVATE);


        Enter.setOnClickListener(this);
       // CreateAccountLink.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button7:
                String date = datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth();
                Toast.makeText(getApplicationContext(),date,Toast.LENGTH_SHORT);

                getVehicleList();
                break;

        }

    }

    public void getVehicleList(){
        UserClient userService = LoginAuth.getClient().create(UserClient.class);

        Call<VehicleList> call = userService.getVehicleList(nic.getText().toString().trim());

        call.enqueue(new Callback<VehicleList>() {
            @Override
            public void onResponse(Call<VehicleList> call, Response<VehicleList> response) {
                if (response.isSuccessful()) {

                            List<Vehicle> vehicleList = response.body().getVehicle();

                            for(Vehicle vehicle:vehicleList){
                                vehicles.add(vehicle.getVehicleNumber());
                            }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("policyholdername",vehicleList.get(0).getfName()+" "+vehicleList.get(0).getlName());
                    editor.putString("nic",nic.getText().toString().trim());
                    editor.putString("place",place.getText().toString().trim());
                    int month = datePicker.getMonth()+1;
                    String date = datePicker.getYear()+"-"+month+"-"+datePicker.getDayOfMonth();
                    editor.putString("date",date);
                    editor.putString("description",description.getText().toString().trim());
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), com.example.myapplication.CostEstimationActivity3.class);
                    intent.putExtra("vehicles",vehicles);

                    startActivity(intent);


                } else {

                        Toast.makeText(getApplicationContext(), "nic required", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<VehicleList> call, Throwable t) {

            }
        });
    }
}