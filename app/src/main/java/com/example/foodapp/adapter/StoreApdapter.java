package com.example.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.other.StoreModel;

import org.w3c.dom.Text;

import java.util.List;

public class StoreApdapter extends RecyclerView.Adapter<StoreApdapter.ViewHolder> {

    List<StoreModel> list ;
    Context context;

    public StoreApdapter(List<StoreModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_itemstore_hz, parent , false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageStore.setImageResource(list.get(position).getImageStore());
        holder.tvHintStore.setText(list.get(position).getNameHintStore());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageStore ;
        TextView  tvHintStore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageStore = itemView.findViewById(R.id.imgStore);
            tvHintStore = itemView.findViewById(R.id.tvHintStore);

        }
    }

}
