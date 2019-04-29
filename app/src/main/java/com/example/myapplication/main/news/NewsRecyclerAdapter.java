package com.example.myapplication.main.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.main.bean.SubNews;

import java.util.List;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/22
 * Time: 20:05
 * Describe: ${as}
 */
public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {
    private List<SubNews.DataBean.NewsBean> list;
    private Context context;
    private onClickItem onClickItem;

    public void setOnClickItem(NewsRecyclerAdapter.onClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public NewsRecyclerAdapter(List<SubNews.DataBean.NewsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        final View inflate = LayoutInflater.from(context).inflate(R.layout.news_rel_adp, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String listimage = list.get(i).getListimage();
        String befImgUrl = listimage.substring(0, 7);
        String latImgUrl = listimage.substring(15);
        String comImagUrl = befImgUrl+"169.254.179.62"+latImgUrl;

        Glide.with(context).load(comImagUrl).into(viewHolder.imageView);
        viewHolder.textView.setText(list.get(i).getTitle());
        viewHolder.textView2.setText(list.get(i).getPubdate());


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItem != null){
                    onClickItem.callback(v, i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_adp);
            textView = itemView.findViewById(R.id.text_adp);
            textView2 = itemView.findViewById(R.id.text_time);
        }
    }

    public interface onClickItem{
        void callback(View v,int position );
    }

}
