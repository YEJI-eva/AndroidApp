package com.example.an.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class SignActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btn_sign_up).setOnClickListener(onClickListener);
        findViewById(R.id.btn_goto_login).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_sign_up:
                    signUp();
                    break;
                case R.id.btn_goto_login:
                    startActivity(LoginActivity.class);
                    break;
            }
        }
    };

    private void signUp(){
        String email = ((EditText)findViewById(R.id.et_mail)).getText().toString();
        String pw = ((EditText)findViewById(R.id.et_pw)).getText().toString();
        String pwCheck = ((EditText)findViewById(R.id.et_pwC)).getText().toString();


        if(email.length() > 0 && pw.length() >0 && pwCheck.length() > 0){
            if (pw.equals(pwCheck) ){
                if (pw.length() >= 6){


            mAuth.createUserWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                shortToast("회원가입이 완료되었습니다.");
                                //UI
                            } else {
                                // If sign in fails, display a message to the user.
                                if (task.getException()!=null){
                                    longToast(task.getException().toString());
                                }

                            }

                        }
                    });
                }else {
                    shortToast("비밀번호 6자리이상 입력하세요.");
                }
            }else {
                shortToast("비밀번호가 다릅니다.");
            }

        }else {
            shortToast("이메일 또는 비밀번호를 입력해 주세요.");
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
        startActivity(intent);
    }

}
