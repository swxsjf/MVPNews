package com.example.myapplication.main.news;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.main.bean.SubNews;
import com.example.myapplication.view.WrapHeightViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/19
 * Time: 21:01
 * Describe: ${as}
 */
public class NewsPagerManger implements NewsView {
    private ViewGroup container;
    private NewsPresenter presenter;
    private ViewPager viewPager;
    private RecyclerView news_recyclerView;
    private Context context;
    @BindView(R.id.news_viewPager)
    WrapHeightViewPager news_viewPager;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    private View redDotView;
    private float dotsMargin;
    private Handler handler;
    private long delayMillis = 2000;
    private Runnable runnable;
    private NewsRecyclerAdapter adapter;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    public NewsPagerManger(ViewGroup container) {
        this.container = container;
        presenter = new NewsPresenter();
    }

    public View createPager() {
        context = container.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View pager = inflater.inflate(R.layout.pager_news, container, false);
        viewPager = pager.findViewById(R.id.news_viewPager);
        news_recyclerView = pager.findViewById(R.id.news_recyclerView);
        ButterKnife.bind(this,pager);

        presenter.attachView(this);
        return pager;
    }

    public void initData(final String url) {
        presenter.getTopNews(url);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Todo 下拉刷新获取数据
                presenter.getTopNews(url);
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (i >= 0){
                    swipeRefreshLayout.setRefreshing(true);
                }
                else {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onSuccess(SubNews data) {
        final List<SubNews.DataBean.TopnewsBean> topnews = data.getData().getTopnews();
        viewPager.setAdapter(new SubNewsPagerAdapter(topnews));

        //适配器recyclerView适配代码
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        news_recyclerView.setLayoutManager(manager);
        List<SubNews.DataBean.NewsBean> news = data.getData().getNews();
        adapter = new NewsRecyclerAdapter(news);
        news_recyclerView.setAdapter(adapter);

        //添加标题 如：蜗居生活
        textView.setText(topnews.get(0).getTitle());
        //加载小圆点
        initIndicator(topnews);
        news_viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int i) {
                textView.setText(topnews.get(i).getTitle());
                redDotView.setTranslationX(i * dotsMargin);
            }
        });

        //小圆点自动滑动
        autoPlayViewPager();

        //ViewPager点击与小圆点自动滑动处理事件
        stopPlayWhenTouch();

        //点击recyclerView跳转新闻页面
        jumpNewsPager(data);
    }

    private void jumpNewsPager(final SubNews data) {
        adapter.setOnClickItem(new NewsRecyclerAdapter.onClickItem() {
            @Override
            public void callback(View v, int position) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                String url = data.getData().getNews().get(position).getUrl();
                intent.putExtra("newsUrl",url);
                context.startActivity(intent);
            }
        });
    }

    private void stopPlayWhenTouch() {
        news_viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.postDelayed(runnable,delayMillis);
                        break;
                }
                return false;
            }
        });
    }

    private void autoPlayViewPager() {
        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = news_viewPager.getCurrentItem()+1;
                int pageCount = news_viewPager.getAdapter().getCount();
                news_viewPager.setCurrentItem(currentItem%pageCount);
                handler.postDelayed(this,delayMillis);
            }
        };
        handler.postDelayed(runnable,delayMillis);
    }

    public void onDestroy(){
        handler.removeCallbacksAndMessages(null);
    }

    private void initIndicator(List<SubNews.DataBean.TopnewsBean> topnews) {
        dotsMargin = context.getResources().getDimension(R.dimen.margin_dots);
        LayoutInflater inflater = LayoutInflater.from(context);
        for (int i = 0; i< topnews.size(); i++){
            View inflate = inflater.inflate(R.layout.view_gray_indicator, relativeLayout, false);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) inflate.getLayoutParams();
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            layoutParams.rightMargin = (int)(dotsMargin * (i+1));
            inflate.setLayoutParams(layoutParams);
            inflate.requestLayout();
            relativeLayout.addView(inflate);
        }

        redDotView = inflater.inflate(R.layout.view_red_indicator, relativeLayout, false);
        relativeLayout.addView(redDotView);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) redDotView.getLayoutParams();
        layoutParams.rightMargin = (int) (dotsMargin * topnews.size());
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        redDotView.setLayoutParams(layoutParams);
        redDotView.requestLayout();

    }

    @Override
    public void onFailure(String message) {

    }
}
