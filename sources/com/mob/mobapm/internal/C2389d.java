package com.mob.mobapm.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.mob.mobapm.internal.d */
public class C2389d extends InputStream {

    /* renamed from: a */
    private ArrayList<InputStream> f2309a = new ArrayList<>();

    /* renamed from: b */
    private int f2310b;

    C2389d() {
    }

    /* renamed from: a */
    public void mo29302a(InputStream inputStream) throws Throwable {
        this.f2309a.add(inputStream);
    }

    public int available() throws IOException {
        if (m2862a()) {
            return 0;
        }
        return this.f2309a.get(this.f2310b).available();
    }

    public void close() throws IOException {
        Iterator<InputStream> it = this.f2309a.iterator();
        while (it.hasNext()) {
            it.next().close();
        }
    }

    public int read() throws IOException {
        if (m2862a()) {
            return -1;
        }
        int read = this.f2309a.get(this.f2310b).read();
        while (read < 0) {
            int i = this.f2310b + 1;
            this.f2310b = i;
            if (i >= this.f2309a.size()) {
                break;
            }
            read = this.f2309a.get(this.f2310b).read();
        }
        return read;
    }

    public long skip(long j) throws IOException {
        throw new IOException();
    }

    /* renamed from: a */
    private boolean m2862a() {
        ArrayList<InputStream> arrayList = this.f2309a;
        return arrayList == null || arrayList.size() <= 0;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (m2862a()) {
            return -1;
        }
        int read = this.f2309a.get(this.f2310b).read(bArr, i, i2);
        while (read < 0) {
            int i3 = this.f2310b + 1;
            this.f2310b = i3;
            if (i3 >= this.f2309a.size()) {
                break;
            }
            read = this.f2309a.get(this.f2310b).read(bArr, i, i2);
        }
        return read;
    }
}
