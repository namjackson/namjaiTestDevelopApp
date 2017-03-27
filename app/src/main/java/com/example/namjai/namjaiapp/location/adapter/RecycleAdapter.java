package com.example.namjai.namjaiapp.location.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.namjai.namjaiapp.R;
import com.example.namjai.namjaiapp.location.bean.locationBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by namjai on 2017-01-31.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private List<locationBean> locationList = new ArrayList<>();

    private int itemLayout;
    private Context context;

    //Activity 에서 설정해주는 클릭 리스너
    OnMessageClickLinster mListener;
    public void setOnItemClickListner(OnMessageClickLinster listener){
        mListener= listener;
    }


    public RecycleAdapter(List<locationBean> list, int itemLayout){
        this.locationList = list;
        this.itemLayout = itemLayout;
    }

    public void changeList(List<locationBean> list){
        this.locationList = list;
    }

    /**
     * 메시지 추가
     * @param bean
     */
    public void add(locationBean bean){
        locationList.add(bean);
        notifyDataSetChanged();
    }


    /**
     * 레이아웃을 만들어서 Holer에 저장
     * @param viewGroup
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
        return new ViewHolder(view);
    }


    /**
     * listView getView 를 대체
     * 넘겨 받은 데이터를 화면에 출력하는 역할
     *
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.setItemData(locationList.get(position));
        viewHolder.setOnMessageClickListner(mListener);



    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }




    public void deleteMessage(int index){
        locationList.remove(index);
        notifyItemRemoved(index);
    }

    public interface OnMessageClickLinster {
        public void onMessageClicked(ViewHolder viewHolder, View view, locationBean locationBean, int position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textLocation;
        public TextView textLongitude;
        public TextView textLatitude;
        public TextView textAltitude;
        public TextView textAccuracy;
        public LinearLayout ll_message;
        public CardView cardview;

        locationBean locationBean;
        OnMessageClickLinster mItemClickLister;

        public void setOnMessageClickListner(OnMessageClickLinster listner){
            mItemClickLister= listner;
        }

        public ViewHolder(View itemView){
            super(itemView);
            ll_message = (LinearLayout) itemView.findViewById(R.id.ll_location);
            textLocation = (TextView) itemView.findViewById(R.id.location);
            textLongitude = (TextView) itemView.findViewById(R.id.longitude);
            textLatitude = (TextView) itemView.findViewById(R.id.latitude);
            textAltitude = (TextView) itemView.findViewById(R.id.altitude);
            textAccuracy = (TextView) itemView.findViewById(R.id.accuracy);

            cardview = (CardView)itemView.findViewById(R.id.cardview);
            ll_message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(mItemClickLister != null){
                        mItemClickLister.onMessageClicked(ViewHolder.this , view, locationBean, position);
                    }
                }
            });
        }

        public void setItemData(locationBean bean){
            locationBean = bean;

            textLocation.setText("위치정보 : " + locationBean.getProvider());
            textLongitude.setText("위도 : "+locationBean.getLongitude()+"");
            textLatitude.setText("경도 : "+locationBean.getLatitude()+"");
            textAltitude.setText("고도 : "+locationBean.getAltitude()+"");
            textAccuracy.setText("정확도 : "+locationBean.getAccuracy()+"");

        }

    }

}




