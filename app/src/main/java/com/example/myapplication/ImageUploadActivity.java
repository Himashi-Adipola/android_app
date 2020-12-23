package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.apiOps.ApiClientModel;
import com.example.myapplication.apiOps.ApiClientModelInterface;
import com.example.myapplication.apiOps.LoginAuth;
import com.example.myapplication.apiOps.UserClient;
import com.example.myapplication.model.Login;
import com.example.myapplication.model.ModelResponse;
import com.example.myapplication.model.Prediction;
import com.example.myapplication.model.Report;
import com.example.myapplication.model.Vehicle;
import com.example.myapplication.model.VehicleList;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageUploadActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btn_choose,btn_choose2,btn_choose3,btn_choose4 ,submit_report, generate_report,submit_report2,submit_report3,submit_report4;
    private ImageView image,image2,image3,image4;
    private static final int STORAGE_PERMISSION_CODE= 124;
    private  static  final int PICK_IMAGE_REQUST =2;
    private Uri filePath;
    private Bitmap bitmap;
    final Prediction prediction=new Prediction();
    ProgressDialog progressDialog;
    private TextView damageType, damageCategory,damageType2,damageCategory2,damageType3,damageCategory3,damageType4,damageCategory4;
    private String[] damageTypes,damageCategories;
    private TextView cost,cost2,cost3,cost4,totalCostText;
    private double totalCost;
    private double[] costs;
    private String[] filePaths;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);
        sharedPreferences = getSharedPreferences("myData", MODE_PRIVATE);
        requestStoragePermission();
        init();
    }
    public void init(){
        submit_report =(Button)findViewById(R.id.btn_submit_report);
        submit_report.setOnClickListener(this);

        submit_report2 =(Button)findViewById(R.id.btn_submit_report1a);
        submit_report2.setOnClickListener(this);

        submit_report3 =(Button)findViewById(R.id.btn_submit_report1b);
        submit_report3.setOnClickListener(this);

        submit_report4 =(Button)findViewById(R.id.btn_submit_report1c);
        submit_report4.setOnClickListener(this);


        image = findViewById(R.id.image);
        image2 = findViewById(R.id.image1a);
        image3 = findViewById(R.id.image1b);
        image4 = findViewById(R.id.image1c);

        btn_choose =findViewById(R.id.btn_choose);
        btn_choose.setOnClickListener(this);

        btn_choose2 =findViewById(R.id.btn_choose1a);
        btn_choose2.setOnClickListener(this);

        btn_choose3 =findViewById(R.id.btn_choose1b);
        btn_choose3.setOnClickListener(this);

        btn_choose4 =findViewById(R.id.btn_choose1c);
        btn_choose4.setOnClickListener(this);

        damageType =(TextView) findViewById(R.id.damage_type);
        damageCategory =(TextView) findViewById(R.id.damage_Category);
        cost =(TextView) findViewById(R.id.cost_estimate);

        damageType2 =(TextView) findViewById(R.id.damage_type1a);
        damageCategory2 =(TextView) findViewById(R.id.damage_Category1a);
        cost2 =(TextView) findViewById(R.id.cost_estimate1a);

        damageType3 =(TextView) findViewById(R.id.damage_type1b);
        damageCategory3 =(TextView) findViewById(R.id.damage_Category1b);
        cost3 =(TextView) findViewById(R.id.cost_estimate1b);

        damageType4 =(TextView) findViewById(R.id.damage_type1c);
        damageCategory4 =(TextView) findViewById(R.id.damage_Category1c);
        cost4 =(TextView) findViewById(R.id.cost_estimate1c);

        totalCostText = (TextView) findViewById(R.id.total_cost);

        generate_report =(Button)findViewById(R.id.generate_Report);
        generate_report.setOnClickListener(this);

        String role = sharedPreferences.getString("role","agent");
        if(role.equals("agent")){
            generate_report.setVisibility(View.VISIBLE);
        }else{
            generate_report.setVisibility(View.INVISIBLE);
        }

        damageTypes = new String[4];
        damageCategories = new String[4];
        costs = new double[4];
        filePaths= new String[4];
        initCost();
        totalCost=0;

    }

    private void requestStoragePermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
            return;
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode ==STORAGE_PERMISSION_CODE){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"permisson Granted",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this,"permisson  not Granted",Toast.LENGTH_LONG).show();

            }
        }
    }

    private void showFileChooser(int requestCode){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select Picture"),requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            if (requestCode == 1) {
                SetImage(image,filePath);
                filePaths[0] = getPath(filePath);
            }else if (requestCode == 2){
                SetImage(image2,filePath);
                filePaths[1] = getPath(filePath);
            }else if(requestCode == 3){
                SetImage(image3,filePath);
                filePaths[2] = getPath(filePath);
            }else{
                SetImage(image4,filePath);
                filePaths[3] = getPath(filePath);
            }
        }
    }

    private void SetImage(ImageView image, Uri filePath) {
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            image.setImageBitmap(bitmap);

//                uploadToServer(getPath(filePath));
        } catch (IOException e) {

        }
    }

    private String getPath(Uri uri){
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        cursor.moveToFirst();
        String documentId = cursor.getString(0);
        documentId = documentId.substring(documentId.lastIndexOf(":") +1);
        cursor.close();
        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID +"=?", new String[]{documentId},null
        );
        cursor.moveToFirst();
        String path  =cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_choose:
