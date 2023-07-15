package com.example.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.ModelAPI.storeAPI.StoreModelAPI;
import com.example.foodapp.R;

import java.util.Collections;
import java.util.List;

public class StoreHintAdapter extends RecyclerView.Adapter<StoreHintAdapter.FoodViewHolder> {
   // List<FoodMode> list;
    Context context;
    //List<Data> dataList;
    List<StoreModelAPI> datalist;


    public StoreHintAdapter(List<StoreModelAPI> list, Context context) {
        this.datalist = list;
        this.context = context;


    }

    public StoreHintAdapter(List<StoreModelAPI> dataList) {
        this.datalist = dataList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new FoodViewHolder(LayoutInflater.from(parent.getContext()).inflate( R.layout.list_hintstore_hz,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {

      holder.bind(datalist.get(position));


    }

    @Override
    public int getItemCount() {
        Collections.shuffle(datalist);
        return Math.min(datalist.size(), 6);
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder{

        ImageView imgStore;
        TextView tvNameStore;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imgStore = itemView.findViewById(R.id.imgHintStore);
            tvNameStore= itemView.findViewById(R.id.tvHintStore);
        }
        public void bind(StoreModelAPI data){
           tvNameStore.setText(data.getName());
           Glide.with(context).load(data.getImageStore().getString()).into(imgStore);
        }
    }
}
