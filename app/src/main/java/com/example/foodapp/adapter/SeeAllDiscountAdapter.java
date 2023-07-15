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
import com.example.foodapp.R;
import com.example.foodapp.other.DiscountModelReal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SeeAllDiscountAdapter extends RecyclerView.Adapter<SeeAllDiscountAdapter.DiscountViewHoler> {
    List <DiscountModelReal> list;
    Context context;

    public SeeAllDiscountAdapter(List<DiscountModelReal> list, Context context) {
        this.list = list;
        this.context = context;

    }
    private List<DiscountModelReal> dataList = new ArrayList<>();

    public void setData(List<DiscountModelReal> newData) {
        dataList.clear();

        // Tạo một HashSet để kiểm tra tính duy nhất của các id
        Set<Integer> uniqueIds = new HashSet<>();

        for (DiscountModelReal item : newData) {
            if (!uniqueIds.contains(item.getId())) {
                uniqueIds.add(item.getId());
                dataList.add(item);
            }
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DiscountViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DiscountViewHoler(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_discount, parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountViewHoler holder, int position) {

        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        Collections.shuffle(list);
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    public class DiscountViewHoler extends RecyclerView.ViewHolder{
        ImageView imgBaner;
        TextView  tvDesc, tvTitle, tvTime;


        public DiscountViewHoler(@NonNull View itemView) {
            super(itemView);
            imgBaner = itemView.findViewById(R.id.imgDiscountMain);
            tvDesc   = itemView.findViewById(R.id.tvDescDiscount);
            tvTime   = itemView.findViewById(R.id.tvTimeDiscount);
            tvTitle  = itemView.findViewById(R.id.tvTitleDiscount);
        }
        public void bind(DiscountModelReal data){
            tvDesc.setText(data.getDescription());
            tvTitle.setText(data.getName());
            tvTime .setText(data.getTime());
            Glide.with(context).load(data.getImageDiscount().getString()).into(imgBaner);
        }
    }
}
