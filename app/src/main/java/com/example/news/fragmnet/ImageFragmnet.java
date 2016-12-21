package com.example.news.fragmnet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.news.R;
import com.example.news.activity.WebActivity;
import com.example.news.adapter.MyRecyclerviewAdapter;
import com.example.news.entity.NewsBean;
import com.example.news.utils.Constant;
import com.example.news.utils.NoHttpInstance;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

/**
 * Created by c8469 on 2016/12/14.
 */

public class ImageFragmnet extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_image_fragmnet_acitivity_main, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_iamge_fragment_activity_main);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

        Request<String> stringRequest = NoHttp.createStringRequest(Constant.BASE_URL + Constant.URLS[9]);

        NoHttpInstance.getInstance().add(0, stringRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {

                String jsonStr = response.get();

                NewsBean newsBean = JSON.parseObject(jsonStr, NewsBean.class);

                final MyRecyclerviewAdapter adapter = new MyRecyclerviewAdapter(newsBean.getResult().getData(),getContext());

                adapter.setOnRecyclerviewItemClickListener(new MyRecyclerviewAdapter.OnRecyclerviewItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {

                        String url = adapter.datas.get(position).getUrl();

                        String img_url = adapter.datas.get(position).getThumbnail_pic_s();

                        Intent intent = new Intent(getContext(), WebActivity.class);

                        intent.putExtra("url",url);

                        intent.putExtra("img_url",img_url);

                        startActivity(intent);


                    }
                });

                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });

        //回调机制，监听机制




        return view;

    }
}
