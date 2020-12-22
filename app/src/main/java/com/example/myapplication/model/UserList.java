package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserList {
    @SerializedName("users")
    List<users> users;

    public List<com.example.myapplication.model.users> getUsers() {
        return users;
    }

    public void setUsers(List<com.example.myapplication.model.users> users) {
        this.users = users;
    }
}
