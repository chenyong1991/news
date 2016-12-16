package com.example.news.fragmnet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.news.R;
import com.example.news.adapter.MyNewsPageAdapter;
import com.example.news.adapter.MyNewsPageStateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c8469 on 2016/12/14.
 */

public class NewsFragmnet extends Fragment {


    private ViewPager viewPager;
    private TabLayout tabs;
    private List<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.layout_news_fragment_activity_main, null);
        viewPager = (ViewPager) ll.findViewById(R.id.vp_news_fragment_activity_main);
        tabs = (TabLayout) ll.findViewById(R.id.tabs_news_fragment_activity_main);
        //关联viewpager
        tabs.setupWithViewPager(viewPager);

        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        return ll;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragments = new ArrayList<>();
        fragments.add(new NewsBeanFragmnet(0));
        fragments.add(new NewsBeanFragmnet(1));
        fragments.add(new NewsBeanFragmnet(2));
        fragments.add(new NewsBeanFragmnet(3));
        fragments.add(new NewsBeanFragmnet(4));
        fragments.add(new NewsBeanFragmnet(5));
        fragments.add(new NewsBeanFragmnet(6));
        fragments.add(new NewsBeanFragmnet(7));
        fragments.add(new NewsBeanFragmnet(8));
        fragments.add(new NewsBeanFragmnet(9));

//        MyNewsPageStateAdapter adapter = new MyNewsPageStateAdapter(getFragmentManager(), fragments);
        MyNewsPageAdapter adapter = new MyNewsPageAdapter(getFragmentManager(),fragments);
        viewPager.setAdapter(adapter);

        /*Request<String> stringRequest = NoHttp.createStringRequest(Constant.BASE_URL + Constant.TOP_NEWS_QUERY_STRING);

        NoHttpInstance.getInstance().add(Constant.WHAT_NENS_REQUEST, stringRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {

                String result = response.get();

                NewsBean newsBean = JSON.parseObject(result, NewsBean.class);

            }

            @Override
            public void onFailed(int what, Response<String> response) {

                Toast.makeText(getContext(), "response.getException():" + response.getException(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFinish(int what) {

            }
        });*/


    }
}
