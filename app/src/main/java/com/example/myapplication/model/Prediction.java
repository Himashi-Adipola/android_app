package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prediction {

    @SerializedName("damage_category")
    @Expose
    private String damageCategory;
    @SerializedName("damage_type")
    @Expose
    private String damageType;

    public String getDamageCategory() {
        return damageCategory;
    }

    public void setDamageCategory(String damageCategory) {
        this.damageCategory = damageCategory;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }
}
