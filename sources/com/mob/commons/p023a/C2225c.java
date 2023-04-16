package com.mob.commons.p023a;

import android.os.Message;
import com.facebook.share.internal.ShareConstants;
import com.mob.commons.C2262b;
import com.mob.commons.C2293c;
import com.mob.commons.C2300e;
import com.mob.commons.C2308i;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Hashon;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.mob.commons.a.c */
/* compiled from: AtClt */
public class C2225c extends C2226d {

    /* renamed from: a */
    private long f1954a;

    /* renamed from: b */
    private HashMap<Long, Long> f1955b;

    /* access modifiers changed from: protected */
    /* renamed from: b_ */
    public boolean mo28994b_() {
        return C2262b.m2256W() > 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public File mo28993a() {
        return C2300e.m2466a("comm/locks/.at_lock");
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo28995d() {
        this.f1954a = C2262b.m2260a();
        this.f1955b = C2308i.m2563t();
        mo28999b(1);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28987a(Message message) {
        if (message.what == 1 && C2262b.m2256W() > 0) {
            m2009h();
        }
    }

    /* renamed from: h */
    private void m2009h() {
        if (this.f1955b == null) {
            this.f1955b = new HashMap<>();
        }
        for (Map.Entry next : this.f1955b.entrySet()) {
            if (!(next == null || ((Long) next.getKey()).longValue() == this.f1954a)) {
                m2010i();
            }
        }
        long a = C2262b.m2260a();
        long j = this.f1954a;
        long j2 = a - j;
        this.f1955b.put(Long.valueOf(j), Long.valueOf(j2));
        C2308i.m2518b(this.f1955b);
        NLog instance = MobLog.getInstance();
        instance.mo29768d("[%s] %s", "AtClt", "Cache AT: {\"launchAt: " + this.f1954a + ", \"duration\": " + j2 + "}");
        long s = C2308i.m2562s();
        if (j2 >= C2262b.m2258Y() * 1000 && s <= C2262b.m2260a()) {
            m2010i();
        }
        mo28997a(1, C2262b.m2256W() * 1000);
    }

    /* renamed from: i */
    private void m2010i() {
        try {
            HashMap hashMap = new HashMap();
            for (Map.Entry next : this.f1955b.entrySet()) {
                if (next != null) {
                    hashMap.put("launchAt", next.getKey());
                    hashMap.put("duration", next.getValue());
                }
            }
            long a = C2262b.m2260a();
            HashMap hashMap2 = new HashMap();
            hashMap2.put("type", "APP_RUNTIMES_STATS");
            hashMap2.put(ShareConstants.WEB_DIALOG_PARAM_DATA, hashMap);
            hashMap2.put("datetime", Long.valueOf(a));
            C2293c.m2435a().mo29068a(a, (HashMap<String, Object>) hashMap2);
            NLog instance = MobLog.getInstance();
            instance.mo29768d("[%s] %s", "AtClt", "Push AT: " + new Hashon().fromHashMap(hashMap));
            C2308i.m2542h(a + (C2262b.m2258Y() * 1000));
            if (this.f1955b != null) {
                this.f1955b.clear();
            }
            C2308i.m2518b((HashMap<Long, Long>) null);
        } catch (Throwable th) {
            MobLog.getInstance().mo29788w(th, "[%s] %s", "AtClt", "Push error");
        }
    }
}
