package com.wsgmac1105.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wsgmac1105.demo.R;

/**
 * Created by wushaoge on 15/11/12.
 */
public class LeftFragment extends BaseFragment {


    

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left,null);

        return view;
    }
}
