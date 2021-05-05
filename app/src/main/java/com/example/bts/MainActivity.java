package com.example.bts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bts.model.Login;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String URL_BASE = "http://18.139.50.74:8080/";

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(URL_BASE + "/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();
    APIServiceLogin apiServiceLogin = retrofit.create(APIServiceLogin.class);

    SharedPreferences sharedPreferences;
    private EditText usernames;
    private EditText passwords;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText usernameEt = (EditText)findViewById(R.id.username);
        EditText passwordEt = (EditText)findViewById(R.id.password);
        Button loginButton = (Button)findViewById(R.id.loginbutton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(usernameEt.getText().toString()) || TextUtils.isEmpty(passwordEt.getText().toString())){
                    Toast.makeText(MainActivity.this, "Username / Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    usernames = usernameEt;
                    passwords = passwordEt;
                    FunLogin();
                }
            }
        });
    }



    public void FunLogin(){
        Login loginData = new Login(usernames.getText().toString(),passwords.getText().toString());
        Log.i("username",usernames.getText().toString());
        Call<LoginResponse> call = apiServiceLogin.login(loginData);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()){
                    token = response.body().getData().getToken();
                    Log.i("tokens",token);
                    sharedPreferences = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token",token);
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                    startActivity(intent);
                }
                else Toast.makeText(MainActivity.this, "Username / Password Salah", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Terjadi Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}