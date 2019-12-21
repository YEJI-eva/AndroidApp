package com.example.second;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PickKoreanMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_korean_menu);

        final Button noodleBtn = (Button) findViewById(R.id.noodleBtn);

        noodleBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent koreanNoodleIntent = new Intent(PickKoreanMenuActivity.this, KoreanNoodleActivity.class);
                PickKoreanMenuActivity.this.startActivity(koreanNoodleIntent);
            }
        });


    }
}