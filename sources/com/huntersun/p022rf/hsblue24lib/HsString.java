package com.huntersun.p022rf.hsblue24lib;

import kotlin.UByte;

/* renamed from: com.huntersun.rf.hsblue24lib.HsString */
public final class HsString {
    static byte[] ParseHexStringArray(String[] strArr) {
        byte[] bArr = new byte[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            bArr[i] = (byte) Integer.parseInt(strArr[i], 16);
        }
        return bArr;
    }

    public static byte[] ParseHexString(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return ParseHexStringArray(str.split("[ :]+"));
    }

    public static String getHexString(byte[] bArr, String str) {
        String str2 = "";
        if (bArr == null) {
            return str2;
        }
        for (int i = 0; i < bArr.length; i++) {
            str2 = str2 + Integer.toString((bArr[i] & UByte.MAX_VALUE) + UByte.MIN_VALUE, 16).substring(1);
            if (!(str == null || str.isEmpty() || i == bArr.length - 1)) {
                str2 = str2 + str;
            }
        }
        return str2;
    }
}
