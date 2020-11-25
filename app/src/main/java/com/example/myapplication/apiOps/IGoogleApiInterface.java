package com.example.myapplication.apiOps;

import com.example.myapplication.model.MyPlaces;
import com.example.myapplication.model.PlaceDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IGoogleApiInterface {
    @GET
    Call<MyPlaces> getNearByPlaces(@Url String url);

    @GET
    Call<PlaceDetail> getDetailPlace(@Url String url);



}
