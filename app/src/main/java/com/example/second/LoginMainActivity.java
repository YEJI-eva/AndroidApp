package com.example.second;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.second.LoginActivity;
import com.example.second.PickCuisineActivity;
import com.example.second.R;

public class LoginMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smain);

        final Button logoutButton = (Button) findViewById(R.id.logoutBtn);
        final Button pickCuisineButton = (Button) findViewById(R.id.pickCuisineBtn);



        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(LoginMainActivity.this, LoginActivity.class);
                LoginMainActivity.this.startActivity(loginIntent);
            }

        });

        pickCuisineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인하면서 던진 username을 받아서 다시 PickCusineActivity로 던져줍니다.
                Intent intent = getIntent();
                String userName = intent.getStringExtra("username");

                Intent pickCuisineIntent = new Intent(LoginMainActivity.this, PickCuisineActivity.class);
                pickCuisineIntent.putExtra("userName", userName);
                LoginMainActivity.this.startActivity(pickCuisineIntent);
            }
        });

    }
}
