package com.baidu.mobstat;

import android.content.Context;
import com.baidu.mobstat.ActivityLifeObserver;
import com.baidu.mobstat.AutoTrack;
import com.baidu.mobstat.C0879ad;
import com.baidu.mobstat.C0936au;

public class ActivityLifeTask {

    /* renamed from: a */
    private static boolean f633a = false;

    /* renamed from: b */
    private static ActivityLifeObserver.IActivityLifeCallback f634b;

    /* renamed from: c */
    private static ActivityLifeObserver.IActivityLifeCallback f635c;

    /* renamed from: d */
    private static ActivityLifeObserver.IActivityLifeCallback f636d;

    /* renamed from: e */
    private static ActivityLifeObserver.IActivityLifeCallback f637e;

    public static synchronized void registerActivityLifeCallback(Context context) {
        synchronized (ActivityLifeTask.class) {
            if (!f633a) {
                m703a(context);
                ActivityLifeObserver.instance().clearObservers();
                ActivityLifeObserver.instance().addObserver(f634b);
                ActivityLifeObserver.instance().addObserver(f636d);
                ActivityLifeObserver.instance().addObserver(f635c);
                ActivityLifeObserver.instance().addObserver(f637e);
                ActivityLifeObserver.instance().registerActivityLifeCallback(context);
                f633a = true;
            }
        }
    }

    /* renamed from: a */
    private static synchronized void m703a(Context context) {
        synchronized (ActivityLifeTask.class) {
            f634b = new AutoTrack.MyActivityLifeCallback(1);
            f636d = new C0879ad.C0880a();
            f635c = new C0936au.C0937a();
            f637e = new AutoTrack.MyActivityLifeCallback(2);
        }
    }
}
