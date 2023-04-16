package com.huayu.tzc.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends PagerAdapter {
    private List<View> viewList;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ViewPagerAdapter(List<View> list) {
        this.viewList = list;
    }

    public int getCount() {
        List<View> list = this.viewList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @NotNull
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        viewGroup.addView(this.viewList.get(i));
        return this.viewList.get(i);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView(this.viewList.get(i));
    }
}
