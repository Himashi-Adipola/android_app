package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelResponse {
    @SerializedName("predictions")
    @Expose
    private Prediction predictions;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public Prediction getPredictions() {
        return predictions;
    }

    public void setPredictions(Prediction predictions) {
        this.predictions = predictions;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
