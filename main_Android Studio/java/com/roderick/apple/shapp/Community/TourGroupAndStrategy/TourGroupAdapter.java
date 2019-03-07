package com.roderick.apple.shapp.Community.TourGroupAndStrategy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.roderick.apple.shapp.R;

import java.util.List;

/**
 * Created by dell on 2018/3/29.
 */

public class TourGroupAdapter extends BaseAdapter{
    private Fragment1 context;
    private List<TourGroupModel> tourGroupModelList;
    public TourGroupAdapter(Fragment1 context, List<TourGroupModel> tourGroupModelList){
        this.context=context;
        this.tourGroupModelList=tourGroupModelList;
    }
    @Override
    public int getCount() {
        return tourGroupModelList.size();
    }

    @Override
    public TourGroupModel getItem(int position) {
        return tourGroupModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
           convertView= LayoutInflater.from(context.getActivity()).inflate( R.layout.tour_group_item,null);
            //convertView= getView(0).getLayoutInflater().inflate(R.layout.tour_group_item,null);
        }
        TextView textTourGroupTitle=(TextView) convertView.findViewById(R.id.text_tourGroup_title);
        TextView textTourGroupDesction=(TextView) convertView.findViewById(R.id.text_tourGroup_desction);
        ImageView imageTourGroup=(ImageView) convertView.findViewById(R.id.imageView_tourGroup);

        TourGroupModel tourGroupModel=tourGroupModelList.get(position);
        textTourGroupTitle.setText(tourGroupModel.getTitle());
        textTourGroupDesction.setText(tourGroupModel.getDesc());
        //Glide框架加载图片
        Glide.with(context).load(tourGroupModel.getPicture()).into(imageTourGroup);
        return convertView;
    }

}
