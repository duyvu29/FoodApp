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
import com.example.foodapp.ModelAPI.FoodModel2;
import com.example.foodapp.R;
import com.example.foodapp.other.DiscountModel;
import com.example.foodapp.other.DiscountModelReal;

import java.util.Collections;
import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHoler> {
    List <DiscountModelReal> list;
    Context context;

    public DiscountAdapter(List<DiscountModelReal> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public DiscountViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DiscountViewHoler(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_discountitem, parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountViewHoler holder, int position) {
        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        Collections.shuffle(list);
        return Math.min(list.size(), 5);
    }


    public class DiscountViewHoler extends RecyclerView.ViewHolder{
        ImageView imgBaner;
        TextView  tvDesc;

        public DiscountViewHoler(@NonNull View itemView) {
            super(itemView);
            imgBaner = itemView.findViewById(R.id.imgDiscount);
            tvDesc   = itemView.findViewById(R.id.tvHintDiscount);
        }
        public void bind(DiscountModelReal data){
            tvDesc.setText(data.getName());
            Glide.with(context).load(data.getImageDiscount().getString()).into(imgBaner);
        }
    }
}
