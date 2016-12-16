package com.example.news.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.news.R;
import com.example.news.entity.NewsBean;
import com.example.news.utils.NoHttpInstance;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.List;

/**
 * Created by c8469 on 2016/12/14.
 */

public class MyDatasListviewAdapter extends BaseAdapter {

    private Context context;
    public List<NewsBean.ResultBean.DataBean> datas;

    public MyDatasListviewAdapter(Context context,List<NewsBean.ResultBean.DataBean> datas){

        this.context = context;
        this.datas = datas;

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

        View view = null;
        ViewHolder holder = null;

        if(convertView == null){

            holder = new ViewHolder();
            view = View.inflate(context, R.layout.item_listview_news_fragment_activity_main, null);
            holder.iv_pic = (ImageView) view.findViewById(R.id.iv_pic_news_fragment_activity_main);
            holder.tv_title = (TextView) view.findViewById(R.id.tv_title_news_fragment_activity_main);
            holder.tv_date = (TextView) view.findViewById(R.id.tv_date_news_fragment_activity_main);

            view.setTag(holder);

        }else{

            view = convertView;
            holder = (ViewHolder) view.getTag();

        }

        //初始化数据->
        /*final ImageView iv = holder.iv_pic;
        Request<Bitmap> imageRequest = NoHttp.createImageRequest(datas.get(position).getThumbnail_pic_s());

        NoHttpInstance.getInstance().add(1, imageRequest, new OnResponseListener<Bitmap>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<Bitmap> response) {

                iv.setImageBitmap(response.get());

            }

            @Override
            public void onFailed(int what, Response<Bitmap> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });*/
        Glide.with(context).load(datas.get(position).getThumbnail_pic_s()).crossFade().into(holder.iv_pic);

        holder.tv_title.setText(datas.get(position).getTitle());
        holder.tv_date.setText(datas.get(position).getDate());


        return view;
    }

    class ViewHolder{

        ImageView iv_pic;
        TextView tv_title;
        TextView tv_date;

    }

}
