package com.example.second;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

    public class BodyActivity extends FragmentActivity {

        private RecentFragment callLogFragment;
        private FavoritesFragment favoritesFragment;
        private NearbyFragmenet nearByFragment;
        private MeFragment meFragment;
        private FoodFragment foodFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* setContentView(R.layout.activity_splash);*/


        callLogFragment = new RecentFragment();
        favoritesFragment = new FavoritesFragment();
        nearByFragment =new NearbyFragmenet();
        meFragment =new  MeFragment();
        foodFragment =new FoodFragment();
        initFragment();


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                if(tabId==R.id.tab_recent){
                    transaction.replace(R.id.contentContainer, callLogFragment).commit();
                }

                else if(tabId == R.id.tab_favorites){
                    transaction.replace(R.id.contentContainer, favoritesFragment).commit();

                }

                else if(tabId == R.id.tab_nearby){
                    transaction.replace(R.id.contentContainer, nearByFragment).commit();
                }
                else if(tabId == R.id.tab_me){
                    transaction.replace(R.id.contentContainer,meFragment).commit();
                }
                else if(tabId == R.id.tab_food){
                    transaction.replace(R.id.contentContainer, foodFragment).commit();
                } else{
                    transaction.replace(R.id.contentContainer, callLogFragment).commit();
                }
            }
        });
    }
    /*
     App 실행시 보여지는 Fragment 설정.
    */
    public void initFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentContainer, callLogFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