//                requestStoragePermission();
                showFileChooser(1);
                break;
            case R.id.btn_choose1a:
//                requestStoragePermission();
                showFileChooser(2);
                break;
            case R.id.btn_choose1b:
//                requestStoragePermission();
                showFileChooser(3);
                break;
            case R.id.btn_choose1c:
//                requestStoragePermission();
                showFileChooser(4);
                break;
            case R.id.btn_submit_report:
                uploadToServer(damageType,damageCategory,cost,0);
//                startActivity(new Intent(this, GenarateReportActivity.class));
//                startActivity( new Intent(this,com.example.myapplication.MainActivity.class));
//                requestStoragePermission();
//                showFileChooser();
                break;
            case R.id.btn_submit_report1a:
                uploadToServer(damageType2,damageCategory2,cost2,1);
//
                break;
            case R.id.btn_submit_report1b:
                uploadToServer(damageType3,damageCategory3,cost3,2);
//
                break;
            case R.id.btn_submit_report1c:
                uploadToServer(damageType4,damageCategory4,cost4,3);
//
                break;
            case R.id.generate_Report:
                putReport();
                Intent i = new Intent(getApplicationContext(), GenarateReportActivity.class);
                i.putExtra("damageTypes", damageTypes);
                i.putExtra("damageCategories",damageCategories);
                i.putExtra("filePaths", filePaths);
                i.putExtra("totalCost",totalCost);
                i.putExtra("costs",costs);
                startActivity(i);
//

