package com.example.second;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
//네이버
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;
import com.nhn.android.oauth.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nhn.android.naverlogin.OAuthLogin;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "LoginActivity";

    /**
     * client 정보를 넣어준다.
     */
    private static String OAUTH_CLIENT_ID = "B1djaBmQSXTl2zsNPbLS";
    private static String OAUTH_CLIENT_SECRET = "lXuFzb7_Xr";
    private static String OAUTH_CLIENT_NAME = "네이버 아이디로 로그인";

    private static OAuthLogin mOAuthLoginInstance;
    private static Context mContext;

    /**
     * UI 요소들
     */
    private TextView mApiResultText;
    private static TextView mOauthAT;
    private static TextView mOauthRT;
    private static TextView mOauthExpires;
    private static TextView mOauthTokenType;
    private static TextView mOAuthState;

    private OAuthLoginButton mOAuthLoginButton;
    //여기까지 네이버

    // 빈곳 눌렀을때 키보드창 내리기
    InputMethodManager imm;
    EditText passwordText;
    EditText emailText;


    // google login add
    private SignInButton btn_google; // 구글 로그인 버튼
    private FirebaseAuth auth; // 파이어 베이스 인증 객체
    private GoogleApiClient googleApiClient; // 구글 API 클라이언트 객체
    private static final int REQ_SIGN_GOOGLE = 100; // 구글 로그인 결과 코드


    @Override
    protected void onCreate(Bundle savedInstanceState) { // 앱이 실행될 때 처음 수행되는 곳
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


            // google login add
            GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();

            googleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                    .build();

            auth = FirebaseAuth.getInstance(); // 파이어베이스 인증 객체 초기화.

            btn_google = findViewById(R.id.btn_google);
            btn_google.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                    startActivityForResult(intent, REQ_SIGN_GOOGLE);
                }
            });


        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        emailText = (EditText) findViewById(R.id.emailText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        final Button loginBtn = (Button) findViewById(R.id.loginBtn);
        final RelativeLayout background = (RelativeLayout) findViewById(R.id.background);
        final TextView registerBtn = (TextView) findViewById(R.id.joinBtn);

        background.setOnClickListener(myClickListener);
        loginBtn.setOnClickListener(myClickListener);

        mContext = this;

        initData();
        initView();

        this.setTitle("LoginActivity Ver." + OAuthLogin.getVersion());

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String email = emailText.getText().toString();
                final String password = passwordText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success) {
                                String email = jsonObject.getString("email");
                                String password = jsonObject.getString("password");
                                String userName = jsonObject.getString("username");
                                Intent intent = new Intent(LoginActivity.this, MemberMainActivity.class);
                                intent.putExtra("email", email);
                                intent.putExtra("password", password);
                                intent.putExtra("username", userName);
                                // 로그인하면 메인액티비티로 username을 던져줍니다.
                                LoginActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("로그인에 실패하셨습니다.")
                                        .setNegativeButton("다시시도", null)
                                        .create()
                                        .show();
                            }
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }

    // 빈화면이나 로그인 버튼을 눌렀을때도 키보드 내려가도록함
    View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            hideKeyboard();
            switch (v.getId())
            {
                case R.id.background :
                    break;
                case R.id.loginBtn :
                    break;
            }
        }
    };

    private void hideKeyboard() {
        imm.hideSoftInputFromWindow(emailText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(passwordText.getWindowToken(), 0);
    }

    private void initData() {
        mOAuthLoginInstance = OAuthLogin.getInstance();

        mOAuthLoginInstance.showDevelopersLog(true);
        mOAuthLoginInstance.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME);

        /*
         * 2015년 8월 이전에 등록하고 앱 정보 갱신을 안한 경우 기존에 설정해준 callback intent url 을 넣어줘야 로그인하는데 문제가 안생긴다.
         * 2015년 8월 이후에 등록했거나 그 뒤에 앱 정보 갱신을 하면서 package name 을 넣어준 경우 callback intent url 을 생략해도 된다.
         */
        //mOAuthLoginInstance.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME, OAUTH_callback_intent_url);
    }

    private void initView() {
        mApiResultText = (TextView) findViewById(R.id.api_result_text); //가져오기 안ㄴ됨

        mOAuthLoginButton = (OAuthLoginButton) findViewById(R.id.buttonOAuthLoginImg);
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
    }

    @Override
    protected void onResume() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onResume();
    }

    /*
     * startOAuthLoginActivity() 호출시 인자로 넘기거나, OAuthLoginButton 에 등록해주면 인증이 종료되는 걸 알 수 있다.
     */
    static private OAuthLoginHandler mOAuthLoginHandler = new  OAuthLoginHandler(){
        @Override
        public void run(boolean success) {
            if (success) {
                String accessToken = mOAuthLoginInstance.getAccessToken(mContext);
                String refreshToken = mOAuthLoginInstance.getRefreshToken(mContext);
                long expiresAt = mOAuthLoginInstance.getExpiresAt(mContext);
                String tokenType = mOAuthLoginInstance.getTokenType(mContext);
                mOauthAT.setText(accessToken);
                mOauthRT.setText(refreshToken);
                mOauthExpires.setText(String.valueOf(expiresAt));
                mOauthTokenType.setText(tokenType);
                mOAuthState.setText(mOAuthLoginInstance.getState(mContext).toString());
            } else {
                String errorCode = mOAuthLoginInstance.getLastErrorCode(mContext).getCode();
                String errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext);
                Toast.makeText(mContext, "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }

    };

    public void onButtonClick(View v) throws Throwable {

        switch (v.getId()) {
            case R.id.buttonOAuth: {
                mOAuthLoginInstance.startOauthLoginActivity(LoginActivity.this, mOAuthLoginHandler);
                break;
            }
            case R.id.buttonOAuthLogout: {
                mOAuthLoginInstance.logout(mContext);
                break;
            }
            default:
                break;
        }
    }


    // google login add

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 구글 로그인 인증을 요청 했을 때 결과 값 돌려 받음
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_SIGN_GOOGLE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount(); // account안에 정보 다 담겨있음
                resultLogin(account); // 로그인 결과값 던져줌
            }
        }
    }

    private void resultLogin(final GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) { // 로그인 성공시
                            Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                            //Intent intent = new Intent(LoginActivity.this, ResultActivity.class);
                            // account data 써먹을 것 입력해 줌
                            intent.putExtra("nickName", account.getDisplayName());
                            intent.putExtra("photoUrl", String.valueOf(account.getPhotoUrl())); // 사진을 String으로 던짐
                            startActivity(intent);
                        } else { // 로그인 실패시
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private class DeleteTokenTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            boolean isSuccessDeleteToken = mOAuthLoginInstance.logoutAndDeleteToken(mContext);

            if (!isSuccessDeleteToken) {
                // 서버에서 token 삭제에 실패했어도 클라이언트에 있는 token 은 삭제되어 로그아웃된 상태이다
                // 실패했어도 클라이언트 상에 token 정보가 없기 때문에 추가적으로 해줄 수 있는 것은 없음
                Log.d(TAG, "errorCode:" + mOAuthLoginInstance.getLastErrorCode(mContext));
                Log.d(TAG, "errorDesc:" + mOAuthLoginInstance.getLastErrorDesc(mContext));
            }
            return null;
        }
    }

    private class RequestApiTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            mApiResultText.setText((String) "");
        }

        @Override
        protected String doInBackground(Void... params) {
            String url = "https://openapi.naver.com/v1/nid/me";
            String at = mOAuthLoginInstance.getAccessToken(mContext);
            return mOAuthLoginInstance.requestApi(mContext, at, url);
        }
        protected void onPostExecute(String content) {
            mApiResultText.setText((String) content);
        }
    }

    private class RefreshTokenTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            return mOAuthLoginInstance.refreshAccessToken(mContext);
        }
    }
}
