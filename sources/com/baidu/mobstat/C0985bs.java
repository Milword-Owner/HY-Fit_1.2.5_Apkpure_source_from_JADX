package com.baidu.mobstat;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.baidu.mobstat.bs */
public final class C0985bs {

    /* renamed from: com.baidu.mobstat.bs$a */
    public static class C0986a {
        /* renamed from: a */
        public static String m1411a(byte[] bArr) {
            try {
                return C0985bs.m1410b(MessageDigest.getInstance("md5"), bArr);
            } catch (Exception unused) {
                return "";
            }
        }
    }

    /* renamed from: com.baidu.mobstat.bs$b */
    public static class C0987b {
        /* renamed from: a */
        public static String m1413a(byte[] bArr) {
            try {
                return C0985bs.m1410b(MessageDigest.getInstance("SHA-256"), bArr);
            } catch (Exception unused) {
                return "";
            }
        }

        /* renamed from: a */
        public static String m1412a(File file) {
            try {
                return C0985bs.m1409b(MessageDigest.getInstance("SHA-256"), file);
            } catch (NoSuchAlgorithmException unused) {
                return "";
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static String m1410b(MessageDigest messageDigest, byte[] bArr) {
        messageDigest.update(bArr);
        return m1408a(messageDigest.digest());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x002d, code lost:
        if (r1 == null) goto L_0x0030;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0028 A[SYNTHETIC, Splitter:B:21:0x0028] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1409b(java.security.MessageDigest r3, java.io.File r4) {
        /*
            boolean r0 = r4.isFile()
            if (r0 == 0) goto L_0x0039
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x002c, all -> 0x0024 }
            r1.<init>(r4)     // Catch:{ Exception -> 0x002c, all -> 0x0024 }
            r4 = 4048(0xfd0, float:5.672E-42)
            byte[] r4 = new byte[r4]     // Catch:{ Exception -> 0x0022, all -> 0x0020 }
        L_0x0010:
            int r0 = r1.read(r4)     // Catch:{ Exception -> 0x0022, all -> 0x0020 }
            r2 = -1
            if (r0 != r2) goto L_0x001b
        L_0x0017:
            r1.close()     // Catch:{ Exception -> 0x0030 }
            goto L_0x0030
        L_0x001b:
            r2 = 0
            r3.update(r4, r2, r0)     // Catch:{ Exception -> 0x0022, all -> 0x0020 }
            goto L_0x0010
        L_0x0020:
            r3 = move-exception
            goto L_0x0026
        L_0x0022:
            goto L_0x002d
        L_0x0024:
            r3 = move-exception
            r1 = r0
        L_0x0026:
            if (r1 == 0) goto L_0x002b
            r1.close()     // Catch:{ Exception -> 0x002b }
        L_0x002b:
            throw r3
        L_0x002c:
            r1 = r0
        L_0x002d:
            if (r1 == 0) goto L_0x0030
            goto L_0x0017
        L_0x0030:
            byte[] r3 = r3.digest()
            java.lang.String r3 = m1408a(r3)
            return r3
        L_0x0039:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0985bs.m1409b(java.security.MessageDigest, java.io.File):java.lang.String");
    }

    /* renamed from: a */
    private static String m1408a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            int i2 = (bArr[i] >> 4) & 15;
            byte b = bArr[i] & 15;
            sb.append((char) (i2 >= 10 ? (i2 + 97) - 10 : i2 + 48));
            sb.append((char) (b >= 10 ? (b + 97) - 10 : b + 48));
        }
        return sb.toString();
    }
}
