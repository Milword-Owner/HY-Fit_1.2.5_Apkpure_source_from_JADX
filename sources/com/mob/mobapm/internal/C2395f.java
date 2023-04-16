package com.mob.mobapm.internal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* renamed from: com.mob.mobapm.internal.f */
public class C2395f extends C2387b {

    /* renamed from: c */
    private StringBuilder f2319c = new StringBuilder();

    /* renamed from: a */
    public C2395f mo29322a(String str) {
        this.f2319c.append(str);
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public long mo29297b() throws Throwable {
        return (long) this.f2319c.toString().getBytes("utf-8").length;
    }

    public String toString() {
        return this.f2319c.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public InputStream mo29295a() throws Throwable {
        return new ByteArrayInputStream(this.f2319c.toString().getBytes("utf-8"));
    }
}
