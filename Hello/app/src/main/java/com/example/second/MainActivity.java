package com.example.second;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.second.LoginActivity;
import com.example.second.PickCuisineActivity;
import com.example.second.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smain);

        final Button loginButton = (Button) findViewById(R.id.loginBtn);
        final Button pickCuisineButton = (Button) findViewById(R.id.pickCuisineBtn);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(loginIntent);
            }

        });

        pickCuisineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickCuisineIntent = new Intent(MainActivity.this, PickCuisineActivity.class);
                MainActivity.this.startActivity(pickCuisineIntent);
            }
        });

    }
}
