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

public class SeeAllStoreAdapter extends RecyclerView.Adapter<SeeAllStoreAdapter.SeeFoodViewHolder> {
    private Context context;
    //List<Data> dataList;
    List<StoreModelAPI> datalist;

    public SeeAllStoreAdapter(Context context, List<StoreModelAPI> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public SeeFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SeeFoodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_store, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SeeFoodViewHolder holder, int position) {
        holder.bind(datalist.get(position));

    }

    @Override
    public int getItemCount() {
        Collections.shuffle(datalist);
        return datalist.size();
    }

    public class SeeFoodViewHolder extends RecyclerView.ViewHolder{

        ImageView imageStore;
        TextView tvNameStore,tvAddress, tvDesc;


        public SeeFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imageStore  = itemView.findViewById(R.id.imgListStore);
            tvNameStore = itemView.findViewById(R.id.tvListStore);
            tvAddress    = itemView.findViewById(R.id.tvAddress);
            tvDesc     = itemView.findViewById(R.id.tvListDescStore);
        }
        public void bind(StoreModelAPI data){
            tvNameStore.setText(data.getName());
            tvAddress.setText(data.getAddress());
            tvDesc.setText(data.getDescription());
            Glide.with(context).load(data.getImageStore().getString()).into(imageStore);
        }
    }
}
