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

    /*public RestaurantAdapter(KoreanNoodleActivity koreanNoodleActivity, ArrayList<RestaurantData> mArrayList) {

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return mList.size();
    }

    void addItem(RestaurantData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        mList.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        protected TextView restaurantNum;
        protected TextView restaurantName;
        protected TextView address;
        protected TextView tel;

        ItemViewHolder(View view) {
            super(view);

            this.restaurantNum = (TextView) view.findViewById(R.id.textView_list_restaurantNum);
            this.restaurantName = (TextView) view.findViewById(R.id.textView_list_restaurantName);
            this.address = (TextView) view.findViewById(R.id.textView_list_address);
            this.tel = (TextView) view.findViewById(R.id.textView_list_tel);
        }

        void onBind(RestaurantData data) {
            restaurantNum.setText(data.getRestaurantNum());
            restaurantName.setText(data.getRestaurantName());
            address.setText(data.getAddress());
            tel.setText(data.getTel());
        }
    }*/

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

        //viewHolder.registerNum.setText(mList.get(position).getRegisterNum());
        viewHolder.restaurantName.setText(mList.get(position).getRestaurantName());
        viewHolder.address.setText(mList.get(position).getAddress());
        viewHolder.tel.setText(mList.get(position).getTel());

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
