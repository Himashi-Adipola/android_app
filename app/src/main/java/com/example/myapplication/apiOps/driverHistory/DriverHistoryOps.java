package com.example.myapplication.apiOps.driverHistory;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.myapplication.apiOps.ApiInterface;
import com.example.myapplication.model.DriverHistory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.ContextCompat.startActivity;

/*
* this class basically do
* 1  send get call to driver history get endpoint and retrieve all driver histories
* 2. add retrieved driver history objects to array
* 3. filter driver history using nic number
* 4. set driver history activity view if driver history available
* 5. else send thoest msg saying driver history not available
* */
public class DriverHistoryOps {

    private ApiInterface apiInterface;
    private static final String TAG = "DriverHistoryVerifyActivity";

    List<DriverHistory> driverHistories = new ArrayList<>();

    //set when matched to provided nic
    private static DriverHistory driverHistory = null;

    public void getDriveHistories(String driverNIC)
    {
        Call<List<DriverHistory>> driverHistoryGetCall = apiInterface.getDriverHistories();

        driverHistoryGetCall.enqueue(new Callback<List<DriverHistory>>() {
            @Override
            public void onResponse(Call<List<DriverHistory>> call, Response<List<DriverHistory>> response) {
                // add all driver histories to our list
                driverHistories.addAll(response.body());
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<List<DriverHistory>> call, Throwable t) {

                // oya localHost url ekata point karama methanin exception eka paniwi eka firewall eken hadaganna
                // error eka search karala

                Log.e(TAG,"error : "+t.getMessage());
            }
        });
    }

    public boolean goToDriverHistoryView( List<DriverHistory> driverHistoryList,String driverNIC)
    {
        boolean driverAvailable = false;
        for (DriverHistory driverHistory : driverHistoryList)
        {
            if (driverHistory.getNic().equals(driverNIC))
            {
                this.driverHistory = driverHistory;
                driverAvailable =  true;
            }
        }
        return driverAvailable;
    }

    public static DriverHistory getDriverHistory()
    {
        return driverHistory;
    }

    public void setApiInterface(ApiInterface apiInterface)
    {
        this.apiInterface = apiInterface;
    }
}
