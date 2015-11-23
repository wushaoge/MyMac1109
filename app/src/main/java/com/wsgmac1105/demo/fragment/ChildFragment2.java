package com.wsgmac1105.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wsgmac1105.demo.R;
import com.wsgmac1105.demo.activity.TestActivity;
import com.wsgmac1105.demo.view.CustomImgContainer;
import com.wsgmac1105.demo.view.FirstGroupView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wushaoge on 15/11/12.
 */
public class ChildFragment2 extends BaseFragment {

    private final String TITLE = "热门推荐";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child2, null);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public String getPageTitle() {
        return TITLE;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
