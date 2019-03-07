package com.roderick.apple.shapp.Community.Discuss;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.roderick.apple.shapp.Personal.Login.Login_Message;
import com.roderick.apple.shapp.R;

import java.util.List;

public class MessageAdapter extends BaseAdapter {

    private Fragment3 context;
    private List<MessageModel> messageModelList;
    public MessageAdapter(Fragment3 context, List<MessageModel> messageModelList){
        this.context=context;
        this.messageModelList=messageModelList;
    }

    @Override
    public int getCount() {
        return messageModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context.getActivity()).inflate(R.layout.message_item,null);
        }
        TextView messageText=(TextView) convertView.findViewById(R.id.text_message);
        ImageView userImage=(ImageView)convertView.findViewById(R.id.image_user);
        TextView useAdress=(TextView)convertView.findViewById(R.id.user_adress);
        MessageModel messageModel=messageModelList.get(position);
        useAdress.setText(messageModel.getUserAdress());
        messageText.setText(messageModel.getContent());
        Glide.with(context).load(messageModel.getHeadprotrait()).into(userImage);

        return convertView;
    }
}
