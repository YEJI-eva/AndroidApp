package com.example.second;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Store extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.store, container, false);
        String restaurantName;
        String restaurantAddress;
        String tel;

        final Bundle bundle = getArguments();

        if (bundle != null) {
            restaurantName = bundle.getString("restaurantName");
            restaurantAddress = bundle.getString("restaurantAddress");
            tel = bundle.getString("tel");
            Log.d("a", restaurantName);
            Log.d("b", restaurantAddress);
            Log.d("c", tel);

            TextView textView = (TextView) view.findViewById(R.id.textView_store);
            String str = "이름: " + restaurantName + "\n" + "주소: " + restaurantAddress + "\n" + "전화번호: " + tel;
            Log.d("str : ", str);

            textView.setText(str);
        }

        Button cameraBtn = (Button) view.findViewById(R.id.btn_goto_camera);

        cameraBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent uploadIntent = new Intent(getActivity(), GetUploadPhotoActivity.class);

                String storeNum = bundle.getString("restaurantNum");
                uploadIntent.getStringExtra(storeNum);
                getActivity().startActivity(uploadIntent);
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /* private static String IP_ADDRESS = "192.168.0.50";
    private static String TAG = "phptest";

    private EditText mEditTextRestaurantNum;
    private EditText mEditTextRestaurantName;
    private EditText mEditTextAddress;
    private TextView mTextViewResult;
    private ArrayList<RestaurantData> mArrayList;
    private RestaurantAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private EditText mEditTextSearchKeyword;
    private String mJsonString;
    private View view;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.store, container, false);


        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.listView_main_lista);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mArrayList = new ArrayList<RestaurantData>();

        mAdapter = new RestaurantAdapter((KoreanNoodleActivity) getActivity(), mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        mArrayList.clear();
        mAdapter.notifyDataSetChanged();

        GetData task = new GetData();
        task.execute( "http://" + IP_ADDRESS + "/getKoreanNoodle.php", "");

        return mRecyclerView;
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
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "response - " + result);

            if (result == null){
                mTextViewResult.setText(errorString);
            }
            else {
                mJsonString = result;
                showResult();
            }
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
        String TAG_REGISTERNUM = "REGISTERNUM";
        String TAG_RESTARANTNAME = "RESTARANTNAME";
        String TAG_ADDRESS = "ADDRESS";
        String TAG_TEL ="TEL";

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String registerNum = item.getString(TAG_REGISTERNUM);
                String restaurantName = item.getString(TAG_RESTARANTNAME);
                String address = item.getString(TAG_ADDRESS);
                String tel = item.getString(TAG_TEL);

                RestaurantData restaurantData = new RestaurantData();

                restaurantData.setRegisterNum(registerNum);
                restaurantData.setRestaurantName(restaurantName);
                restaurantData.setAddress(address);
                restaurantData.setTel(tel);

                mArrayList.add(restaurantData);
                mAdapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            Log.d(TAG, "showResult : ", e);
        }
    }*/


}
