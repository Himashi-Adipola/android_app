package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FAQActivity extends AppCompatActivity {
    TextView Q1new,Q1,A1,Q2,A2,Q3,A3,Q4,A4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_q);
        Q1new=(TextView)findViewById(R.id.q1new);
        Q1=(TextView)findViewById(R.id.q1);
        A1=(TextView)findViewById(R.id.a1);
        Q2=(TextView)findViewById(R.id.q2);
        A2=(TextView)findViewById(R.id.a2);
        Q3=(TextView)findViewById(R.id.q3);
        A3=(TextView)findViewById(R.id.a3);
        Q4=(TextView)findViewById(R.id.q4);
        A4=(TextView)findViewById(R.id.a4);

        Button btn=(Button)findViewById(R.id.callnow);
        Button btn1=(Button)findViewById(R.id.messagenow);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0711279637"));
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + 711279637));
                //intent.putExtra("sms_body", message);
                startActivity(intent);
            }
        });
    }
    public void q1Clicked(View v){
        A1.setVisibility(View.VISIBLE);
        A2.setVisibility(View.GONE);
        A3.setVisibility(View.GONE);
        A4.setVisibility(View.GONE);
    }
    public void q2Clicked(View v){
        A2.setVisibility(View.VISIBLE);
        A1.setVisibility(View.GONE);
        A3.setVisibility(View.GONE);
        A4.setVisibility(View.GONE);
    }
    public void q3Clicked(View v){
        A3.setVisibility(View.VISIBLE);
        A1.setVisibility(View.GONE);
        A2.setVisibility(View.GONE);
        A4.setVisibility(View.GONE);
    }
    public void q4Clicked(View v){
        A4.setVisibility(View.VISIBLE);
        A1.setVisibility(View.GONE);
        A2.setVisibility(View.GONE);
        A3.setVisibility(View.GONE);
    }
}