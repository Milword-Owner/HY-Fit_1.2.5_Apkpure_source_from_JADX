package com.huayu.tzc.utils;

import kotlin.UByte;

public class ByteUtil {
    public static String bytesToHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    public static String byteToHex(byte b) {
        String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
        if (hexString.length() >= 2) {
            return hexString;
        }
        return "0" + hexString;
    }
}
