package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences sharedPreferences;

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

        sharedPreferences = getSharedPreferences("myData", MODE_PRIVATE);

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
        BtnButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnButton:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"claimpaul1@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, getSubject());
                email.putExtra(Intent.EXTRA_TEXT, getMessage());

                //need this to prompts email client only
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
                break;

        }
    }

    private void setReportData(){
        txtVehicleNo.setText(getString(R.string.vehicle_no)+sharedPreferences.getString("vehicleno","xx-xxxx"));
        txtPolicyHolderName.setText(getString(R.string.policy_holder_name)+sharedPreferences.getString("policyholdername","name"));
        txtPolicyHolderNIC.setText(getString(R.string.policy_holder_nic)+sharedPreferences.getString("nic","xxxxxxxxxv"));
        txtAgentName.setText(getString(R.string.agent_name)+sharedPreferences.getString("username","name"));
        txtTotalCost.setText(getString(R.string.estimate_cost_text)+Double.toString(totalCost));
    }

    private String getMessage(){
        String message;
        message = "Vehicle No: "+ sharedPreferences.getString("vehicleno","xx-xxxx")+"\n";
        message += "Policy Holder Name: "+sharedPreferences.getString("policyholdername","name")+"\n";
        message += "Agent Name: "+sharedPreferences.getString("username","name")+"\n";
        message += "Description: "+sharedPreferences.getString("description","description")+"\n";
        message += "Date: "+sharedPreferences.getString("date","date")+"\n";
        message += "Place: "+sharedPreferences.getString("place","place")+"\n";
        message += "Estimate Cost: "+Double.toString(totalCost)+"\n";


        return message;
    }

    private String getSubject(){
        String subject;
        subject = "Policy Holder "+sharedPreferences.getString("policyholdername","name")+
                " Vehicle No: "+ sharedPreferences.getString("vehicleno","xx-xxxx")+ " Cost Estimation Report";

        return subject;
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


