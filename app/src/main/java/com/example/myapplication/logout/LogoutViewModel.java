package com.example.myapplication.logout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LogoutViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public LogoutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is faq fragment");
    }



    public LiveData<String> getText() {
        return mText;
    }
}
