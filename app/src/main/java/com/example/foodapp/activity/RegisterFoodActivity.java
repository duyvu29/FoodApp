package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.BoringLayout;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.foodapp.JsonInterfaceApi.JsonApi;
import com.example.foodapp.ModelAPI.User;
import com.example.foodapp.R;
import com.example.foodapp.RetrofitApi.UserApi;
import com.example.foodapp.databinding.ActivityRegisterFoodBinding;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterFoodActivity extends AppCompatActivity {

    private ActivityRegisterFoodBinding binding;
    JsonApi jsonApi;

    private int id;

    private String name;

    private String Phone;

    private String account;

    private String password;

    private String address ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_register_food);
        binding = ActivityRegisterFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // sự kiện
        event();
    }

    private void event() {
        // khởi tạo API
       // jsonApi = new UserApi().getJson();
        binding.imdBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.cbRules.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    binding.btnRegister.setVisibility(View.INVISIBLE);
                }else if (isChecked){
                    binding.btnRegister.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValueUserRegister()){
                    setApiReister(createUser());
                }
            }
        });
    }
    // set data cho app
    private User createUser(){
        User user = new User();
        user.setName(binding.edtFullName.getText().toString());
        user.setPhone("+84"+binding.edtNumberPhone.getText().toString());
        user.setAccount(binding.edtMail.getText().toString());
        user.setPassword(binding.edtPassword.getText().toString());
        user.setAddress(binding.edtLocaltion.getText().toString());

        return  user;

    }
    // set API cho chức năng đăng kí
    private void setApiReister(User user){

        Handler handler = new Handler();

        jsonApi = new UserApi().getJson();

        Call<User> call = jsonApi.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null){

                    Toast.makeText(RegisterFoodActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(getApplicationContext(), LoginFoodActivity.class);
                            startActivity(i);
                        }
                    }, 500);
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Error", "onFailure: " + t.getMessage());
                Toast.makeText(RegisterFoodActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });



    }
    private Boolean isValueUserRegister(){

        if (binding.edtFullName.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (binding.edtNumberPhone.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Enter phone", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (binding.edtMail.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Enter mail", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (binding.edtPassword.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (binding.edtLocaltion.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Enter address", Toast.LENGTH_SHORT).show();
            return false;
        }

      return true;

    }
   
}