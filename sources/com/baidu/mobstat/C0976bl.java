package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.baidu.mobstat.bl */
public final class C0976bl {

    /* renamed from: com.baidu.mobstat.bl$b */
    public static class C0978b {
        /* renamed from: a */
        public static byte[] m1331a(int i, byte[] bArr) throws Exception {
            int i2 = i - 1;
            if (i2 < 0 || C0983bq.f1271a.length <= i2) {
                return new byte[0];
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(C0983bq.f1271a[i2].getBytes(), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(1, secretKeySpec);
            return instance.doFinal(bArr);
        }

        /* renamed from: b */
        public static byte[] m1332b(int i, byte[] bArr) throws Exception {
            int i2 = i - 1;
            if (i2 < 0 || C0983bq.f1271a.length <= i2) {
                return new byte[0];
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(C0983bq.f1271a[i2].getBytes(), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(2, secretKeySpec);
            return instance.doFinal(bArr);
        }

        /* renamed from: c */
        public static String m1333c(int i, byte[] bArr) {
            try {
                return C0981bo.m1356b(m1331a(i, bArr));
            } catch (Exception unused) {
                return "";
            }
        }

        /* renamed from: d */
        public static String m1334d(int i, byte[] bArr) {
            String c = m1333c(i, bArr);
            if (TextUtils.isEmpty(c)) {
                return "";
            }
            return c + "|" + i;
        }
    }

    /* renamed from: com.baidu.mobstat.bl$a */
    public static class C0977a {
        @SuppressLint({"TrulyRandom"})
        /* renamed from: a */
        public static byte[] m1328a(byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, ivParameterSpec);
            return instance.doFinal(bArr3);
        }

        /* renamed from: a */
        public static byte[] m1327a() throws Exception {
            KeyGenerator instance = KeyGenerator.getInstance("AES");
            instance.init(128, new SecureRandom());
            return instance.generateKey().getEncoded();
        }

        /* renamed from: b */
        public static byte[] m1330b() {
            byte[] bArr = new byte[16];
            new SecureRandom().nextBytes(bArr);
            return bArr;
        }

        /* renamed from: a */
        public static String m1326a(byte[] bArr) {
            try {
                return m1329b(m1327a(), m1330b(), bArr);
            } catch (Exception unused) {
                return "";
            }
        }

        /* renamed from: b */
        public static String m1329b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            try {
                byte[] a = m1328a(bArr, bArr2, C0984br.m1405a(bArr3));
                return C0981bo.m1356b(a) + "|" + C0990bv.m1424a(bArr) + "|" + C0990bv.m1424a(bArr2);
            } catch (Exception unused) {
                return "";
            }
        }
    }
}
