package com.example.news.fragmnet;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.news.R;
import com.example.news.activity.WebActivity;
import com.example.news.adapter.MyDatasListviewAdapter;
import com.example.news.entity.NewsBean;
import com.example.news.utils.Constant;
import com.example.news.utils.GlideImageLoader;
import com.example.news.utils.NoHttpInstance;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c8469 on 2016/12/14.
 */

public class NewsBeanFragmnet extends Fragment {

    private SwipeRefreshLayout layout;

    private ListView listview;
    private List<NewsBean.ResultBean.DataBean> datas;

    private int type = 0;//新闻类型
    private MyDatasListviewAdapter adapter;
    private Banner banner;

    public NewsBeanFragmnet(int type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout = (SwipeRefreshLayout) inflater.inflate(R.layout.layout_newsbean_fragment_activity_main,null);

        listview = (ListView) layout.findViewById(R.id.listview_news_fragment_activity_main);

        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        //getDatasFromNet
                        adapter.datas.remove(0);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                                layout.setRefreshing(false);

                            }
                        });

                    }
                }).start();

            }
        });

        banner = new Banner(getContext());
        //width? height?   往一个容器里面添加view params
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,500);
        //把参数赋值给banner
        banner.setLayoutParams(params);

        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerAnimation(Transformer.Accordion);
        List<String> urls = new ArrayList<>();
        urls.add("http://03.imgmini.eastday.com/mobile/20161215/20161215180307_912a994af58ed04ce9ac56d6a9b18f4e_1_mwpm_03200403.jpeg");
        urls.add("http://img5.mtime.cn/mg/2016/12/06/153553.41083212.jpg");
        urls.add("http://img5.mtime.cn/mg/2016/12/04/143436.93575096.jpg");
        urls.add("http://img5.mtime.cn/mg/2016/12/02/103948.81410564.jpg");
        banner.setImages(urls);

        listview.addHeaderView(banner);

        banner.start();



        /*refreshLayout = (SwipeRefreshLayout) inflater.inflate(R.layout.layout_newsbean_fragment_activity_main, null);

        Toast.makeText(getContext(), "onCreate" + type, Toast.LENGTH_SHORT).show();

        listview = (ListView) refreshLayout.findViewById(R.id.listview);*/

        /*Banner banner = new Banner(getContext());
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
        banner.setLayoutParams(params);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setBannerAnimation(Transformer.DepthPage);
        List<String> urls = new ArrayList<>();
        urls.add("http://03.imgmini.eastday.com/mobile/20161215/20161215180307_912a994af58ed04ce9ac56d6a9b18f4e_1_mwpm_03200403.jpeg");
        urls.add("http://img5.mtime.cn/mg/2016/12/06/153553.41083212.jpg");
        urls.add("http://img5.mtime.cn/mg/2016/12/04/143436.93575096.jpg");
        urls.add("http://img5.mtime.cn/mg/2016/12/02/103948.81410564.jpg");
        banner.setImages(urls);
        List<String> titles = new ArrayList<>();
        titles.add("title1");
        titles.add("title2");
        titles.add("title3");
        titles.add("title4");
        banner.setBannerTitles(titles);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
        listview.addHeaderView(banner);*/

        /*refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        SystemClock.sleep(2000);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "refresh", Toast.LENGTH_SHORT).show();
                                adapter.datas.remove(0);
                                adapter.notifyDataSetChanged();
                                refreshLayout.setRefreshing(false);

                            }
                        });

                    }
                }).start();

            }
        });*/

        return layout;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Request<String> topNewsStringRequest = NoHttp.createStringRequest(Constant.BASE_URL + Constant.URLS[type]);

        NoHttpInstance.getInstance().add(Constant.WHAT_NENS_REQUEST, topNewsStringRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {


                String result = response.get();

                NewsBean newsBean = JSON.parseObject(result, NewsBean.class);

                datas =   newsBean.getResult().getData();

                adapter = new MyDatasListviewAdapter(getContext(),datas);

                listview.setAdapter(adapter);
            }

            @Override
            public void onFailed(int what, Response<String> response) {

                Toast.makeText(getContext(), "response.getException():" + response.getException(), Toast.LENGTH_SHORT).show();

                Log.d(".....................", "onFailed: " + response.getException());

            }

            @Override
            public void onFinish(int what) {

            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), WebActivity.class);
                String url = datas.get(position - 1).getUrl();
                String img_url = datas.get(position - 1).getThumbnail_pic_s();
                intent.putExtra("url",url);
                intent.putExtra("img_url",img_url);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        //结束轮播
        banner.stopAutoPlay();

    }

}
