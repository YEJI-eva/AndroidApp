package com.example.second;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ReviewWriteRequest extends StringRequest {
    //서버 URL설정 (PHP파일 연동)
    final static private String URL="http://192.168.0.133/writeReview.php";
    private Map<String, String> parameters;

    public ReviewWriteRequest(String storeNum, String userName, String review, String rate, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("storeNum", storeNum);
        parameters.put("userName", userName);
        parameters.put("review", review);
        parameters.put("rate", rate);
    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}
