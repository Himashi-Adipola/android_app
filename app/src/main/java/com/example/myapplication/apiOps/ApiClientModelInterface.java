package com.example.myapplication.apiOps;

import com.example.myapplication.model.ModelResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiClientModelInterface {
    @Multipart
    @POST("/predict")
    Call<ModelResponse> uploadImage(@Part MultipartBody.Part file);
}
