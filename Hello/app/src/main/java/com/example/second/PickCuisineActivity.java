package com.example.second;

import android.content.Intent;
import android.os.Bundle;
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

        koreanBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent koreanIntent = new Intent(PickCuisineActivity.this, PickKoreanMenuActivity.class);
                PickCuisineActivity.this.startActivity(koreanIntent);
            }
        });

        chineseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent chineseIntent = new Intent(PickCuisineActivity.this, PickChineseMenuActivity.class);
                PickCuisineActivity.this.startActivity(chineseIntent);
            }
        });
    }
}
