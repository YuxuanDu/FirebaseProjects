package com.google.firebase.quickstart.auth;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Yuxuan on 2/12/2017.
 */

public class ChatAdapter extends BaseAdapter {

    LayoutInflater inflater;
    Context context;
    Activity activity;
    List<ChatMessage> chatMessageList;

    public ChatAdapter(Activity activity, List<ChatMessage> chatMessageList) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.chatMessageList = chatMessageList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return chatMessageList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView username;
        TextView datetime;
        TextView message;
        if (view == null) {
            view = inflater.inflate(R.layout.chatlist_unit, viewGroup, false);
        }
        username = view.findViewById(R.id.tv_username);
        datetime = view.findViewById(R.id.tv_datetime);
        message = view.findViewById(R.id.tv_message);
        username.setText(chatMessageList.get(position).getMessageUser());
        datetime.setText(chatMessageList.get(position).getMessageTime());
        message.setText(chatMessageList.get(position).getMessageText());
        return view;
    }


}
