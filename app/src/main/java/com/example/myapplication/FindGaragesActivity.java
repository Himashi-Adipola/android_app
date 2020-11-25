package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FindGaragesActivity extends AppCompatActivity implements View.OnClickListener {

    Button Contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_garages);
        init();
    }
    private void init(){
        Contact =(Button) findViewById(R.id.button4);


        Contact.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button4:
                startActivity( new Intent(this,FindGaragesActivity2.class));
                break;

        }

    }
}