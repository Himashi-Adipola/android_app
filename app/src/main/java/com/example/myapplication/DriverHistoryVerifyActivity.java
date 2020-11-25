package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.apiOps.ApiClient;
import com.example.myapplication.apiOps.ApiInterface;
import com.example.myapplication.apiOps.driverHistory.DriverHistoryOps;
import com.example.myapplication.model.DriverHistory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class DriverHistoryVerifyActivity extends AppCompatActivity implements View.OnClickListener{

    // initialize parameters
    Button btnDriverHistory;
    EditText txtDriverNIC;
    ApiInterface apiInterface;

    List<DriverHistory> driverHistories = new ArrayList<>();

    //set when matched to provided nic
    private DriverHistory driverHistory = null;

    private static final String TAG = "DriverHistoryVerifyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_history_verify);
        init();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    private void init(){
        btnDriverHistory =(Button) findViewById(R.id.btnDriverHistory);
        txtDriverNIC = findViewById(R.id.txtNIC);
        btnDriverHistory.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        DriverHistoryOps driverHistoryOps = new DriverHistoryOps();
        driverHistoryOps.setApiInterface(apiInterface);
        String driverNIC = "";

        if (view.getId() ==  R.id.btnDriverHistory)
        {
            driverNIC = txtDriverNIC.getText().toString();
            getDriveHistories(view);
            //driverHistoryOps.getDriveHistories(driverNIC);
        }
        if (driverHistoryOps.goToDriverHistoryView(driverHistories,driverNIC))
        {
            startActivity( new Intent(this, com.example.myapplication.DriverHistoryActivity.class));
        }


       /* switch (view.getId()) {
            case R.id.btnDriverHistory:
                startActivity( new Intent(this, com.example.myapplication.DriverHistoryActivity.class));
                break;

        }
        getDriveHistories(view);*/

    }

    // send backend call for get driver histories
    public void getDriveHistories(View view)
    {
        Call<List<DriverHistory>> driverHistoryGetCall = apiInterface.getDriverHistories();

        driverHistoryGetCall.enqueue(new Callback<List<DriverHistory>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<List<DriverHistory>> call, Response<List<DriverHistory>> response) {
                Log.e(TAG,"response body : " + response.body().get(0).getlName());
                driverHistories.addAll(response.body());
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<List<DriverHistory>> call, Throwable t) {
                Log.e(TAG,"error : "+t.getMessage());
            }
        });
    }
}