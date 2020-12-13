package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.apiOps.ApiInterface;
import com.example.myapplication.apiOps.LoginAuth;
import com.example.myapplication.model.CostPrediction;

import io.reactivex.internal.observers.InnerQueuedObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenarateReportActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtVehicleNo,txtPolicyHolderName,txtPolicyHolderNIC,txtAgentName,txtTotalCost;
    private ListView listView;
    private Button BtnButton;
    String[] damageTypes;
    String[] damageCategories;
    String[] filePaths;
    double[] costs;
    double totalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genarate_report);
        Intent intent = getIntent();
        damageTypes = intent.getStringArrayExtra("damageTypes");
        damageCategories = intent.getStringArrayExtra("damageCategories");
        filePaths = intent.getStringArrayExtra("filePaths");
        totalCost = intent.getDoubleExtra("totalCost",0.0);
        costs = intent.getDoubleArrayExtra("costs");

        listView = findViewById(R.id.damage_image_list);
        ClaimImageListView claimImageListView = new ClaimImageListView(this,damageTypes,damageCategories,filePaths,costs);
        listView.setAdapter(claimImageListView);
        init();
    }

    private void init() {

        txtVehicleNo = findViewById(R.id.txtVehicleNo);
        txtPolicyHolderName = findViewById(R.id.txtPolicyHolderName);
        txtPolicyHolderNIC = findViewById(R.id.txtPolicyHolderNIC);
        txtAgentName = findViewById(R.id.txtAgentName);
        txtTotalCost = findViewById(R.id.txtTotalCost);

        setReportData();

        BtnButton = findViewById(R.id.btnButton);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnButton:
                Intent i = new Intent(getApplicationContext(), SendEmailActivity.class);
             //   i.putExtra("damageType", damageType);
              //  i.putExtra("damageCategory", damageCategory);
                startActivity(i);
                break;

        }
    }

    private void setReportData(){
        txtVehicleNo.setText(getString(R.string.vehicle_no)+" CAT - 6548");
        txtPolicyHolderName.setText(getString(R.string.policy_holder_name)+"K.S.S Fernando");
        txtPolicyHolderNIC.setText(getString(R.string.policy_holder_nic)+"921546587v");
        txtAgentName.setText(getString(R.string.agent_name)+"S.V. Perera");
        txtTotalCost.setText(getString(R.string.estimate_cost_text)+Double.toString(totalCost));
    }

    private void getCostData() {
//        //.out.println("GEttttttttttttttt" + damageType + damageCategory);
//
//        ApiInterface getCost = LoginAuth.getClient().create(ApiInterface.class);
//        Call<CostPrediction> call = getCost.getByBoth(damageType,damageCategory);
//
//        call.enqueue(new Callback<CostPrediction>() {
//            @Override
//            public void onResponse(Call<CostPrediction> call, Response<CostPrediction> response) {
//                if (response.isSuccessful()) {
//                 cost =response.body().getCost();
//
//                    txtPrice.setText(Integer.toString(cost));
////                    RepireCost.setText(Integer.toString(repaircost));
//                     System.out.print("repire cost"+cost);
//
//                } else {
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CostPrediction> call, Throwable t) {
//                System.out.println(t.getMessage());
//            }
//        });
//
    }
}


