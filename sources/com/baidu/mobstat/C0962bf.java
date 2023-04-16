package com.baidu.mobstat;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.baidu.mobstat.C0985bs;
import java.net.URLEncoder;
import java.util.ArrayList;

/* renamed from: com.baidu.mobstat.bf */
public class C0962bf {

    /* renamed from: a */
    private static volatile boolean f1232a;

    /* renamed from: b */
    private static volatile boolean f1233b;

    /* renamed from: c */
    private static volatile boolean f1234c;

    /* renamed from: a */
    private static String m1219a() {
        return "https://dxp.baidu.com/vizParser";
    }

    /* renamed from: a */
    private static boolean m1224a(int i) {
        if (i == 0) {
            return f1232a;
        }
        if (i == 1) {
            return f1233b;
        }
        if (i != 2) {
            return false;
        }
        return f1234c;
    }

    /* renamed from: a */
    private static void m1223a(int i, boolean z) {
        if (i == 0) {
            f1232a = z;
        } else if (i == 1) {
            f1233b = z;
        } else if (i == 2) {
            f1234c = z;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x012a, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x012c, code lost:
        if (r4 == null) goto L_0x013d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r4.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0132, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0133, code lost:
        if (r4 != null) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0135, code lost:
        r4.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0138, code lost:
        throw r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x013a, code lost:
        if (r4 != null) goto L_0x012e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x013e, code lost:
        return r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0089 */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c7 A[Catch:{ Exception -> 0x0139, all -> 0x0132 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00eb A[Catch:{ Exception -> 0x0139, all -> 0x0132 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0132 A[Catch:{ Exception -> 0x012a, all -> 0x0132 }, ExcHandler: all (r10v2 'th' java.lang.Throwable A[CUSTOM_DECLARE, Catch:{  }]), PHI: r4 
      PHI: (r4v4 java.net.HttpURLConnection) = (r4v1 java.net.HttpURLConnection), (r4v6 java.net.HttpURLConnection), (r4v6 java.net.HttpURLConnection), (r4v6 java.net.HttpURLConnection), (r4v6 java.net.HttpURLConnection), (r4v6 java.net.HttpURLConnection), (r4v6 java.net.HttpURLConnection), (r4v6 java.net.HttpURLConnection) binds: [B:30:0x0070, B:31:?, B:37:0x0089, B:60:0x0125, B:61:?, B:56:0x011f, B:57:?, B:35:0x007d] A[DONT_GENERATE, DONT_INLINE], Splitter:B:37:0x0089] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized boolean m1225a(android.content.Context r10, java.lang.String r11, int r12, boolean r13) {
        /*
            java.lang.Class<com.baidu.mobstat.bf> r0 = com.baidu.mobstat.C0962bf.class
            monitor-enter(r0)
            boolean r1 = m1224a((int) r12)     // Catch:{ all -> 0x013f }
            r2 = 1
            if (r1 == 0) goto L_0x000c
            monitor-exit(r0)
            return r2
        L_0x000c:
            r1 = 0
            if (r10 != 0) goto L_0x0011
            monitor-exit(r0)
            return r1
        L_0x0011:
            java.lang.String r11 = m1222a(r10, r11, r12)     // Catch:{ all -> 0x013f }
            boolean r3 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x013f }
            if (r3 == 0) goto L_0x001d
            monitor-exit(r0)
            return r1
        L_0x001d:
            com.baidu.mobstat.bc r3 = com.baidu.mobstat.C0956bc.m1198c()     // Catch:{ all -> 0x013f }
            boolean r3 = r3.mo11630b()     // Catch:{ all -> 0x013f }
            if (r3 == 0) goto L_0x0041
            if (r13 == 0) goto L_0x0041
            com.baidu.mobstat.bc r3 = com.baidu.mobstat.C0956bc.m1198c()     // Catch:{ all -> 0x013f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x013f }
            r4.<init>()     // Catch:{ all -> 0x013f }
            java.lang.String r5 = "requestUrl:"
            r4.append(r5)     // Catch:{ all -> 0x013f }
            r4.append(r11)     // Catch:{ all -> 0x013f }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x013f }
            r3.mo11624a((java.lang.String) r4)     // Catch:{ all -> 0x013f }
        L_0x0041:
            com.baidu.mobstat.bg r3 = com.baidu.mobstat.C0963bg.m1227c()     // Catch:{ all -> 0x013f }
            boolean r3 = r3.mo11630b()     // Catch:{ all -> 0x013f }
            if (r3 == 0) goto L_0x0063
            com.baidu.mobstat.bg r3 = com.baidu.mobstat.C0963bg.m1227c()     // Catch:{ all -> 0x013f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x013f }
            r4.<init>()     // Catch:{ all -> 0x013f }
            java.lang.String r5 = "requestUrl:"
            r4.append(r5)     // Catch:{ all -> 0x013f }
            r4.append(r11)     // Catch:{ all -> 0x013f }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x013f }
            r3.mo11624a((java.lang.String) r4)     // Catch:{ all -> 0x013f }
        L_0x0063:
            java.lang.String r3 = m1226b(r12)     // Catch:{ all -> 0x013f }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x013f }
            if (r4 == 0) goto L_0x006f
            monitor-exit(r0)
            return r1
        L_0x006f:
            r4 = 0
            java.net.HttpURLConnection r4 = com.baidu.mobstat.C0980bn.m1351d(r10, r11)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r4.connect()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r5 = 0
            if (r12 != r2) goto L_0x0089
            java.lang.String r11 = "X-INTERVAL"
            java.lang.String r11 = r4.getHeaderField(r11)     // Catch:{ Exception -> 0x0089, all -> 0x0132 }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ Exception -> 0x0089, all -> 0x0132 }
            long r5 = r11.longValue()     // Catch:{ Exception -> 0x0089, all -> 0x0132 }
        L_0x0089:
            int r11 = r4.getResponseCode()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            int r7 = r4.getContentLength()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            com.baidu.mobstat.bc r8 = com.baidu.mobstat.C0956bc.m1198c()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            boolean r8 = r8.mo11630b()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            if (r8 == 0) goto L_0x00bd
            if (r13 == 0) goto L_0x00bd
            com.baidu.mobstat.bc r13 = com.baidu.mobstat.C0956bc.m1198c()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r8.<init>()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            java.lang.String r9 = "contentLength:"
            r8.append(r9)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r8.append(r7)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            java.lang.String r9 = " fileName:"
            r8.append(r9)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r8.append(r3)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r13.mo11624a((java.lang.String) r8)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
        L_0x00bd:
            com.baidu.mobstat.bg r13 = com.baidu.mobstat.C0963bg.m1227c()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            boolean r13 = r13.mo11630b()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            if (r13 == 0) goto L_0x00e7
            com.baidu.mobstat.bg r13 = com.baidu.mobstat.C0963bg.m1227c()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r8.<init>()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            java.lang.String r9 = "contentLength:"
            r8.append(r9)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r8.append(r7)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            java.lang.String r9 = " fileName:"
            r8.append(r9)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r8.append(r3)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r13.mo11624a((java.lang.String) r8)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
        L_0x00e7:
            r13 = 200(0xc8, float:2.8E-43)
            if (r11 != r13) goto L_0x012c
            if (r12 == 0) goto L_0x0111
            if (r12 == r2) goto L_0x00ff
            r11 = 2
            if (r12 == r11) goto L_0x00f3
            goto L_0x0111
        L_0x00f3:
            com.baidu.mobstat.bp r11 = com.baidu.mobstat.C0982bp.m1357a()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r11.mo11684c((android.content.Context) r10, (long) r5)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            goto L_0x0111
        L_0x00ff:
            com.baidu.mobstat.bp r11 = com.baidu.mobstat.C0982bp.m1357a()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r11.mo11675a((android.content.Context) r10, (long) r8)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            com.baidu.mobstat.bp r11 = com.baidu.mobstat.C0982bp.m1357a()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            r11.mo11680b((android.content.Context) r10, (long) r5)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
        L_0x0111:
            if (r7 <= 0) goto L_0x0125
            java.io.FileOutputStream r10 = r10.openFileOutput(r3, r1)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            java.io.InputStream r11 = r4.getInputStream()     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            boolean r11 = com.baidu.mobstat.C0988bt.m1415a(r11, r10)     // Catch:{ Exception -> 0x0139, all -> 0x0132 }
            com.baidu.mobstat.C0988bt.m1414a(r10)     // Catch:{ Exception -> 0x0123, all -> 0x0132 }
            goto L_0x0125
        L_0x0123:
            r1 = r11
            goto L_0x013a
        L_0x0125:
            m1223a((int) r12, (boolean) r2)     // Catch:{ Exception -> 0x012a, all -> 0x0132 }
            r1 = 1
            goto L_0x012c
        L_0x012a:
            r1 = 1
            goto L_0x013a
        L_0x012c:
            if (r4 == 0) goto L_0x013d
        L_0x012e:
            r4.disconnect()     // Catch:{ all -> 0x013f }
            goto L_0x013d
        L_0x0132:
            r10 = move-exception
            if (r4 == 0) goto L_0x0138
            r4.disconnect()     // Catch:{ all -> 0x013f }
        L_0x0138:
            throw r10     // Catch:{ all -> 0x013f }
        L_0x0139:
        L_0x013a:
            if (r4 == 0) goto L_0x013d
            goto L_0x012e
        L_0x013d:
            monitor-exit(r0)
            return r1
        L_0x013f:
            r10 = move-exception
            monitor-exit(r0)
            goto L_0x0143
        L_0x0142:
            throw r10
        L_0x0143:
            goto L_0x0142
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0962bf.m1225a(android.content.Context, java.lang.String, int, boolean):boolean");
    }

    /* renamed from: a */
    private static String m1222a(Context context, String str, int i) {
        if (i == 0) {
            return m1219a();
        }
        if (i != 1) {
            return i != 2 ? "" : m1221a(context, str);
        }
        return m1220a(context);
    }

    /* renamed from: a */
    private static String m1220a(Context context) {
        String a = C0980bn.m1345a(context, C0883af.f942b);
        ArrayList<Pair> arrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(a)) {
            String a2 = C0985bs.C0986a.m1411a(a.getBytes());
            if (!TextUtils.isEmpty(a2)) {
                arrayList.add(new Pair("sign", a2));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Pair pair : arrayList) {
            try {
                String encode = URLEncoder.encode(((String) pair.first).toString(), "UTF-8");
                String encode2 = URLEncoder.encode(((String) pair.second).toString(), "UTF-8");
                if (TextUtils.isEmpty(sb.toString())) {
                    sb.append(encode + "=" + encode2);
                } else {
                    sb.append("&" + encode + "=" + encode2);
                }
            } catch (Exception unused) {
            }
        }
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(sb2)) {
            return "https://dxp.baidu.com/autoTracker";
        }
        return "https://dxp.baidu.com/autoTracker" + "?" + sb2;
    }

    /* renamed from: a */
    private static String m1221a(Context context, String str) {
        ArrayList<Pair> arrayList = new ArrayList<>();
        arrayList.add(new Pair("sdkVersion", StatService.getSdkVersion()));
        arrayList.add(new Pair("appKey", "" + str));
        arrayList.add(new Pair("packageName", context.getPackageName()));
        arrayList.add(new Pair("appVersion", C0991bw.m1453g(context)));
        arrayList.add(new Pair("cuid", CooperService.instance().getCUID(context, false)));
        arrayList.add(new Pair("imei", CooperService.instance().getDevicImei(context)));
        arrayList.add(new Pair("platform", "Android"));
        arrayList.add(new Pair("model", Build.MODEL));
        arrayList.add(new Pair("s", Build.VERSION.SDK_INT + ""));
        arrayList.add(new Pair(Config.f779OS, Build.VERSION.RELEASE));
        StringBuilder sb = new StringBuilder();
        for (Pair pair : arrayList) {
            try {
                String encode = URLEncoder.encode(((String) pair.first).toString(), "UTF-8");
                String encode2 = URLEncoder.encode(((String) pair.second).toString(), "UTF-8");
                if (TextUtils.isEmpty(sb.toString())) {
                    sb.append(encode + "=" + encode2);
                } else {
                    sb.append("&" + encode + "=" + encode2);
                }
            } catch (Exception unused) {
            }
        }
        return "https://dxp.baidu.com/circleConfig?" + sb.toString();
    }

    /* renamed from: b */
    private static String m1226b(int i) {
        if (i == 0) {
            return C0883af.f941a;
        }
        if (i != 1) {
            return i != 2 ? "" : C0883af.f943c;
        }
        return C0883af.f942b;
    }
}
