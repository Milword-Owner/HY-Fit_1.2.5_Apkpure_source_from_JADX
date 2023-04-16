package com.flyco.tablayout.listener;

import androidx.annotation.DrawableRes;

public interface CustomTabEntity {
    @DrawableRes
    int getTabSelectedIcon();

    String getTabTitle();

    @DrawableRes
    int getTabUnselectedIcon();
}
