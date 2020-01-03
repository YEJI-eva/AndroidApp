package com.example.second;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.CustomViewHolder> {

        private ArrayList<RestaurantData> mList = null;
        private Activity context = null;


    public RestaurantAdapter(Activity context, ArrayList<RestaurantData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView registerNum;
        protected TextView restaurantName;
        protected TextView address;
        protected TextView tel;


        public CustomViewHolder(View view) {
            super(view);
//            this.registerNum = (TextView) view.findViewById(R.id.textView_list_registerNum);
            this.restaurantName = (TextView) view.findViewById(R.id.textView_list_restaurantName);
            this.address = (TextView) view.findViewById(R.id.textView_list_address);
            this.tel = (TextView) view.findViewById(R.id.textView_list_tel);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewHolder, int position) {

//        viewHolder.registerNum.setText(mList.get(position).getRegisterNum());
        viewHolder.restaurantName.setText(mList.get(position).getRestaurantName());
        viewHolder.address.setText(mList.get(position).getAddress());
        viewHolder.tel.setText(mList.get(position).getTel());

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
