package com.mob.guard;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.mob.guard.a */
public class C2327a {
    /* renamed from: a */
    public static String m2650a(String str, String str2) throws Throwable {
        if (!(str == null || str2 == null)) {
            try {
                byte[] bytes = str.getBytes("UTF-8");
                byte[] bArr = new byte[16];
                System.arraycopy(bytes, 0, bArr, 0, Math.min(bytes.length, 16));
                byte[] bytes2 = str2.getBytes("UTF-8");
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
                Cipher b = m2651b("AES" + "/EC" + "B/PKCS7P" + "adding", "BC");
                b.init(1, secretKeySpec);
                byte[] bArr2 = new byte[b.getOutputSize(bytes2.length)];
                b.doFinal(bArr2, b.update(bytes2, 0, bytes2.length, bArr2, 0));
                return new String(Base64.encode(bArr2, 2), "UTF-8");
            } catch (Throwable th) {
                C2335e.m2675b().mo29769d(th);
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0015 A[SYNTHETIC, Splitter:B:11:0x0015] */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static javax.crypto.Cipher m2651b(java.lang.String r2, java.lang.String r3) throws java.lang.Throwable {
        /*
            r0 = 0
            boolean r1 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x001a }
            if (r1 != 0) goto L_0x0012
            java.security.Provider r1 = java.security.Security.getProvider(r3)     // Catch:{ Throwable -> 0x0012 }
            if (r1 == 0) goto L_0x0012
            javax.crypto.Cipher r1 = javax.crypto.Cipher.getInstance(r2, r1)     // Catch:{ Throwable -> 0x0012 }
            goto L_0x0013
        L_0x0012:
            r1 = r0
        L_0x0013:
            if (r1 != 0) goto L_0x0019
            javax.crypto.Cipher r1 = javax.crypto.Cipher.getInstance(r2, r3)     // Catch:{ Throwable -> 0x001a }
        L_0x0019:
            return r1
        L_0x001a:
            r2 = move-exception
            com.mob.tools.log.NLog r3 = com.mob.guard.C2335e.m2675b()
            r3.mo29769d(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.guard.C2327a.m2651b(java.lang.String, java.lang.String):javax.crypto.Cipher");
    }
}
