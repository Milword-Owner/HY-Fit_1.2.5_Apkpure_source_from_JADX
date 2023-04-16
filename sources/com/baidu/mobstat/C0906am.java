package com.baidu.mobstat;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import com.baidu.mobstat.C0985bs;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.am */
public class C0906am {

    /* renamed from: c */
    private static volatile String f1005c;

    /* renamed from: d */
    private static volatile int f1006d;

    /* renamed from: a */
    private final C0908b f1007a = new C0908b();

    /* renamed from: b */
    private final Handler f1008b = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    public static void m940a() {
        f1006d = 0;
    }

    /* renamed from: b */
    public static void m942b() {
        f1005c = "";
    }

    /* renamed from: a */
    public JSONObject mo11509a(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            if (!C0894aj.m867a()) {
                return null;
            }
            int i = f1006d + 1;
            f1006d = i;
            if (i >= 3) {
                C0894aj.m866a(false);
            }
            Bitmap b = mo11510b(activity);
            if (b == null) {
                return null;
            }
            JSONArray c = m943c(activity);
            String a = C0985bs.C0986a.m1411a(c.toString().getBytes());
            if (f1005c != null && f1005c.equals(a)) {
                return null;
            }
            f1005c = a;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("screenshot", C0968bi.m1244a(b));
                jSONObject.put("hash", C0968bi.m1262b(b));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(Config.DEVICE_WIDTH, b.getWidth());
                jSONObject2.put("h", b.getHeight());
                jSONObject.put("screen", jSONObject2);
                jSONObject.put("page", activity.getClass().getName());
                jSONObject.put("objects", c);
                return jSONObject;
            } catch (Throwable unused) {
                return jSONObject;
            }
        } catch (Throwable unused2) {
            return null;
        }
    }

    /* renamed from: c */
    private JSONArray m943c(Activity activity) throws Exception {
        JSONArray jSONArray = new JSONArray();
        View a = C0968bi.m1241a(activity);
        m941a(activity, a, jSONArray, "", a);
        return jSONArray;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x004a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004b  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m941a(android.app.Activity r13, android.view.View r14, org.json.JSONArray r15, java.lang.String r16, android.view.View r17) throws java.lang.Exception {
        /*
            r12 = this;
            r6 = r13
            r0 = r14
            r1 = r16
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            android.graphics.Rect r2 = com.baidu.mobstat.C0968bi.m1282e((android.view.View) r14)
            if (r2 != 0) goto L_0x000e
            return
        L_0x000e:
            boolean r3 = com.baidu.mobstat.C0885ah.m847a((android.view.View) r14)
            if (r3 == 0) goto L_0x0015
            return
        L_0x0015:
            java.lang.String r3 = com.baidu.mobstat.C0968bi.m1293k(r14)
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 == 0) goto L_0x0020
            return
        L_0x0020:
            boolean r4 = com.baidu.mobstat.C0968bi.m1276c((android.app.Activity) r13, (android.view.View) r14)
            if (r4 == 0) goto L_0x0027
            return
        L_0x0027:
            java.lang.String r4 = com.baidu.mobstat.C0968bi.m1271c((android.view.View) r14)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 == 0) goto L_0x0042
            java.lang.String r4 = com.baidu.mobstat.C0968bi.m1247a((android.view.View) r14, (java.lang.String) r1)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 == 0) goto L_0x0042
            r7 = r17
            java.lang.String r4 = com.baidu.mobstat.C0968bi.m1246a((android.view.View) r14, (android.view.View) r7)
            goto L_0x0044
        L_0x0042:
            r7 = r17
        L_0x0044:
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 == 0) goto L_0x004b
            return
        L_0x004b:
            r8 = -1
            java.lang.Long r5 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0055 }
            long r8 = r5.longValue()     // Catch:{ Exception -> 0x0055 }
        L_0x0055:
            r10 = 0
            int r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r5 >= 0) goto L_0x005c
            return
        L_0x005c:
            org.json.JSONObject r5 = new org.json.JSONObject
            r5.<init>()
            org.json.JSONArray r8 = new org.json.JSONArray
            r8.<init>()
            org.json.JSONObject r9 = new org.json.JSONObject
            r9.<init>()
            java.lang.String r10 = "p"
            r9.put(r10, r3)
            java.lang.String r3 = "i"
            r9.put(r3, r4)
            java.lang.String r10 = com.baidu.mobstat.C0968bi.m1263b((android.view.View) r14)
            java.lang.String r3 = "t"
            r9.put(r3, r10)
            r8.put(r9)
            java.lang.String r3 = "path"
            r5.put(r3, r8)
            java.lang.String r3 = "type"
            r5.put(r3, r10)
            java.lang.String r3 = com.baidu.mobstat.C0968bi.m1245a((android.view.View) r14)
            java.lang.String r4 = "value"
            r5.put(r4, r3)
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            int r4 = r2.left
            float r4 = (float) r4
            int r4 = com.baidu.mobstat.C0884ag.m838a(r13, r4)
            java.lang.String r8 = "x"
            r3.put(r8, r4)
            int r4 = r2.top
            float r4 = (float) r4
            int r4 = com.baidu.mobstat.C0884ag.m838a(r13, r4)
            java.lang.String r8 = "y"
            r3.put(r8, r4)
            int r4 = r2.width()
            float r4 = (float) r4
            int r4 = com.baidu.mobstat.C0884ag.m838a(r13, r4)
            java.lang.String r8 = "w"
            r3.put(r8, r4)
            int r4 = r2.height()
            float r4 = (float) r4
            int r4 = com.baidu.mobstat.C0884ag.m838a(r13, r4)
            java.lang.String r8 = "h"
            r3.put(r8, r4)
            java.lang.String r4 = "frame"
            r5.put(r4, r3)
            int r3 = com.baidu.mobstat.C0968bi.m1290i((android.view.View) r14)
            java.lang.String r4 = "alpha"
            r5.put(r4, r3)
            java.lang.Class r3 = r13.getClass()
            java.lang.String r3 = r3.getName()
            java.lang.String r4 = "page"
            r5.put(r4, r3)
            float r3 = com.baidu.mobstat.C0968bi.m1292j(r14)
            double r3 = (double) r3
            java.lang.String r8 = "z"
            r5.put(r8, r3)
            boolean r3 = r0 instanceof android.webkit.WebView
            java.lang.String r4 = "child"
            if (r3 == 0) goto L_0x0133
            r8 = 0
            r9 = r0
            android.webkit.WebView r9 = (android.webkit.WebView) r9
            java.lang.String r2 = com.baidu.mobstat.C0974bk.m1319a((android.app.Activity) r13, (android.webkit.WebView) r9, (android.graphics.Rect) r2)
            boolean r9 = android.text.TextUtils.isEmpty(r2)
            java.lang.String r11 = "url"
            if (r9 != 0) goto L_0x0118
            org.json.JSONObject r8 = new org.json.JSONObject
            r8.<init>(r2)
            java.lang.String r2 = r8.optString(r11)
            java.lang.String r9 = "objects"
            org.json.JSONArray r8 = r8.optJSONArray(r9)
            goto L_0x011a
        L_0x0118:
            java.lang.String r2 = ""
        L_0x011a:
            if (r8 != 0) goto L_0x0121
            org.json.JSONArray r8 = new org.json.JSONArray
            r8.<init>()
        L_0x0121:
            r5.put(r4, r8)
            boolean r8 = android.text.TextUtils.isEmpty(r2)
            if (r8 == 0) goto L_0x0130
            java.lang.String r2 = "/"
            r5.put(r11, r2)
            goto L_0x0133
        L_0x0130:
            r5.put(r11, r2)
        L_0x0133:
            boolean r1 = com.baidu.mobstat.C0968bi.m1269b((android.view.View) r14, (java.lang.String) r1)
            java.lang.String r2 = "edit"
            r5.put(r2, r1)
            r1 = r15
            r15.put(r5)
            if (r3 == 0) goto L_0x0143
            return
        L_0x0143:
            boolean r1 = r0 instanceof android.view.ViewGroup
            if (r1 == 0) goto L_0x016a
            r8 = r0
            android.view.ViewGroup r8 = (android.view.ViewGroup) r8
            org.json.JSONArray r9 = new org.json.JSONArray
            r9.<init>()
            r5.put(r4, r9)
            r0 = 0
            r11 = 0
        L_0x0154:
            int r0 = r8.getChildCount()
            if (r11 >= r0) goto L_0x0172
            android.view.View r2 = r8.getChildAt(r11)
            r0 = r12
            r1 = r13
            r3 = r9
            r4 = r10
            r5 = r17
            r0.m941a(r1, r2, r3, r4, r5)
            int r11 = r11 + 1
            goto L_0x0154
        L_0x016a:
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            r5.put(r4, r0)
        L_0x0172:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0906am.m941a(android.app.Activity, android.view.View, org.json.JSONArray, java.lang.String, android.view.View):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0047 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0049  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap mo11510b(android.app.Activity r5) {
        /*
            r4 = this;
            com.baidu.mobstat.am$b r0 = r4.f1007a
            r0.mo11513a((android.app.Activity) r5)
            java.util.concurrent.FutureTask r5 = new java.util.concurrent.FutureTask
            com.baidu.mobstat.am$b r0 = r4.f1007a
            r5.<init>(r0)
            android.os.Handler r0 = r4.f1008b
            r0.post(r5)
            java.util.List r0 = java.util.Collections.emptyList()
            r1 = 2
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0036, TimeoutException -> 0x002b, Exception -> 0x0020 }
            java.lang.Object r5 = r5.get(r1, r3)     // Catch:{ InterruptedException -> 0x0036, TimeoutException -> 0x002b, Exception -> 0x0020 }
            java.util.List r5 = (java.util.List) r5     // Catch:{ InterruptedException -> 0x0036, TimeoutException -> 0x002b, Exception -> 0x0020 }
            goto L_0x0041
        L_0x0020:
            r5 = move-exception
            com.baidu.mobstat.bb r1 = com.baidu.mobstat.C0955bb.m1194c()
            java.lang.String r2 = "autotrace: Exception thrown during screenshot attempt"
            r1.mo11634d(r2, r5)
            goto L_0x0040
        L_0x002b:
            r5 = move-exception
            com.baidu.mobstat.bb r1 = com.baidu.mobstat.C0955bb.m1194c()
            java.lang.String r2 = "autotrace: Screenshot took more than 2 second to be scheduled and executed. No screenshot will be sent."
            r1.mo11632c(r2, r5)
            goto L_0x0040
        L_0x0036:
            r5 = move-exception
            com.baidu.mobstat.bb r1 = com.baidu.mobstat.C0955bb.m1194c()
            java.lang.String r2 = "autotrace: Screenshot interrupted, no screenshot will be sent."
            r1.mo11628b(r2, r5)
        L_0x0040:
            r5 = r0
        L_0x0041:
            int r0 = r5.size()
            if (r0 != 0) goto L_0x0049
            r5 = 0
            return r5
        L_0x0049:
            r0 = 0
            java.lang.Object r5 = r5.get(r0)
            com.baidu.mobstat.am$c r5 = (com.baidu.mobstat.C0906am.C0909c) r5
            com.baidu.mobstat.am$a r5 = r5.f1018c
            android.graphics.Bitmap r5 = r5.f1009a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0906am.mo11510b(android.app.Activity):android.graphics.Bitmap");
    }

    /* renamed from: com.baidu.mobstat.am$b */
    static class C0908b implements Callable<List<C0909c>> {

        /* renamed from: a */
        private Activity f1011a;

        /* renamed from: b */
        private final List<C0909c> f1012b = new ArrayList();

        /* renamed from: c */
        private final DisplayMetrics f1013c = new DisplayMetrics();

        /* renamed from: d */
        private final C0907a f1014d = new C0907a();

        /* renamed from: e */
        private final int f1015e = 160;

        /* renamed from: a */
        public void mo11513a(Activity activity) {
            this.f1011a = activity;
        }

        /* renamed from: a */
        public List<C0909c> call() throws Exception {
            this.f1012b.clear();
            HashSet<Activity> hashSet = new HashSet<>(1);
            hashSet.add(this.f1011a);
            for (Activity activity : hashSet) {
                String canonicalName = activity.getClass().getCanonicalName();
                View b = C0968bi.m1261b(activity);
                activity.getWindowManager().getDefaultDisplay().getMetrics(this.f1013c);
                this.f1012b.add(new C0909c(canonicalName, b));
            }
            int size = this.f1012b.size();
            for (int i = 0; i < size; i++) {
                m949b();
                m948a(this.f1012b.get(i));
                m950c();
            }
            return this.f1012b;
        }

        /* renamed from: b */
        private void m949b() {
            C0885ah.m844a(this.f1011a, false);
        }

        /* renamed from: c */
        private void m950c() {
            C0885ah.m844a(this.f1011a, true);
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x007e A[SYNTHETIC, Splitter:B:17:0x007e] */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x00b4  */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void m948a(com.baidu.mobstat.C0906am.C0909c r14) {
            /*
                r13 = this;
                android.view.View r0 = r14.f1017b
                r1 = 0
                r2 = 0
                r3 = 1
                java.lang.Class<android.view.View> r4 = android.view.View.class
                java.lang.String r5 = "createSnapshot"
                r6 = 3
                java.lang.Class[] r7 = new java.lang.Class[r6]     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                java.lang.Class<android.graphics.Bitmap$Config> r8 = android.graphics.Bitmap.Config.class
                r7[r2] = r8     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                java.lang.Class r8 = java.lang.Integer.TYPE     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                r7[r3] = r8     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                java.lang.Class r8 = java.lang.Boolean.TYPE     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                r9 = 2
                r7[r9] = r8     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                java.lang.reflect.Method r4 = r4.getDeclaredMethod(r5, r7)     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                r4.setAccessible(r3)     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                java.lang.Object[] r5 = new java.lang.Object[r6]     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                android.graphics.Bitmap$Config r6 = android.graphics.Bitmap.Config.RGB_565     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                r5[r2] = r6     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                r6 = -1
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                r5[r3] = r6     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                r5[r9] = r6     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                java.lang.Object r4 = r4.invoke(r0, r5)     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4     // Catch:{ NoSuchMethodException -> 0x0071, IllegalArgumentException -> 0x0066, InvocationTargetException -> 0x005b, IllegalAccessException -> 0x0050, ClassCastException -> 0x0045, Exception -> 0x003a }
                goto L_0x007c
            L_0x003a:
                r4 = move-exception
                com.baidu.mobstat.bb r5 = com.baidu.mobstat.C0955bb.m1194c()
                java.lang.String r6 = " autotrace:createSnapshot encounter exception"
                r5.mo11634d(r6, r4)
                goto L_0x007b
            L_0x0045:
                r4 = move-exception
                com.baidu.mobstat.bb r5 = com.baidu.mobstat.C0955bb.m1194c()
                java.lang.String r6 = "autotrace: createSnapshot didn't return a bitmap"
                r5.mo11634d(r6, r4)
                goto L_0x007b
            L_0x0050:
                r4 = move-exception
                com.baidu.mobstat.bb r5 = com.baidu.mobstat.C0955bb.m1194c()
                java.lang.String r6 = "autotrace: Can't access createSnapshot, using drawCache"
                r5.mo11634d(r6, r4)
                goto L_0x007b
            L_0x005b:
                r4 = move-exception
                com.baidu.mobstat.bb r5 = com.baidu.mobstat.C0955bb.m1194c()
                java.lang.String r6 = "autotrace: Exception when calling createSnapshot"
                r5.mo11634d(r6, r4)
                goto L_0x007b
            L_0x0066:
                r4 = move-exception
                com.baidu.mobstat.bb r5 = com.baidu.mobstat.C0955bb.m1194c()
                java.lang.String r6 = "autotrace: Can't call createSnapshot with arguments"
                r5.mo11628b(r6, r4)
                goto L_0x007b
            L_0x0071:
                r4 = move-exception
                com.baidu.mobstat.bb r5 = com.baidu.mobstat.C0955bb.m1194c()
                java.lang.String r6 = "autotrace: Can't call createSnapshot, will use drawCache"
                r5.mo11625a((java.lang.String) r6, (java.lang.Throwable) r4)
            L_0x007b:
                r4 = r1
            L_0x007c:
                if (r4 != 0) goto L_0x00b0
                boolean r5 = r0.isDrawingCacheEnabled()     // Catch:{ Exception -> 0x0092 }
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r5)     // Catch:{ Exception -> 0x0092 }
                r0.setDrawingCacheEnabled(r3)     // Catch:{ Exception -> 0x0092 }
                r0.buildDrawingCache(r3)     // Catch:{ Exception -> 0x0092 }
                android.graphics.Bitmap r3 = r0.getDrawingCache()     // Catch:{ Exception -> 0x0092 }
                r4 = r3
                goto L_0x00b0
            L_0x0092:
                r3 = move-exception
                com.baidu.mobstat.bb r5 = com.baidu.mobstat.C0955bb.m1194c()
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                java.lang.String r7 = "autotrace: Can't take a bitmap snapshot of view "
                r6.append(r7)
                r6.append(r0)
                java.lang.String r7 = ", skipping for now."
                r6.append(r7)
                java.lang.String r6 = r6.toString()
                r5.mo11625a((java.lang.String) r6, (java.lang.Throwable) r3)
            L_0x00b0:
                r3 = 1065353216(0x3f800000, float:1.0)
                if (r4 == 0) goto L_0x00f1
                int r5 = r4.getDensity()
                if (r5 == 0) goto L_0x00be
                r3 = 1126170624(0x43200000, float:160.0)
                float r5 = (float) r5
                float r3 = r3 / r5
            L_0x00be:
                int r5 = r4.getWidth()
                int r6 = r4.getHeight()
                int r7 = r4.getWidth()
                float r7 = (float) r7
                float r7 = r7 * r3
                double r7 = (double) r7
                r9 = 4602678819172646912(0x3fe0000000000000, double:0.5)
                java.lang.Double.isNaN(r7)
                double r7 = r7 + r9
                int r7 = (int) r7
                int r8 = r4.getHeight()
                float r8 = (float) r8
                float r8 = r8 * r3
                double r11 = (double) r8
                java.lang.Double.isNaN(r11)
                double r11 = r11 + r9
                int r8 = (int) r11
                if (r5 <= 0) goto L_0x00f1
                if (r6 <= 0) goto L_0x00f1
                if (r7 <= 0) goto L_0x00f1
                if (r8 <= 0) goto L_0x00f1
                com.baidu.mobstat.am$a r5 = r13.f1014d
                r6 = 160(0xa0, float:2.24E-43)
                r5.mo11511a(r7, r8, r6, r4)
            L_0x00f1:
                if (r1 == 0) goto L_0x00fc
                boolean r1 = r1.booleanValue()
                if (r1 != 0) goto L_0x00fc
                r0.setDrawingCacheEnabled(r2)
            L_0x00fc:
                r14.f1019d = r3
                com.baidu.mobstat.am$a r0 = r13.f1014d
                r14.f1018c = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0906am.C0908b.m948a(com.baidu.mobstat.am$c):void");
        }
    }

    /* renamed from: com.baidu.mobstat.am$a */
    static class C0907a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public Bitmap f1009a = null;

        /* renamed from: b */
        private final Paint f1010b = new Paint(2);

        /* renamed from: a */
        public synchronized void mo11511a(int i, int i2, int i3, Bitmap bitmap) {
            if (!(this.f1009a != null && this.f1009a.getWidth() == i && this.f1009a.getHeight() == i2)) {
                try {
                    this.f1009a = Bitmap.createBitmap(i, i2, Bitmap.Config.RGB_565);
                } catch (OutOfMemoryError unused) {
                    this.f1009a = null;
                }
                if (this.f1009a != null) {
                    this.f1009a.setDensity(i3);
                }
            }
            if (this.f1009a != null) {
                new Canvas(this.f1009a).drawBitmap(bitmap, 0.0f, 0.0f, this.f1010b);
            }
        }
    }

    /* renamed from: com.baidu.mobstat.am$c */
    static class C0909c {

        /* renamed from: a */
        public final String f1016a;

        /* renamed from: b */
        public final View f1017b;

        /* renamed from: c */
        public C0907a f1018c = null;

        /* renamed from: d */
        public float f1019d = 1.0f;

        public C0909c(String str, View view) {
            this.f1016a = str;
            this.f1017b = view;
        }
    }
}
