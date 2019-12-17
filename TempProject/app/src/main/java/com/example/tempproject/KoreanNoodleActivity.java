package com.example.tempproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class KoreanNoodleActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "192.168.0.50";
    private static String TAG = "phptest";

    private ArrayList<RestaurantData> mArrayList;
    private RestaurantAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private EditText mEditTextSearchKeyword;
    private String mJsonString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korean_noodle);

        final Button infoBtn = (Button) findViewById(R.id.infoBtn);

        infoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent storeInfoIntent = new Intent(KoreanNoodleActivity.this, StoreInfoActivity.class);
                KoreanNoodleActivity.this.startActivity(storeInfoIntent);
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.listView_main_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mArrayList = new ArrayList<RestaurantData>();

        mAdapter = new RestaurantAdapter(this, mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        mArrayList.clear();
        mAdapter.notifyDataSetChanged();

        GetData task = new GetData();
        task.execute( "http://" + IP_ADDRESS + "/getKoreanNoodle.php", "");

    }

    private class GetData extends AsyncTask<String, Void, String>{

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(KoreanNoodleActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            /*mTextViewResult.setText(result);*/
            Log.d(TAG, "response - " + result);

                mJsonString = result;
                showResult();

        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];
            String postParameters = params[1];

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();
            } catch (Exception e) {
                Log.d(TAG, "GetData : Error ", e);
                errorString = e.toString();

                return null;
            }
        }
    }

    private void showResult(){

        String TAG_JSON="koreanFood";
        String TAG_registerNum = "registerNum";
        String TAG_restaurantName = "restaurantName";
        String TAG_address = "address";
        String TAG_tel ="tel";

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String registerNum = item.getString(TAG_registerNum);
                String restaurantName = item.getString(TAG_restaurantName);
                String address = item.getString(TAG_address);
                String tel = item.getString(TAG_tel);

                RestaurantData restaurantData = new RestaurantData();

                restaurantData.setRegisterNum(registerNum);
                restaurantData.setRestaurantName(restaurantName);
                restaurantData.setTel(tel);
                restaurantData.setAddress(address);

                mArrayList.add(restaurantData);
                mAdapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            Log.d(TAG, "showResult : ", e);
        }
    }
}