package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CostEstimationActivity2 extends AppCompatActivity implements View.OnClickListener{
    Button Enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_estimation2);
        init();
    }
    private void init(){
        Enter =(Button) findViewById(R.id.button7);
       // CreateAccountLink = (TextView) findViewById(R.id.tRegisterLink);

        Enter.setOnClickListener(this);
       // CreateAccountLink.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button7:
                startActivity( new Intent(this, com.example.myapplication.CostEstimationActivity3.class));
                break;

        }

    }
}