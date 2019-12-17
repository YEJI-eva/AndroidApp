package com.example.tempproject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    //서버 URL설정 (PHP파일 연동)
    final static private String URL="http://192.168.0.50/Register.php";
    private Map<String, String> parameters;

    public RegisterRequest(String email, String password, String username, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("email", email);
        parameters.put("password", password);
        parameters.put("username", username);
    }
    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}