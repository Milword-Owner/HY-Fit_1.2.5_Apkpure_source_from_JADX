package com.huayu.tzc.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private FragmentManager fragmentManager;
    private ArrayList<Fragment> fragments;

    public ViewPagerFragmentAdapter(FragmentManager fragmentManager2, ArrayList<Fragment> arrayList) {
        super(fragmentManager2);
        this.fragments = arrayList;
        this.fragmentManager = fragmentManager2;
    }

    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    public int getCount() {
        return this.fragments.size();
    }
}
