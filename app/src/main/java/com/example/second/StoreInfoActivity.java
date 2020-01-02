package com.example.second;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.second.LocationMap;
import com.example.second.R;
import com.example.second.Review;
import com.example.second.Store;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class StoreInfoActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; // 바텀 네이게이션뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private LocationMap locationMap;
    private Store store;
    private Review review;
//    private Myinfo Myinfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_info);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottomBarMap:
                        setFrag(0);
                        break;
                    case R.id.bottomBarStore:
                        setFrag(1);
                        break;
                    case R.id.bottomBarReview:
                        setFrag(2);
                        break;
//                    case R.id.bottomBarMyinfo:
//                        setFrag(3);
                }
                return true;
            }
        });
        locationMap = new LocationMap();
        store = new Store();
        review = new Review();
//        Myinfo= new Myinfo();
        setFrag(0); // 첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 선택.
    }


    private void setFrag(int n) {
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String restaurantNum = intent.getStringExtra("restaurantNum");
        String restaurantName = intent.getStringExtra("restaurantName");
        String restaurantAddress = intent.getStringExtra("restaurantAddress");
        String tel = intent.getStringExtra("tel");

        Bundle bundle = new Bundle();
        bundle.putString("userName", userName);
        bundle.putString("restaurantNum", restaurantNum);
        bundle.putString("restaurantName", restaurantName);
        bundle.putString("restaurantAddress", restaurantAddress);
        bundle.putString("tel", tel);

        store.setArguments(bundle);
        locationMap.setArguments(bundle);
        review.setArguments(bundle);
//        Myinfo.setArguments(bundle);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, locationMap);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, store);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, review);
                ft.commit();
                break;
//            case 3:
//                ft.replace(R.id.main_frame, Myinfo);
//                ft.commit();
//                break;


        }
    }
}