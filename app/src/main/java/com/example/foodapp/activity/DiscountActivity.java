package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.foodapp.JsonInterfaceApi.JsonApi;
import com.example.foodapp.R;
import com.example.foodapp.RetrofitApi.UserApi;
import com.example.foodapp.adapter.DiscountAdapter;
import com.example.foodapp.adapter.SeeAllDiscountAdapter;
import com.example.foodapp.databinding.ActivityDiscountBinding;
import com.example.foodapp.other.DiscountModelReal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscountActivity extends AppCompatActivity {
    private ActivityDiscountBinding binding;
    private SeeAllDiscountAdapter adapter;
    public JsonApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiscountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListDiscount();
        getDiscount();

        binding.imgBackDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void setListDiscount(){
        binding.rcvDiscount.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false));
        binding.rcvDiscount.setHasFixedSize(true);
        binding.rcvDiscount.setNestedScrollingEnabled(true);


    }
    private  void getDiscount(){
        api = new UserApi().getJson();
        Call<List<DiscountModelReal>> call = api.getAllDiscount();
        call.enqueue(new Callback<List<DiscountModelReal>>() {
            @Override
            public void onResponse(Call<List<DiscountModelReal>> call, Response<List<DiscountModelReal>> response) {
                adapter = new SeeAllDiscountAdapter((List<DiscountModelReal>) response.body(),getApplicationContext());
                adapter.setData(response.body());
                binding.rcvDiscount.setAdapter(adapter);
                Log.d("TAG", "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<List<DiscountModelReal>> call, Throwable t) {
                Log.d("TAG", "onFailure: "+ t.getMessage());

            }
        });
    }
}