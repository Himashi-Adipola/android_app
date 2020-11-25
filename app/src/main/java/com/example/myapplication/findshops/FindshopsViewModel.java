package com.example.myapplication.findshops;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FindshopsViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public FindshopsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is find shops fragment");
    }



    public LiveData<String> getText() {
        return mText;
    }
}
