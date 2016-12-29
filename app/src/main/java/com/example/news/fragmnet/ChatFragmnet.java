package com.example.news.fragmnet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.adapter.MyChatListAdapter;
import com.example.news.entity.MyMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c8469 on 2016/12/14.
 */

public class ChatFragmnet extends Fragment {

    private ListView listView;
    private List<MyMessage> datas;
    private EditText et_message;
    private Button btn_send;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_chat_fragment_activity_main, container, false);

        listView = (ListView) view.findViewById(R.id.lv_chat_fragment_activity_main);

        et_message = (EditText) view.findViewById(R.id.et_message_chat_fragment_activity_main);

        btn_send = (Button) view.findViewById(R.id.btn_send_chat_fragment_activity_main);

        datas = new ArrayList<>();

        datas.add(new MyMessage(0,"我是小羊羊机器人"));
        datas.add(new MyMessage(1,"我是小羊羊"));



        MyChatListAdapter adapter = new MyChatListAdapter(datas,getContext());

        listView.setAdapter(adapter);

        listView.setSelection(datas.size());

        return view;

    }
}
