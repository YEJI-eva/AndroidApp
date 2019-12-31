package com.example.second;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.CustomViewHolder> {
    private ArrayList<ReviewData> mList = null;
    private Activity context = null;

    public ReviewAdapter(Activity context, ArrayList<ReviewData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView userName;
        protected TextView reviewContent;
        protected TextView regDate;
        protected TextView rate;


        public CustomViewHolder(View view) {
            super(view);
            this.userName = (TextView) view.findViewById(R.id.textView_list_userName);
            this.reviewContent = (TextView) view.findViewById(R.id.textView_list_reviewContent);
            this.regDate = (TextView) view.findViewById(R.id.textView_list_regDate);
            this.rate = (TextView) view.findViewById(R.id.textView_list_rate);
        }
    }

    @Override
    public ReviewAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_list, null);
        ReviewAdapter.CustomViewHolder viewHolder = new ReviewAdapter.CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.CustomViewHolder viewHolder, int position) {

        viewHolder.userName.setText(mList.get(position).getUserName());
        viewHolder.reviewContent.setText(mList.get(position).getReviewContent());
        viewHolder.regDate.setText(mList.get(position).getRegDate());
        viewHolder.rate.setText(mList.get(position).getRate());

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
