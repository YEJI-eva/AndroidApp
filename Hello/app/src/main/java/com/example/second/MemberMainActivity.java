package com.example.second;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MemberMainActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_main);

        final Button pickCuisineButton = (Button) findViewById(R.id.pickCuisineBtn);

        pickCuisineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인하면서 던진 username을 받아서 다시 PickCusineActivity로 던져줍니다.
                Intent intent = getIntent();
                String userName = intent.getStringExtra("username");

                Intent pickCuisineIntent = new Intent(MemberMainActivity.this, PickCuisineActivity.class);
                pickCuisineIntent.putExtra("userName", userName);
                MemberMainActivity.this.startActivity(pickCuisineIntent);
            }
        });
        backPressCloseHandler = new BackPressCloseHandler(this);
    }
    @Override
    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }
}