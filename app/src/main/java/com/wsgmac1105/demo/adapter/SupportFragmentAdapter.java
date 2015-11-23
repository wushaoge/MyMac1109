package com.wsgmac1105.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wsgmac1105.demo.fragment.BaseFragment;

import java.util.List;

/**
 * Created by wushaoge on 15/11/17.
 */
public class SupportFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragment;

    public SupportFragmentAdapter(FragmentManager fm,List<BaseFragment> mFragment) {
        super(fm);
        this.mFragment = mFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragment.get(position).getPageTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        if(null!=mFragment&&mFragment.size()>0){
            return mFragment.size();
        }
        return 0;
    }
}
