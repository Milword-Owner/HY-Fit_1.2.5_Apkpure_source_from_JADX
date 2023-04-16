package com.mob.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: com.mob.commons.f */
/* compiled from: MobChannel */
public class C2305f {

    /* renamed from: a */
    private static C2305f f2130a;

    /* renamed from: b */
    private HashMap<String, Object> f2131b = m2478c();

    private C2305f() {
        if (this.f2131b == null) {
            this.f2131b = new HashMap<>();
        }
        ArrayList<MobProduct> products = MobProductCollector.getProducts();
        if (products != null && !products.isEmpty()) {
            Iterator<MobProduct> it = products.iterator();
            while (it.hasNext()) {
                MobProduct next = it.next();
                if (!this.f2131b.containsKey(next.getProductTag())) {
                    this.f2131b.put(next.getProductTag(), 0);
                }
            }
        }
    }

    /* renamed from: a */
    public static C2305f m2476a() {
        if (f2130a == null) {
            synchronized (C2305f.class) {
                if (f2130a == null) {
                    f2130a = new C2305f();
                }
            }
        }
        return f2130a;
    }

    /* renamed from: a */
    public synchronized void mo29099a(MobProduct mobProduct, int i) {
        if (mobProduct != null) {
            this.f2131b.put(mobProduct.getProductTag(), Integer.valueOf(i));
            m2477a(this.f2131b);
        }
    }

    /* renamed from: b */
    public synchronized HashMap<String, Object> mo29100b() {
        return this.f2131b;
    }

    /* renamed from: c */
    private HashMap<String, Object> m2478c() {
        try {
            return C2308i.m2564u();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private void m2477a(HashMap<String, Object> hashMap) {
        try {
            C2308i.m2524c(hashMap);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
