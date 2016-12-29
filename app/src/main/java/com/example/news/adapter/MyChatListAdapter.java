package com.example.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.entity.MyMessage;

import java.util.List;

/**
 * Created by c8469 on 2016/12/23.
 */

public class MyChatListAdapter extends BaseAdapter {

    private List<MyMessage> datas;
    private Context context;

    public MyChatListAdapter(List<MyMessage> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ChatViewHolder holder = null;

        if (getItemViewType(position) == 0) {

            if (convertView == null) {
                holder = new ChatViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_from_message_chat_fragment_activity_main, parent, false);
                holder.tv = (TextView) convertView.findViewById(R.id.tv_chatfrom_chat_fragment_activity_main);
                convertView.setTag(holder);
            } else {

                holder = (ChatViewHolder) convertView.getTag();

            }

            holder.tv.setText(datas.get(position).getText());

        } else if (getItemViewType(position) == 1) {

            if (convertView == null) {
                holder = new ChatViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_to_message_chat_fragment_activity_main, parent, false);
                holder.tv = (TextView) convertView.findViewById(R.id.tv_chatto_chat_fragment_activity_main);
                convertView.setTag(holder);
            } else {

                holder = (ChatViewHolder) convertView.getTag();

            }

            holder.tv.setText(datas.get(position).getText());


        }


        return convertView;
    }


    @Override
    public int getItemViewType(int position) {
        int type = 0;
        switch (datas.get(position).getType()) {

            case 0:
                return 0;
            case 1:
                return 1;

        }

        return 0;
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    class ChatViewHolder {

        TextView tv;


    }

}
