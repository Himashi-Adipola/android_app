package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class CostPrediction {

    @SerializedName("damageType")
    String damageType;


    @SerializedName("category")
    String category;

    @SerializedName("cost")
    int cost;


    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "CostPrediction{" +
                "damageType='" + damageType + '\'' +
                ", category='" + category + '\'' +
                ", cost=" + cost +
                '}';
    }
}
