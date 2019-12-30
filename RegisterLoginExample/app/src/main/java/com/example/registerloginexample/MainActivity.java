package com.example.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv_name, tv_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_name = findViewById(R.id.tv_name);
        tv_pass = findViewById(R.id.tv_pass);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String userPass = intent.getStringExtra("userPass");
        tv_name.setText(userName);
        tv_pass.setText(userPass);
    }
}
