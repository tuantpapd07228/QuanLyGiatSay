package com.example.quanlygiatsay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quanlygiatsay.fragment.ComfirmFragment;
import com.example.quanlygiatsay.fragment.DeliveryFragment;
import com.example.quanlygiatsay.model.User;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        drawerLayout =  findViewById(R.id.main_drawerlayout);
        toggle =  new ActionBarDrawerToggle(this, drawerLayout, toolbar , R.string.open , R.string.close );
        toggle.setDrawerIndicatorEnabled(true);
        toolbar.setTitle("Trang chủ");

        toggle.syncState();


        drawerLayout.addDrawerListener(toggle);
        NavigationView navigationView = findViewById(R.id.main_navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id  = item.getItemId();
                Fragment fragment = null;
                if (id == R.id.menu_comfirm_order){
                    Intent i = new Intent(MainActivity.this, StatisticActivity.class);
                    i.putExtra("user",user);
                    startActivity(i);
                } else if (id == R.id.menu_comfirm_delivery) {
                    toolbar.setTitle("Xác nhận");
                    fragment = new ComfirmFragment();
                }else if (id == R.id.menu_comfirm_complete) {
                    toolbar.setTitle("Giao hàng");
                    fragment = new DeliveryFragment();
                }else if (id == R.id.menu_logout) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                if (fragment != null){
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_framelayout , fragment).commit();
                }
                drawerLayout.close();
                return true;

            }
        });
    }

    public User getUser(){
        return user;
    }
}