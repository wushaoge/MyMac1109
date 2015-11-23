package com.wsgmac1105.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wsgmac1105.demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wushaoge on 15/11/12.
 */
public class ChildFragment8 extends BaseFragment {

    private final String TITLE = "手机";

    @Bind(R.id.iv_image)
    ImageView ivImage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child8, null);
        ButterKnife.bind(this, view);

        ivImage.setBackgroundResource(R.drawable.wsg09);

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
