package com.example.myapplication;

import android.bluetooth.BluetoothClass;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.apiOps.LoginAuth;
import com.example.myapplication.apiOps.UserClient;
import com.example.myapplication.model.Login;
import com.example.myapplication.model.User;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText Email, Password;
    Button LoginButton;
    TextView PasswordReset;
    String device_name ="browser";
    String token;
    private TextView alertText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("myData", MODE_PRIVATE);
        init();
    }

    private void init(){
        Email = (EditText) findViewById(R.id.email);
        Password =(EditText) findViewById(R.id.password);
        LoginButton =(Button) findViewById(R.id.bLogin);
        PasswordReset = (TextView) findViewById( R.id.resetPassword);

        LoginButton.setOnClickListener(this);
        PasswordReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bLogin:
                loginA();
//                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                builder.setCancelable(true);
//                builder.setTitle("Message");
//                builder.setMessage("You Are Successfully registered");
//                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//
//                    }
//                });
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
////                        alertText.setVisibility(view.VISIBLE);
//                        alertText.setVisibility(view.VISIBLE);
//                    }
//                });
//                builder.show();
                break;


            case R.id.resetPassword:
                startActivity( new Intent(this,ResetPassword.class));
                break;
        }

    }

    private void loginA(){
        Login login = new Login();
      login.setEmail(Email.getText().toString().trim());
      login.setPassword(Password.getText().toString().trim());
      login.setDevice_name(device_name);


        UserClient userService = LoginAuth.getClient().create(UserClient.class);

        Call<JSONObject>call = userService.LoginAPPP(login);

        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.isSuccessful()) {
                    View tv2 = findViewById(R.id.tv2);
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Message");
                builder.setMessage("You Are Successfully login");
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        alertText.setVisibility(view.VISIBLE);
                        tv2.setVisibility(View.VISIBLE);
                    }
                });
                builder.show();

                    System.out.println("response body" + response.body());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email",Email.getText().toString().trim());
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), AgentProfileActivity.class);
                   startActivity(intent);
                } else {
                    try {


                        Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {

                    }
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                View tv2 = findViewById(R.id.tv2);
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Message");
                builder.setMessage("You are not login");
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        alertText.setVisibility(view.VISIBLE);
                        tv2.setVisibility(View.VISIBLE);
                    }
                });
                builder.show();

            }
        });
    }


    }

