package com.mob.mobapm.p031e;

import android.text.TextUtils;
import com.mob.mobapm.core.C2357d;
import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.log.NLog;
import java.net.InetAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/* renamed from: com.mob.mobapm.e.f */
public class C2380f {

    /* renamed from: a */
    private static final Pattern f2291a = Pattern.compile(".*?(gif|jpeg|png|jpg|bmp|tiff|webp|JPG|GIF|JPEG|PNG|BMP|TIFF|WEBP)");

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0073 A[Catch:{ all -> 0x0077 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m2821a(com.mob.mobapm.core.Transaction r3) {
        /*
            if (r3 == 0) goto L_0x0093
            java.lang.String r0 = r3.getHost()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x000e
            goto L_0x0093
        L_0x000e:
            java.util.HashMap r0 = com.mob.mobapm.core.C2357d.m2778d()
            if (r0 == 0) goto L_0x0093
            java.util.HashMap r0 = com.mob.mobapm.core.C2357d.m2778d()
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0020
            goto L_0x0093
        L_0x0020:
            java.lang.String r0 = r3.getHost()     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = ".mob.com"
            boolean r0 = r0.endsWith(r1)     // Catch:{ all -> 0x0077 }
            if (r0 == 0) goto L_0x002d
            return
        L_0x002d:
            java.util.HashMap r0 = com.mob.mobapm.core.C2357d.m2778d()     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = "rules"
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0077 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x0077 }
            if (r0 == 0) goto L_0x0066
            int r1 = r0.size()     // Catch:{ all -> 0x0077 }
            if (r1 > 0) goto L_0x0042
            goto L_0x0066
        L_0x0042:
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0077 }
        L_0x0046:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0077 }
            if (r1 == 0) goto L_0x0069
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0077 }
            java.util.HashMap r1 = (java.util.HashMap) r1     // Catch:{ all -> 0x0077 }
            java.lang.String r2 = "host"
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0077 }
            java.lang.String r2 = r3.getHost()     // Catch:{ all -> 0x0077 }
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0077 }
            if (r1 == 0) goto L_0x0046
            m2823c(r3)     // Catch:{ all -> 0x0077 }
            goto L_0x0046
        L_0x0066:
            m2823c(r3)     // Catch:{ all -> 0x0077 }
        L_0x0069:
            boolean r0 = r3.isCreate()     // Catch:{ all -> 0x0077 }
            if (r0 == 0) goto L_0x0093
            boolean r0 = com.mob.mobapm.core.C2356c.f2255i     // Catch:{ all -> 0x0077 }
            if (r0 == 0) goto L_0x0093
            m2822b(r3)     // Catch:{ all -> 0x0077 }
            goto L_0x0093
        L_0x0077:
            r3 = move-exception
            com.mob.tools.log.NLog r0 = com.mob.mobapm.p030d.C2373a.m2807a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "APM: check host error: "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r0.mo29775i(r3, r1)
        L_0x0093:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.mobapm.p031e.C2380f.m2821a(com.mob.mobapm.core.Transaction):void");
    }

    /* renamed from: b */
    public static void m2822b(Transaction transaction) {
        if (!C2357d.m2778d().containsKey("dnsWhiteMap")) {
            return;
        }
        if (!C2357d.m2778d().containsKey("dnsWhiteMap") || C2357d.m2778d().get("dnsWhiteMap") != null) {
            try {
                HashMap hashMap = (HashMap) C2357d.m2778d().get("dnsWhiteMap");
                if (hashMap.containsKey(transaction.getHost())) {
                    String hostAddress = InetAddress.getByName(transaction.getHost()).getHostAddress();
                    transaction.setIp(hostAddress);
                    Object obj = hashMap.get(transaction.getHost());
                    if (obj instanceof Collection) {
                        for (String equals : (List) obj) {
                            if (equals.equals(hostAddress)) {
                                transaction.setHijacked(0);
                                return;
                            }
                        }
                    }
                    transaction.setHijacked(1);
                }
            } catch (Throwable th) {
                NLog a = C2373a.m2807a();
                a.mo29768d("APM: prase di error:" + th, new Object[0]);
            }
        }
    }

    /* renamed from: c */
    public static void m2823c(Transaction transaction) {
        if (C2357d.m2778d() != null && !C2357d.m2778d().isEmpty()) {
            HashMap<String, Object> d = C2357d.m2778d();
            int i = 0;
            int intValue = (!d.containsKey("samplingRate") || d.get("samplingRate") == null) ? 0 : ((Integer) d.get("samplingRate")).intValue();
            if (d.containsKey("picSamplingRate") && d.get("picSamplingRate") != null) {
                i = ((Integer) d.get("picSamplingRate")).intValue();
            }
            int nextInt = new Random().nextInt(100);
            if (nextInt > 0 && !TextUtils.isEmpty(transaction.getPath()) && f2291a.matcher(transaction.getPath()).matches() && nextInt <= i) {
                transaction.setCreate(true);
            } else if (nextInt > 0 && !f2291a.matcher(transaction.getPath()).matches() && nextInt <= intValue) {
                transaction.setCreate(true);
            }
        }
    }
}
