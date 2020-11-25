package com.example.myapplication.driverhistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DriverhistoryViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public DriverhistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is driver history fragment");
    }



    public LiveData<String> getText() {
        return mText;
    }

}
