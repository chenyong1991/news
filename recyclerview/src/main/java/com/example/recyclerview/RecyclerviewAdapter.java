package com.example.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by c8469 on 2016/12/20.
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {

    public List<String> datas;
    private Context context;

    public RecyclerviewAdapter(Context context,List<String> datas){

        this.context = context;

        this.datas = datas;

    }
    //初始化视图
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);

        return new MyViewHolder(view);
    }
    //初始化数据
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv.setText(datas.get(position));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            tv = (TextView) itemView.findViewById(R.id.tv);

        }
    }

}
