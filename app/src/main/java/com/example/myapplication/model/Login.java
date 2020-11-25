package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("device_name")
    private String device_name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

//    public Login(String email, String password, String device_name){
//        this.email=email;
//        this.password=password;
//        this.device_name=device_name;
//    }
}
