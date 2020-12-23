package com.example.myapplication.apiOps;

import com.example.myapplication.model.Agent;
import com.example.myapplication.model.Login;
import com.example.myapplication.model.MyPlaces;
import com.example.myapplication.model.Report;
import com.example.myapplication.model.User;
import com.example.myapplication.model.UserList;
import com.example.myapplication.model.VehicleList;
import com.example.myapplication.model.users;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface UserClient {

//    @POST("login")
//    Call<Login> LoginAPPP(@Body Login login);
    @POST("mobile_login")
    Call<JSONObject> LoginAPPP(@Body Login Login);

    @POST("register")
    Call<JSONObject> register(@Body users user);

    @GET("get_login/{email}")
    Call<UserList> getLogin (@Path("email") String email);

    @GET("get_vehiclenumbers/{nic}")
    Call<VehicleList> getVehicleList(@Path("nic") String nic);

    @POST("put_report")
    Call<JSONObject> putReport(@Body Report report);

    @GET("get_agent/{email}")
    Call<List<Agent>> getAgent (@Path("email") String email);

}
