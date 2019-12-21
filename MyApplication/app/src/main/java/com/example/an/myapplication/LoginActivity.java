package com.example.an.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btn_login).setOnClickListener(onClickListener);
        findViewById(R.id.btn_goto_sign_up).setOnClickListener(onClickListener);
        findViewById(R.id.btn_goto_pwReset).setOnClickListener(onClickListener);



    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }



    View.OnClickListener onClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_login:
                    login();
                    break;
                case R.id.btn_goto_sign_up:
                    startActivity(SignActivity.class);
                    break;
                case R.id.btn_goto_pwReset:
                    startActivity(PwResetActivity.class);
                    break;

            }
        }
    };

    private void login(){
        String email = ((EditText)findViewById(R.id.et_mail)).getText().toString();
        String pw = ((EditText)findViewById(R.id.et_pw)).getText().toString();


        if(email.length() > 0 && pw.length() >0 ){
            mAuth.signInWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();
                                shortToast("로그인 성공.");
                                startActivity(MainActivity.class);
                            } else {
                                // If sign in fails, display a message to the user.
                                longToast(task.getException().toString());
                            }

                            // ...
                        }
                    });
        }else {
            shortToast("이메일, 비밀번호를 입력해 주세요.");
        }
    }
    private void longToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
    private void shortToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    private void  startActivity(Class c){
        Intent intent =new Intent(this,c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
