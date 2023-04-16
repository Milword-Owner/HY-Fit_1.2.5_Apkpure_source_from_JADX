package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class BDStatCore {
    public static final int INVOKE_ACT = 1;
    public static final int INVOKE_API = 0;
    public static final int INVOKE_CUSTOME = 3;
    public static final int INVOKE_FRAG = 2;

    /* renamed from: a */
    private static BDStatCore f639a;

    /* renamed from: b */
    private Handler f640b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public volatile boolean f641c = false;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public SessionAnalysis f642d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public EventAnalysis f643e;

    /* renamed from: f */
    private Runnable f644f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public long f645g = 0;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public volatile boolean f646h = false;

    /* renamed from: i */
    private Handler f647i;

    public void doSendNewSessionLog(Context context) {
    }

    public static BDStatCore instance() {
        if (f639a == null) {
            synchronized (BDStatCore.class) {
                if (f639a == null) {
                    f639a = new BDStatCore();
                }
            }
        }
        return f639a;
    }

    private BDStatCore() {
        HandlerThread handlerThread = new HandlerThread("BDStatCore", 10);
        handlerThread.start();
        this.f640b = new Handler(handlerThread.getLooper());
        this.f642d = new SessionAnalysis();
        this.f643e = new EventAnalysis();
        HandlerThread handlerThread2 = new HandlerThread("dataAnalyzeThread");
        handlerThread2.start();
        handlerThread2.setPriority(10);
        this.f647i = new Handler(handlerThread2.getLooper());
    }

    /* renamed from: a */
    private void m708a(Context context) {
        String appKey = CooperService.instance().getAppKey(context);
        if (!TextUtils.isEmpty(appKey)) {
            C0936au.m1089a(appKey);
        }
    }

    public void init(final Context context) {
        m708a(context);
        if (!this.f641c) {
            ActivityLifeTask.registerActivityLifeCallback(context);
            this.f640b.post(new Runnable() {
                public void run() {
                    if (!BDStatCore.this.f641c) {
                        PrefOperate.loadMetaDataConfig(context);
                        boolean unused = BDStatCore.this.f641c = true;
                    }
                }
            });
        }
    }

    public void setAutoSendLog(Context context, boolean z) {
        if (context != null) {
            init(context);
            m713b(context);
            this.f642d.setAutoSend(z);
        }
    }

    public void setSessionTimeOut(int i) {
        this.f642d.setSessionTimeOut(i);
    }

    public JSONObject getPageSessionHead() {
        return this.f642d.getPageSessionHead();
    }

    public long getSessionStartTime() {
        return this.f642d.getSessionStartTime();
    }

    public int getSessionSize() {
        return this.f642d.getSessionSize();
    }

    public void onSessionStart(Context context, boolean z) {
        if (context != null) {
            init(context);
            m713b(context);
            final long currentTimeMillis = System.currentTimeMillis();
            final Context context2 = context;
            final boolean z2 = z;
            this.f640b.post(new Runnable() {
                public void run() {
                    BDStatCore.this.f642d.onSessionStart(context2, currentTimeMillis, z2);
                }
            });
        }
    }

    public void autoTrackLaunchInfo(Context context, final LaunchInfo launchInfo, final boolean z) {
        if (launchInfo != null) {
            if (z) {
                this.f642d.autoTrackLaunchInfo(launchInfo, z);
                return;
            }
            init(context);
            this.f640b.post(new Runnable() {
                public void run() {
                    BDStatCore.this.f642d.autoTrackLaunchInfo(launchInfo, z);
                }
            });
        }
    }

    public void autoTrackSessionStartTime(final Context context) {
        if (context != null) {
            init(context);
            final long currentTimeMillis = System.currentTimeMillis();
            this.f640b.post(new Runnable() {
                public void run() {
                    BDStatCore.this.f642d.autoTrackSessionStartTime(context, currentTimeMillis);
                }
            });
        }
    }

    public void autoTrackSessionEndTime(final Context context) {
        if (context != null) {
            init(context);
            final long currentTimeMillis = System.currentTimeMillis();
            this.f640b.post(new Runnable() {
                public void run() {
                    BDStatCore.this.f642d.autoTrackSessionEndTime(context, currentTimeMillis);
                }
            });
        }
    }

    public void doSendLogCheck(final Context context) {
        if (context != null) {
            int sessionTimeOut = this.f642d.getSessionTimeOut();
            this.f644f = new Runnable() {
                public void run() {
                    BDStatCore.this.f642d.doSendLogCheck(context, System.currentTimeMillis());
                }
            };
            this.f640b.postDelayed(this.f644f, (long) sessionTimeOut);
        }
    }

    public void cancelSendLogCheck() {
        Runnable runnable = this.f644f;
        if (runnable != null) {
            this.f640b.removeCallbacks(runnable);
        }
        this.f644f = null;
    }

    public void onPageStart(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            init(context);
            m713b(context);
            final int a = m704a();
            final long currentTimeMillis = System.currentTimeMillis();
            final String str2 = str;
            final Context context2 = context;
            this.f640b.post(new Runnable() {
                public void run() {
                    C0955bb c = C0955bb.m1194c();
                    c.mo11624a("Start page view " + str2);
                    BDStatCore.this.f642d.onPageStart(context2, str2, a, currentTimeMillis);
                }
            });
        }
    }

    public void onPageEnd(Context context, String str, ExtraInfo extraInfo) {
        onPageEnd(context, str, extraInfo, false);
    }

    public void onPageEnd(Context context, String str, ExtraInfo extraInfo, boolean z) {
        if (context != null && !TextUtils.isEmpty(str)) {
            init(context);
            final String b = m712b();
            final long currentTimeMillis = System.currentTimeMillis();
            final String str2 = str;
            final Context context2 = context;
            final ExtraInfo extraInfo2 = extraInfo;
            final boolean z2 = z;
            this.f640b.post(new Runnable() {
                public void run() {
                    C0955bb c = C0955bb.m1194c();
                    c.mo11624a("End page view " + str2);
                    SessionAnalysis b = BDStatCore.this.f642d;
                    Context context = context2;
                    String str = str2;
                    b.onPageEnd(context, str, str, b, currentTimeMillis, extraInfo2, z2);
                }
            });
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r0 = r4.getApplicationContext();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onResume(android.app.Activity r4, final boolean r5) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0003
            return
        L_0x0003:
            android.content.Context r0 = r4.getApplicationContext()
            if (r0 != 0) goto L_0x000a
            return
        L_0x000a:
            r3.init(r0)
            r3.m713b((android.content.Context) r0)
            java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference
            r1.<init>(r4)
            android.os.Handler r4 = r3.f640b
            com.baidu.mobstat.BDStatCore$2 r2 = new com.baidu.mobstat.BDStatCore$2
            r2.<init>(r1, r5, r0)
            r4.post(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.BDStatCore.onResume(android.app.Activity, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000a, code lost:
        r0 = r0.getApplicationContext();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onResume(androidx.fragment.app.Fragment r4) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0003
            return
        L_0x0003:
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()
            if (r0 != 0) goto L_0x000a
            return
        L_0x000a:
            android.content.Context r0 = r0.getApplicationContext()
            if (r0 != 0) goto L_0x0011
            return
        L_0x0011:
            r3.init(r0)
            r3.m713b((android.content.Context) r0)
            java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference
            r1.<init>(r4)
            android.os.Handler r4 = r3.f640b
            com.baidu.mobstat.BDStatCore$3 r2 = new com.baidu.mobstat.BDStatCore$3
            r2.<init>(r1, r0)
            r4.post(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.BDStatCore.onResume(androidx.fragment.app.Fragment):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000a, code lost:
        r0 = r0.getApplicationContext();
     */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onResume(android.app.Fragment r4) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0003
            return
        L_0x0003:
            android.app.Activity r0 = r4.getActivity()
            if (r0 != 0) goto L_0x000a
            return
        L_0x000a:
            android.content.Context r0 = r0.getApplicationContext()
            if (r0 != 0) goto L_0x0011
            return
        L_0x0011:
            r3.init(r0)
            r3.m713b((android.content.Context) r0)
            java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference
            r1.<init>(r4)
            android.os.Handler r4 = r3.f640b
            com.baidu.mobstat.BDStatCore$4 r2 = new com.baidu.mobstat.BDStatCore$4
            r2.<init>(r1, r0)
            r4.post(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.BDStatCore.onResume(android.app.Fragment):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r4 = r8.getApplicationContext();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPause(android.app.Activity r8, boolean r9, com.baidu.mobstat.ExtraInfo r10) {
        /*
            r7 = this;
            if (r8 != 0) goto L_0x0003
            return
        L_0x0003:
            android.content.Context r4 = r8.getApplicationContext()
            if (r4 != 0) goto L_0x000a
            return
        L_0x000a:
            r7.init(r4)
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
            r2.<init>(r8)
            android.os.Handler r8 = r7.f640b
            com.baidu.mobstat.BDStatCore$5 r6 = new com.baidu.mobstat.BDStatCore$5
            r0 = r6
            r1 = r7
            r3 = r9
            r5 = r10
            r0.<init>(r2, r3, r4, r5)
            r8.post(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.BDStatCore.onPause(android.app.Activity, boolean, com.baidu.mobstat.ExtraInfo):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r4 = r9.getActivity();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000a, code lost:
        r5 = r4.getApplicationContext();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPause(androidx.fragment.app.Fragment r9) {
        /*
            r8 = this;
            if (r9 != 0) goto L_0x0003
            return
        L_0x0003:
            androidx.fragment.app.FragmentActivity r4 = r9.getActivity()
            if (r4 != 0) goto L_0x000a
            return
        L_0x000a:
            android.content.Context r5 = r4.getApplicationContext()
            if (r5 != 0) goto L_0x0011
            return
        L_0x0011:
            r8.init(r5)
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
            r2.<init>(r9)
            android.os.Handler r6 = r8.f640b
            com.baidu.mobstat.BDStatCore$6 r7 = new com.baidu.mobstat.BDStatCore$6
            r0 = r7
            r1 = r8
            r3 = r9
            r0.<init>(r2, r3, r4, r5)
            r6.post(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.BDStatCore.onPause(androidx.fragment.app.Fragment):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r4 = r9.getActivity();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000a, code lost:
        r5 = r4.getApplicationContext();
     */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPause(android.app.Fragment r9) {
        /*
            r8 = this;
            if (r9 != 0) goto L_0x0003
            return
        L_0x0003:
            android.app.Activity r4 = r9.getActivity()
            if (r4 != 0) goto L_0x000a
            return
        L_0x000a:
            android.content.Context r5 = r4.getApplicationContext()
            if (r5 != 0) goto L_0x0011
            return
        L_0x0011:
            r8.init(r5)
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
            r2.<init>(r9)
            android.os.Handler r6 = r8.f640b
            com.baidu.mobstat.BDStatCore$7 r7 = new com.baidu.mobstat.BDStatCore$7
            r0 = r7
            r1 = r8
            r3 = r9
            r0.<init>(r2, r3, r4, r5)
            r6.post(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.BDStatCore.onPause(android.app.Fragment):void");
    }

    /* renamed from: a */
    private int m704a() {
        Class<?> cls;
        Class<?> cls2;
        Class<?> cls3;
        try {
            cls = Class.forName("android.app.Fragment");
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        try {
            cls2 = Class.forName("androidx.fragment.app.Fragment");
        } catch (ClassNotFoundException unused2) {
            cls2 = null;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int i = 0;
        while (stackTrace != null && i < stackTrace.length) {
            String className = stackTrace[i].getClassName();
            if (!TextUtils.isEmpty(className) && "onResume".equals(stackTrace[i].getMethodName())) {
                try {
                    cls3 = Class.forName(className);
                } catch (Throwable unused3) {
                    cls3 = null;
                }
                if (cls3 == null) {
                    continue;
                } else if (Activity.class.isAssignableFrom(cls3)) {
                    return 1;
                } else {
                    if (cls != null && cls.isAssignableFrom(cls3)) {
                        return 2;
                    }
                    if (cls2 != null && cls2.isAssignableFrom(cls3)) {
                        return 2;
                    }
                }
            }
            i++;
        }
        return 3;
    }

    /* renamed from: b */
    private String m712b() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement className : stackTrace) {
            String className2 = className.getClassName();
            if (!TextUtils.isEmpty(className2)) {
                Class<?> cls = null;
                try {
                    cls = Class.forName(className2);
                } catch (Throwable unused) {
                }
                if (cls != null && Activity.class.isAssignableFrom(cls)) {
                    return cls.getName();
                }
            }
        }
        return "";
    }

    public void onEvent(Context context, String str, String str2, int i, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        onEvent(context, str, str2, i, extraInfo, map, z, false);
    }

    public void onEvent(Context context, String str, String str2, int i, ExtraInfo extraInfo, Map<String, String> map, boolean z, boolean z2) {
        if (context != null) {
            init(context);
            final long currentTimeMillis = System.currentTimeMillis();
            final String str3 = str2;
            final Context context2 = context;
            final boolean z3 = z;
            final String str4 = str;
            final int i2 = i;
            final Map<String, String> map2 = map;
            final ExtraInfo extraInfo2 = extraInfo;
            final boolean z4 = z2;
            this.f640b.post(new Runnable() {
                public void run() {
                    String str = str3;
                    if (TextUtils.isEmpty(str)) {
                        str = "";
                    }
                    BDStatCore.this.f642d.onSessionStart(context2, currentTimeMillis, z3);
                    C0955bb.m1194c().mo11624a("Put event" + BDStatCore.this.m707a(str4, str, i2, 0, map2, extraInfo2));
                    BDStatCore.this.f643e.onEvent(context2, BDStatCore.this.f642d.getSessionStartTime(), str4, str, i2, currentTimeMillis, extraInfo2, map2, z4);
                }
            });
        }
    }

    public void onEvent(Context context, String str, String str2, int i, String str3, String str4, int i2, boolean z) {
        if (context != null) {
            init(context);
            m713b(context);
            final long currentTimeMillis = System.currentTimeMillis();
            final String str5 = str2;
            final Context context2 = context;
            final String str6 = str;
            final int i3 = i;
            final String str7 = str3;
            final String str8 = str4;
            final int i4 = i2;
            final boolean z2 = z;
            this.f640b.post(new Runnable() {
                public void run() {
                    String str = str5;
                    if (TextUtils.isEmpty(str)) {
                        str = "";
                    }
                    BDStatCore.this.f642d.onSessionStart(context2, currentTimeMillis, false);
                    C0955bb.m1194c().mo11624a("Put event" + BDStatCore.this.m707a(str6, str, i3, 0, (Map<String, String>) null, (ExtraInfo) null));
                    BDStatCore.this.f643e.onEvent(context2, BDStatCore.this.f642d.getSessionStartTime(), str6, str, i3, currentTimeMillis, str7, str8, i4, z2);
                }
            });
        }
    }

    public void onEvent(Context context, String str, String str2, int i, long j, JSONArray jSONArray, JSONArray jSONArray2, String str3, String str4, String str5, Map<String, String> map) {
        onEvent(context, str, str2, i, j, jSONArray, jSONArray2, str3, str4, str5, map, false);
    }

    public void onEvent(Context context, String str, String str2, int i, long j, JSONArray jSONArray, JSONArray jSONArray2, String str3, String str4, String str5, Map<String, String> map, boolean z) {
        if (context != null) {
            init(context);
            m713b(context);
            final String str6 = str2;
            final Context context2 = context;
            final long j2 = j;
            final String str7 = str;
            final int i2 = i;
            final Map<String, String> map2 = map;
            final JSONArray jSONArray3 = jSONArray;
            final JSONArray jSONArray4 = jSONArray2;
            final String str8 = str3;
            final String str9 = str4;
            final String str10 = str5;
            C084010 r17 = r0;
            Handler handler = this.f640b;
            final boolean z2 = z;
            C084010 r0 = new Runnable() {
                public void run() {
                    String str = str6;
                    if (TextUtils.isEmpty(str)) {
                        str = "";
                    }
                    BDStatCore.this.f642d.onSessionStart(context2, j2, false);
                    C0955bb.m1194c().mo11624a("Put event" + BDStatCore.this.m707a(str7, str, i2, 0, map2, (ExtraInfo) null));
                    long sessionStartTime = BDStatCore.this.f642d.getSessionStartTime();
                    EventAnalysis c = BDStatCore.this.f643e;
                    Context context = context2;
                    String str2 = str7;
                    int i = i2;
                    long j = j2;
                    JSONArray jSONArray = jSONArray3;
                    JSONArray jSONArray2 = jSONArray4;
                    String str3 = str8;
                    String str4 = str9;
                    String str5 = str10;
                    Map map = map2;
                    String str6 = str;
                    c.onEvent(context, sessionStartTime, str2, str6, i, j, jSONArray, jSONArray2, str3, str4, str5, map, z2);
                }
            };
            handler.post(r17);
        }
    }

    public void onEventStart(Context context, String str, String str2, boolean z) {
        if (context != null) {
            init(context);
            m713b(context);
            final long currentTimeMillis = System.currentTimeMillis();
            final String str3 = str2;
            final Context context2 = context;
            final boolean z2 = z;
            final String str4 = str;
            this.f640b.post(new Runnable() {
                public void run() {
                    String str = str3;
                    if (TextUtils.isEmpty(str)) {
                        str = "";
                    }
                    BDStatCore.this.f642d.onSessionStart(context2, currentTimeMillis, z2);
                    C0955bb.m1194c().mo11624a("Start event" + BDStatCore.this.m707a(str4, str, 1, -1, (Map<String, String>) null, (ExtraInfo) null));
                    BDStatCore.this.f643e.onEventStart(context2, str4, str, currentTimeMillis);
                }
            });
        }
    }

    public void onEventEnd(Context context, String str, String str2, ExtraInfo extraInfo, Map<String, String> map) {
        onEventEnd(context, str, str2, extraInfo, map, false);
    }

    public void onEventEnd(Context context, String str, String str2, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        if (context != null) {
            init(context);
            final long currentTimeMillis = System.currentTimeMillis();
            final String str3 = str2;
            final String str4 = str;
            final Map<String, String> map2 = map;
            final ExtraInfo extraInfo2 = extraInfo;
            final Context context2 = context;
            final boolean z2 = z;
            this.f640b.post(new Runnable() {
                public void run() {
                    String str = str3;
                    if (TextUtils.isEmpty(str)) {
                        str = "";
                    }
                    long sessionStartTime = BDStatCore.this.f642d.getSessionStartTime();
                    C0955bb.m1194c().mo11624a("End event" + BDStatCore.this.m707a(str4, str, 1, -1, map2, extraInfo2));
                    BDStatCore.this.f643e.onEventEnd(context2, sessionStartTime, str4, str, currentTimeMillis, extraInfo2, map2, z2);
                }
            });
        }
    }

    public void onEventDuration(Context context, String str, String str2, long j, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        onEventDuration(context, str, str2, j, extraInfo, map, z, false);
    }

    public void onEventDuration(Context context, String str, String str2, long j, ExtraInfo extraInfo, Map<String, String> map, boolean z, boolean z2) {
        if (context != null && !TextUtils.isEmpty(str)) {
            init(context);
            m713b(context);
            final long currentTimeMillis = System.currentTimeMillis();
            final String str3 = str2;
            final Context context2 = context;
            final boolean z3 = z;
            final String str4 = str;
            final long j2 = j;
            final Map<String, String> map2 = map;
            final ExtraInfo extraInfo2 = extraInfo;
            final boolean z4 = z2;
            this.f640b.post(new Runnable() {
                public void run() {
                    String str = str3;
                    if (TextUtils.isEmpty(str)) {
                        str = "";
                    }
                    BDStatCore.this.f642d.onSessionStart(context2, currentTimeMillis, z3);
                    C0955bb.m1194c().mo11624a("Put event" + BDStatCore.this.m707a(str4, str, 1, j2, map2, extraInfo2));
                    BDStatCore.this.f643e.onEventDuration(context2, BDStatCore.this.f642d.getSessionStartTime(), str4, str, currentTimeMillis, j2, extraInfo2, map2, z4);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001b  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String m707a(java.lang.String r4, java.lang.String r5, int r6, long r7, java.util.Map<java.lang.String, java.lang.String> r9, com.baidu.mobstat.ExtraInfo r10) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            if (r9 == 0) goto L_0x0018
            int r2 = r9.size()
            if (r2 == 0) goto L_0x0018
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x0018 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x0018 }
            r2.<init>(r9)     // Catch:{ Exception -> 0x0018 }
            goto L_0x0019
        L_0x0018:
            r2 = r1
        L_0x0019:
            if (r10 == 0) goto L_0x001f
            org.json.JSONObject r1 = r10.dumpToJson()
        L_0x001f:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = " eventId "
            r9.append(r10)
            r9.append(r4)
            java.lang.String r4 = ", with eventLabel "
            r9.append(r4)
            r9.append(r5)
            java.lang.String r4 = ", with acc "
            r9.append(r4)
            r9.append(r6)
            java.lang.String r4 = r9.toString()
            r0.append(r4)
            r4 = 0
            int r6 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x005d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = ", with duration "
            r4.append(r5)
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            r0.append(r4)
        L_0x005d:
            if (r2 == 0) goto L_0x0079
            int r4 = r2.length()
            if (r4 == 0) goto L_0x0079
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = ", with attributes "
            r4.append(r5)
            r4.append(r2)
            java.lang.String r4 = r4.toString()
            r0.append(r4)
        L_0x0079:
            if (r1 == 0) goto L_0x0095
            int r4 = r1.length()
            if (r4 == 0) goto L_0x0095
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = ", with extraInfo "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            r0.append(r4)
        L_0x0095:
            java.lang.String r4 = r0.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.BDStatCore.m707a(java.lang.String, java.lang.String, int, long, java.util.Map, com.baidu.mobstat.ExtraInfo):java.lang.String");
    }

    public void onStat(final Context context, final String str) {
        if (!this.f642d.isSessionStart()) {
            this.f640b.post(new Runnable() {
                public void run() {
                    LogSender.instance().sendEmptyLogData(context, str);
                }
            });
        }
    }

    public void onErised(Context context, String str, String str2, String str3) {
        if (!this.f642d.isSessionStart()) {
            init(context);
            final long currentTimeMillis = System.currentTimeMillis();
            final Context context2 = context;
            final String str4 = str2;
            final String str5 = str3;
            this.f640b.post(new Runnable() {
                public void run() {
                    DataCore.instance().init(context2);
                    EventAnalysis c = BDStatCore.this.f643e;
                    Context context = context2;
                    long j = currentTimeMillis;
                    c.onEvent(context, j, str4, str5, 1, j, (ExtraInfo) null, (Map<String, String>) null, false);
                    DataCore.instance().saveLogData(context2, true, false, currentTimeMillis, false);
                    if (currentTimeMillis - BDStatCore.this.f645g > 30000 && C0991bw.m1470q(context2)) {
                        LogSender.instance().onSend(context2);
                        long unused = BDStatCore.this.f645g = currentTimeMillis;
                    }
                }
            });
        }
    }

    /* renamed from: b */
    private void m713b(final Context context) {
        Handler handler;
        if (C0989bu.m1416a().mo11724c() && !this.f646h && context != null && (handler = this.f647i) != null) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    try {
                        if (!C1071s.m1778b(context)) {
                            C1071s.m1777a(2).mo11872a(context);
                        }
                    } catch (Throwable unused) {
                    }
                    boolean unused2 = BDStatCore.this.f646h = false;
                }
            }, Config.BPLUS_DELAY_TIME);
            this.f646h = true;
        }
    }
}
