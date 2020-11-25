package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;

import android.content.Intent;
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
import com.example.myapplication.model.ModelResponse;
import com.example.myapplication.model.Prediction;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageUploadActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btn_choose ,submit_report, generate_report;
    private ImageView image;
    private static final int STORAGE_PERMISSION_CODE= 124;
    private  static  final int PICK_IMAGE_REQUST =2;
    private Uri filePath;
    private Bitmap bitmap;
    final Prediction prediction=new Prediction();
    ProgressDialog progressDialog;
    private TextView damageType, damageCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);
        requestStoragePermission();
        init();
    }
    public void init(){
        submit_report =(Button)findViewById(R.id.btn_submit_report);
        submit_report.setOnClickListener(this);
        image = findViewById(R.id.image);
        btn_choose =findViewById(R.id.btn_choose);
        btn_choose.setOnClickListener(this);

        damageType =(TextView) findViewById(R.id.damage_type);

        damageCategory =(TextView) findViewById(R.id.damage_Category);
        generate_report =(Button)findViewById(R.id.generate_Report);
        generate_report.setOnClickListener(this);


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

    private void showFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select Picture"),PICK_IMAGE_REQUST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==PICK_IMAGE_REQUST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                image.setImageBitmap(bitmap);

//                uploadToServer(getPath(filePath));
            }catch (IOException e){

            }
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
                showFileChooser();
                break;
            case R.id.btn_submit_report:
                uploadToServer();
//                startActivity(new Intent(this, GenarateReportActivity.class));
//                startActivity( new Intent(this,com.example.myapplication.MainActivity.class));
//                requestStoragePermission();
//                showFileChooser();
                break;
            case R.id.generate_Report:
                Intent i = new Intent(getApplicationContext(), GenarateReportActivity.class);
                i.putExtra("damageType", prediction.getDamageType());
                i.putExtra("damageCategory",prediction.getDamageCategory());
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

    private void uploadToServer( ) {
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

                    damageType.setText(damageType.getText() +" "+ prediction.getDamageType());
                    damageCategory.setText(damageCategory.getText() +" "+ prediction.getDamageCategory());

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
}