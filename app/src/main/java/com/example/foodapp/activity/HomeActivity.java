package com.example.foodapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodapp.R;
import com.example.foodapp.adapter.ViewPagerAdpter;
import com.example.foodapp.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomBar.setItemIconTintList(null);


        ViewPagerAdpter adapter = new ViewPagerAdpter(this);
        binding.viewPagerHome.setAdapter(adapter);


        /** lock slide viewpager **/
        binding.viewPagerHome.setUserInputEnabled(false);

        // sự kiện
        event();

    }

    private void event() {

        binding.bottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.homee){
                    binding.viewPagerHome.setCurrentItem(0);
                } else if ( id == R.id.food){
                    binding.viewPagerHome.setCurrentItem(1);
                }else if (id == R.id.order){
                    binding.viewPagerHome.setCurrentItem(2);
                }else if (id == R.id.setting){
                    binding.viewPagerHome.setCurrentItem(3);
                }
                return true;
            }
        });

        binding.viewPagerHome.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                switch (position){
                    case 0:
                        binding.bottomBar.getMenu().findItem(R.id.homee).setChecked(true);
                        break;

                    case 1:
                        binding.bottomBar.getMenu().findItem(R.id.food).setChecked(true);
                        break;

                    case 2:
                        binding.bottomBar.getMenu().findItem(R.id.order).setChecked(true);
                        break;

                    case 3:
                        binding.bottomBar.getMenu().findItem(R.id.setting).setChecked(true);
                        break;
                }
            }
        });
    }
}