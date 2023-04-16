package com.mob.mobapm.internal;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.mob.mobapm.internal.c */
public class C2388c extends C2387b {

    /* renamed from: c */
    private ArrayList<C2387b> f2308c = new ArrayList<>();

    /* renamed from: a */
    public C2388c mo29300a(C2387b bVar) throws Throwable {
        this.f2308c.add(bVar);
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public long mo29297b() throws Throwable {
        Iterator<C2387b> it = this.f2308c.iterator();
        long j = 0;
        while (it.hasNext()) {
            j += it.next().mo29297b();
        }
        return j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<C2387b> it = this.f2308c.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public InputStream mo29295a() throws Throwable {
        C2389d dVar = new C2389d();
        Iterator<C2387b> it = this.f2308c.iterator();
        while (it.hasNext()) {
            dVar.mo29302a(it.next().mo29295a());
        }
        return dVar;
    }
}
