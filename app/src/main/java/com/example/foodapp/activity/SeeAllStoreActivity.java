package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.foodapp.JsonInterfaceApi.JsonApi;
import com.example.foodapp.ModelAPI.FoodModel2;
import com.example.foodapp.ModelAPI.storeAPI.StoreModelAPI;
import com.example.foodapp.RetrofitApi.UserApi;
import com.example.foodapp.adapter.SeeAllStoreAdapter;
import com.example.foodapp.databinding.ActivitySeeAllStoreBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeAllStoreActivity extends AppCompatActivity {
     private ActivitySeeAllStoreBinding binding;
     private SeeAllStoreAdapter adapter;
    public JsonApi jsonApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeeAllStoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


       // sự kiện
        event();
        // get all foodd to api
        getFood();
        //
        setLisFood();

    }
    private void event() {
        // code
        binding.imgBackSeeAllFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setLisFood(){
        /**
         mListFood = new ArrayList<>();
         adapter2 = new FoodAdapter(mListFood,getContext());
         rcvHomeFood.setAdapter(adapter2);**/

        // set Horizontal cho list
        binding.rcvSeeAllStre.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false));
        binding.rcvSeeAllStre.setHasFixedSize(true);
        binding.rcvSeeAllStre.setNestedScrollingEnabled(true);
    }
    private void getFood() {
        jsonApi = new UserApi().getJson();
        Call<List<StoreModelAPI>> call = jsonApi.getAllStore();
        call.enqueue(new Callback<List<StoreModelAPI>>() {

            @Override
            public void onResponse(Call<List<StoreModelAPI>> call, Response<List<StoreModelAPI>> response) {
                //mListFood = new ArrayList<>();
                adapter = new SeeAllStoreAdapter(getApplication(),(List<StoreModelAPI>) response.body());
                binding.rcvSeeAllStre.setAdapter(adapter);
                Log.d("Food", "onRsponseFood: " + response.body().toString());
            }
            @Override
            public void onFailure(Call<List<StoreModelAPI>> call, Throwable t) {
                Log.d("Fail", "onFailure: " + t.getMessage());
            }
        });
    }



}