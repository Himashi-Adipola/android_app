package com.example.myapplication.services;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ServicesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ServicesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("It is not uncommon for people to use vehicles during their busy lives, as they become more and more used to the needs of people.The ClaimPaul mobile application is a great idea to help speed up the process of repair vehicles and claim insurance due to the heavy traffic congestion in the major cities busy environment.Therefore, We need a lot of service providers to full fill above those jobs which created more job opportunities.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}