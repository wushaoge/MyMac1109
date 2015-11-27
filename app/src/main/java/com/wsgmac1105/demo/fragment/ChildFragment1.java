package com.wsgmac1105.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wsgmac1105.demo.R;
import com.wsgmac1105.demo.view.DragLoginView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 滑动布局
 * Created by wushaoge on 15/11/12.
 */
public class ChildFragment1 extends BaseFragment {

    private final String TITLE = "主页";
    @Bind(R.id.btn_login)
    Button btnLogin;

    @Bind(R.id.view_mask)
    View viewMask;

    @Bind(R.id.dlv_dragLogin)
    DragLoginView dlvDragLogin;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child1, null);
        ButterKnife.bind(this, view);

        initView();

        return view;
    }


    private void initView(){
        dlvDragLogin.setEnabled(true);

        //dlvDragLogin.show();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dlvDragLogin.isShow()) {
                    dlvDragLogin.dismiss();
                } else {
                    dlvDragLogin.show();
                }
            }
        });

        viewMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dlvDragLogin.isShow()){
                    dlvDragLogin.dismiss();
                }
            }
        });

        dlvDragLogin.setOnStatusListener(new DragLoginView.OnStatusListener() {
            @Override
            public void onShow() {
                //显示
                viewMask.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDismiss() {
                //隐藏
                viewMask.setVisibility(View.GONE);
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
