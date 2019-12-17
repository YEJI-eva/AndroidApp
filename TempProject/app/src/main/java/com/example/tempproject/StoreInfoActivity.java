package com.example.tempproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StoreInfoActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; // 바텀 네이게이션뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private LocationMap locationMap;
    private Store store;
    private Review review;

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
                        setFrag(0);
                        break;
                    case R.id.bottomBarReview:
                        setFrag(0);
                        break;
                }

                return true;
            }

        });
        locationMap = new LocationMap();
        store = new Store();
        review = new Review();
        setFrag(0); // 첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 선택.
    }

    // 프레그먼트 교체가 일어나는 실행문
    private void setFrag(int n) {
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
        }
    }
}
