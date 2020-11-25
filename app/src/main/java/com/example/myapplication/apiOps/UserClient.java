package com.example.myapplication.apiOps;

import com.example.myapplication.model.Login;
import com.example.myapplication.model.MyPlaces;
import com.example.myapplication.model.User;
import com.example.myapplication.model.users;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface UserClient {

//    @POST("login")
//    Call<Login> LoginAPPP(@Body Login login);
    @POST("login")
    Call<JSONObject> LoginAPPP(@Body Login Login);

    @POST("/register")
    Call<JSONObject> register(@Body users user);



}
