package p005cn.sharesdk.framework.loopshare.watermark;

import android.os.Build;
import android.text.TextUtils;
import com.huayu.tzc.statusbar.OSUtils;

/* renamed from: cn.sharesdk.framework.loopshare.watermark.e */
/* compiled from: Rom */
public class C0770e {

    /* renamed from: a */
    private static String f394a;

    /* renamed from: b */
    private static String f395b;

    /* renamed from: a */
    public static boolean m492a() {
        return m493a(OSUtils.ROM_EMUI);
    }

    /* renamed from: b */
    public static boolean m495b() {
        return m493a(OSUtils.ROM_VIVO);
    }

    /* renamed from: a */
    public static boolean m493a(String str) {
        String str2 = f394a;
        if (str2 != null) {
            return str2.equals(str);
        }
        String b = m494b("ro.miui.ui.version.name");
        f395b = b;
        if (!TextUtils.isEmpty(b)) {
            f394a = OSUtils.ROM_MIUI;
        } else {
            String b2 = m494b("ro.build.version.emui");
            f395b = b2;
            if (!TextUtils.isEmpty(b2)) {
                f394a = OSUtils.ROM_EMUI;
            } else {
                String b3 = m494b("ro.build.version.opporom");
                f395b = b3;
                if (!TextUtils.isEmpty(b3)) {
                    f394a = OSUtils.ROM_OPPO;
                } else {
                    String b4 = m494b("ro.vivo.os.version");
                    f395b = b4;
                    if (!TextUtils.isEmpty(b4)) {
                        f394a = OSUtils.ROM_VIVO;
                    } else {
                        String b5 = m494b("ro.smartisan.version");
                        f395b = b5;
                        if (!TextUtils.isEmpty(b5)) {
                            f394a = OSUtils.ROM_SMARTISAN;
                        } else {
                            f395b = Build.DISPLAY;
                            if (f395b.toUpperCase().contains(OSUtils.ROM_FLYME)) {
                                f394a = OSUtils.ROM_FLYME;
                            } else {
                                f395b = "unknown";
                                f394a = Build.MANUFACTURER.toUpperCase();
                            }
                        }
                    }
                }
            }
        }
        return f394a.equals(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0066 A[SYNTHETIC, Splitter:B:18:0x0066] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0072 A[SYNTHETIC, Splitter:B:25:0x0072] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m494b(java.lang.String r9) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x003f, all -> 0x003c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x003f, all -> 0x003c }
            r2.<init>()     // Catch:{ IOException -> 0x003f, all -> 0x003c }
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch:{ IOException -> 0x003f, all -> 0x003c }
            r2.append(r9)     // Catch:{ IOException -> 0x003f, all -> 0x003c }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x003f, all -> 0x003c }
            java.lang.Process r1 = r1.exec(r2)     // Catch:{ IOException -> 0x003f, all -> 0x003c }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x003f, all -> 0x003c }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x003f, all -> 0x003c }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ IOException -> 0x003f, all -> 0x003c }
            r3.<init>(r1)     // Catch:{ IOException -> 0x003f, all -> 0x003c }
            r1 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r1)     // Catch:{ IOException -> 0x003f, all -> 0x003c }
            java.lang.String r1 = r2.readLine()     // Catch:{ IOException -> 0x003a }
            r2.close()     // Catch:{ IOException -> 0x003a }
            r2.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0039:
            return r1
        L_0x003a:
            r1 = move-exception
            goto L_0x0041
        L_0x003c:
            r9 = move-exception
            r2 = r0
            goto L_0x0070
        L_0x003f:
            r1 = move-exception
            r2 = r0
        L_0x0041:
            com.mob.tools.log.NLog r3 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ all -> 0x006f }
            java.lang.String r4 = "ShareSDK"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x006f }
            r6 = 0
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            r7.<init>()     // Catch:{ all -> 0x006f }
            java.lang.String r8 = "Unable to read prop "
            r7.append(r8)     // Catch:{ all -> 0x006f }
            r7.append(r9)     // Catch:{ all -> 0x006f }
            java.lang.String r9 = r7.toString()     // Catch:{ all -> 0x006f }
            r5[r6] = r9     // Catch:{ all -> 0x006f }
            r9 = 1
            r5[r9] = r1     // Catch:{ all -> 0x006f }
            r3.mo29771e(r4, r5)     // Catch:{ all -> 0x006f }
            if (r2 == 0) goto L_0x006e
            r2.close()     // Catch:{ IOException -> 0x006a }
            goto L_0x006e
        L_0x006a:
            r9 = move-exception
            r9.printStackTrace()
        L_0x006e:
            return r0
        L_0x006f:
            r9 = move-exception
        L_0x0070:
            if (r2 == 0) goto L_0x007a
            r2.close()     // Catch:{ IOException -> 0x0076 }
            goto L_0x007a
        L_0x0076:
            r0 = move-exception
            r0.printStackTrace()
        L_0x007a:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.framework.loopshare.watermark.C0770e.m494b(java.lang.String):java.lang.String");
    }
}
