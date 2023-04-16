package com.mob;

import com.mob.tools.proguard.PublicMemberKeeper;

@Deprecated
public class RHolder implements PublicMemberKeeper {

    /* renamed from: a */
    private static RHolder f1726a;

    /* renamed from: b */
    private int f1727b;

    /* renamed from: c */
    private int f1728c;

    /* renamed from: d */
    private int f1729d;

    private RHolder() {
    }

    public static RHolder getInstance() {
        if (f1726a == null) {
            synchronized (RHolder.class) {
                if (f1726a == null) {
                    f1726a = new RHolder();
                }
            }
        }
        return f1726a;
    }

    public RHolder setActivityThemeId(int i) {
        this.f1727b = i;
        return f1726a;
    }

    public int getActivityThemeId() {
        return this.f1727b;
    }

    public RHolder setDialogLayoutId(int i) {
        this.f1728c = i;
        return f1726a;
    }

    public int getDialogLayoutId() {
        return this.f1728c;
    }

    public RHolder setDialogThemeId(int i) {
        this.f1729d = i;
        return f1726a;
    }

    public int getDialogThemeId() {
        return this.f1729d;
    }
}
