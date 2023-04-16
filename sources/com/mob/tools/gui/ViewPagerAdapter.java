package com.mob.tools.gui;

import android.view.View;
import android.view.ViewGroup;

public abstract class ViewPagerAdapter {
    private MobViewPager parent;

    public abstract int getCount();

    public abstract View getView(int i, View view, ViewGroup viewGroup);

    public void onScreenChange(int i, int i2) {
    }

    public void onScreenChanging(float f) {
    }

    /* access modifiers changed from: package-private */
    public final void setMobViewPager(MobViewPager mobViewPager) {
        this.parent = mobViewPager;
    }

    public void invalidate() {
        MobViewPager mobViewPager = this.parent;
        if (mobViewPager != null) {
            mobViewPager.setAdapter(this);
        }
    }
}
