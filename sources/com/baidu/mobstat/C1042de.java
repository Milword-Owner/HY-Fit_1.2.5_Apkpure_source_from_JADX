package com.baidu.mobstat;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

/* renamed from: com.baidu.mobstat.de */
public class C1042de implements C1038da {

    /* renamed from: a */
    private byte[] f1381a;

    /* renamed from: b */
    private TreeMap<String, String> f1382b = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    /* renamed from: b */
    public Iterator<String> mo11824b() {
        return Collections.unmodifiableSet(this.f1382b.keySet()).iterator();
    }

    /* renamed from: b */
    public String mo11823b(String str) {
        String str2 = this.f1382b.get(str);
        return str2 == null ? "" : str2;
    }

    /* renamed from: c */
    public byte[] mo11826c() {
        return this.f1381a;
    }

    /* renamed from: a */
    public void mo11819a(String str, String str2) {
        this.f1382b.put(str, str2);
    }

    /* renamed from: c */
    public boolean mo11825c(String str) {
        return this.f1382b.containsKey(str);
    }
}
