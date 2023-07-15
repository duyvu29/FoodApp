package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.foodapp.JsonInterfaceApi.JsonApi;
import com.example.foodapp.ModelAPI.User;
import com.example.foodapp.RetrofitApi.UserApi;
import com.example.foodapp.databinding.ActivityLoginBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFoodActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private List<User> mListusers;
    public JsonApi jsonApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mListusers = new ArrayList<>();


        loginRetrofit();
        event();

    }

    private void event() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoginValue()){
                  checkUserInformation();
                }
            }
        });
        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent i = new Intent(getApplicationContext(), RegisterFoodActivity.class);
                    startActivity(i);

            }
        });

        binding.tvForgotPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fogot = new Intent(getApplicationContext(),ForgotPassActivity.class);
                startActivity(fogot);
            }
        });

    }
    // check pass and account
    private void checkUserInformation(){
        Handler handler = new Handler();
        
        String account = binding.edtAccount.getText().toString().trim();
        String pass    = binding.edtPassword.getText().toString().trim();
        String phone   = binding.edtAccount.getText().toString().trim();

        if (mListusers == null || mListusers.isEmpty()){
            return;
        }
        Boolean isHasUser = false;
        for (User user : mListusers ){
            if (account.equals(user.getAccount())  && pass.equals(user.getPassword())  ){
                isHasUser = true;
                break;
            }
        }
        for (User user : mListusers ){
            if (account.equals(user.getPhone())  && pass.equals(user.getPassword())  ){
                isHasUser = true;
                break;
            }
        }
        if (isHasUser){
            Toast.makeText(this, "Thành công", Toast.LENGTH_SHORT).show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intentHome = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intentHome);
                }
            },500);
           
        } else {
            Toast.makeText(this, "Tài khoàn hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
        }

    }


    //set login retrofit
    public void loginRetrofit(){
        jsonApi = new UserApi().getJson();
        Call<List<User>> call = jsonApi.setLogin();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    mListusers= response.body();
                    Log.d("login", "onResponse: " + response.body().toString());

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(LoginFoodActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public boolean isLoginValue(){
        if (binding.edtAccount.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Enter account", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtPassword.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
        }
        return true;
    }



}