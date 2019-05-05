package com.example.myapplication.main.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.main.picture.PictureAdapter;
import com.example.myapplication.main.picture.PictureBean;
import com.example.myapplication.main.picture.PicturePresenter;
import com.example.myapplication.main.picture.PictureView;
import com.example.myapplication.utils.ToastHelper;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PictureFragment extends BaseFragment implements PictureView {
    private PicturePresenter presenter;

    @BindView(R.id.pictureRecyclerView)
    RecyclerView pictureRecyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PicturePresenter();
    }

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_picture, container, false);
        presenter.attachView(this);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getPictureData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onSuccess(PictureBean data) {
        List<PictureBean.DataBean.NewsBean> news = data.getData().getNews();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        pictureRecyclerView.setLayoutManager(manager);
        pictureRecyclerView.setAdapter(new PictureAdapter(news));

    }

    @Override
    public void onFailure(String message) {
        ToastHelper.showShortToast(message);
    }


    public void changeLayoutManger(){
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.LayoutManager layoutManager = pictureRecyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager){
            pictureRecyclerView.setLayoutManager(linearLayoutManager);
        }
        else {
            pictureRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        }
    }

}
