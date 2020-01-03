package com.example.second;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById(R.id.joinBtn).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.joinBtn:
                    join();
            }
        }
    };

    private void join() {
        String email = ((EditText)findViewById(R.id.emailText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordText)).getText().toString();
        String username = ((EditText)findViewById(R.id.usernameText)).getText().toString();
        String password2 = ((EditText)findViewById(R.id.passwordText2)).getText().toString();
        if (email.length()>0 && password.length()>0 && password2.length()>0){
            if (password.equals(password2)){
                if (password.length() >= 6){

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if(success) {
                                    // Toast.makeText(getApplicationContext(),"회원등록에 성공했습니다.", Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("회원등록에 성공했습니다.")
                                            .setPositiveButton("확인", null)
                                            .create()
                                            .show();
                                    startActivity(LoginActivity.class);
                                } else {
                                    //Toast.makeText(getApplicationContext(),"회원등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                    //return;
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("회원등록에 실패했습니다.")
                                            .setNegativeButton("확인", null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    com.example.second.RegisterRequest registerRequest = new com.example.second.RegisterRequest(email, password, username, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);

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