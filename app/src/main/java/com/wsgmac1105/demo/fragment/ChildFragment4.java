package com.wsgmac1105.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.wsgmac1105.demo.R;
import com.wsgmac1105.demo.activity.TestActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wushaoge on 15/11/12.
 */
public class ChildFragment4 extends BaseFragment {

    private final String TITLE = "动漫";
    @Bind(R.id.tv_test)
    TextView tvTest;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child4, null);
        ButterKnife.bind(getActivity(), view);
        return view;
    }

    @Override
    public String getPageTitle() {
        return TITLE;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(getActivity());
    }
}
