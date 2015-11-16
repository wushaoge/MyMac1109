package com.wsgmac1105.demo.fragment;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.util.LogUtils;
import com.wsgmac1105.demo.R;
import com.wsgmac1105.demo.view.WSGPagerSlidingTabStrip;


import org.simple.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wushaoge on 15/11/12.
 */
public class MainFragment extends BaseFragment {


    @Bind(R.id.pst_tabs)
    WSGPagerSlidingTabStrip myPagerSlidingTabStrip;
    @Bind(R.id.vp_viewpager)
    ViewPager myViewpager;

    MyPagerAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {

        myAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        myViewpager.setAdapter(myAdapter);
        myViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LogUtils.e("position的值"+position);
                LogUtils.e("positionOffset的值"+positionOffset);
                LogUtils.e("positionOffsetPixels的值"+positionOffsetPixels);

                ArgbEvaluator argbEvaluator = new ArgbEvaluator();



            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        myPagerSlidingTabStrip.setViewPager(myViewpager);
        myPagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                colorChange(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        initTabsValue();

    }


    /**
     * mPagerSlidingTabStrip默认值配置
     *
     */
    private void initTabsValue() {
        // 底部游标颜色
        myPagerSlidingTabStrip.setIndicatorColor(Color.BLUE);
        // tab的分割线颜色
        myPagerSlidingTabStrip.setDividerColor(Color.TRANSPARENT);
        // tab背景
//        myPagerSlidingTabStrip.setBackgroundColor(Color.parseColor("#4876FF"));
        myPagerSlidingTabStrip.setBackgroundColor(getResources().getColor(R.color.lightcoral));
        // tab底线高度
        myPagerSlidingTabStrip.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1, getResources().getDisplayMetrics()));
        // 游标高度
        myPagerSlidingTabStrip.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                5, getResources().getDisplayMetrics()));
        // 选中的文字颜色
        myPagerSlidingTabStrip.setSelectedTextColor(Color.BLUE);
        //myPagerSlidingTabStrip.setTextColorResource(R.color.white);

        // 正常文字颜色
        myPagerSlidingTabStrip.setTextColor(Color.BLACK);
    }

   /* private void setSelectTextColor(int position) {
        int tabCount = myAdapter.getCount();
        tabCount = ((LinearLayout)myPagerSlidingTabStrip.getChildAt(0)).getChildCount();
        LogUtils.e("tab的数量"+tabCount);
        for (int i = 0; i < tabCount; i++) {
            View view = ((LinearLayout)myPagerSlidingTabStrip.getChildAt(0)).getChildAt(i);
            if (view instanceof ImageButton) {
            } else if (view instanceof TextView) {
                //LogUtils.e("进入自定义方式");
                TextView tabTextView = (TextView) view;
                if (position == i) {
                    ((TextView) view).setTextColor(Color.GREEN);
                } else {
                    ((TextView) view).setTextColor(Color.BLACK);
                }
            }
        }
    }*/

    /**
     * 界面颜色的更改
     */
    @SuppressLint("NewApi")
    private void colorChange(int position) {
        LogUtils.e("发送颜色");


        // 用来提取颜色的Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                SuperAwesomeCardFragment.getBackgroundBitmapPosition(position));
        // Palette的部分
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            /**
             * 提取完之后的回调方法
             */
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
				/* 界面颜色UI统一性处理,看起来更Material一些 */
                myPagerSlidingTabStrip.setBackgroundColor(vibrant.getRgb());
                myPagerSlidingTabStrip.setTextColor(vibrant.getTitleTextColor());
                // 其中状态栏、游标、底部导航栏的颜色需要加深一下，也可以不加，具体情况在代码之后说明
                myPagerSlidingTabStrip.setIndicatorColor(colorBurn(vibrant.getRgb()));

                //MainActivity.toolbar.setBackgroundColor(vibrant.getRgb());

                EventBus.getDefault().post(vibrant.getRgb(),"rgb");

                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = getActivity().getWindow();
                    // 很明显，这两货是新API才有的。
                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                }
            }
        });
    }



    /**
     * 颜色加深处理
     *
     * @param RGBValues
     *            RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *            Android中我们一般使用它的16进制，
     *            例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *            red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *            所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }


    public class MyPagerAdapter extends FragmentPagerAdapter{

        private final String[] TITLES = {"主页","热门推荐","热门收藏","本月热榜","今日热榜","专栏","随机","伪装"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            MainChildFragment fragment = new MainChildFragment();
            Bundle b = new Bundle();
            b.putInt("position", position);
            fragment.setArguments(b);
            return fragment;
        }



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
