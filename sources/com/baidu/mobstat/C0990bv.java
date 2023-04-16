package com.baidu.mobstat;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* renamed from: com.baidu.mobstat.bv */
public class C0990bv {
    /* renamed from: a */
    public static String m1424a(byte[] bArr) throws Exception {
        try {
            return C0981bo.m1356b(m1427a(false, C0983bq.m1404a(), bArr));
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: a */
    public static byte[] m1427a(boolean z, byte[] bArr, byte[] bArr2) throws Exception {
        RSAKey a = m1425a(z, bArr);
        return m1426a(1, (Key) a, ((a.getModulus().bitLength() + 1) / 8) - 11, bArr2);
    }

    /* renamed from: b */
    public static byte[] m1428b(boolean z, byte[] bArr, byte[] bArr2) throws Exception {
        RSAKey a = m1425a(z, bArr);
        return m1426a(2, (Key) a, (a.getModulus().bitLength() + 1) / 8, bArr2);
    }

    /* renamed from: a */
    private static RSAKey m1425a(boolean z, byte[] bArr) throws Exception {
        KeyFactory instance = KeyFactory.getInstance("RSA");
        if (z) {
            return (RSAPrivateKey) instance.generatePrivate(new PKCS8EncodedKeySpec(bArr));
        }
        return (RSAPublicKey) instance.generatePublic(new X509EncodedKeySpec(bArr));
    }

    /* renamed from: a */
    private static byte[] m1426a(int i, Key key, int i2, byte[] bArr) throws Exception {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(i, key);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i3 = 0;
        while (i3 < bArr.length) {
            int length = bArr.length - i3;
            if (length > i2) {
                length = i2;
            }
            byteArrayOutputStream.write(instance.doFinal(bArr, i3, length));
            i3 += i2;
        }
        return byteArrayOutputStream.toByteArray();
    }
}
