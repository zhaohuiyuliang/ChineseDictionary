package com.zi.dian.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zi.dian.ui.FragmentBase;

import java.util.List;

/**
 * Created by wangliang on 6/13/16.
 */
public class AdapterViewPagerHome extends FragmentStatePagerAdapter {
    private List<FragmentBase> localFragments;

    public AdapterViewPagerHome(FragmentManager fm, List<FragmentBase> localFragments) {
        super(fm);
        this.localFragments = localFragments;
    }

    @Override
    public int getCount() {
        return localFragments.size();
    }

    @Override
    public Fragment getItem(int position) {

        return localFragments.get(position);
    }

}
