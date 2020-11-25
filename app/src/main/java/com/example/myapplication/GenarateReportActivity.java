package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.apiOps.ApiInterface;
import com.example.myapplication.apiOps.LoginAuth;
import com.example.myapplication.model.CostPrediction;

import io.reactivex.internal.observers.InnerQueuedObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenarateReportActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtDamageType;
    private TextView txtDamageCategory;
    private TextView txtPrice;
    private Button BtnButton;
    String damageType;
    String damageCategory;
    int cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genarate_report);
        Intent intent = getIntent();
        damageType = intent.getStringExtra("damageType");
        damageCategory = intent.getStringExtra("damageCategory");

        init();
    }

    private void init() {
        txtDamageType = (TextView) findViewById(R.id.txtDamageType);
        txtDamageType.setText(damageType);
        txtDamageCategory = (TextView) findViewById(R.id.txtDamageCategory);
        txtDamageCategory.setText(damageCategory);
        txtPrice = (TextView) findViewById(R.id.txtPrice); /// check this

        BtnButton = (Button) findViewById(R.id.btnButton);
        BtnButton.setOnClickListener(this);
        getCostData();
//        txtPrice = (TextView) findViewById(R.id.txtPrice);
//        txtDamageType.setText(damageType);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnButton:
                Intent i = new Intent(getApplicationContext(), SendEmailActivity.class);
                i.putExtra("damageType", damageType);
                i.putExtra("damageCategory", damageCategory);
                startActivity(i);
                break;

        }
    }


    private void getCostData() {
        System.out.println("GEttttttttttttttt" + damageType + damageCategory);

        ApiInterface getCost = LoginAuth.getClient().create(ApiInterface.class);
        Call<CostPrediction> call = getCost.getByBoth(damageType,damageCategory);

        call.enqueue(new Callback<CostPrediction>() {
            @Override
            public void onResponse(Call<CostPrediction> call, Response<CostPrediction> response) {
                if (response.isSuccessful()) {
                 cost =response.body().getCost();

                    txtPrice.setText(Integer.toString(cost));
//                    RepireCost.setText(Integer.toString(repaircost));
                     System.out.print("repire cost"+cost);

                } else {


                }
            }

            @Override
            public void onFailure(Call<CostPrediction> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }
}


