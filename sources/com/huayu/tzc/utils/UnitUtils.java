package com.huayu.tzc.utils;

import android.text.TextUtils;
import android.util.Log;
import com.baidu.mobstat.Config;
import java.math.BigInteger;
import java.text.DecimalFormat;

public class UnitUtils {
    public static int getHeight(String str) {
        int i = 0;
        if (!str.contains("'")) {
            try {
                i = (int) Double.parseDouble(str);
                Log.e("ss", "getHeight: " + i);
                return i;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Log.e("TAG", "getHeight: " + e.getMessage());
                return i;
            }
        } else {
            String[] split = str.split("'");
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1].replace("\"", ""));
            double d = (double) parseInt;
            Double.isNaN(d);
            double d2 = (double) ((float) (d * 30.48d));
            double d3 = (double) parseInt2;
            Double.isNaN(d3);
            Double.isNaN(d2);
            return (int) oneF((float) (d2 + (d3 * 2.45d)));
        }
    }

    public static float getFloatWeight(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1.0f;
        }
        if (str.contains(Config.TRACE_TODAY_VISIT_SPLIT)) {
            return stlb2St(str);
        }
        return Float.parseFloat(str);
    }

    public static String getMsgType(byte[] bArr) {
        String bigInteger = new BigInteger(1, new byte[]{bArr[10]}).toString(2);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 8 - bigInteger.length(); i++) {
            stringBuffer.append(0);
        }
        stringBuffer.append(bigInteger);
        return stringBuffer.reverse().toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0058 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static float getDecimal(java.lang.String r4) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 2
            char r2 = r4.charAt(r1)
            r0.append(r2)
            r2 = 1
            char r4 = r4.charAt(r2)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            int r0 = r4.hashCode()
            r3 = 1536(0x600, float:2.152E-42)
            if (r0 == r3) goto L_0x003e
            r3 = 1537(0x601, float:2.154E-42)
            if (r0 == r3) goto L_0x0034
            r3 = 1567(0x61f, float:2.196E-42)
            if (r0 == r3) goto L_0x002a
            goto L_0x0048
        L_0x002a:
            java.lang.String r0 = "10"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0048
            r4 = 2
            goto L_0x0049
        L_0x0034:
            java.lang.String r0 = "01"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0048
            r4 = 1
            goto L_0x0049
        L_0x003e:
            java.lang.String r0 = "00"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0048
            r4 = 0
            goto L_0x0049
        L_0x0048:
            r4 = -1
        L_0x0049:
            if (r4 == 0) goto L_0x0058
            if (r4 == r2) goto L_0x0055
            if (r4 == r1) goto L_0x0051
            r4 = 0
            goto L_0x005b
        L_0x0051:
            r4 = 1008981770(0x3c23d70a, float:0.01)
            goto L_0x005b
        L_0x0055:
            r4 = 1065353216(0x3f800000, float:1.0)
            goto L_0x005b
        L_0x0058:
            r4 = 1036831949(0x3dcccccd, float:0.1)
        L_0x005b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huayu.tzc.utils.UnitUtils.getDecimal(java.lang.String):float");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String computeWeight(byte[] r9, java.lang.String r10, byte[] r11) {
        /*
            java.lang.String r0 = com.huayu.tzc.utils.ByteUtil.bytesToHex(r9)
            r1 = 16
            int r0 = java.lang.Integer.parseInt(r0, r1)
            java.lang.String r11 = getMsgType(r11)
            float r2 = getDecimal(r11)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "computeWeight: "
            r3.append(r4)
            r3.append(r11)
            java.lang.String r5 = "   "
            r3.append(r5)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            java.lang.String r5 = "s"
            android.util.Log.e(r5, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r5 = 4
            char r5 = r11.charAt(r5)
            r3.append(r5)
            r5 = 3
            char r11 = r11.charAt(r5)
            r3.append(r11)
            java.lang.String r11 = r3.toString()
            int r3 = r11.hashCode()
            r5 = 1536(0x600, float:2.152E-42)
            r6 = 2
            r7 = 0
            r8 = 1
            if (r3 == r5) goto L_0x0071
            r5 = 1567(0x61f, float:2.196E-42)
            if (r3 == r5) goto L_0x0067
            r5 = 1568(0x620, float:2.197E-42)
            if (r3 == r5) goto L_0x005d
            goto L_0x007b
        L_0x005d:
            java.lang.String r3 = "11"
            boolean r11 = r11.equals(r3)
            if (r11 == 0) goto L_0x007b
            r11 = 2
            goto L_0x007c
        L_0x0067:
            java.lang.String r3 = "10"
            boolean r11 = r11.equals(r3)
            if (r11 == 0) goto L_0x007b
            r11 = 1
            goto L_0x007c
        L_0x0071:
            java.lang.String r3 = "00"
            boolean r11 = r11.equals(r3)
            if (r11 == 0) goto L_0x007b
            r11 = 0
            goto L_0x007c
        L_0x007b:
            r11 = -1
        L_0x007c:
            java.lang.String r3 = "kg"
            if (r11 == 0) goto L_0x00f4
            if (r11 == r8) goto L_0x00e6
            if (r11 == r6) goto L_0x0088
            java.lang.String r9 = ""
            goto L_0x0100
        L_0x0088:
            byte r10 = r9[r7]
            java.lang.String r10 = com.huayu.tzc.utils.ByteUtil.byteToHex(r10)
            int r10 = java.lang.Integer.parseInt(r10, r1)
            byte r9 = r9[r8]
            java.lang.String r9 = com.huayu.tzc.utils.ByteUtil.byteToHex(r9)
            int r9 = java.lang.Integer.parseInt(r9, r1)
            java.lang.Object[] r11 = new java.lang.Object[r8]
            float r9 = (float) r9
            r0 = 1092616192(0x41200000, float:10.0)
            float r9 = r9 / r0
            java.lang.Float r9 = java.lang.Float.valueOf(r9)
            r11[r7] = r9
            java.lang.String r9 = "%.1f"
            java.lang.String r9 = java.lang.String.format(r9, r11)
            float r9 = java.lang.Float.parseFloat(r9)
            java.lang.String r11 = ":"
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x00d1
            int r9 = java.lang.Math.round(r9)
            float r9 = (float) r9
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r10)
            r0.append(r11)
            int r9 = (int) r9
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            goto L_0x00e3
        L_0x00d1:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r10)
            r0.append(r11)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
        L_0x00e3:
            java.lang.String r10 = "st:lb"
            goto L_0x0100
        L_0x00e6:
            float r9 = (float) r0
            float r9 = r9 * r2
            float r9 = twoF(r9)
            java.lang.String r9 = java.lang.String.valueOf(r9)
            java.lang.String r10 = "lb"
            goto L_0x0100
        L_0x00f4:
            float r9 = (float) r0
            float r9 = r9 * r2
            float r9 = twoF(r9)
            java.lang.String r9 = java.lang.String.valueOf(r9)
            r10 = r3
        L_0x0100:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r4)
            r11.append(r9)
            r11.append(r10)
            java.lang.String r11 = r11.toString()
            android.util.Log.e(r3, r11)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r9)
            r11.append(r10)
            java.lang.String r9 = r11.toString()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huayu.tzc.utils.UnitUtils.computeWeight(byte[], java.lang.String, byte[]):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getIndex(java.lang.String r6) {
        /*
            int r0 = r6.hashCode()
            r1 = 3420(0xd5c, float:4.792E-42)
            r2 = 0
            r3 = -1
            r4 = 2
            r5 = 1
            if (r0 == r1) goto L_0x002a
            r1 = 3446(0xd76, float:4.829E-42)
            if (r0 == r1) goto L_0x0020
            r1 = 109719855(0x68a312f, float:5.19821E-35)
            if (r0 == r1) goto L_0x0016
            goto L_0x0034
        L_0x0016:
            java.lang.String r0 = "st:lb"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0034
            r6 = 2
            goto L_0x0035
        L_0x0020:
            java.lang.String r0 = "lb"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0034
            r6 = 1
            goto L_0x0035
        L_0x002a:
            java.lang.String r0 = "kg"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0034
            r6 = 0
            goto L_0x0035
        L_0x0034:
            r6 = -1
        L_0x0035:
            if (r6 == 0) goto L_0x0040
            if (r6 == r5) goto L_0x003f
            if (r6 == r4) goto L_0x003d
            r2 = -1
            goto L_0x0040
        L_0x003d:
            r2 = 2
            goto L_0x0040
        L_0x003f:
            r2 = 1
        L_0x0040:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huayu.tzc.utils.UnitUtils.getIndex(java.lang.String):int");
    }

    public static float getWeight(int i, float f) {
        if (i != 1) {
            return i != 2 ? f : kg2St(f);
        }
        return kg2Lb(f);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static float getKgWeight(float r4, java.lang.String r5) {
        /*
            int r0 = r5.hashCode()
            r1 = 3420(0xd5c, float:4.792E-42)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L_0x0028
            r1 = 3446(0xd76, float:4.829E-42)
            if (r0 == r1) goto L_0x001e
            r1 = 109719855(0x68a312f, float:5.19821E-35)
            if (r0 == r1) goto L_0x0014
            goto L_0x0032
        L_0x0014:
            java.lang.String r0 = "st:lb"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0032
            r5 = 2
            goto L_0x0033
        L_0x001e:
            java.lang.String r0 = "lb"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0032
            r5 = 1
            goto L_0x0033
        L_0x0028:
            java.lang.String r0 = "kg"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0032
            r5 = 0
            goto L_0x0033
        L_0x0032:
            r5 = -1
        L_0x0033:
            if (r5 == 0) goto L_0x0044
            if (r5 == r3) goto L_0x0040
            if (r5 == r2) goto L_0x003b
            r4 = 0
            goto L_0x0044
        L_0x003b:
            float r4 = st2Kg(r4)
            goto L_0x0044
        L_0x0040:
            float r4 = lb2Kg(r4)
        L_0x0044:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huayu.tzc.utils.UnitUtils.getKgWeight(float, java.lang.String):float");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003d A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte switchUnit(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = 3420(0xd5c, float:4.792E-42)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L_0x0028
            r1 = 3446(0xd76, float:4.829E-42)
            if (r0 == r1) goto L_0x001e
            r1 = 109719855(0x68a312f, float:5.19821E-35)
            if (r0 == r1) goto L_0x0014
            goto L_0x0032
        L_0x0014:
            java.lang.String r0 = "st:lb"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0032
            r4 = 2
            goto L_0x0033
        L_0x001e:
            java.lang.String r0 = "lb"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0032
            r4 = 1
            goto L_0x0033
        L_0x0028:
            java.lang.String r0 = "kg"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0032
            r4 = 0
            goto L_0x0033
        L_0x0032:
            r4 = -1
        L_0x0033:
            if (r4 == 0) goto L_0x003d
            if (r4 == r3) goto L_0x003c
            if (r4 == r2) goto L_0x003a
            return r3
        L_0x003a:
            r4 = 4
            return r4
        L_0x003c:
            return r2
        L_0x003d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huayu.tzc.utils.UnitUtils.switchUnit(java.lang.String):byte");
    }

    public static float kg2Lb(float f) {
        double d = (double) f;
        Double.isNaN(d);
        return oneF((float) (d * 2.2046226d));
    }

    public static float kg2St(float f) {
        return twoF(f * 0.157473f);
    }

    public static float lb2Kg(float f) {
        return twoF(f * 0.4535924f);
    }

    public static float lb2St(float f) {
        StringBuilder sb = new StringBuilder();
        sb.append("lb2St: ");
        sb.append(f);
        sb.append("  ");
        float f2 = f * 0.0714286f;
        sb.append(twoF(f2));
        Log.e("2", sb.toString());
        return twoF(f2);
    }

    public static float st2Lb(float f) {
        float oneF = oneF(f * 14.0f);
        if (oneF < 0.0f) {
            return 0.0f;
        }
        return oneF;
    }

    public static float st2Kg(float f) {
        return twoF(f * 6.350293f);
    }

    public static String st2StLb(float f) {
        int oneF = (int) oneF(f);
        float st2Lb = st2Lb(f - ((float) oneF));
        if (st2Lb < 0.0f) {
            st2Lb = 0.0f;
        }
        if (st2Lb > 10.0f) {
            return oneF + Config.TRACE_TODAY_VISIT_SPLIT + Math.round(st2Lb);
        }
        return oneF + Config.TRACE_TODAY_VISIT_SPLIT + st2Lb;
    }

    public static String st2StLbOne(float f) {
        int oneF = (int) oneF(f);
        float st2Lb = st2Lb(f - ((float) oneF));
        if (st2Lb < 0.0f) {
            st2Lb = 0.0f;
        }
        if (st2Lb > 10.0f) {
            return oneF + Config.TRACE_TODAY_VISIT_SPLIT + ((int) st2Lb);
        }
        return oneF + Config.TRACE_TODAY_VISIT_SPLIT + st2Lb;
    }

    public static String st2StLb2(float f) {
        int oneF = (int) oneF(f);
        int round = Math.round(st2Lb(f - ((float) oneF)));
        return oneF + Config.TRACE_TODAY_VISIT_SPLIT + round;
    }

    public static float stlb2St(String str) {
        String[] split = str.split(Config.TRACE_TODAY_VISIT_SPLIT);
        int parseInt = Integer.parseInt(split[0]);
        float oneF = oneF(lb2St(Float.parseFloat(split[1])));
        Log.e("TAG", "stlb2St: " + str + "  " + oneF);
        return ((float) parseInt) + oneF;
    }

    public static float oneF(float f) {
        return Float.parseFloat(new DecimalFormat(".0").format((double) f).replace(",", "."));
    }

    public static Double oneD(double d) {
        return Double.valueOf(Double.parseDouble(new DecimalFormat(".00").format(d).replace(",", ".")));
    }

    public static float twoF(float f) {
        return Float.parseFloat(new DecimalFormat(".00").format((double) f).replace(",", "."));
    }
}
