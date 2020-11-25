package com.example.myapplication.costestimation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CostestimationViewModel extends ViewModel{
    private MutableLiveData<String> mText;
    public CostestimationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is cost estimation fragment");
    }



    public LiveData<String> getText() {
        return mText;
    }

}
