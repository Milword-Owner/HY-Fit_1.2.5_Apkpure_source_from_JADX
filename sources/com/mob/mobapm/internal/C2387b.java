package com.mob.mobapm.internal;

import com.mob.tools.network.ByteCounterInputStream;
import com.mob.tools.network.OnReadListener;
import java.io.InputStream;

/* renamed from: com.mob.mobapm.internal.b */
public abstract class C2387b {

    /* renamed from: a */
    private long f2306a;

    /* renamed from: b */
    private OnReadListener f2307b;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract InputStream mo29295a() throws Throwable;

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract long mo29297b() throws Throwable;

    /* renamed from: c */
    public InputStream mo29299c() throws Throwable {
        ByteCounterInputStream byteCounterInputStream = new ByteCounterInputStream(mo29295a());
        byteCounterInputStream.setOnInputStreamReadListener(this.f2307b);
        long j = this.f2306a;
        if (j > 0) {
            byteCounterInputStream.skip(j);
        }
        return byteCounterInputStream;
    }
}
