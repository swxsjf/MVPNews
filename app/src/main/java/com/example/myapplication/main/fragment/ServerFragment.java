package com.example.myapplication.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServerFragment extends BaseFragment {

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        return getTextView();
    }

}
