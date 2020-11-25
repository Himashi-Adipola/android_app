package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FindSparePartsActivity extends AppCompatActivity implements View.OnClickListener{
    Button Contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_spare_parts);
        init();
    }
    private void init(){
        Contact=(Button) findViewById(R.id.button5);


        Contact.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button5:
                startActivity( new Intent(this,FindSparePartsActivity2.class));
                break;

        }

    }
}