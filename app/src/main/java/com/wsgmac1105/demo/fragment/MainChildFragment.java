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
public class MainChildFragment extends BaseFragment {


    @Bind(R.id.iv_image)
    ImageView ivImage;


    private int position;
    private static final int[] drawables = {R.drawable.wsg02, R.drawable.wsg13, R.drawable.wsg04, R.drawable.wsg06,
            R.drawable.wsg07, R.drawable.wsg10, R.drawable.wsg03, R.drawable.wsg12};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_child, null);
        ButterKnife.bind(this, view);


        position = getArguments().getInt("position");

        ivImage.setBackgroundResource(drawables[position]);


        return view;
    }


    /**
     * 提供当前Fragment的主色调的Bitmap对象,供Palette解析颜色
     *
     * @return
     */
    public static int getBackgroundBitmapPosition(int selectViewPagerItem) {
        return drawables[selectViewPagerItem];
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
