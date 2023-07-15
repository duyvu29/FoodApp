package com.example.foodapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.foodapp.activity.HomeActivity;
import com.example.foodapp.fragment.HomeFragment;
import com.example.foodapp.fragment.ManagerActivity;
import com.example.foodapp.fragment.OrderActivity;
import com.example.foodapp.fragment.SearchfoodActivity;

public class ViewPagerAdpter extends FragmentStateAdapter {

    public ViewPagerAdpter(@NonNull FragmentActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new SearchfoodActivity();
            case 2:
                return new OrderActivity();
            case 3:
                return new ManagerActivity();
            default:
                return new HomeFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
