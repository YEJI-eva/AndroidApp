package com.example.second;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PickCuisineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_cuisine);

        final Button koreanBtn = (Button) findViewById(R.id.koreanBtn);
        final Button chineseBtn = (Button) findViewById(R.id.chineseBtn);
        final Button japaneseBtn = (Button) findViewById(R.id.japaneseBtn);
        final Button westernBtn = (Button) findViewById(R.id.westernBtn);

        koreanBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String userName = intent.getStringExtra("userName");
                String restaurantNum = intent.getStringExtra("restaurantNum");
                Log.d("userName", userName);
                Intent koreanIntent = new Intent(PickCuisineActivity.this, PickKoreanMenuActivity.class);
                koreanIntent.putExtra("userName", userName);
                koreanIntent.putExtra("restaurantNum", restaurantNum);
                PickCuisineActivity.this.startActivity(koreanIntent);
            }
        });

        chineseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String userName = intent.getStringExtra("userName");
                String restaurantNum = intent.getStringExtra("restaurantNum");
                Intent chineseIntent = new Intent(PickCuisineActivity.this, PickChineseMenuActivity.class);
                chineseIntent.putExtra("userName", userName);
                chineseIntent.putExtra("restaurantNum", restaurantNum);
                PickCuisineActivity.this.startActivity(chineseIntent);
            }
        });

        japaneseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String userName = intent.getStringExtra("userName");
                String restaurantNum = intent.getStringExtra("restaurantNum");
                Intent japaneseIntent = new Intent(PickCuisineActivity.this, PickJapaneseMenuActivity.class);
                japaneseIntent.putExtra("userName", userName);
                japaneseIntent.putExtra("restaurantNum", restaurantNum);
                PickCuisineActivity.this.startActivity(japaneseIntent);
            }
        });

        westernBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String userName = intent.getStringExtra("userName");
                String restaurantNum = intent.getStringExtra("restaurantNum");
                Intent westernIntent = new Intent(PickCuisineActivity.this, PickWesternMenuActivity.class);
                westernIntent.putExtra("userName", userName);
                westernIntent.putExtra("restaurantNum", restaurantNum);
                PickCuisineActivity.this.startActivity(westernIntent);
            }
        });
    }
}
