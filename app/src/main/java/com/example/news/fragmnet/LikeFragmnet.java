package com.example.news.fragmnet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.adapter.MyLikeListviewAdapter;
import com.example.news.db.MyNewsOrmliteOpenHelper;
import com.example.news.entity.News;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by c8469 on 2016/12/14.
 */

public class LikeFragmnet extends Fragment {

    private ListView listView;
    private Dao<News, Long> dao;
    private MyLikeListviewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_like_fragment_activity_main, container, false);

        listView = (ListView) view.findViewById(R.id.listview_like_fragment_activity_main);

        try {
            dao = MyNewsOrmliteOpenHelper.getInstance(getContext()).getDao(News.class);

            List<News> newses = dao.queryForAll();
            adapter = new MyLikeListviewAdapter(getContext(), newses);
            listView.setAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                try {
                    dao.delete(adapter.datas.get(position));

                    adapter.datas.remove(position);
                    adapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return true;
            }
        });

        return view;

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {

            try {
                List<News> newses = dao.queryForAll();
                adapter.datas = newses;
                adapter.notifyDataSetChanged();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
}
