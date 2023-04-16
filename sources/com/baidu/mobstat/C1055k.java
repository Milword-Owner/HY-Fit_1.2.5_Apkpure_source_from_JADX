package com.baidu.mobstat;

import android.content.Context;
import com.baidubce.http.StatusCodes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.baidu.mobstat.k */
public enum C1055k {
    AP_LIST(0) {
        /* renamed from: a */
        public C1054j mo11849a(Context context) {
            return new C1062m(context);
        }
    },
    APP_LIST(1) {
        /* renamed from: a */
        public C1054j mo11849a(Context context) {
            return new C1065p(context);
        }
    },
    APP_TRACE(2) {
        /* renamed from: a */
        public C1054j mo11849a(Context context) {
            return new C1066q(context);
        }
    },
    APP_CHANGE(3) {
        /* renamed from: a */
        public C1054j mo11849a(Context context) {
            return new C1064o(context);
        }
    },
    APP_APK(4) {
        /* renamed from: a */
        public C1054j mo11849a(Context context) {
            return new C1063n(context);
        }
    };
    

    /* renamed from: f */
    private int f1429f;

    /* renamed from: a */
    public abstract C1054j mo11849a(Context context);

    private C1055k(int i) {
        this.f1429f = i;
    }

    public String toString() {
        return String.valueOf(this.f1429f);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        if (r1 != null) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002c, code lost:
        if (r1 == null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0030, code lost:
        return r0;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.ArrayList<com.baidu.mobstat.C1053i> mo11850a(android.content.Context r3, int r4, int r5) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0037 }
            r0.<init>()     // Catch:{ all -> 0x0037 }
            r1 = 0
            com.baidu.mobstat.j r1 = r2.mo11849a(r3)     // Catch:{ Exception -> 0x0024 }
            boolean r3 = r1.mo11842a()     // Catch:{ Exception -> 0x0024 }
            if (r3 != 0) goto L_0x0018
            if (r1 == 0) goto L_0x0016
            r1.close()     // Catch:{ all -> 0x0037 }
        L_0x0016:
            monitor-exit(r2)
            return r0
        L_0x0018:
            java.util.ArrayList r0 = r1.mo11841a((int) r4, (int) r5)     // Catch:{ Exception -> 0x0024 }
            if (r1 == 0) goto L_0x002f
        L_0x001e:
            r1.close()     // Catch:{ all -> 0x0037 }
            goto L_0x002f
        L_0x0022:
            r3 = move-exception
            goto L_0x0031
        L_0x0024:
            r3 = move-exception
            com.baidu.mobstat.ba r4 = com.baidu.mobstat.C0954ba.m1191c()     // Catch:{ all -> 0x0022 }
            r4.mo11629b((java.lang.Throwable) r3)     // Catch:{ all -> 0x0022 }
            if (r1 == 0) goto L_0x002f
            goto L_0x001e
        L_0x002f:
            monitor-exit(r2)
            return r0
        L_0x0031:
            if (r1 == 0) goto L_0x0036
            r1.close()     // Catch:{ all -> 0x0037 }
        L_0x0036:
            throw r3     // Catch:{ all -> 0x0037 }
        L_0x0037:
            r3 = move-exception
            monitor-exit(r2)
            goto L_0x003b
        L_0x003a:
            throw r3
        L_0x003b:
            goto L_0x003a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C1055k.mo11850a(android.content.Context, int, int):java.util.ArrayList");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
        if (r2 != null) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002d, code lost:
        if (r2 == null) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0031, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        return -1;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized long mo11848a(android.content.Context r4, long r5, java.lang.String r7) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = -1
            r2 = 0
            com.baidu.mobstat.j r2 = r3.mo11849a(r4)     // Catch:{ Exception -> 0x0025 }
            boolean r4 = r2.mo11842a()     // Catch:{ Exception -> 0x0025 }
            if (r4 != 0) goto L_0x0015
            if (r2 == 0) goto L_0x0013
            r2.close()     // Catch:{ all -> 0x0038 }
        L_0x0013:
            monitor-exit(r3)
            return r0
        L_0x0015:
            java.lang.String r4 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x0025 }
            long r0 = r2.mo11838a((java.lang.String) r4, (java.lang.String) r7)     // Catch:{ Exception -> 0x0025 }
            if (r2 == 0) goto L_0x0030
        L_0x001f:
            r2.close()     // Catch:{ all -> 0x0038 }
            goto L_0x0030
        L_0x0023:
            r4 = move-exception
            goto L_0x0032
        L_0x0025:
            r4 = move-exception
            com.baidu.mobstat.ba r5 = com.baidu.mobstat.C0954ba.m1191c()     // Catch:{ all -> 0x0023 }
            r5.mo11629b((java.lang.Throwable) r4)     // Catch:{ all -> 0x0023 }
            if (r2 == 0) goto L_0x0030
            goto L_0x001f
        L_0x0030:
            monitor-exit(r3)
            return r0
        L_0x0032:
            if (r2 == 0) goto L_0x0037
            r2.close()     // Catch:{ all -> 0x0038 }
        L_0x0037:
            throw r4     // Catch:{ all -> 0x0038 }
        L_0x0038:
            r4 = move-exception
            monitor-exit(r3)
            goto L_0x003c
        L_0x003b:
            throw r4
        L_0x003c:
            goto L_0x003b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C1055k.mo11848a(android.content.Context, long, java.lang.String):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003a, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0042, code lost:
        if (r1 != null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0053, code lost:
        if (r1 != null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0057, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0062, code lost:
        return 0;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int mo11847a(android.content.Context r6, java.util.ArrayList<java.lang.Long> r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            if (r7 == 0) goto L_0x0061
            int r1 = r7.size()     // Catch:{ all -> 0x005e }
            if (r1 != 0) goto L_0x000b
            goto L_0x0061
        L_0x000b:
            r1 = 0
            com.baidu.mobstat.j r1 = r5.mo11849a(r6)     // Catch:{ Exception -> 0x004a }
            boolean r6 = r1.mo11842a()     // Catch:{ Exception -> 0x004a }
            if (r6 != 0) goto L_0x001d
            if (r1 == 0) goto L_0x001b
            r1.close()     // Catch:{ all -> 0x005e }
        L_0x001b:
            monitor-exit(r5)
            return r0
        L_0x001d:
            int r6 = r7.size()     // Catch:{ Exception -> 0x004a }
            r2 = 0
        L_0x0022:
            if (r0 >= r6) goto L_0x0042
            java.lang.Object r3 = r7.get(r0)     // Catch:{ Exception -> 0x0040 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ Exception -> 0x0040 }
            long r3 = r3.longValue()     // Catch:{ Exception -> 0x0040 }
            boolean r3 = r1.mo11845b(r3)     // Catch:{ Exception -> 0x0040 }
            if (r3 != 0) goto L_0x003b
            if (r1 == 0) goto L_0x0039
            r1.close()     // Catch:{ all -> 0x005e }
        L_0x0039:
            monitor-exit(r5)
            return r2
        L_0x003b:
            int r2 = r2 + 1
            int r0 = r0 + 1
            goto L_0x0022
        L_0x0040:
            r6 = move-exception
            goto L_0x004c
        L_0x0042:
            if (r1 == 0) goto L_0x0056
        L_0x0044:
            r1.close()     // Catch:{ all -> 0x005e }
            goto L_0x0056
        L_0x0048:
            r6 = move-exception
            goto L_0x0058
        L_0x004a:
            r6 = move-exception
            r2 = 0
        L_0x004c:
            com.baidu.mobstat.ba r7 = com.baidu.mobstat.C0954ba.m1191c()     // Catch:{ all -> 0x0048 }
            r7.mo11629b((java.lang.Throwable) r6)     // Catch:{ all -> 0x0048 }
            if (r1 == 0) goto L_0x0056
            goto L_0x0044
        L_0x0056:
            monitor-exit(r5)
            return r2
        L_0x0058:
            if (r1 == 0) goto L_0x005d
            r1.close()     // Catch:{ all -> 0x005e }
        L_0x005d:
            throw r6     // Catch:{ all -> 0x005e }
        L_0x005e:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        L_0x0061:
            monitor-exit(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C1055k.mo11847a(android.content.Context, java.util.ArrayList):int");
    }

    /* renamed from: a */
    public synchronized List<String> mo11851a(Context context, int i) {
        List<String> arrayList;
        arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        m1725a(context, arrayList, arrayList2, arrayList3, i, StatusCodes.INTERNAL_ERROR);
        if (arrayList3.size() != 0 && arrayList.size() == 0 && arrayList2.size() == 0) {
            C1053i iVar = (C1053i) arrayList3.get(0);
            long a = iVar.mo11835a();
            String b = iVar.mo11836b();
            arrayList2.add(Long.valueOf(a));
            arrayList.add(b);
        }
        int a2 = mo11847a(context, (ArrayList<Long>) arrayList2);
        if (a2 != arrayList.size()) {
            arrayList = arrayList.subList(0, a2);
        }
        return arrayList;
    }

    /* renamed from: a */
    private int m1725a(Context context, List<String> list, ArrayList<Long> arrayList, ArrayList<C1053i> arrayList2, int i, int i2) {
        int c = m1726c(context);
        int i3 = i2;
        int i4 = 0;
        int i5 = 0;
        while (c > 0) {
            if (c < i3) {
                i3 = c;
            }
            ArrayList<C1053i> a = mo11850a(context, i3, i5);
            if (i5 == 0 && a.size() != 0) {
                arrayList2.add(a.get(0));
            }
            Iterator<C1053i> it = a.iterator();
            while (it.hasNext()) {
                C1053i next = it.next();
                long a2 = next.mo11835a();
                String b = next.mo11836b();
                int length = b.length() + i4;
                if (length > i) {
                    break;
                }
                arrayList.add(Long.valueOf(a2));
                list.add(b);
                i4 = length;
            }
            c -= i3;
            i5 += i3;
        }
        return i4;
    }

    /* renamed from: b */
    public synchronized boolean mo11852b(Context context) {
        return m1726c(context) == 0;
    }

    /* renamed from: b */
    public synchronized boolean mo11853b(Context context, int i) {
        return m1726c(context) >= i;
    }

    /* renamed from: c */
    private int m1726c(Context context) {
        C1054j jVar = null;
        try {
            jVar = mo11849a(context);
            if (jVar.mo11842a()) {
                int b = jVar.mo11844b();
                if (jVar != null) {
                    jVar.close();
                }
                return b;
            }
            if (jVar == null) {
                return 0;
            }
            jVar.close();
            return 0;
        } catch (Exception e) {
            C0954ba.m1191c().mo11629b((Throwable) e);
            if (jVar == null) {
                return 0;
            }
        } catch (Throwable th) {
            if (jVar != null) {
                jVar.close();
            }
            throw th;
        }
    }
}
