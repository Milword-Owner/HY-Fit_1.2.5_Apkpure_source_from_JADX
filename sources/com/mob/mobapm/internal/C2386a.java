package com.mob.mobapm.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/* renamed from: com.mob.mobapm.internal.a */
public class C2386a extends C2387b {

    /* renamed from: c */
    private File f2305c;

    /* renamed from: a */
    public void mo29296a(String str) {
        this.f2305c = new File(str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public long mo29297b() throws Throwable {
        return this.f2305c.length();
    }

    public String toString() {
        return this.f2305c.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public InputStream mo29295a() throws Throwable {
        return new FileInputStream(this.f2305c);
    }
}
