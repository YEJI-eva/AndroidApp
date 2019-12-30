package com.example.second;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

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

public class Review extends Fragment {

    private static String IP_ADDRESS = "192.168.0.50";
    private static String TAG = "phptest";

    private ArrayList<ReviewData> mArrayList;
    private ReviewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private EditText mEditTextSearchKeyword;
    private String mJsonString;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.review, container, false);

        Button writeReviewBtn = (Button) view.findViewById(R.id.writeReview);

        // 가게번호, 회원이름 받아와서 리부 작성누르면 작성페이지로 값 넘겨주는 작업
        writeReviewBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String storeNum;
                final String userName;

                Bundle bundle = getArguments();

                if (bundle != null) {
                    storeNum = bundle.getString("restaurantNum");
                    userName = bundle.getString("userName");

                    Intent reviewIntent = new Intent(getActivity(), ReviewWriteActivity.class);
                    reviewIntent.putExtra("userName", userName);
                    reviewIntent.putExtra("storeNum", storeNum);
                    getActivity().startActivity(reviewIntent);
                }

            }
        });
        Bundle bundle = getArguments();
        String storeNum = (String) bundle.getString("restaurantNum");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.review_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mArrayList = new ArrayList<ReviewData>();

        mAdapter = new ReviewAdapter(getActivity(), mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        mArrayList.clear();
        mAdapter.notifyDataSetChanged();


        Log.d("@@@StoreNum@@@", storeNum);
        // 여기서 php로 보낼 값을 담아줍니다.
        Review.GetData task = new Review.GetData();
        task.execute( "http://" + IP_ADDRESS + "/getReviews.php", storeNum);

        return view;
    }


    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(getActivity(),
                    "Please Wait", null, true, true);
        }



        @Override
        protected String doInBackground(String... params) {
            // 위에서 php로 보내기위해 담은 값을 찾아 줍니다.
            String serverURL = params[0];
            String storeNum = (String) params[1];
            String postParameters = "storeNum=" + storeNum;
            Log.d("@@@param[1]@@@", postParameters);

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                //파라미터에 담아서 PHP로 보내줍니다
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
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response - " + result);

            mJsonString = result;
            showResult();
        }
    }

    private void showResult(){

        String TAG_JSON="reviews";
        String TAG_userName = "userName";
        String TAG_reviewContent = "reviewContent";
        String TAG_regDate = "regDate";
        String TAG_rate ="rate";

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String userName = item.getString(TAG_userName);
                String reviewContent = item.getString(TAG_reviewContent);
                String regDate = item.getString(TAG_regDate);
                String rate = item.getString(TAG_rate);

                ReviewData reviewData = new ReviewData();

                reviewData.setUserName(userName);
                reviewData.setReviewContent(reviewContent);
                reviewData.setRegDate(regDate);
                reviewData.setRate(rate);

                mArrayList.add(reviewData);
                mAdapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            Log.d(TAG, "showResult : ", e);
        }
    }
}