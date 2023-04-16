package com.mob.commons;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.tools.MobLog;
import com.mob.tools.utils.DeviceHelper;

/* renamed from: com.mob.commons.j */
/* compiled from: ServerConfig */
public class C2310j {
    /* renamed from: a */
    public static String m2570a() {
        String str = "api.fc.mob.com";
        try {
            boolean isPackageInstalled = DeviceHelper.getInstance(MobSDK.getContext()).isPackageInstalled(C2312k.m2575a(17));
            if (!MobSDK.checkV6() && !isPackageInstalled) {
                str = "m.data.mob.com";
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        return m2573b(str);
    }

    /* renamed from: b */
    public static String m2572b() {
        String str = "api.fd.mob.com";
        try {
            boolean isPackageInstalled = DeviceHelper.getInstance(MobSDK.getContext()).isPackageInstalled(C2312k.m2575a(17));
            if (!MobSDK.checkV6() && !isPackageInstalled) {
                str = "c.data.mob.com";
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        return m2573b(str);
    }

    /* renamed from: a */
    public static String m2571a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String str2 = "";
        if (str.startsWith("http://")) {
            str = str.replace("http://", str2);
        }
        if (str.startsWith("https://")) {
            str = str.replace("https://", str2);
        }
        int i = C23111.f2143a[MobSDK.getDomain().ordinal()];
        if (i == 1) {
            str2 = "jp";
        } else if (i == 2) {
            str2 = "us";
        }
        if (TextUtils.isEmpty(str2)) {
            return m2574c("http://" + str);
        }
        if (str.startsWith(str2 + ".")) {
            return m2574c("http://" + str);
        }
        return m2574c("http://" + str2 + "." + str);
    }

    /* renamed from: com.mob.commons.j$1 */
    /* compiled from: ServerConfig */
    static /* synthetic */ class C23111 {

        /* renamed from: a */
        static final /* synthetic */ int[] f2143a = new int[InternationalDomain.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.mob.commons.InternationalDomain[] r0 = com.mob.commons.InternationalDomain.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f2143a = r0
                int[] r0 = f2143a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.mob.commons.InternationalDomain r1 = com.mob.commons.InternationalDomain.JP     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f2143a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.mob.commons.InternationalDomain r1 = com.mob.commons.InternationalDomain.US     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2310j.C23111.<clinit>():void");
        }
    }

    /* renamed from: b */
    public static String m2573b(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String str2 = "";
        if (str.startsWith("http://")) {
            str = str.replace("http://", str2);
        }
        if (str.startsWith("https://")) {
            str = str.replace("https://", str2);
        }
        if (MobSDK.checkV6()) {
            str2 = "v6";
        } else {
            int i = C23111.f2143a[MobSDK.getDomain().ordinal()];
            if (i == 1) {
                str2 = "jp";
            } else if (i == 2) {
                str2 = "us";
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return m2574c("http://" + str);
        }
        if (str.startsWith(str2 + ".")) {
            return m2574c("http://" + str);
        }
        return m2574c("http://" + str2 + "." + str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009e A[Catch:{ Throwable -> 0x00ce }, RETURN] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m2574c(java.lang.String r8) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Throwable -> 0x00ce }
            if (r0 == 0) goto L_0x0007
            return r8
        L_0x0007:
            boolean r0 = com.mob.MobSDK.checkForceHttps()     // Catch:{ Throwable -> 0x00ce }
            if (r0 != 0) goto L_0x001d
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x00ce }
            r2 = 23
            if (r1 < r2) goto L_0x00d6
            android.security.NetworkSecurityPolicy r1 = android.security.NetworkSecurityPolicy.getInstance()     // Catch:{ Throwable -> 0x00ce }
            boolean r1 = r1.isCleartextTrafficPermitted()     // Catch:{ Throwable -> 0x00ce }
            if (r1 != 0) goto L_0x00d6
        L_0x001d:
            java.lang.String r8 = r8.trim()     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r1 = "http://"
            boolean r1 = r8.startsWith(r1)     // Catch:{ Throwable -> 0x00ce }
            if (r1 == 0) goto L_0x00d6
            java.lang.String r1 = r8.trim()     // Catch:{ Throwable -> 0x00ce }
            android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch:{ Throwable -> 0x00ce }
            if (r1 == 0) goto L_0x00d6
            java.lang.String r2 = r1.getScheme()     // Catch:{ Throwable -> 0x00ce }
            if (r2 == 0) goto L_0x00d6
            java.lang.String r3 = "http"
            boolean r2 = r2.equals(r3)     // Catch:{ Throwable -> 0x00ce }
            if (r2 == 0) goto L_0x00d6
            java.lang.String r2 = r1.getHost()     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r3 = r1.getPath()     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r4 = r1.getQuery()     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r5 = ""
            if (r2 == 0) goto L_0x009f
            int r1 = r1.getPort()     // Catch:{ Throwable -> 0x00ce }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ce }
            r6.<init>()     // Catch:{ Throwable -> 0x00ce }
            r6.append(r2)     // Catch:{ Throwable -> 0x00ce }
            if (r1 <= 0) goto L_0x0076
            r2 = 80
            if (r1 != r2) goto L_0x0064
            goto L_0x0076
        L_0x0064:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ce }
            r2.<init>()     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r7 = ":"
            r2.append(r7)     // Catch:{ Throwable -> 0x00ce }
            r2.append(r1)     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r1 = r2.toString()     // Catch:{ Throwable -> 0x00ce }
            goto L_0x0077
        L_0x0076:
            r1 = r5
        L_0x0077:
            r6.append(r1)     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r2 = r6.toString()     // Catch:{ Throwable -> 0x00ce }
            if (r0 != 0) goto L_0x009f
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x00ce }
            r1 = 24
            if (r0 < r1) goto L_0x009f
            android.security.NetworkSecurityPolicy r0 = android.security.NetworkSecurityPolicy.getInstance()     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r1 = "isCleartextTrafficPermitted"
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Throwable -> 0x00ce }
            r7 = 0
            r6[r7] = r2     // Catch:{ Throwable -> 0x00ce }
            java.lang.Object r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r0, r1, r6)     // Catch:{ Throwable -> 0x00ce }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Throwable -> 0x00ce }
            boolean r0 = r0.booleanValue()     // Catch:{ Throwable -> 0x00ce }
            if (r0 == 0) goto L_0x009f
            return r8
        L_0x009f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ce }
            r0.<init>()     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r1 = "https://"
            r0.append(r1)     // Catch:{ Throwable -> 0x00ce }
            r0.append(r2)     // Catch:{ Throwable -> 0x00ce }
            if (r3 != 0) goto L_0x00af
            r3 = r5
        L_0x00af:
            r0.append(r3)     // Catch:{ Throwable -> 0x00ce }
            if (r4 != 0) goto L_0x00b5
            goto L_0x00c6
        L_0x00b5:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ce }
            r1.<init>()     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r2 = "?"
            r1.append(r2)     // Catch:{ Throwable -> 0x00ce }
            r1.append(r4)     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r5 = r1.toString()     // Catch:{ Throwable -> 0x00ce }
        L_0x00c6:
            r0.append(r5)     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r8 = r0.toString()     // Catch:{ Throwable -> 0x00ce }
            return r8
        L_0x00ce:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.mo29769d(r0)
        L_0x00d6:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C2310j.m2574c(java.lang.String):java.lang.String");
    }
}
