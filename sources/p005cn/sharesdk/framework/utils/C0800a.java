package p005cn.sharesdk.framework.utils;

import android.os.Build;
import android.text.TextUtils;
import com.huayu.tzc.statusbar.OSUtils;

/* renamed from: cn.sharesdk.framework.utils.a */
/* compiled from: CheckRomAll */
public class C0800a {

    /* renamed from: a */
    private static String f596a;

    /* renamed from: b */
    private static String f597b;

    /* renamed from: a */
    public static boolean m656a() {
        return m657a(OSUtils.ROM_MIUI);
    }

    /* renamed from: a */
    public static boolean m657a(String str) {
        String str2 = f596a;
        if (str2 != null) {
            return str2.equals(str);
        }
        String b = m658b("ro.miui.ui.version.name");
        f597b = b;
        if (!TextUtils.isEmpty(b)) {
            f596a = OSUtils.ROM_MIUI;
        } else {
            String b2 = m658b("ro.build.version.emui");
            f597b = b2;
            if (!TextUtils.isEmpty(b2)) {
                f596a = OSUtils.ROM_EMUI;
            } else {
                String b3 = m658b("ro.build.version.opporom");
                f597b = b3;
                if (!TextUtils.isEmpty(b3)) {
                    f596a = OSUtils.ROM_OPPO;
                } else {
                    String b4 = m658b("ro.vivo.os.version");
                    f597b = b4;
                    if (!TextUtils.isEmpty(b4)) {
                        f596a = OSUtils.ROM_VIVO;
                    } else {
                        String b5 = m658b("ro.smartisan.version");
                        f597b = b5;
                        if (!TextUtils.isEmpty(b5)) {
                            f596a = OSUtils.ROM_SMARTISAN;
                        } else {
                            f597b = Build.DISPLAY;
                            if (f597b.toUpperCase().contains(OSUtils.ROM_FLYME)) {
                                f596a = OSUtils.ROM_FLYME;
                            } else {
                                f597b = "unknown";
                                f596a = Build.MANUFACTURER.toUpperCase();
                            }
                        }
                    }
                }
            }
        }
        return f596a.equals(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x007d A[SYNTHETIC, Splitter:B:18:0x007d] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x009e A[SYNTHETIC, Splitter:B:25:0x009e] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m658b(java.lang.String r8) {
        /*
            java.lang.String r0 = "CheckRomAll getProp finally catch "
            r1 = 0
            r2 = 0
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            r4.<init>()     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            java.lang.String r5 = "getprop "
            r4.append(r5)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            r4.append(r8)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            java.lang.Process r3 = r3.exec(r4)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            java.io.InputStream r3 = r3.getInputStream()     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            r5.<init>(r3)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            r3 = 1024(0x400, float:1.435E-42)
            r4.<init>(r5, r3)     // Catch:{ IOException -> 0x0057, all -> 0x0054 }
            java.lang.String r3 = r4.readLine()     // Catch:{ IOException -> 0x0052 }
            r4.close()     // Catch:{ IOException -> 0x0052 }
            r4.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x0051
        L_0x0038:
            r8 = move-exception
            com.mob.tools.log.NLog r1 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            r4.append(r8)
            java.lang.String r8 = r4.toString()
            java.lang.Object[] r0 = new java.lang.Object[r2]
            r1.mo29768d(r8, r0)
        L_0x0051:
            return r3
        L_0x0052:
            r3 = move-exception
            goto L_0x0059
        L_0x0054:
            r8 = move-exception
            r4 = r1
            goto L_0x009c
        L_0x0057:
            r3 = move-exception
            r4 = r1
        L_0x0059:
            com.mob.tools.log.NLog r5 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ all -> 0x009b }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x009b }
            r6.<init>()     // Catch:{ all -> 0x009b }
            java.lang.String r7 = "CheckRomAll unable to read prop "
            r6.append(r7)     // Catch:{ all -> 0x009b }
            r6.append(r8)     // Catch:{ all -> 0x009b }
            java.lang.String r8 = " ex "
            r6.append(r8)     // Catch:{ all -> 0x009b }
            r6.append(r3)     // Catch:{ all -> 0x009b }
            java.lang.String r8 = r6.toString()     // Catch:{ all -> 0x009b }
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ all -> 0x009b }
            r5.mo29768d(r8, r3)     // Catch:{ all -> 0x009b }
            if (r4 == 0) goto L_0x009a
            r4.close()     // Catch:{ IOException -> 0x0081 }
            goto L_0x009a
        L_0x0081:
            r8 = move-exception
            com.mob.tools.log.NLog r3 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            r4.append(r8)
            java.lang.String r8 = r4.toString()
            java.lang.Object[] r0 = new java.lang.Object[r2]
            r3.mo29768d(r8, r0)
        L_0x009a:
            return r1
        L_0x009b:
            r8 = move-exception
        L_0x009c:
            if (r4 == 0) goto L_0x00bb
            r4.close()     // Catch:{ IOException -> 0x00a2 }
            goto L_0x00bb
        L_0x00a2:
            r1 = move-exception
            com.mob.tools.log.NLog r3 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            r4.append(r1)
            java.lang.String r0 = r4.toString()
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r3.mo29768d(r0, r1)
        L_0x00bb:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.framework.utils.C0800a.m658b(java.lang.String):java.lang.String");
    }
}
