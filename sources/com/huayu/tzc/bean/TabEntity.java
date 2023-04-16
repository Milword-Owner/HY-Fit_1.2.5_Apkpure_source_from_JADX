package com.huayu.tzc.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabEntity implements CustomTabEntity {
    public int selectedIcon;
    public String title;
    public int unSelectedIcon;

    public TabEntity(String str, int i, int i2) {
        this.title = str;
        this.selectedIcon = i;
        this.unSelectedIcon = i2;
    }

    public String getTabTitle() {
        return this.title;
    }

    public int getTabSelectedIcon() {
        return this.selectedIcon;
    }

    public int getTabUnselectedIcon() {
        return this.unSelectedIcon;
    }
}
