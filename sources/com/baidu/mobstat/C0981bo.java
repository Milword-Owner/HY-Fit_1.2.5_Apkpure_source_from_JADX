package com.baidu.mobstat;

import androidx.core.view.MotionEventCompat;
import java.io.UnsupportedEncodingException;
import kotlin.UByte;

/* renamed from: com.baidu.mobstat.bo */
public final class C0981bo {

    /* renamed from: a */
    private static final byte[] f1268a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    /* renamed from: a */
    public static byte[] m1354a(byte[] bArr) {
        return m1355a(bArr, bArr.length);
    }

    /* renamed from: a */
    public static byte[] m1355a(byte[] bArr, int i) {
        byte b;
        int i2;
        int i3;
        int i4 = (i / 4) * 3;
        if (i4 == 0) {
            return new byte[0];
        }
        byte[] bArr2 = new byte[i4];
        int i5 = i;
        int i6 = 0;
        while (true) {
            byte b2 = bArr[i5 - 1];
            b = 10;
            if (!(b2 == 10 || b2 == 13 || b2 == 32 || b2 == 9)) {
                if (b2 != 61) {
                    break;
                }
                i6++;
            }
            i5--;
        }
        int i7 = 0;
        byte b3 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < i5) {
            byte b4 = bArr[i7];
            if (!(b4 == b || b4 == 13 || b4 == 32 || b4 == 9)) {
                if (b4 >= 65 && b4 <= 90) {
                    i2 = b4 - 65;
                } else if (b4 >= 97 && b4 <= 122) {
                    i2 = b4 - 71;
                } else if (b4 >= 48 && b4 <= 57) {
                    i2 = b4 + 4;
                } else if (b4 == 43) {
                    i2 = 62;
                } else if (b4 != 47) {
                    return null;
                } else {
                    i2 = 63;
                }
                byte b5 = (b3 << 6) | ((byte) i2);
                if (i9 % 4 == 3) {
                    int i10 = i8 + 1;
                    bArr2[i8] = (byte) ((16711680 & b5) >> 16);
                    int i11 = i10 + 1;
                    bArr2[i10] = (byte) ((65280 & b5) >> 8);
                    i3 = i11 + 1;
                    bArr2[i11] = (byte) (b5 & UByte.MAX_VALUE);
                } else {
                    i3 = i8;
                }
                i9++;
                i8 = i3;
                b3 = b5;
            }
            i7++;
            b = 10;
        }
        if (i6 > 0) {
            int i12 = b3 << (i6 * 6);
            int i13 = i8 + 1;
            bArr2[i8] = (byte) ((i12 & 16711680) >> 16);
            if (i6 == 1) {
                i8 = i13 + 1;
                bArr2[i13] = (byte) ((i12 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
            } else {
                i8 = i13;
            }
        }
        byte[] bArr3 = new byte[i8];
        System.arraycopy(bArr2, 0, bArr3, 0, i8);
        return bArr3;
    }

    /* renamed from: b */
    public static String m1356b(byte[] bArr) throws UnsupportedEncodingException {
        return m1353a(bArr, "utf-8");
    }

    /* renamed from: a */
    public static String m1353a(byte[] bArr, String str) throws UnsupportedEncodingException {
        int length = (bArr.length * 4) / 3;
        byte[] bArr2 = new byte[(length + (length / 76) + 3)];
        int length2 = bArr.length - (bArr.length % 3);
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length2; i3 += 3) {
            int i4 = i + 1;
            byte[] bArr3 = f1268a;
            bArr2[i] = bArr3[(bArr[i3] & UByte.MAX_VALUE) >> 2];
            int i5 = i4 + 1;
            int i6 = i3 + 1;
            bArr2[i4] = bArr3[((bArr[i3] & 3) << 4) | ((bArr[i6] & UByte.MAX_VALUE) >> 4)];
            int i7 = i5 + 1;
            int i8 = i3 + 2;
            bArr2[i5] = bArr3[((bArr[i6] & 15) << 2) | ((bArr[i8] & UByte.MAX_VALUE) >> 6)];
            i = i7 + 1;
            bArr2[i7] = bArr3[bArr[i8] & 63];
            if ((i - i2) % 76 == 0 && i != 0) {
                i2++;
            }
        }
        int length3 = bArr.length % 3;
        if (length3 == 1) {
            int i9 = i + 1;
            byte[] bArr4 = f1268a;
            bArr2[i] = bArr4[(bArr[length2] & UByte.MAX_VALUE) >> 2];
            int i10 = i9 + 1;
            bArr2[i9] = bArr4[(bArr[length2] & 3) << 4];
            int i11 = i10 + 1;
            bArr2[i10] = 61;
            i = i11 + 1;
            bArr2[i11] = 61;
        } else if (length3 == 2) {
            int i12 = i + 1;
            byte[] bArr5 = f1268a;
            bArr2[i] = bArr5[(bArr[length2] & UByte.MAX_VALUE) >> 2];
            int i13 = i12 + 1;
            int i14 = length2 + 1;
            bArr2[i12] = bArr5[((bArr[i14] & UByte.MAX_VALUE) >> 4) | ((bArr[length2] & 3) << 4)];
            int i15 = i13 + 1;
            bArr2[i13] = bArr5[(bArr[i14] & 15) << 2];
            i = i15 + 1;
            bArr2[i15] = 61;
        }
        return new String(bArr2, 0, i, str);
    }
}