//
        }

    }

    private void bindData(){
//        claimId.setText(Integer.toString(registerId1));
//        year.setText(Integer.toString(year1));
//        policyNumber.setText(Integer.toString(policyNumber1));
//        OwnerName.setText(ownerName1);
//        Model.setText(model1);
//        VehicleColor.setText(colour1);


    }

    private void putReport(){
        Report report =new Report();
        report.setVehicleNumber(sharedPreferences.getString("vehicleno","xx-xxxx"));
        report.setPlace(sharedPreferences.getString("place","place"));
        report.setrDate(sharedPreferences.getString("date","xx-xx-xxxx"));
        report.setrCost(totalCost);
        report.setrDescription(sharedPreferences.getString("description","description"));
        report.setAgId(sharedPreferences.getInt("agId",0));


        UserClient userService = LoginAuth.getClient().create(UserClient.class);

        Call<JSONObject>call = userService.putReport(report);

        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.isSuccessful()) {
                    System.out.println("response body" + response.body());


                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

                } else {
                    try {
                        Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {

                    }
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {

            }
        });
    }

    private void uploadToServer(TextView damageType, TextView damageCategory,TextView cost,int index ) {
        progressDialog = ProgressDialog.show(this, "", "Please wait");
        ApiClientModelInterface userService = ApiClientModel.getClient().create(ApiClientModelInterface.class);
        //Create a file object using file path
        String path = getPath(filePath);
        File file = new File(path);
        // Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileReqBody);
        //Create request body with text description and text media type
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), file.getName());
        //
        Call call = userService.uploadImage(part);
        System.out.println(call.request());
        call.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {

                if (response.isSuccessful()) {

                    prediction.setDamageCategory(response.body().getPredictions().getDamageCategory());
                    prediction.setDamageType(response.body().getPredictions().getDamageType());

                    damageType.setText( getString(R.string.damage_type_text)+prediction.getDamageType());
                    damageCategory.setText(getString(R.string.damage_cat_text)+prediction.getDamageCategory());
                    double costValue = getCost(prediction.getDamageType(),prediction.getDamageCategory());
                    cost.setText(getString(R.string.estimate_cost_text) + Double.toString(costValue));
                    damageTypes[index]=prediction.getDamageType();
                    damageCategories[index]=prediction.getDamageCategory();
                    costs[index]=costValue;
                    totalCost = getTotalCost();
                    totalCostText.setText("Rs: "+Double.toString(totalCost));
                    progressDialog.cancel();

//                    System.out.println("resposesssssss body" + prediction.setDamageCategory(response.body().getPredictions().getDamageCategory()));
//                    Intent intent = new Intent(getApplicationContext(), DriverHistoryActivity.class);
//                    Gson gson =new Gson();
//                    String productObject =gson.toJson(prediction);
//                    intent.putExtra("registerId", registerId);
////                    intent.putExtra("image",imageBytes);
//                    intent.putExtra("product",productObject);
//                    startActivity(intent);

                } else {
                    try {
                        Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {

                    }
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    private double getCost(String damageType, String damageCategory){
        double cost=0.00;
        if(damageType.equals("minor")){
            switch(damageCategory){
                case "doordent": cost = 2000.00; break;
                case "bumperdent": cost = 6500.00; break;
                case "glass_shatter": cost = 11000.00; break;
                case "head_lamp_broken": cost = 6000.00; break;
                case "nodamage": cost = 0.00; break;
                case "scratches": cost = 7000.00; break;
                case "tail_lamp_broken": cost = 6000.00; break;
                case "smashes": cost = 400000.00; break;
            }
        }else if(damageType.equals("moderate")){
            switch(damageCategory){
                case "doordent": cost = 3000.00; break;
                case "bumperdent": cost = 7500.00; break;
                case "glass_shatter": cost = 12500.00; break;
                case "head_lamp_broken": cost = 6500.00; break;
                case "nodamage": cost = 0.00; break;
                case "scratches": cost = 7500.00; break;
                case "tail_lamp_broken": cost = 6500.00; break;
                case "smashes": cost = 400000.00; break;
            }
        }else if(damageType.equals("severe")){
            switch(damageCategory){
                case "doordent": cost = 4000.00; break;
                case "bumperdent": cost = 8000.00; break;
                case "glass_shatter": cost = 14000.00; break;
                case "head_lamp_broken": cost = 7000.00; break;
                case "nodamage": cost = 0.00; break;
                case "scratches": cost = 8000.00; break;
                case "tail_lamp_broken": cost = 7000.00; break;
                case "smashes": cost = 400000.00; break;
            }
        }


        return cost;
    }

    private double getTotalCost(){
        double total=0.00;
        for(int i=0; i<4; i++){
            total=total+costs[i];
        }
        return total;
    }
    private void initCost(){
        for(int i=0; i<4; i++){
            costs[i] = 0.00;
        }    }
}