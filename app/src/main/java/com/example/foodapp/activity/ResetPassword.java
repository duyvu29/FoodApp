package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.foodapp.JsonInterfaceApi.JsonApi;
import com.example.foodapp.ModelAPI.ReponseApi;
import com.example.foodapp.ModelAPI.User;
import com.example.foodapp.R;
import com.example.foodapp.RetrofitApi.UserApi;
import com.example.foodapp.databinding.ActivityResetPasswordBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ResetPassword extends AppCompatActivity {
    private ActivityResetPasswordBinding binding;
    private List<User> mUser;
    private JsonApi mJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mUser = new ArrayList<>();

        // sự kiện
        event ();
    }

    private void event() {
        binding.imgSetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = binding.edtNewPass.getText().toString().trim();
                String b = binding.edtConfirmPass.getText().toString().trim();
                if (isValueInput()){
                    if (a.equals(b)){
                       setChangePassword(setData());
                    }else {
                        Toast.makeText(ResetPassword.this, "Xác nhận sai, xin mời nhập lại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    /**      set Change PassWord       **/

    private void setChangePassword(User user){
        mJson = new UserApi().getJson();
        Call <ReponseApi> call = mJson.changPassword(user.getPassword(), user.getId());
        call.enqueue(new Callback<ReponseApi>() {
            @Override
            public void onResponse(Call<ReponseApi> call, Response<ReponseApi> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        String result = response.body().getResult();
                        if (result.equals("Success")) {
                            Toast.makeText(ResetPassword.this, "Update one user success.", Toast.LENGTH_SHORT).show();
                            Log.d("Reset", "Updated with result: " + result);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ReponseApi> call, Throwable t) {
                Toast.makeText(ResetPassword.this, "Update fail", Toast.LENGTH_SHORT).show();
            }
        });

    }
    /** set data **/
    private User setData(){
        User user = new User();
        user.setPassword(binding.edtConfirmPass.getText().toString().trim());
        return user;
    }

   // check if new pass == confirm => true
    private Boolean isValueInput(){
        if (binding.edtNewPass.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if  (binding.edtConfirmPass.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Enter confirm password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

}