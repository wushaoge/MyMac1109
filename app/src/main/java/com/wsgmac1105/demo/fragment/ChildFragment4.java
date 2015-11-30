package com.wsgmac1105.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wsgmac1105.demo.R;
import com.wsgmac1105.demo.view.DragLoginView2;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wushaoge on 15/11/12.
 */
public class ChildFragment4 extends BaseFragment {

    private final String TITLE = "动漫";
    @Bind(R.id.dlv_dragLogin2)
    DragLoginView2 dlvDragLogin2;
    @Bind(R.id.btn_open)
    Button btnOpen;
    @Bind(R.id.btn_close)
    Button btnClose;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child4, null);
        ButterKnife.bind(this, view);

        initView();

        return view;
    }


    private void initView(){

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlvDragLogin2.open();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlvDragLogin2.close();
            }
        });

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
