package com.example.second;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyinfoAdapter extends RecyclerView.Adapter<MyinfoAdapter.CustomViewHolder> {

    private ArrayList<MyinfoData> mList = null;
    private Activity context = null;


    public MyinfoAdapter(Activity context, ArrayList<MyinfoData> list) {
        this.context = context;
        this.mList = list;
    }


        class CustomViewHolder extends RecyclerView.ViewHolder {
            protected TextView userName;
            protected TextView reviewContent;
            protected TextView regDate;
            protected RatingBar ratingBar;


            public CustomViewHolder(View view) {
                super(view);
                this.userName = (TextView) view.findViewById(R.id.textView_list_userName);
                this.reviewContent = (TextView) view.findViewById(R.id.textView_list_reviewContent);
                this.regDate = (TextView) view.findViewById(R.id.textView_list_regDate);
                this.ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
                ratingBar.setIsIndicator(true);
            }
        }

        @Override
        public MyinfoAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.myinfo_list, null);
            MyinfoAdapter.CustomViewHolder viewHolder = new MyinfoAdapter.CustomViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyinfoAdapter.CustomViewHolder viewHolder, int position) {

            viewHolder.userName.setText(mList.get(position).getUserName());
            viewHolder.reviewContent.setText(mList.get(position).getReviewContent());
            viewHolder.regDate.setText(mList.get(position).getRegDate());
            String rate = mList.get(position).getRate();
            Float rating = Float.parseFloat(rate);
            viewHolder.ratingBar.setRating(rating);

        }

        @Override
        public int getItemCount() {
            return (null != mList ? mList.size() : 0);
        }
    }

