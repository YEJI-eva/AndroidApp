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

public class Review extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.review, container, false);

        Button cameraBtn = (Button) view.findViewById(R.id.writeReview);

        cameraBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String restaurantNum;
                final String userName;

                Bundle bundle = getArguments();

                if (bundle != null) {
                    restaurantNum = bundle.getString("restaurantNum");
                    userName = bundle.getString("userName");

                    Log.d("a", restaurantNum);
                    Intent reviewIntent = new Intent(getActivity(), ReviewWriteActivity.class);
                    reviewIntent.putExtra("userName", userName);
                    reviewIntent.putExtra("storeNum", restaurantNum);
                    getActivity().startActivity(reviewIntent);
                }

            }
        });

        return view;
    }
}