package com.baidubce.util;

import com.baidubce.BceClientException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HashUtils {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    public static byte[] computeMd5Hash(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        return computeHash(inputStream, MessageDigest.getInstance("MD5"));
    }

    public static byte[] computeSha256Hash(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        return computeHash(inputStream, MessageDigest.getInstance("SHA-256"));
    }

    public static byte[] computeHash(InputStream inputStream, MessageDigest messageDigest) throws IOException {
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int read = inputStream.read(bArr, 0, bArr.length);
                if (read != -1) {
                    messageDigest.update(bArr, 0, read);
                } else {
                    try {
                        return messageDigest.digest();
                    } catch (Exception e) {
                        throw new BceClientException("Fail to close InputStream.", e);
                    }
                }
            }
        } finally {
            try {
                inputStream.close();
            } catch (Exception e2) {
                throw new BceClientException("Fail to close InputStream.", e2);
            }
        }
    }

    public static String sha256Hex(String str, String str2) {
        try {
            Mac instance = Mac.getInstance("HmacSHA256");
            instance.init(new SecretKeySpec(str.getBytes(UTF8), "HmacSHA256"));
            return new String(ConvertUtils.encodeHex(instance.doFinal(str2.getBytes(UTF8))));
        } catch (Exception e) {
            throw new BceClientException("Fail to generate the signature", e);
        }
    }
}
