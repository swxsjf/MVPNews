package com.example.myapplication.main.picture;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/29
 * Time: 13:45
 * Describe: ${as}
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {

    private List<PictureBean.DataBean.NewsBean> news;

    public PictureAdapter(List<PictureBean.DataBean.NewsBean> news) {
        this.news = news;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_picture, parent, false);
        return new PictureViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
        String largeimage = news.get(position).getLargeimage();
        largeimage = (position % 3 == 0) ? "https://img.ivsky.com/img/tupian/pre/201809/14/baise_xiaomao-003.jpg"
                : "https://t2.hddhhn.com/uploads/allimg/121106/1-121106100401.jpg";
        Glide.with(holder.itemView.getContext())
                .load(largeimage)
                .into(holder.pictureImageView);
//        ImageLoader.getInstance().load(largeimage, holder.pictureImageView);
        holder.pictureTextView.setText(news.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    class PictureViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.pictureImageView)
        ImageView pictureImageView;

        @BindView(R.id.pictureTextView)
        TextView pictureTextView;

        public PictureViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
