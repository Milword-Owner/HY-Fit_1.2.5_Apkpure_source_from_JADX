package com.mob.commons.p023a;

import android.os.Handler;
import android.os.Message;
import com.facebook.share.internal.ShareConstants;
import com.mob.MobSDK;
import com.mob.commons.C2262b;
import com.mob.commons.C2293c;
import com.mob.commons.FBListener;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.SharePrefrenceHelper;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.mob.commons.a.a */
/* compiled from: ActClt */
public class C2221a extends C2226d implements Handler.Callback {

    /* renamed from: a */
    private SharePrefrenceHelper f1945a;

    /* renamed from: b */
    private FBListener f1946b = null;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public long f1947c = 0;

    /* renamed from: d */
    private HashMap<Long, Long> f1948d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public boolean f1949e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public boolean f1950f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public Handler f1951g;

    C2221a() {
        new Thread() {
            public void run() {
                C2221a.this.mo28989a_();
            }
        }.start();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a_ */
    public void mo28989a_() {
        this.f1949e = C2262b.m2235B();
        this.f1950f = C2262b.m2328w();
        if (this.f1949e || this.f1950f) {
            this.f1946b = new FBListener() {
                public void onFBChanged(boolean z, boolean z2, long j) {
                    if (z2) {
                        long unused = C2221a.this.f1947c = C2262b.m2260a();
                        if (C2221a.this.f1949e) {
                            C2221a aVar = C2221a.this;
                            Handler unused2 = aVar.f1951g = MobHandlerThread.newHandler(aVar);
                            C2221a.this.f1951g.sendEmptyMessage(0);
                        }
                    }
                    if (z) {
                        if (!z2) {
                            long unused3 = C2221a.this.f1947c = C2262b.m2260a();
                            C2221a.this.f1951g.sendEmptyMessage(1);
                        }
                        if (C2221a.this.f1950f) {
                            C2221a.this.m1994h();
                        }
                    } else if (C2221a.this.f1949e && j > 0) {
                        C2221a.this.m1996j();
                        C2221a.this.m1997k();
                        C2221a.this.f1951g.removeMessages(1);
                    }
                }
            };
            C2242m.m2106a().mo29012a(this.f1946b);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28987a(Message message) {
        int i = message.what;
        if (i == 0) {
            m1997k();
            this.f1951g.sendEmptyMessage(1);
        } else if (i == 1) {
            m1996j();
            this.f1951g.sendEmptyMessageDelayed(1, C2262b.m2236C() * 1000);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo28990b() {
        if (this.f1946b != null) {
            C2242m.m2106a().mo29013b(this.f1946b);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: h */
    public void m1994h() {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("type", "PV");
            hashMap.put("datetime", Long.valueOf(C2262b.m2260a()));
            C2293c.m2435a().mo29068a(C2262b.m2260a(), (HashMap<String, Object>) hashMap);
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
    }

    /* renamed from: i */
    private synchronized void m1995i() {
        if (this.f1945a == null) {
            this.f1945a = new SharePrefrenceHelper(MobSDK.getContext());
            this.f1945a.open("top_time");
        }
    }

    /* renamed from: c */
    public HashMap<Long, Long> mo28991c() {
        HashMap<Long, Long> hashMap;
        try {
            m1995i();
            hashMap = (HashMap) this.f1945a.get("key_active_log");
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            hashMap = null;
        }
        return hashMap == null ? new HashMap<>() : hashMap;
    }

    /* renamed from: a */
    public void mo28988a(HashMap<Long, Long> hashMap) {
        m1995i();
        if (hashMap == null || hashMap.isEmpty()) {
            this.f1945a.remove("key_active_log");
        } else {
            this.f1945a.put("key_active_log", hashMap);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: j */
    public synchronized void m1996j() {
        try {
            if (this.f1948d == null) {
                this.f1948d = new HashMap<>();
            }
            long a = C2262b.m2260a();
            NLog instance = MobLog.getInstance();
            instance.mo29768d("[cache] foregndAt: " + this.f1947c + ", duration: " + (a - this.f1947c), new Object[0]);
            this.f1948d.put(Long.valueOf(this.f1947c), Long.valueOf(a));
            mo28988a(this.f1948d);
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        return;
    }

    /* access modifiers changed from: private */
    /* renamed from: k */
    public synchronized void m1997k() {
        try {
            m1995i();
            HashMap<Long, Long> c = mo28991c();
            if (c != null && !c.isEmpty()) {
                for (Map.Entry next : c.entrySet()) {
                    long longValue = ((Long) next.getKey()).longValue();
                    long longValue2 = ((Long) next.getValue()).longValue();
                    long j = longValue2 - longValue;
                    NLog instance = MobLog.getInstance();
                    instance.mo29768d("foregndAt: " + longValue + ", until: " + longValue2 + ", runtimes: " + j, new Object[0]);
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", "BACK_INFO");
                    hashMap.put("datetime", Long.valueOf(C2262b.m2260a()));
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("until", Long.valueOf(longValue2));
                    hashMap2.put("runtimes", Long.valueOf(j));
                    hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, hashMap2);
                    C2293c.m2435a().mo29068a(C2262b.m2260a(), (HashMap<String, Object>) hashMap);
                }
                if (this.f1948d != null) {
                    this.f1948d.clear();
                }
                mo28988a((HashMap<Long, Long>) null);
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        return;
    }
}
