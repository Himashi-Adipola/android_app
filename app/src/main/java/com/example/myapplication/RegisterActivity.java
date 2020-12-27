package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.apiOps.ApiClient;
import com.example.myapplication.apiOps.LoginAuth;
import com.example.myapplication.apiOps.UserClient;
import com.example.myapplication.model.users;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText FirstName1, LastName1, Email1,Password1, VerifyPassword1;
    Button Register;
    TextView DirectToLogin;
    String role = "policyholder";
    private TextView alertText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private  void  init(){
        FirstName1 =(EditText) findViewById(R.id.FirstName);
        LastName1 =(EditText) findViewById(R.id.lastName);
        Email1 =(EditText) findViewById(R.id.email);
        Password1 =(EditText)findViewById(R.id.password);
        VerifyPassword1 =(EditText) findViewById(R.id.verifypassword);
        Register =(Button) findViewById(R.id.register);
        DirectToLogin =(TextView) findViewById(R.id.directToLogin);

        Register.setOnClickListener(this);
        DirectToLogin.setOnClickListener(this);



    }

    @Override

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register:
                loadRecylerViewData();
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Massage");
                builder.setMessage("You Are Successfully registered");
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
                        alertText.setVisibility(view.VISIBLE);
                    }
                });
                builder.show();

                 break;
            case R.id.directToLogin:
                startActivity( new Intent(this,LoginActivity.class));
                break;
        }

    }

    private void loadRecylerViewData() {
        users user = new users();
        user.setFirstName(FirstName1.getText().toString());
        user.setLastName(LastName1.getText().toString());
        user.setEmail(Email1.getText().toString());
        user.setPassword(Password1.getText().toString());
        user.setRole(role);
        System.out.println("outputtttttt"+ user);

        UserClient userService = LoginAuth.getClient().create(UserClient.class);

        Call <JSONObject> call = userService.register(user);

        call.enqueue(new Callback<JSONObject>() {

            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.isSuccessful()) {
                    startActivity( new Intent(getApplicationContext(),AgentProfileActivity.class));
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

    }

