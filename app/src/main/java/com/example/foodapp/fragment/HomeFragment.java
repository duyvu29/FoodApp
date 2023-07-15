package com.example.foodapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodapp.JsonInterfaceApi.JsonApi;
import com.example.foodapp.ModelAPI.storeAPI.StoreModelAPI;
import com.example.foodapp.R;
import com.example.foodapp.RetrofitApi.UserApi;
import com.example.foodapp.activity.DiscountActivity;
import com.example.foodapp.activity.SeeAllStoreActivity;
import com.example.foodapp.adapter.DiscountAdapter;
import com.example.foodapp.adapter.StoreHintAdapter;
import com.example.foodapp.adapter.StoreApdapter;
import com.example.foodapp.other.DiscountModelReal;
import com.example.foodapp.other.StoreModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    TextView tvSeeAllStore, tvSeeAllFood, tvSeeDiscount;

     /** Danh sách cửa hàng gợi ý **/
     private RecyclerView rcvHomeStore;
     private ArrayList <StoreModel> listStore;
     private StoreApdapter adapter ;

     /** Danh sách món ăn gợi ý**/
     private RecyclerView rcvHomeFood;
     private StoreHintAdapter adapter2;
     /** Danh sách Discount **/
     private RecyclerView rcvHomeDiscount;
    // private ArrayList<DiscountModel> listDiscount;
     private DiscountAdapter adapterModel;



    public JsonApi jsonApi;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_home,container, false);

        // mapping
        mapping(root);

        // API home fragment
        getFood();
        getDiscount();


        // create RecyclerView Home fragment
        setListHintStore();
        setListHintFood();
        setListDiscount();

         // Event
        event();



        return root;
    }
    private void event(){
        tvSeeAllFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent i = new Intent(getActivity(), SeeAllStoreActivity.class);
                 startActivity(i);
            }
        });
        tvSeeDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent discount = new Intent(getActivity(), DiscountActivity.class);
                startActivity(discount);
            }
        });
    }

    private void mapping(View root) {
        rcvHomeDiscount = root.findViewById(R.id.rcvDiscount);
        rcvHomeStore   = root.findViewById(R.id.rcvStoreHorizontal);
        rcvHomeFood    = root.findViewById(R.id.rcvFoodHint);
        tvSeeAllFood   = root.findViewById(R.id.tvSeeAllFood);
        tvSeeDiscount  = root.findViewById(R.id.tvSeeAllDiscount);
    }

    private void setListHintStore() {
        // add list
        listStore= new ArrayList<>();

        listStore.add(new StoreModel(R.drawable.rice_store,"Cơm"));
        listStore.add(new StoreModel(R.drawable.ramen,"Bún/Phở"));
        listStore.add(new StoreModel(R.drawable.cup,"Cafe/Nước ép"));
        listStore.add(new StoreModel(R.drawable.fried_chicken,"Đồ ăn nhanh"));
        listStore.add(new StoreModel(R.drawable.diet,"Healthy"));
        listStore.add(new StoreModel(R.drawable.bubble_tea,"Trà sữa"));
        listStore.add(new StoreModel(R.drawable.nachos,"Đồ ăn vặt"));

        // set Adapter
        adapter = new StoreApdapter(listStore, getActivity());
        rcvHomeStore.setAdapter(adapter);

        // set horizontal
        rcvHomeStore.setLayoutManager(new GridLayoutManager(getActivity(),2, GridLayoutManager.HORIZONTAL, false));
        rcvHomeStore.setHasFixedSize(true);
        rcvHomeStore.setNestedScrollingEnabled(true);
    }


    private void setListHintFood(){
       /**
        mListFood = new ArrayList<>();
        adapter2 = new FoodAdapter(mListFood,getContext());
        rcvHomeFood.setAdapter(adapter2);**/

        // set Horizontal cho list
        rcvHomeFood.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        rcvHomeFood.setHasFixedSize(true);
        rcvHomeFood.setNestedScrollingEnabled(true);
    }

    private void setListDiscount(){
      //  listDiscount = new ArrayList<>();
        //listDiscount.add(new DiscountModel(R.drawable.banner,"Món gì cũng có,giảm 177.000Đ"));

        //set adapter
        // adapterModel = new DiscountAdapter(listDiscount,getActivity());
       // rcvHomeDiscount.setAdapter(adapterModel);

        // set horizontal

        rcvHomeDiscount.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.HORIZONTAL, false));
        rcvHomeDiscount.setHasFixedSize(true);
        rcvHomeDiscount.setNestedScrollingEnabled(true);
    }
    /** API FOOD **/
    private void getFood(){
        jsonApi = new UserApi().getJson();
        Call<List<StoreModelAPI>> call = jsonApi.getAllStore();
        call.enqueue(new Callback<List<StoreModelAPI>>() {

            @Override
            public void onResponse(Call<List<StoreModelAPI>> call, Response<List<StoreModelAPI>> response) {
                //mListFood = new ArrayList<>();
                adapter2 = new StoreHintAdapter((List<StoreModelAPI>) response.body(),getContext());
                rcvHomeFood.setAdapter(adapter2);
                Log.d("Food", "onRsponse: " + response.body().toString());
            }
            @Override
            public void onFailure(Call<List<StoreModelAPI>> call, Throwable t) {
                Log.d("Fail", "onFailure: " + t.getMessage());
            }
        });
    }
    /** API DISCOUNT **/
    private  void getDiscount(){
        jsonApi = new UserApi().getJson();
        Call<List<DiscountModelReal>> call = jsonApi.getAllDiscount();
        call.enqueue(new Callback<List<DiscountModelReal>>() {
            @Override
            public void onResponse(Call<List<DiscountModelReal>> call, Response<List<DiscountModelReal>> response) {
                adapterModel = new DiscountAdapter((List<DiscountModelReal>) response.body(), getContext());
                rcvHomeDiscount.setAdapter(adapterModel);
                Log.d("TAG", "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<List<DiscountModelReal>> call, Throwable t) {
                Log.d("TAG", "onFailure: "+ t.getMessage());

            }
        });
    }

}