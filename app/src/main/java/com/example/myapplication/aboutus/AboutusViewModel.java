package com.example.myapplication.aboutus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutusViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AboutusViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("ClaimPaul develops artificial intelligence for accident and disaster recovery. From vehicle accidents to natural disasters, our AI looks at photos of damage and predicts repair costs. We’re applying our AI today to tangible problems that affect many thousands of people across the country every day, and making a difference by restoring their livelihoods more quickly and efficiently. Our technology is patented and trained on the largest datasets in the industry");
    }

    public LiveData<String> getText() {
        return mText;
    }
}