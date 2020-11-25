package com.example.myapplication.model;

public class User {
    private int id;
    private String email;
    private String token;

    public int getId(int id){
        return  id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getEmail(String email){
        return  email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getToken(String token){
        return  token;
    }
    public void setToken(String token){
        this.token=token;
    }

}
