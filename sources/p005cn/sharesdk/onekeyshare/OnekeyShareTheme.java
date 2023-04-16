package p005cn.sharesdk.onekeyshare;

import p005cn.sharesdk.onekeyshare.themes.classic.ClassicTheme;

/* renamed from: cn.sharesdk.onekeyshare.OnekeyShareTheme */
public enum OnekeyShareTheme {
    CLASSIC(0, new ClassicTheme());
    
    private final OnekeyShareThemeImpl impl;
    private final int value;

    private OnekeyShareTheme(int i, OnekeyShareThemeImpl onekeyShareThemeImpl) {
        this.value = i;
        this.impl = onekeyShareThemeImpl;
    }

    public int getValue() {
        return this.value;
    }

    public OnekeyShareThemeImpl getImpl() {
        return this.impl;
    }

    public static OnekeyShareTheme fromValue(int i) {
        for (OnekeyShareTheme onekeyShareTheme : values()) {
            if (onekeyShareTheme.value == i) {
                return onekeyShareTheme;
            }
        }
        return CLASSIC;
    }
}
