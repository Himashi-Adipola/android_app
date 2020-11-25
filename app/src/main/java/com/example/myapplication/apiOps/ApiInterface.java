package com.example.myapplication.apiOps;

import com.example.myapplication.model.CostPrediction;
import com.example.myapplication.model.DriverHistory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

//    @GET("d9f1c2f6-3765-4ec3-9a95-a75a8ae2e9f2")
    @GET("d9f1c2f6-3765-4ec3-9a95-a75a8ae2e9f2")
    Call<List<DriverHistory>> getDriverHistories();

    @GET("/getcost/{damageType}/{damageArea}")
    Call<CostPrediction>getByBoth( @Path("damageType") String damageType, @Path("damageArea") String damageArea );

}
