package com.example.myapplication;

import com.example.myapplication.apiOps.ApiClientMap;
import com.example.myapplication.apiOps.IGoogleApiInterface;
import com.example.myapplication.model.Results;

public class Common {
    public  static Results currentResults;

    private static final String GOOGLE_API_URL ="https://maps.googleapis.com/";
    public  static IGoogleApiInterface getGoogleApiInterface(){

        return ApiClientMap.getClient(GOOGLE_API_URL).create(IGoogleApiInterface.class);
    }
}
