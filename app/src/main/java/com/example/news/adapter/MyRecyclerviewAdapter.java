package com.example.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.news.R;
import com.example.news.entity.NewsBean;

import java.util.List;

/**
 * Created by c8469 on 2016/12/21.
 */

public class MyRecyclerviewAdapter extends RecyclerView.Adapter<MyRecyclerviewAdapter.MyViewHolder> {

    public List<NewsBean.ResultBean.DataBean> datas;
    private Context context;
    private OnRecyclerviewItemClickListener listener;

    public MyRecyclerviewAdapter(List<NewsBean.ResultBean.DataBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_iamge_fragment_activity_main, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Glide.with(context).load(datas.get(position).getThumbnail_pic_s()).into(holder.iv);
        holder.tv.setText(datas.get(position).getAuthor_name());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tv;

        public MyViewHolder(final View itemView) {
            super(itemView);

            iv = (ImageView) itemView.findViewById(R.id.iv_icon_image_fragment_activity_main);
            tv = (TextView) itemView.findViewById(R.id.tv_title_image_fragment_activity_main);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClick(itemView,getLayoutPosition());

                }
            });

        }
    }


    public void setOnRecyclerviewItemClickListener(OnRecyclerviewItemClickListener listener){

        this.listener = listener;

    }

    public interface OnRecyclerviewItemClickListener{

        void onItemClick(View v,int position);

    }


}
