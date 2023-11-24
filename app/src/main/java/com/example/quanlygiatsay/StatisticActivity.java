package com.example.quanlygiatsay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.example.quanlygiatsay.adapter.ViewPagerStatisticAdapter;
import com.example.quanlygiatsay.model.User;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class StatisticActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    String[] tabTitles = {"thong ke ngay", "khoang ngay"};
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        viewPager2 = findViewById(R.id.statictis_viewpager2);
        tabLayout = findViewById(R.id.statictis_tablayout);
        ViewPagerStatisticAdapter adapter = new ViewPagerStatisticAdapter(this);
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) ->
                    tab.setText(tabTitles[position])
                    ).attach();
        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");
    }
    public User getUser(){
        return user;
    }
}