package com.example.myapplication.main;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.base.MvpActivity;
import com.example.myapplication.main.bean.AvatarBean;
import com.example.myapplication.main.bean.NewsCategory;
import com.example.myapplication.main.fragment.GovernmentFragment;
import com.example.myapplication.main.fragment.HomeFragment;
import com.example.myapplication.main.fragment.NewsFragment;
import com.example.myapplication.main.fragment.PictureFragment;
import com.example.myapplication.main.fragment.ServerFragment;
import com.example.myapplication.main.fragment.SettingsFragment;
import com.example.myapplication.utils.ToastHelper;
import com.example.myapplication.utils.TraverseUtils;
import com.example.myapplication.view.AvatarImageView;

import java.util.List;

import butterknife.BindView;

public class Main2Activity extends MvpActivity<MainPresenter, MainView, AvatarBean> implements MainView {

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    private long lastTime = 0;
    private NavigationView navigationView;
    private AvatarImageView avatarImageView;
    private TextView toolBarTitleTextView;
    private Toolbar toolBar;
    private DrawerLayout drawLayout;

    private List<NewsCategory.DataBean.ChildrenBean> children;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView();

        initView();
        initData();

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initData() {
        presenter.getNewsCategory();
        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                Main2Activity.this.startActivityForResult(intent, 100);
            }
        });
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawLayout.isDrawerOpen(Gravity.START)) {
                    drawLayout.closeDrawer(Gravity.START);
                } else {
                    drawLayout.openDrawer(Gravity.START);
                }
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BaseFragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.homeItem:
                        toolBar.setNavigationIcon(null);
                        fragment = new HomeFragment();
                        break;

                    case R.id.newsItem:
                        toolBar.setNavigationIcon(R.drawable.img_menu);
                        fragment = new NewsFragment();
                        break;

                    case R.id.serviceItem:
                        toolBar.setNavigationIcon(R.drawable.img_menu);
                        fragment = new ServerFragment();
                        break;

                    case R.id.governmentItem:
                        toolBar.setNavigationIcon(R.drawable.img_menu);
                        fragment = new GovernmentFragment();
                        break;

                    case R.id.settingsItem:
                        toolBar.setNavigationIcon(null);
                        fragment = new SettingsFragment();
                        break;
                }

                Main2Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout,fragment)
                        .commit();
                toolBarTitleTextView.setText(menuItem.getTitle());
                drawLayout.closeDrawer(Gravity.START);

                return true;
            }
        });
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initView() {
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);
        avatarImageView = headerView.findViewById(R.id.avatarImageView);
        toolBarTitleTextView = (TextView) findViewById(R.id.toolBarTitleTextView);
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        drawLayout = (DrawerLayout) findViewById(R.id.drawLayout);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null) {
            Uri uri = data.getData();
            ContentResolver contentResolver = getContentResolver();
            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                String path = cursor.getString(cursor.getColumnIndex("_data"));
                presenter.upload(path);
                cursor.close();
            }
        }
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime > 3000) {
            Toast.makeText(context, "再按一次退出", Toast.LENGTH_SHORT).show();
            lastTime = currentTime;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onSuccess(AvatarBean data) {
        Glide.with(context).load(data.getData().getUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        System.out.println("onLoadFailed:");
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        avatarImageView.setImageDrawable(resource);
                        avatarImageView.resetAvatar();
                        System.out.println("onResourceReady");
                        return true;
                    }
                }).into(avatarImageView);
    }

    @Override
    public void onFailure(String message) {
        Log.e("aaa", "file:" + message);
        ToastHelper.showShortToast(message);
    }

    @Override
    public void onNewsCategory(NewsCategory category) {
        bottomNavigationView.setSelectedItemId(R.id.newsItem);

        Menu menu = navigationView.getMenu();
        final List<NewsCategory.DataBean> list = category.getData();
        children = list.get(0).getChildren();
        for (int i = 0; i < list.size(); i++) {
            String title = list.get(i).getTitle();
            MenuItem menuItem = menu.add(title);
            menuItem.setIcon(R.drawable.menu_arr_normal);
        }
        TraverseUtils.traverse(navigationView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int itemId = menuItem.getItemId();
                CharSequence itemTitle = menuItem.getTitle();
                BaseFragment baseFragment = null;

                for (int i = 0; i <list.size(); i++){
                    if ("新闻".equals(itemTitle.toString())){
                        baseFragment = new NewsFragment();
                    }
                    else if ("组图".equals(itemTitle.toString())){
                        baseFragment = new PictureFragment();
                    }
                    Main2Activity.this
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout,baseFragment)
                            .commit();
                }
                toolBarTitleTextView.setText(itemTitle);
                drawLayout.closeDrawer(Gravity.START);

                return true;
            }
        });
    }

    public List<NewsCategory.DataBean.ChildrenBean> getChildren(){
        return children;
    }
}
