package com.huayu.tzc.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> fragment_list;
    private final ArrayList<String> tab_title_list;

    public MyFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<String> arrayList, ArrayList<Fragment> arrayList2) {
        super(fragmentManager);
        this.tab_title_list = arrayList;
        this.fragment_list = arrayList2;
    }

    public Fragment getItem(int i) {
        return this.fragment_list.get(i);
    }

    public int getCount() {
        return this.fragment_list.size();
    }

    public CharSequence getPageTitle(int i) {
        return this.tab_title_list.get(i);
    }
}
