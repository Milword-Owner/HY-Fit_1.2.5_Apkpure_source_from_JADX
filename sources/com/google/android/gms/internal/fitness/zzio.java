package com.google.android.gms.internal.fitness;

import com.github.mikephil.charting.utils.Utils;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzio<T> implements zzja<T> {
    private static final int[] zzaac = new int[0];
    private static final Unsafe zzaad = zzjy.zzdt();
    private final boolean zzaaa;
    private final zzgo<?> zzaab;
    private final int[] zzaae;
    private final Object[] zzaaf;
    private final int zzaag;
    private final int zzaah;
    private final boolean zzaai;
    private final boolean zzaaj;
    private final boolean zzaak;
    private final int[] zzaal;
    private final int zzaam;
    private final int zzaan;
    private final zzip zzaao;
    private final zzhu zzaap;
    private final zzid zzaaq;
    private final zzik zzzy;
    private final zzjs<?, ?> zzzz;

    private zzio(int[] iArr, Object[] objArr, int i, int i2, zzik zzik, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzip zzip, zzhu zzhu, zzjs<?, ?> zzjs, zzgo<?> zzgo, zzid zzid) {
        this.zzaae = iArr;
        this.zzaaf = objArr;
        this.zzaag = i;
        this.zzaah = i2;
        this.zzaai = zzik instanceof zzgy;
        this.zzaaj = z;
        this.zzaaa = zzgo != null && zzgo.zze(zzik);
        this.zzaak = false;
        this.zzaal = iArr2;
        this.zzaam = i3;
        this.zzaan = i4;
        this.zzaao = zzip;
        this.zzaap = zzhu;
        this.zzzz = zzjs;
        this.zzaab = zzgo;
        this.zzzy = zzik;
        this.zzaaq = zzid;
    }

    /* JADX WARNING: Removed duplicated region for block: B:164:0x034d  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0396  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.gms.internal.fitness.zzio<T> zza(java.lang.Class<T> r33, com.google.android.gms.internal.fitness.zzii r34, com.google.android.gms.internal.fitness.zzip r35, com.google.android.gms.internal.fitness.zzhu r36, com.google.android.gms.internal.fitness.zzjs<?, ?> r37, com.google.android.gms.internal.fitness.zzgo<?> r38, com.google.android.gms.internal.fitness.zzid r39) {
        /*
            r0 = r34
            boolean r1 = r0 instanceof com.google.android.gms.internal.fitness.zzix
            if (r1 == 0) goto L_0x040d
            com.google.android.gms.internal.fitness.zzix r0 = (com.google.android.gms.internal.fitness.zzix) r0
            int r1 = r0.zzcs()
            int r2 = com.google.android.gms.internal.fitness.zziw.zzaax
            r3 = 0
            r4 = 1
            if (r1 != r2) goto L_0x0014
            r11 = 1
            goto L_0x0015
        L_0x0014:
            r11 = 0
        L_0x0015:
            java.lang.String r1 = r0.zzcz()
            int r2 = r1.length()
            char r5 = r1.charAt(r3)
            r6 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r6) goto L_0x0031
            r5 = 1
        L_0x0027:
            int r7 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r6) goto L_0x0032
            r5 = r7
            goto L_0x0027
        L_0x0031:
            r7 = 1
        L_0x0032:
            int r5 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x0051
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x003e:
            int r10 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r6) goto L_0x004e
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r9
            r7 = r7 | r5
            int r9 = r9 + 13
            r5 = r10
            goto L_0x003e
        L_0x004e:
            int r5 = r5 << r9
            r7 = r7 | r5
            goto L_0x0052
        L_0x0051:
            r10 = r5
        L_0x0052:
            if (r7 != 0) goto L_0x0061
            int[] r5 = zzaac
            r15 = r5
            r5 = 0
            r7 = 0
            r9 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r16 = 0
            goto L_0x018a
        L_0x0061:
            int r5 = r10 + 1
            char r7 = r1.charAt(r10)
            if (r7 < r6) goto L_0x0080
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x006d:
            int r10 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r6) goto L_0x007d
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r9
            r7 = r7 | r5
            int r9 = r9 + 13
            r5 = r10
            goto L_0x006d
        L_0x007d:
            int r5 = r5 << r9
            r5 = r5 | r7
            goto L_0x0082
        L_0x0080:
            r10 = r5
            r5 = r7
        L_0x0082:
            int r7 = r10 + 1
            char r9 = r1.charAt(r10)
            if (r9 < r6) goto L_0x00a1
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x008e:
            int r12 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x009e
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r10
            r9 = r9 | r7
            int r10 = r10 + 13
            r7 = r12
            goto L_0x008e
        L_0x009e:
            int r7 = r7 << r10
            r9 = r9 | r7
            goto L_0x00a2
        L_0x00a1:
            r12 = r7
        L_0x00a2:
            int r7 = r12 + 1
            char r10 = r1.charAt(r12)
            if (r10 < r6) goto L_0x00c2
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00ae:
            int r13 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x00be
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r12
            r10 = r10 | r7
            int r12 = r12 + 13
            r7 = r13
            goto L_0x00ae
        L_0x00be:
            int r7 = r7 << r12
            r7 = r7 | r10
            r10 = r7
            goto L_0x00c3
        L_0x00c2:
            r13 = r7
        L_0x00c3:
            int r7 = r13 + 1
            char r12 = r1.charAt(r13)
            if (r12 < r6) goto L_0x00e3
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00cf:
            int r14 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x00df
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r13
            r12 = r12 | r7
            int r13 = r13 + 13
            r7 = r14
            goto L_0x00cf
        L_0x00df:
            int r7 = r7 << r13
            r7 = r7 | r12
            r12 = r7
            goto L_0x00e4
        L_0x00e3:
            r14 = r7
        L_0x00e4:
            int r7 = r14 + 1
            char r13 = r1.charAt(r14)
            if (r13 < r6) goto L_0x0104
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00f0:
            int r15 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x0100
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r14
            r13 = r13 | r7
            int r14 = r14 + 13
            r7 = r15
            goto L_0x00f0
        L_0x0100:
            int r7 = r7 << r14
            r7 = r7 | r13
            r13 = r7
            goto L_0x0105
        L_0x0104:
            r15 = r7
        L_0x0105:
            int r7 = r15 + 1
            char r14 = r1.charAt(r15)
            if (r14 < r6) goto L_0x0127
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x0111:
            int r16 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x0122
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r15
            r14 = r14 | r7
            int r15 = r15 + 13
            r7 = r16
            goto L_0x0111
        L_0x0122:
            int r7 = r7 << r15
            r7 = r7 | r14
            r14 = r7
            r7 = r16
        L_0x0127:
            int r15 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x014a
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x0133:
            int r17 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r6) goto L_0x0145
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r7 = r7 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x0133
        L_0x0145:
            int r15 = r15 << r16
            r7 = r7 | r15
            r15 = r17
        L_0x014a:
            int r16 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r6) goto L_0x0175
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            r17 = 13
            r32 = r16
            r16 = r15
            r15 = r32
        L_0x015c:
            int r18 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r6) goto L_0x016f
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r17
            r16 = r16 | r15
            int r17 = r17 + 13
            r15 = r18
            goto L_0x015c
        L_0x016f:
            int r15 = r15 << r17
            r15 = r16 | r15
            r16 = r18
        L_0x0175:
            int r17 = r15 + r14
            int r7 = r17 + r7
            int[] r7 = new int[r7]
            int r17 = r5 << 1
            int r9 = r17 + r9
            r32 = r16
            r16 = r5
            r5 = r14
            r14 = r15
            r15 = r7
            r7 = r9
            r9 = r10
            r10 = r32
        L_0x018a:
            sun.misc.Unsafe r3 = zzaad
            java.lang.Object[] r17 = r0.zzda()
            com.google.android.gms.internal.fitness.zzik r18 = r0.zzcu()
            java.lang.Class r8 = r18.getClass()
            int r6 = r13 * 3
            int[] r6 = new int[r6]
            int r13 = r13 << r4
            java.lang.Object[] r13 = new java.lang.Object[r13]
            int r19 = r14 + r5
            r21 = r14
            r22 = r19
            r5 = 0
            r20 = 0
        L_0x01a8:
            if (r10 >= r2) goto L_0x03ea
            int r23 = r10 + 1
            char r10 = r1.charAt(r10)
            r4 = 55296(0xd800, float:7.7486E-41)
            if (r10 < r4) goto L_0x01dc
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r25 = 13
            r32 = r23
            r23 = r10
            r10 = r32
        L_0x01bf:
            int r26 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r4) goto L_0x01d5
            r4 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r25
            r23 = r23 | r4
            int r25 = r25 + 13
            r10 = r26
            r4 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01bf
        L_0x01d5:
            int r4 = r10 << r25
            r10 = r23 | r4
            r4 = r26
            goto L_0x01de
        L_0x01dc:
            r4 = r23
        L_0x01de:
            int r23 = r4 + 1
            char r4 = r1.charAt(r4)
            r25 = r2
            r2 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r2) goto L_0x0212
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r26 = 13
            r32 = r23
            r23 = r4
            r4 = r32
        L_0x01f5:
            int r27 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r2) goto L_0x020b
            r2 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r26
            r23 = r23 | r2
            int r26 = r26 + 13
            r4 = r27
            r2 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01f5
        L_0x020b:
            int r2 = r4 << r26
            r4 = r23 | r2
            r2 = r27
            goto L_0x0214
        L_0x0212:
            r2 = r23
        L_0x0214:
            r23 = r14
            r14 = r4 & 255(0xff, float:3.57E-43)
            r26 = r12
            r12 = r4 & 1024(0x400, float:1.435E-42)
            if (r12 == 0) goto L_0x0223
            int r12 = r5 + 1
            r15[r5] = r20
            r5 = r12
        L_0x0223:
            r12 = 51
            r29 = r5
            if (r14 < r12) goto L_0x02bc
            int r12 = r2 + 1
            char r2 = r1.charAt(r2)
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r5) goto L_0x0252
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r30 = 13
        L_0x0238:
            int r31 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r5) goto L_0x024d
            r5 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r30
            r2 = r2 | r5
            int r30 = r30 + 13
            r12 = r31
            r5 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0238
        L_0x024d:
            int r5 = r12 << r30
            r2 = r2 | r5
            r12 = r31
        L_0x0252:
            int r5 = r14 + -51
            r30 = r12
            r12 = 9
            if (r5 == r12) goto L_0x0273
            r12 = 17
            if (r5 != r12) goto L_0x025f
            goto L_0x0273
        L_0x025f:
            r12 = 12
            if (r5 != r12) goto L_0x0271
            if (r11 != 0) goto L_0x0271
            int r5 = r20 / 3
            r12 = 1
            int r5 = r5 << r12
            int r5 = r5 + r12
            int r12 = r7 + 1
            r7 = r17[r7]
            r13[r5] = r7
            r7 = r12
        L_0x0271:
            r12 = 1
            goto L_0x0280
        L_0x0273:
            int r5 = r20 / 3
            r12 = 1
            int r5 = r5 << r12
            int r5 = r5 + r12
            int r24 = r7 + 1
            r7 = r17[r7]
            r13[r5] = r7
            r7 = r24
        L_0x0280:
            int r2 = r2 << r12
            r5 = r17[r2]
            boolean r12 = r5 instanceof java.lang.reflect.Field
            if (r12 == 0) goto L_0x028a
            java.lang.reflect.Field r5 = (java.lang.reflect.Field) r5
            goto L_0x0292
        L_0x028a:
            java.lang.String r5 = (java.lang.String) r5
            java.lang.reflect.Field r5 = zza((java.lang.Class<?>) r8, (java.lang.String) r5)
            r17[r2] = r5
        L_0x0292:
            r12 = r6
            long r5 = r3.objectFieldOffset(r5)
            int r6 = (int) r5
            int r2 = r2 + 1
            r5 = r17[r2]
            r27 = r6
            boolean r6 = r5 instanceof java.lang.reflect.Field
            if (r6 == 0) goto L_0x02a5
            java.lang.reflect.Field r5 = (java.lang.reflect.Field) r5
            goto L_0x02ad
        L_0x02a5:
            java.lang.String r5 = (java.lang.String) r5
            java.lang.reflect.Field r5 = zza((java.lang.Class<?>) r8, (java.lang.String) r5)
            r17[r2] = r5
        L_0x02ad:
            long r5 = r3.objectFieldOffset(r5)
            int r2 = (int) r5
            r5 = r2
            r18 = r8
            r6 = r27
            r28 = r30
            r2 = 0
            goto L_0x03ae
        L_0x02bc:
            r12 = r6
            int r5 = r7 + 1
            r6 = r17[r7]
            java.lang.String r6 = (java.lang.String) r6
            java.lang.reflect.Field r6 = zza((java.lang.Class<?>) r8, (java.lang.String) r6)
            r7 = 9
            if (r14 == r7) goto L_0x032e
            r7 = 17
            if (r14 != r7) goto L_0x02d0
            goto L_0x032e
        L_0x02d0:
            r7 = 27
            if (r14 == r7) goto L_0x031f
            r7 = 49
            if (r14 != r7) goto L_0x02d9
            goto L_0x031f
        L_0x02d9:
            r7 = 12
            if (r14 == r7) goto L_0x030b
            r7 = 30
            if (r14 == r7) goto L_0x030b
            r7 = 44
            if (r14 != r7) goto L_0x02e6
            goto L_0x030b
        L_0x02e6:
            r7 = 50
            if (r14 != r7) goto L_0x033c
            int r7 = r21 + 1
            r15[r21] = r20
            int r21 = r20 / 3
            r24 = 1
            int r21 = r21 << 1
            int r27 = r5 + 1
            r5 = r17[r5]
            r13[r21] = r5
            r5 = r4 & 2048(0x800, float:2.87E-42)
            if (r5 == 0) goto L_0x0308
            int r21 = r21 + 1
            int r5 = r27 + 1
            r27 = r17[r27]
            r13[r21] = r27
            r27 = r5
        L_0x0308:
            r21 = r7
            goto L_0x033e
        L_0x030b:
            if (r11 != 0) goto L_0x031c
            int r7 = r20 / 3
            r24 = 1
            int r7 = r7 << 1
            int r7 = r7 + 1
            int r27 = r5 + 1
            r5 = r17[r5]
            r13[r7] = r5
            goto L_0x033e
        L_0x031c:
            r24 = 1
            goto L_0x033c
        L_0x031f:
            r24 = 1
            int r7 = r20 / 3
            int r7 = r7 << 1
            int r7 = r7 + 1
            int r27 = r5 + 1
            r5 = r17[r5]
            r13[r7] = r5
            goto L_0x033e
        L_0x032e:
            r24 = 1
            int r7 = r20 / 3
            int r7 = r7 << 1
            int r7 = r7 + 1
            java.lang.Class r27 = r6.getType()
            r13[r7] = r27
        L_0x033c:
            r27 = r5
        L_0x033e:
            long r5 = r3.objectFieldOffset(r6)
            int r6 = (int) r5
            r5 = r4 & 4096(0x1000, float:5.74E-42)
            r7 = 4096(0x1000, float:5.74E-42)
            if (r5 != r7) goto L_0x0396
            r5 = 17
            if (r14 > r5) goto L_0x0396
            int r5 = r2 + 1
            char r2 = r1.charAt(r2)
            r7 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r7) goto L_0x0372
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r18 = 13
        L_0x035c:
            int r28 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r7) goto L_0x036e
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r18
            r2 = r2 | r5
            int r18 = r18 + 13
            r5 = r28
            goto L_0x035c
        L_0x036e:
            int r5 = r5 << r18
            r2 = r2 | r5
            goto L_0x0374
        L_0x0372:
            r28 = r5
        L_0x0374:
            r5 = 1
            int r18 = r16 << 1
            int r24 = r2 / 32
            int r18 = r18 + r24
            r5 = r17[r18]
            boolean r7 = r5 instanceof java.lang.reflect.Field
            if (r7 == 0) goto L_0x0384
            java.lang.reflect.Field r5 = (java.lang.reflect.Field) r5
            goto L_0x038c
        L_0x0384:
            java.lang.String r5 = (java.lang.String) r5
            java.lang.reflect.Field r5 = zza((java.lang.Class<?>) r8, (java.lang.String) r5)
            r17[r18] = r5
        L_0x038c:
            r18 = r8
            long r7 = r3.objectFieldOffset(r5)
            int r5 = (int) r7
            int r2 = r2 % 32
            goto L_0x039e
        L_0x0396:
            r18 = r8
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r28 = r2
            r2 = 0
        L_0x039e:
            r7 = 18
            if (r14 < r7) goto L_0x03ac
            r7 = 49
            if (r14 > r7) goto L_0x03ac
            int r7 = r22 + 1
            r15[r22] = r6
            r22 = r7
        L_0x03ac:
            r7 = r27
        L_0x03ae:
            int r8 = r20 + 1
            r12[r20] = r10
            int r10 = r8 + 1
            r20 = r1
            r1 = r4 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x03bd
            r1 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x03be
        L_0x03bd:
            r1 = 0
        L_0x03be:
            r4 = r4 & 256(0x100, float:3.59E-43)
            if (r4 == 0) goto L_0x03c5
            r4 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x03c6
        L_0x03c5:
            r4 = 0
        L_0x03c6:
            r1 = r1 | r4
            int r4 = r14 << 20
            r1 = r1 | r4
            r1 = r1 | r6
            r12[r8] = r1
            int r1 = r10 + 1
            int r2 = r2 << 20
            r2 = r2 | r5
            r12[r10] = r2
            r6 = r12
            r8 = r18
            r14 = r23
            r2 = r25
            r12 = r26
            r10 = r28
            r5 = r29
            r4 = 1
            r32 = r20
            r20 = r1
            r1 = r32
            goto L_0x01a8
        L_0x03ea:
            r26 = r12
            r23 = r14
            r12 = r6
            com.google.android.gms.internal.fitness.zzio r1 = new com.google.android.gms.internal.fitness.zzio
            com.google.android.gms.internal.fitness.zzik r10 = r0.zzcu()
            r0 = 0
            r5 = r1
            r7 = r13
            r8 = r9
            r9 = r26
            r12 = r0
            r13 = r15
            r15 = r19
            r16 = r35
            r17 = r36
            r18 = r37
            r19 = r38
            r20 = r39
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r1
        L_0x040d:
            com.google.android.gms.internal.fitness.zzjl r0 = (com.google.android.gms.internal.fitness.zzjl) r0
            int r0 = r0.zzcs()
            int r1 = com.google.android.gms.internal.fitness.zziw.zzaax
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            goto L_0x041c
        L_0x041b:
            throw r0
        L_0x041c:
            goto L_0x041b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.zzio.zza(java.lang.Class, com.google.android.gms.internal.fitness.zzii, com.google.android.gms.internal.fitness.zzip, com.google.android.gms.internal.fitness.zzhu, com.google.android.gms.internal.fitness.zzjs, com.google.android.gms.internal.fitness.zzgo, com.google.android.gms.internal.fitness.zzid):com.google.android.gms.internal.fitness.zzio");
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006a, code lost:
        if (com.google.android.gms.internal.fitness.zzjc.zze(com.google.android.gms.internal.fitness.zzjy.zzo(r10, r6), com.google.android.gms.internal.fitness.zzjy.zzo(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007e, code lost:
        if (com.google.android.gms.internal.fitness.zzjy.zzk(r10, r6) == com.google.android.gms.internal.fitness.zzjy.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0090, code lost:
        if (com.google.android.gms.internal.fitness.zzjy.zzj(r10, r6) == com.google.android.gms.internal.fitness.zzjy.zzj(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a4, code lost:
        if (com.google.android.gms.internal.fitness.zzjy.zzk(r10, r6) == com.google.android.gms.internal.fitness.zzjy.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b6, code lost:
        if (com.google.android.gms.internal.fitness.zzjy.zzj(r10, r6) == com.google.android.gms.internal.fitness.zzjy.zzj(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c8, code lost:
        if (com.google.android.gms.internal.fitness.zzjy.zzj(r10, r6) == com.google.android.gms.internal.fitness.zzjy.zzj(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00da, code lost:
        if (com.google.android.gms.internal.fitness.zzjy.zzj(r10, r6) == com.google.android.gms.internal.fitness.zzjy.zzj(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f0, code lost:
        if (com.google.android.gms.internal.fitness.zzjc.zze(com.google.android.gms.internal.fitness.zzjy.zzo(r10, r6), com.google.android.gms.internal.fitness.zzjy.zzo(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0106, code lost:
        if (com.google.android.gms.internal.fitness.zzjc.zze(com.google.android.gms.internal.fitness.zzjy.zzo(r10, r6), com.google.android.gms.internal.fitness.zzjy.zzo(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x011c, code lost:
        if (com.google.android.gms.internal.fitness.zzjc.zze(com.google.android.gms.internal.fitness.zzjy.zzo(r10, r6), com.google.android.gms.internal.fitness.zzjy.zzo(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x012e, code lost:
        if (com.google.android.gms.internal.fitness.zzjy.zzl(r10, r6) == com.google.android.gms.internal.fitness.zzjy.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0140, code lost:
        if (com.google.android.gms.internal.fitness.zzjy.zzj(r10, r6) == com.google.android.gms.internal.fitness.zzjy.zzj(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0154, code lost:
        if (com.google.android.gms.internal.fitness.zzjy.zzk(r10, r6) == com.google.android.gms.internal.fitness.zzjy.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0165, code lost:
        if (com.google.android.gms.internal.fitness.zzjy.zzj(r10, r6) == com.google.android.gms.internal.fitness.zzjy.zzj(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0178, code lost:
        if (com.google.android.gms.internal.fitness.zzjy.zzk(r10, r6) == com.google.android.gms.internal.fitness.zzjy.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x018b, code lost:
        if (com.google.android.gms.internal.fitness.zzjy.zzk(r10, r6) == com.google.android.gms.internal.fitness.zzjy.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01a4, code lost:
        if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.fitness.zzjy.zzm(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.fitness.zzjy.zzm(r11, r6))) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01bf, code lost:
        if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.fitness.zzjy.zzn(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.fitness.zzjy.zzn(r11, r6))) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01c1, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
        if (com.google.android.gms.internal.fitness.zzjc.zze(com.google.android.gms.internal.fitness.zzjy.zzo(r10, r6), com.google.android.gms.internal.fitness.zzjy.zzo(r11, r6)) != false) goto L_0x01c2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(T r10, T r11) {
        /*
            r9 = this;
            int[] r0 = r9.zzaae
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            r3 = 1
            if (r2 >= r0) goto L_0x01c9
            int r4 = r9.zzai(r2)
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r6 = r4 & r5
            long r6 = (long) r6
            r8 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = r4 & r8
            int r4 = r4 >>> 20
            switch(r4) {
                case 0: goto L_0x01a7;
                case 1: goto L_0x018e;
                case 2: goto L_0x017b;
                case 3: goto L_0x0168;
                case 4: goto L_0x0157;
                case 5: goto L_0x0144;
                case 6: goto L_0x0132;
                case 7: goto L_0x0120;
                case 8: goto L_0x010a;
                case 9: goto L_0x00f4;
                case 10: goto L_0x00de;
                case 11: goto L_0x00cc;
                case 12: goto L_0x00ba;
                case 13: goto L_0x00a8;
                case 14: goto L_0x0094;
                case 15: goto L_0x0082;
                case 16: goto L_0x006e;
                case 17: goto L_0x0058;
                case 18: goto L_0x004a;
                case 19: goto L_0x004a;
                case 20: goto L_0x004a;
                case 21: goto L_0x004a;
                case 22: goto L_0x004a;
                case 23: goto L_0x004a;
                case 24: goto L_0x004a;
                case 25: goto L_0x004a;
                case 26: goto L_0x004a;
                case 27: goto L_0x004a;
                case 28: goto L_0x004a;
                case 29: goto L_0x004a;
                case 30: goto L_0x004a;
                case 31: goto L_0x004a;
                case 32: goto L_0x004a;
                case 33: goto L_0x004a;
                case 34: goto L_0x004a;
                case 35: goto L_0x004a;
                case 36: goto L_0x004a;
                case 37: goto L_0x004a;
                case 38: goto L_0x004a;
                case 39: goto L_0x004a;
                case 40: goto L_0x004a;
                case 41: goto L_0x004a;
                case 42: goto L_0x004a;
                case 43: goto L_0x004a;
                case 44: goto L_0x004a;
                case 45: goto L_0x004a;
                case 46: goto L_0x004a;
                case 47: goto L_0x004a;
                case 48: goto L_0x004a;
                case 49: goto L_0x004a;
                case 50: goto L_0x003c;
                case 51: goto L_0x001c;
                case 52: goto L_0x001c;
                case 53: goto L_0x001c;
                case 54: goto L_0x001c;
                case 55: goto L_0x001c;
                case 56: goto L_0x001c;
                case 57: goto L_0x001c;
                case 58: goto L_0x001c;
                case 59: goto L_0x001c;
                case 60: goto L_0x001c;
                case 61: goto L_0x001c;
                case 62: goto L_0x001c;
                case 63: goto L_0x001c;
                case 64: goto L_0x001c;
                case 65: goto L_0x001c;
                case 66: goto L_0x001c;
                case 67: goto L_0x001c;
                case 68: goto L_0x001c;
                default: goto L_0x001a;
            }
        L_0x001a:
            goto L_0x01c2
        L_0x001c:
            int r4 = r9.zzaj(r2)
            r4 = r4 & r5
            long r4 = (long) r4
            int r8 = com.google.android.gms.internal.fitness.zzjy.zzj(r10, r4)
            int r4 = com.google.android.gms.internal.fitness.zzjy.zzj(r11, r4)
            if (r8 != r4) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.fitness.zzjy.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.fitness.zzjy.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.fitness.zzjc.zze(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x003c:
            java.lang.Object r3 = com.google.android.gms.internal.fitness.zzjy.zzo(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.fitness.zzjy.zzo(r11, r6)
            boolean r3 = com.google.android.gms.internal.fitness.zzjc.zze(r3, r4)
            goto L_0x01c2
        L_0x004a:
            java.lang.Object r3 = com.google.android.gms.internal.fitness.zzjy.zzo(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.fitness.zzjy.zzo(r11, r6)
            boolean r3 = com.google.android.gms.internal.fitness.zzjc.zze(r3, r4)
            goto L_0x01c2
        L_0x0058:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.fitness.zzjy.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.fitness.zzjy.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.fitness.zzjc.zze(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x006e:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.fitness.zzjy.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.fitness.zzjy.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x0082:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.fitness.zzjy.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.fitness.zzjy.zzj(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0094:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.fitness.zzjy.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.fitness.zzjy.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x00a8:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.fitness.zzjy.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.fitness.zzjy.zzj(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x00ba:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.fitness.zzjy.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.fitness.zzjy.zzj(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x00cc:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.fitness.zzjy.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.fitness.zzjy.zzj(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x00de:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.fitness.zzjy.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.fitness.zzjy.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.fitness.zzjc.zze(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x00f4:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.fitness.zzjy.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.fitness.zzjy.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.fitness.zzjc.zze(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x010a:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.fitness.zzjy.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.fitness.zzjy.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.fitness.zzjc.zze(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x0120:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            boolean r4 = com.google.android.gms.internal.fitness.zzjy.zzl(r10, r6)
            boolean r5 = com.google.android.gms.internal.fitness.zzjy.zzl(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0132:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.fitness.zzjy.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.fitness.zzjy.zzj(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0144:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.fitness.zzjy.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.fitness.zzjy.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x0157:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.fitness.zzjy.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.fitness.zzjy.zzj(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0168:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.fitness.zzjy.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.fitness.zzjy.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x017b:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.fitness.zzjy.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.fitness.zzjy.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x018e:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            float r4 = com.google.android.gms.internal.fitness.zzjy.zzm(r10, r6)
            int r4 = java.lang.Float.floatToIntBits(r4)
            float r5 = com.google.android.gms.internal.fitness.zzjy.zzm(r11, r6)
            int r5 = java.lang.Float.floatToIntBits(r5)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x01a7:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            double r4 = com.google.android.gms.internal.fitness.zzjy.zzn(r10, r6)
            long r4 = java.lang.Double.doubleToLongBits(r4)
            double r6 = com.google.android.gms.internal.fitness.zzjy.zzn(r11, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
        L_0x01c1:
            r3 = 0
        L_0x01c2:
            if (r3 != 0) goto L_0x01c5
            return r1
        L_0x01c5:
            int r2 = r2 + 3
            goto L_0x0005
        L_0x01c9:
            com.google.android.gms.internal.fitness.zzjs<?, ?> r0 = r9.zzzz
            java.lang.Object r0 = r0.zzo(r10)
            com.google.android.gms.internal.fitness.zzjs<?, ?> r2 = r9.zzzz
            java.lang.Object r2 = r2.zzo(r11)
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x01dc
            return r1
        L_0x01dc:
            boolean r0 = r9.zzaaa
            if (r0 == 0) goto L_0x01f1
            com.google.android.gms.internal.fitness.zzgo<?> r0 = r9.zzaab
            com.google.android.gms.internal.fitness.zzgt r10 = r0.zzc(r10)
            com.google.android.gms.internal.fitness.zzgo<?> r0 = r9.zzaab
            com.google.android.gms.internal.fitness.zzgt r11 = r0.zzc(r11)
            boolean r10 = r10.equals(r11)
            return r10
        L_0x01f1:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.zzio.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01c3, code lost:
        r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0227, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0228, code lost:
        r1 = r1 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int hashCode(T r9) {
        /*
            r8 = this;
            int[] r0 = r8.zzaae
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            if (r1 >= r0) goto L_0x022c
            int r3 = r8.zzai(r1)
            int[] r4 = r8.zzaae
            r4 = r4[r1]
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r3
            long r5 = (long) r5
            r7 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = r3 & r7
            int r3 = r3 >>> 20
            r7 = 37
            switch(r3) {
                case 0: goto L_0x0219;
                case 1: goto L_0x020e;
                case 2: goto L_0x0203;
                case 3: goto L_0x01f8;
                case 4: goto L_0x01f1;
                case 5: goto L_0x01e6;
                case 6: goto L_0x01df;
                case 7: goto L_0x01d4;
                case 8: goto L_0x01c7;
                case 9: goto L_0x01b9;
                case 10: goto L_0x01ad;
                case 11: goto L_0x01a5;
                case 12: goto L_0x019d;
                case 13: goto L_0x0195;
                case 14: goto L_0x0189;
                case 15: goto L_0x0181;
                case 16: goto L_0x0175;
                case 17: goto L_0x016a;
                case 18: goto L_0x015e;
                case 19: goto L_0x015e;
                case 20: goto L_0x015e;
                case 21: goto L_0x015e;
                case 22: goto L_0x015e;
                case 23: goto L_0x015e;
                case 24: goto L_0x015e;
                case 25: goto L_0x015e;
                case 26: goto L_0x015e;
                case 27: goto L_0x015e;
                case 28: goto L_0x015e;
                case 29: goto L_0x015e;
                case 30: goto L_0x015e;
                case 31: goto L_0x015e;
                case 32: goto L_0x015e;
                case 33: goto L_0x015e;
                case 34: goto L_0x015e;
                case 35: goto L_0x015e;
                case 36: goto L_0x015e;
                case 37: goto L_0x015e;
                case 38: goto L_0x015e;
                case 39: goto L_0x015e;
                case 40: goto L_0x015e;
                case 41: goto L_0x015e;
                case 42: goto L_0x015e;
                case 43: goto L_0x015e;
                case 44: goto L_0x015e;
                case 45: goto L_0x015e;
                case 46: goto L_0x015e;
                case 47: goto L_0x015e;
                case 48: goto L_0x015e;
                case 49: goto L_0x015e;
                case 50: goto L_0x0152;
                case 51: goto L_0x013c;
                case 52: goto L_0x012a;
                case 53: goto L_0x0118;
                case 54: goto L_0x0106;
                case 55: goto L_0x00f8;
                case 56: goto L_0x00e6;
                case 57: goto L_0x00d8;
                case 58: goto L_0x00c6;
                case 59: goto L_0x00b2;
                case 60: goto L_0x00a0;
                case 61: goto L_0x008e;
                case 62: goto L_0x0080;
                case 63: goto L_0x0072;
                case 64: goto L_0x0064;
                case 65: goto L_0x0052;
                case 66: goto L_0x0044;
                case 67: goto L_0x0032;
                case 68: goto L_0x0020;
                default: goto L_0x001e;
            }
        L_0x001e:
            goto L_0x0228
        L_0x0020:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            java.lang.Object r3 = com.google.android.gms.internal.fitness.zzjy.zzo(r9, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x0032:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzh(r9, r5)
            int r3 = com.google.android.gms.internal.fitness.zzhc.zzj(r3)
            goto L_0x0227
        L_0x0044:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzg(r9, r5)
            goto L_0x0227
        L_0x0052:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzh(r9, r5)
            int r3 = com.google.android.gms.internal.fitness.zzhc.zzj(r3)
            goto L_0x0227
        L_0x0064:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzg(r9, r5)
            goto L_0x0227
        L_0x0072:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzg(r9, r5)
            goto L_0x0227
        L_0x0080:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzg(r9, r5)
            goto L_0x0227
        L_0x008e:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.fitness.zzjy.zzo(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x00a0:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            java.lang.Object r3 = com.google.android.gms.internal.fitness.zzjy.zzo(r9, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x00b2:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.fitness.zzjy.zzo(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x00c6:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            boolean r3 = zzi(r9, r5)
            int r3 = com.google.android.gms.internal.fitness.zzhc.zzc((boolean) r3)
            goto L_0x0227
        L_0x00d8:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzg(r9, r5)
            goto L_0x0227
        L_0x00e6:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzh(r9, r5)
            int r3 = com.google.android.gms.internal.fitness.zzhc.zzj(r3)
            goto L_0x0227
        L_0x00f8:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzg(r9, r5)
            goto L_0x0227
        L_0x0106:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzh(r9, r5)
            int r3 = com.google.android.gms.internal.fitness.zzhc.zzj(r3)
            goto L_0x0227
        L_0x0118:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzh(r9, r5)
            int r3 = com.google.android.gms.internal.fitness.zzhc.zzj(r3)
            goto L_0x0227
        L_0x012a:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            float r3 = zzf(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0227
        L_0x013c:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            double r3 = zze(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.fitness.zzhc.zzj(r3)
            goto L_0x0227
        L_0x0152:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.fitness.zzjy.zzo(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x015e:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.fitness.zzjy.zzo(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x016a:
            java.lang.Object r3 = com.google.android.gms.internal.fitness.zzjy.zzo(r9, r5)
            if (r3 == 0) goto L_0x01c3
            int r7 = r3.hashCode()
            goto L_0x01c3
        L_0x0175:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.fitness.zzjy.zzk(r9, r5)
            int r3 = com.google.android.gms.internal.fitness.zzhc.zzj(r3)
            goto L_0x0227
        L_0x0181:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.fitness.zzjy.zzj(r9, r5)
            goto L_0x0227
        L_0x0189:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.fitness.zzjy.zzk(r9, r5)
            int r3 = com.google.android.gms.internal.fitness.zzhc.zzj(r3)
            goto L_0x0227
        L_0x0195:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.fitness.zzjy.zzj(r9, r5)
            goto L_0x0227
        L_0x019d:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.fitness.zzjy.zzj(r9, r5)
            goto L_0x0227
        L_0x01a5:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.fitness.zzjy.zzj(r9, r5)
            goto L_0x0227
        L_0x01ad:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.fitness.zzjy.zzo(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x01b9:
            java.lang.Object r3 = com.google.android.gms.internal.fitness.zzjy.zzo(r9, r5)
            if (r3 == 0) goto L_0x01c3
            int r7 = r3.hashCode()
        L_0x01c3:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x0228
        L_0x01c7:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.fitness.zzjy.zzo(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x01d4:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.fitness.zzjy.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.fitness.zzhc.zzc((boolean) r3)
            goto L_0x0227
        L_0x01df:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.fitness.zzjy.zzj(r9, r5)
            goto L_0x0227
        L_0x01e6:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.fitness.zzjy.zzk(r9, r5)
            int r3 = com.google.android.gms.internal.fitness.zzhc.zzj(r3)
            goto L_0x0227
        L_0x01f1:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.fitness.zzjy.zzj(r9, r5)
            goto L_0x0227
        L_0x01f8:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.fitness.zzjy.zzk(r9, r5)
            int r3 = com.google.android.gms.internal.fitness.zzhc.zzj(r3)
            goto L_0x0227
        L_0x0203:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.fitness.zzjy.zzk(r9, r5)
            int r3 = com.google.android.gms.internal.fitness.zzhc.zzj(r3)
            goto L_0x0227
        L_0x020e:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.fitness.zzjy.zzm(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0227
        L_0x0219:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.fitness.zzjy.zzn(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.fitness.zzhc.zzj(r3)
        L_0x0227:
            int r2 = r2 + r3
        L_0x0228:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x022c:
            int r2 = r2 * 53
            com.google.android.gms.internal.fitness.zzjs<?, ?> r0 = r8.zzzz
            java.lang.Object r0 = r0.zzo(r9)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r8.zzaaa
            if (r0 == 0) goto L_0x024a
            int r2 = r2 * 53
            com.google.android.gms.internal.fitness.zzgo<?> r0 = r8.zzaab
            com.google.android.gms.internal.fitness.zzgt r9 = r0.zzc(r9)
            int r9 = r9.hashCode()
            int r2 = r2 + r9
        L_0x024a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.zzio.hashCode(java.lang.Object):int");
    }

    public final void zzd(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzaae.length; i += 3) {
                int zzai = zzai(i);
                long j = (long) (1048575 & zzai);
                int i2 = this.zzaae[i];
                switch ((zzai & 267386880) >>> 20) {
                    case 0:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzn(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 1:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzm(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 2:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzk(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 3:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzk(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 4:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzj(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 5:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzk(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 6:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzj(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 7:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzl(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 8:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzo(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 9:
                        zza(t, t2, i);
                        break;
                    case 10:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzo(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 11:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzj(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 12:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzj(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 13:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzj(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 14:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzk(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 15:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzj(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 16:
                        if (!zzb(t2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzk(t2, j));
                            zzc(t, i);
                            break;
                        }
                    case 17:
                        zza(t, t2, i);
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.zzaap.zza(t, t2, j);
                        break;
                    case 50:
                        zzjc.zza(this.zzaaq, t, t2, j);
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (!zza(t2, i2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzo(t2, j));
                            zzb(t, i2, i);
                            break;
                        }
                    case 60:
                        zzb(t, t2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (!zza(t2, i2, i)) {
                            break;
                        } else {
                            zzjy.zza((Object) t, j, zzjy.zzo(t2, j));
                            zzb(t, i2, i);
                            break;
                        }
                    case 68:
                        zzb(t, t2, i);
                        break;
                }
            }
            zzjc.zza(this.zzzz, t, t2);
            if (this.zzaaa) {
                zzjc.zza(this.zzaab, t, t2);
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    private final void zza(T t, T t2, int i) {
        long zzai = (long) (zzai(i) & 1048575);
        if (zzb(t2, i)) {
            Object zzo = zzjy.zzo(t, zzai);
            Object zzo2 = zzjy.zzo(t2, zzai);
            if (zzo != null && zzo2 != null) {
                zzjy.zza((Object) t, zzai, zzhc.zzb(zzo, zzo2));
                zzc(t, i);
            } else if (zzo2 != null) {
                zzjy.zza((Object) t, zzai, zzo2);
                zzc(t, i);
            }
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzai = zzai(i);
        int i2 = this.zzaae[i];
        long j = (long) (zzai & 1048575);
        if (zza(t2, i2, i)) {
            Object obj = null;
            if (zza(t, i2, i)) {
                obj = zzjy.zzo(t, j);
            }
            Object zzo = zzjy.zzo(t2, j);
            if (obj != null && zzo != null) {
                zzjy.zza((Object) t, j, zzhc.zzb(obj, zzo));
                zzb(t, i2, i);
            } else if (zzo != null) {
                zzjy.zza((Object) t, j, zzo);
                zzb(t, i2, i);
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:305:0x072f, code lost:
        r8 = (r8 + r9) + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:322:0x07fb, code lost:
        r5 = r5 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x0810, code lost:
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x0855, code lost:
        r5 = r5 + r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:383:0x0909, code lost:
        r5 = r5 + r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:391:0x092c, code lost:
        r3 = r3 + 3;
        r9 = r13;
        r7 = 1048575;
        r8 = 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzm(T r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            boolean r2 = r0.zzaaj
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = 0
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r8 = 1
            r9 = 0
            r11 = 0
            if (r2 == 0) goto L_0x047d
            sun.misc.Unsafe r2 = zzaad
            r12 = 0
            r13 = 0
        L_0x0016:
            int[] r14 = r0.zzaae
            int r14 = r14.length
            if (r12 >= r14) goto L_0x0475
            int r14 = r0.zzai(r12)
            r15 = r14 & r3
            int r15 = r15 >>> 20
            int[] r3 = r0.zzaae
            r3 = r3[r12]
            r14 = r14 & r7
            long r5 = (long) r14
            com.google.android.gms.internal.fitness.zzgu r14 = com.google.android.gms.internal.fitness.zzgu.DOUBLE_LIST_PACKED
            int r14 = r14.mo23211id()
            if (r15 < r14) goto L_0x003f
            com.google.android.gms.internal.fitness.zzgu r14 = com.google.android.gms.internal.fitness.zzgu.SINT64_LIST_PACKED
            int r14 = r14.mo23211id()
            if (r15 > r14) goto L_0x003f
            int[] r14 = r0.zzaae
            int r17 = r12 + 2
            r14 = r14[r17]
        L_0x003f:
            switch(r15) {
                case 0: goto L_0x0461;
                case 1: goto L_0x0455;
                case 2: goto L_0x0445;
                case 3: goto L_0x0435;
                case 4: goto L_0x0425;
                case 5: goto L_0x0419;
                case 6: goto L_0x040d;
                case 7: goto L_0x0401;
                case 8: goto L_0x03e3;
                case 9: goto L_0x03cf;
                case 10: goto L_0x03be;
                case 11: goto L_0x03af;
                case 12: goto L_0x03a0;
                case 13: goto L_0x0395;
                case 14: goto L_0x038a;
                case 15: goto L_0x037b;
                case 16: goto L_0x036c;
                case 17: goto L_0x0357;
                case 18: goto L_0x034c;
                case 19: goto L_0x0343;
                case 20: goto L_0x033a;
                case 21: goto L_0x0331;
                case 22: goto L_0x0328;
                case 23: goto L_0x031f;
                case 24: goto L_0x0316;
                case 25: goto L_0x030d;
                case 26: goto L_0x0304;
                case 27: goto L_0x02f7;
                case 28: goto L_0x02ee;
                case 29: goto L_0x02e5;
                case 30: goto L_0x02db;
                case 31: goto L_0x02d1;
                case 32: goto L_0x02c7;
                case 33: goto L_0x02bd;
                case 34: goto L_0x02b3;
                case 35: goto L_0x029b;
                case 36: goto L_0x0286;
                case 37: goto L_0x0271;
                case 38: goto L_0x025c;
                case 39: goto L_0x0247;
                case 40: goto L_0x0232;
                case 41: goto L_0x021c;
                case 42: goto L_0x0206;
                case 43: goto L_0x01f0;
                case 44: goto L_0x01da;
                case 45: goto L_0x01c4;
                case 46: goto L_0x01ae;
                case 47: goto L_0x0198;
                case 48: goto L_0x0182;
                case 49: goto L_0x0174;
                case 50: goto L_0x0164;
                case 51: goto L_0x0156;
                case 52: goto L_0x014a;
                case 53: goto L_0x013a;
                case 54: goto L_0x012a;
                case 55: goto L_0x011a;
                case 56: goto L_0x010e;
                case 57: goto L_0x0102;
                case 58: goto L_0x00f6;
                case 59: goto L_0x00d8;
                case 60: goto L_0x00c4;
                case 61: goto L_0x00b2;
                case 62: goto L_0x00a2;
                case 63: goto L_0x0092;
                case 64: goto L_0x0086;
                case 65: goto L_0x007a;
                case 66: goto L_0x006a;
                case 67: goto L_0x005a;
                case 68: goto L_0x0044;
                default: goto L_0x0042;
            }
        L_0x0042:
            goto L_0x046f
        L_0x0044:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.fitness.zzjy.zzo(r1, r5)
            com.google.android.gms.internal.fitness.zzik r5 = (com.google.android.gms.internal.fitness.zzik) r5
            com.google.android.gms.internal.fitness.zzja r6 = r0.zzag(r12)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzc(r3, r5, r6)
            goto L_0x0354
        L_0x005a:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            long r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzf((int) r3, (long) r5)
            goto L_0x0354
        L_0x006a:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            int r5 = zzg(r1, r5)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzk(r3, r5)
            goto L_0x0354
        L_0x007a:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzh((int) r3, (long) r9)
            goto L_0x0354
        L_0x0086:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzm(r3, r11)
            goto L_0x0354
        L_0x0092:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            int r5 = zzg(r1, r5)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzn(r3, r5)
            goto L_0x0354
        L_0x00a2:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            int r5 = zzg(r1, r5)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzj(r3, r5)
            goto L_0x0354
        L_0x00b2:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.fitness.zzjy.zzo(r1, r5)
            com.google.android.gms.internal.fitness.zzfx r5 = (com.google.android.gms.internal.fitness.zzfx) r5
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzc((int) r3, (com.google.android.gms.internal.fitness.zzfx) r5)
            goto L_0x0354
        L_0x00c4:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.fitness.zzjy.zzo(r1, r5)
            com.google.android.gms.internal.fitness.zzja r6 = r0.zzag(r12)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzc((int) r3, (java.lang.Object) r5, (com.google.android.gms.internal.fitness.zzja) r6)
            goto L_0x0354
        L_0x00d8:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.fitness.zzjy.zzo(r1, r5)
            boolean r6 = r5 instanceof com.google.android.gms.internal.fitness.zzfx
            if (r6 == 0) goto L_0x00ee
            com.google.android.gms.internal.fitness.zzfx r5 = (com.google.android.gms.internal.fitness.zzfx) r5
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzc((int) r3, (com.google.android.gms.internal.fitness.zzfx) r5)
            goto L_0x0354
        L_0x00ee:
            java.lang.String r5 = (java.lang.String) r5
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r3, (java.lang.String) r5)
            goto L_0x0354
        L_0x00f6:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r3, (boolean) r8)
            goto L_0x0354
        L_0x0102:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzl(r3, r11)
            goto L_0x0354
        L_0x010e:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzg((int) r3, (long) r9)
            goto L_0x0354
        L_0x011a:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            int r5 = zzg(r1, r5)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzi(r3, r5)
            goto L_0x0354
        L_0x012a:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            long r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zze((int) r3, (long) r5)
            goto L_0x0354
        L_0x013a:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            long r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzd((int) r3, (long) r5)
            goto L_0x0354
        L_0x014a:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r3, (float) r4)
            goto L_0x0354
        L_0x0156:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x046f
            r5 = 0
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r3, (double) r5)
            goto L_0x0354
        L_0x0164:
            com.google.android.gms.internal.fitness.zzid r14 = r0.zzaaq
            java.lang.Object r5 = com.google.android.gms.internal.fitness.zzjy.zzo(r1, r5)
            java.lang.Object r6 = r0.zzah(r12)
            int r3 = r14.zzb(r3, r5, r6)
            goto L_0x0354
        L_0x0174:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            com.google.android.gms.internal.fitness.zzja r6 = r0.zzag(r12)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzd(r3, r5, r6)
            goto L_0x0354
        L_0x0182:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.fitness.zzjc.zzd((java.util.List<java.lang.Long>) r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzr(r3)
            int r6 = com.google.android.gms.internal.fitness.zzgk.zzt(r5)
            goto L_0x02af
        L_0x0198:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.fitness.zzjc.zzh(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzr(r3)
            int r6 = com.google.android.gms.internal.fitness.zzgk.zzt(r5)
            goto L_0x02af
        L_0x01ae:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.fitness.zzjc.zzj(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzr(r3)
            int r6 = com.google.android.gms.internal.fitness.zzgk.zzt(r5)
            goto L_0x02af
        L_0x01c4:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.fitness.zzjc.zzi(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzr(r3)
            int r6 = com.google.android.gms.internal.fitness.zzgk.zzt(r5)
            goto L_0x02af
        L_0x01da:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.fitness.zzjc.zze(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzr(r3)
            int r6 = com.google.android.gms.internal.fitness.zzgk.zzt(r5)
            goto L_0x02af
        L_0x01f0:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.fitness.zzjc.zzg(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzr(r3)
            int r6 = com.google.android.gms.internal.fitness.zzgk.zzt(r5)
            goto L_0x02af
        L_0x0206:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.fitness.zzjc.zzk(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzr(r3)
            int r6 = com.google.android.gms.internal.fitness.zzgk.zzt(r5)
            goto L_0x02af
        L_0x021c:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.fitness.zzjc.zzi(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzr(r3)
            int r6 = com.google.android.gms.internal.fitness.zzgk.zzt(r5)
            goto L_0x02af
        L_0x0232:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.fitness.zzjc.zzj(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzr(r3)
            int r6 = com.google.android.gms.internal.fitness.zzgk.zzt(r5)
            goto L_0x02af
        L_0x0247:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.fitness.zzjc.zzf((java.util.List<java.lang.Integer>) r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzr(r3)
            int r6 = com.google.android.gms.internal.fitness.zzgk.zzt(r5)
            goto L_0x02af
        L_0x025c:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.fitness.zzjc.zzc(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzr(r3)
            int r6 = com.google.android.gms.internal.fitness.zzgk.zzt(r5)
            goto L_0x02af
        L_0x0271:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.fitness.zzjc.zzb(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzr(r3)
            int r6 = com.google.android.gms.internal.fitness.zzgk.zzt(r5)
            goto L_0x02af
        L_0x0286:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.fitness.zzjc.zzi(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzr(r3)
            int r6 = com.google.android.gms.internal.fitness.zzgk.zzt(r5)
            goto L_0x02af
        L_0x029b:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.fitness.zzjc.zzj(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzr(r3)
            int r6 = com.google.android.gms.internal.fitness.zzgk.zzt(r5)
        L_0x02af:
            int r3 = r3 + r6
            int r3 = r3 + r5
            goto L_0x0354
        L_0x02b3:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzq(r3, r5, r11)
            goto L_0x0354
        L_0x02bd:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzu(r3, r5, r11)
            goto L_0x0354
        L_0x02c7:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzw(r3, r5, r11)
            goto L_0x0354
        L_0x02d1:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzv(r3, r5, r11)
            goto L_0x0354
        L_0x02db:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzr(r3, r5, r11)
            goto L_0x0354
        L_0x02e5:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzt(r3, r5, r11)
            goto L_0x0354
        L_0x02ee:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzd(r3, r5)
            goto L_0x0354
        L_0x02f7:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            com.google.android.gms.internal.fitness.zzja r6 = r0.zzag(r12)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzc((int) r3, (java.util.List<?>) r5, (com.google.android.gms.internal.fitness.zzja) r6)
            goto L_0x0354
        L_0x0304:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzc(r3, r5)
            goto L_0x0354
        L_0x030d:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzx(r3, r5, r11)
            goto L_0x0354
        L_0x0316:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzv(r3, r5, r11)
            goto L_0x0354
        L_0x031f:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzw(r3, r5, r11)
            goto L_0x0354
        L_0x0328:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzs(r3, r5, r11)
            goto L_0x0354
        L_0x0331:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzp(r3, r5, r11)
            goto L_0x0354
        L_0x033a:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzo(r3, r5, r11)
            goto L_0x0354
        L_0x0343:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzv(r3, r5, r11)
            goto L_0x0354
        L_0x034c:
            java.util.List r5 = zzd((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzw(r3, r5, r11)
        L_0x0354:
            int r13 = r13 + r3
            goto L_0x046f
        L_0x0357:
            boolean r14 = r0.zzb(r1, (int) r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.fitness.zzjy.zzo(r1, r5)
            com.google.android.gms.internal.fitness.zzik r5 = (com.google.android.gms.internal.fitness.zzik) r5
            com.google.android.gms.internal.fitness.zzja r6 = r0.zzag(r12)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzc(r3, r5, r6)
            goto L_0x0354
        L_0x036c:
            boolean r14 = r0.zzb(r1, (int) r12)
            if (r14 == 0) goto L_0x046f
            long r5 = com.google.android.gms.internal.fitness.zzjy.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzf((int) r3, (long) r5)
            goto L_0x0354
        L_0x037b:
            boolean r14 = r0.zzb(r1, (int) r12)
            if (r14 == 0) goto L_0x046f
            int r5 = com.google.android.gms.internal.fitness.zzjy.zzj(r1, r5)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzk(r3, r5)
            goto L_0x0354
        L_0x038a:
            boolean r5 = r0.zzb(r1, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzh((int) r3, (long) r9)
            goto L_0x0354
        L_0x0395:
            boolean r5 = r0.zzb(r1, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzm(r3, r11)
            goto L_0x0354
        L_0x03a0:
            boolean r14 = r0.zzb(r1, (int) r12)
            if (r14 == 0) goto L_0x046f
            int r5 = com.google.android.gms.internal.fitness.zzjy.zzj(r1, r5)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzn(r3, r5)
            goto L_0x0354
        L_0x03af:
            boolean r14 = r0.zzb(r1, (int) r12)
            if (r14 == 0) goto L_0x046f
            int r5 = com.google.android.gms.internal.fitness.zzjy.zzj(r1, r5)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzj(r3, r5)
            goto L_0x0354
        L_0x03be:
            boolean r14 = r0.zzb(r1, (int) r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.fitness.zzjy.zzo(r1, r5)
            com.google.android.gms.internal.fitness.zzfx r5 = (com.google.android.gms.internal.fitness.zzfx) r5
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzc((int) r3, (com.google.android.gms.internal.fitness.zzfx) r5)
            goto L_0x0354
        L_0x03cf:
            boolean r14 = r0.zzb(r1, (int) r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.fitness.zzjy.zzo(r1, r5)
            com.google.android.gms.internal.fitness.zzja r6 = r0.zzag(r12)
            int r3 = com.google.android.gms.internal.fitness.zzjc.zzc((int) r3, (java.lang.Object) r5, (com.google.android.gms.internal.fitness.zzja) r6)
            goto L_0x0354
        L_0x03e3:
            boolean r14 = r0.zzb(r1, (int) r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.fitness.zzjy.zzo(r1, r5)
            boolean r6 = r5 instanceof com.google.android.gms.internal.fitness.zzfx
            if (r6 == 0) goto L_0x03f9
            com.google.android.gms.internal.fitness.zzfx r5 = (com.google.android.gms.internal.fitness.zzfx) r5
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzc((int) r3, (com.google.android.gms.internal.fitness.zzfx) r5)
            goto L_0x0354
        L_0x03f9:
            java.lang.String r5 = (java.lang.String) r5
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r3, (java.lang.String) r5)
            goto L_0x0354
        L_0x0401:
            boolean r5 = r0.zzb(r1, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r3, (boolean) r8)
            goto L_0x0354
        L_0x040d:
            boolean r5 = r0.zzb(r1, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzl(r3, r11)
            goto L_0x0354
        L_0x0419:
            boolean r5 = r0.zzb(r1, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzg((int) r3, (long) r9)
            goto L_0x0354
        L_0x0425:
            boolean r14 = r0.zzb(r1, (int) r12)
            if (r14 == 0) goto L_0x046f
            int r5 = com.google.android.gms.internal.fitness.zzjy.zzj(r1, r5)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzi(r3, r5)
            goto L_0x0354
        L_0x0435:
            boolean r14 = r0.zzb(r1, (int) r12)
            if (r14 == 0) goto L_0x046f
            long r5 = com.google.android.gms.internal.fitness.zzjy.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zze((int) r3, (long) r5)
            goto L_0x0354
        L_0x0445:
            boolean r14 = r0.zzb(r1, (int) r12)
            if (r14 == 0) goto L_0x046f
            long r5 = com.google.android.gms.internal.fitness.zzjy.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzd((int) r3, (long) r5)
            goto L_0x0354
        L_0x0455:
            boolean r5 = r0.zzb(r1, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r3, (float) r4)
            goto L_0x0354
        L_0x0461:
            boolean r5 = r0.zzb(r1, (int) r12)
            if (r5 == 0) goto L_0x046f
            r5 = 0
            int r3 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r3, (double) r5)
            goto L_0x0354
        L_0x046f:
            int r12 = r12 + 3
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            goto L_0x0016
        L_0x0475:
            com.google.android.gms.internal.fitness.zzjs<?, ?> r2 = r0.zzzz
            int r1 = zza(r2, r1)
            int r13 = r13 + r1
            return r13
        L_0x047d:
            sun.misc.Unsafe r2 = zzaad
            r3 = 0
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            r12 = 0
        L_0x0485:
            int[] r13 = r0.zzaae
            int r13 = r13.length
            if (r3 >= r13) goto L_0x0937
            int r13 = r0.zzai(r3)
            int[] r14 = r0.zzaae
            r15 = r14[r3]
            r16 = 267386880(0xff00000, float:2.3665827E-29)
            r17 = r13 & r16
            int r4 = r17 >>> 20
            r11 = 17
            if (r4 > r11) goto L_0x04b1
            int r11 = r3 + 2
            r11 = r14[r11]
            r14 = r11 & r7
            int r11 = r11 >>> 20
            int r11 = r8 << r11
            if (r14 == r6) goto L_0x04ae
            long r8 = (long) r14
            int r12 = r2.getInt(r1, r8)
            goto L_0x04af
        L_0x04ae:
            r14 = r6
        L_0x04af:
            r6 = r14
            goto L_0x04b2
        L_0x04b1:
            r11 = 0
        L_0x04b2:
            r8 = r13 & r7
            long r8 = (long) r8
            switch(r4) {
                case 0: goto L_0x091c;
                case 1: goto L_0x090b;
                case 2: goto L_0x08fa;
                case 3: goto L_0x08ea;
                case 4: goto L_0x08da;
                case 5: goto L_0x08ca;
                case 6: goto L_0x08ba;
                case 7: goto L_0x08ae;
                case 8: goto L_0x0892;
                case 9: goto L_0x0880;
                case 10: goto L_0x0871;
                case 11: goto L_0x0864;
                case 12: goto L_0x0857;
                case 13: goto L_0x084c;
                case 14: goto L_0x0841;
                case 15: goto L_0x0834;
                case 16: goto L_0x0827;
                case 17: goto L_0x0814;
                case 18: goto L_0x0800;
                case 19: goto L_0x07f0;
                case 20: goto L_0x07e4;
                case 21: goto L_0x07d8;
                case 22: goto L_0x07cc;
                case 23: goto L_0x07c0;
                case 24: goto L_0x07b4;
                case 25: goto L_0x07a8;
                case 26: goto L_0x079d;
                case 27: goto L_0x078d;
                case 28: goto L_0x0781;
                case 29: goto L_0x0774;
                case 30: goto L_0x0767;
                case 31: goto L_0x075a;
                case 32: goto L_0x074d;
                case 33: goto L_0x0740;
                case 34: goto L_0x0733;
                case 35: goto L_0x071b;
                case 36: goto L_0x0706;
                case 37: goto L_0x06f1;
                case 38: goto L_0x06dc;
                case 39: goto L_0x06c7;
                case 40: goto L_0x06b2;
                case 41: goto L_0x069c;
                case 42: goto L_0x0686;
                case 43: goto L_0x0670;
                case 44: goto L_0x065a;
                case 45: goto L_0x0644;
                case 46: goto L_0x062e;
                case 47: goto L_0x0618;
                case 48: goto L_0x0602;
                case 49: goto L_0x05f2;
                case 50: goto L_0x05e2;
                case 51: goto L_0x05d4;
                case 52: goto L_0x05c7;
                case 53: goto L_0x05b7;
                case 54: goto L_0x05a7;
                case 55: goto L_0x0597;
                case 56: goto L_0x0589;
                case 57: goto L_0x057c;
                case 58: goto L_0x056f;
                case 59: goto L_0x0551;
                case 60: goto L_0x053d;
                case 61: goto L_0x052b;
                case 62: goto L_0x051b;
                case 63: goto L_0x050b;
                case 64: goto L_0x04fe;
                case 65: goto L_0x04f0;
                case 66: goto L_0x04e0;
                case 67: goto L_0x04d0;
                case 68: goto L_0x04ba;
                default: goto L_0x04b8;
            }
        L_0x04b8:
            goto L_0x080c
        L_0x04ba:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.fitness.zzik r4 = (com.google.android.gms.internal.fitness.zzik) r4
            com.google.android.gms.internal.fitness.zzja r8 = r0.zzag(r3)
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzc(r15, r4, r8)
            goto L_0x080b
        L_0x04d0:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            long r8 = zzh(r1, r8)
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzf((int) r15, (long) r8)
            goto L_0x080b
        L_0x04e0:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            int r4 = zzg(r1, r8)
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzk(r15, r4)
            goto L_0x080b
        L_0x04f0:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            r8 = 0
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzh((int) r15, (long) r8)
            goto L_0x080b
        L_0x04fe:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            r4 = 0
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzm(r15, r4)
            goto L_0x0855
        L_0x050b:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            int r4 = zzg(r1, r8)
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzn(r15, r4)
            goto L_0x080b
        L_0x051b:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            int r4 = zzg(r1, r8)
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzj(r15, r4)
            goto L_0x080b
        L_0x052b:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.fitness.zzfx r4 = (com.google.android.gms.internal.fitness.zzfx) r4
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzc((int) r15, (com.google.android.gms.internal.fitness.zzfx) r4)
            goto L_0x080b
        L_0x053d:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.fitness.zzja r8 = r0.zzag(r3)
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzc((int) r15, (java.lang.Object) r4, (com.google.android.gms.internal.fitness.zzja) r8)
            goto L_0x080b
        L_0x0551:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            java.lang.Object r4 = r2.getObject(r1, r8)
            boolean r8 = r4 instanceof com.google.android.gms.internal.fitness.zzfx
            if (r8 == 0) goto L_0x0567
            com.google.android.gms.internal.fitness.zzfx r4 = (com.google.android.gms.internal.fitness.zzfx) r4
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzc((int) r15, (com.google.android.gms.internal.fitness.zzfx) r4)
            goto L_0x080b
        L_0x0567:
            java.lang.String r4 = (java.lang.String) r4
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r15, (java.lang.String) r4)
            goto L_0x080b
        L_0x056f:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            r4 = 1
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r15, (boolean) r4)
            goto L_0x0855
        L_0x057c:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            r4 = 0
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzl(r15, r4)
            goto L_0x0855
        L_0x0589:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            r8 = 0
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzg((int) r15, (long) r8)
            goto L_0x080b
        L_0x0597:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            int r4 = zzg(r1, r8)
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzi(r15, r4)
            goto L_0x080b
        L_0x05a7:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            long r8 = zzh(r1, r8)
            int r4 = com.google.android.gms.internal.fitness.zzgk.zze((int) r15, (long) r8)
            goto L_0x080b
        L_0x05b7:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            long r8 = zzh(r1, r8)
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzd((int) r15, (long) r8)
            goto L_0x080b
        L_0x05c7:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            r4 = 0
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r15, (float) r4)
            goto L_0x0855
        L_0x05d4:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080c
            r8 = 0
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r15, (double) r8)
            goto L_0x080b
        L_0x05e2:
            com.google.android.gms.internal.fitness.zzid r4 = r0.zzaaq
            java.lang.Object r8 = r2.getObject(r1, r8)
            java.lang.Object r9 = r0.zzah(r3)
            int r4 = r4.zzb(r15, r8, r9)
            goto L_0x080b
        L_0x05f2:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.fitness.zzja r8 = r0.zzag(r3)
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzd(r15, r4, r8)
            goto L_0x080b
        L_0x0602:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzd((java.util.List<java.lang.Long>) r4)
            if (r4 <= 0) goto L_0x080c
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzr(r15)
            int r9 = com.google.android.gms.internal.fitness.zzgk.zzt(r4)
            goto L_0x072f
        L_0x0618:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzh(r4)
            if (r4 <= 0) goto L_0x080c
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzr(r15)
            int r9 = com.google.android.gms.internal.fitness.zzgk.zzt(r4)
            goto L_0x072f
        L_0x062e:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzj(r4)
            if (r4 <= 0) goto L_0x080c
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzr(r15)
            int r9 = com.google.android.gms.internal.fitness.zzgk.zzt(r4)
            goto L_0x072f
        L_0x0644:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzi(r4)
            if (r4 <= 0) goto L_0x080c
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzr(r15)
            int r9 = com.google.android.gms.internal.fitness.zzgk.zzt(r4)
            goto L_0x072f
        L_0x065a:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zze(r4)
            if (r4 <= 0) goto L_0x080c
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzr(r15)
            int r9 = com.google.android.gms.internal.fitness.zzgk.zzt(r4)
            goto L_0x072f
        L_0x0670:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzg(r4)
            if (r4 <= 0) goto L_0x080c
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzr(r15)
            int r9 = com.google.android.gms.internal.fitness.zzgk.zzt(r4)
            goto L_0x072f
        L_0x0686:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzk(r4)
            if (r4 <= 0) goto L_0x080c
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzr(r15)
            int r9 = com.google.android.gms.internal.fitness.zzgk.zzt(r4)
            goto L_0x072f
        L_0x069c:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzi(r4)
            if (r4 <= 0) goto L_0x080c
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzr(r15)
            int r9 = com.google.android.gms.internal.fitness.zzgk.zzt(r4)
            goto L_0x072f
        L_0x06b2:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzj(r4)
            if (r4 <= 0) goto L_0x080c
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzr(r15)
            int r9 = com.google.android.gms.internal.fitness.zzgk.zzt(r4)
            goto L_0x072f
        L_0x06c7:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzf((java.util.List<java.lang.Integer>) r4)
            if (r4 <= 0) goto L_0x080c
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzr(r15)
            int r9 = com.google.android.gms.internal.fitness.zzgk.zzt(r4)
            goto L_0x072f
        L_0x06dc:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzc(r4)
            if (r4 <= 0) goto L_0x080c
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzr(r15)
            int r9 = com.google.android.gms.internal.fitness.zzgk.zzt(r4)
            goto L_0x072f
        L_0x06f1:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzb(r4)
            if (r4 <= 0) goto L_0x080c
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzr(r15)
            int r9 = com.google.android.gms.internal.fitness.zzgk.zzt(r4)
            goto L_0x072f
        L_0x0706:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzi(r4)
            if (r4 <= 0) goto L_0x080c
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzr(r15)
            int r9 = com.google.android.gms.internal.fitness.zzgk.zzt(r4)
            goto L_0x072f
        L_0x071b:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzj(r4)
            if (r4 <= 0) goto L_0x080c
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzr(r15)
            int r9 = com.google.android.gms.internal.fitness.zzgk.zzt(r4)
        L_0x072f:
            int r8 = r8 + r9
            int r8 = r8 + r4
            goto L_0x0855
        L_0x0733:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            r10 = 0
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzq(r15, r4, r10)
            goto L_0x07fb
        L_0x0740:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzu(r15, r4, r10)
            goto L_0x07fb
        L_0x074d:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzw(r15, r4, r10)
            goto L_0x07fb
        L_0x075a:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzv(r15, r4, r10)
            goto L_0x07fb
        L_0x0767:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzr(r15, r4, r10)
            goto L_0x07fb
        L_0x0774:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzt(r15, r4, r10)
            goto L_0x080b
        L_0x0781:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzd(r15, r4)
            goto L_0x080b
        L_0x078d:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.fitness.zzja r8 = r0.zzag(r3)
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzc((int) r15, (java.util.List<?>) r4, (com.google.android.gms.internal.fitness.zzja) r8)
            goto L_0x080b
        L_0x079d:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzc(r15, r4)
            goto L_0x080b
        L_0x07a8:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            r10 = 0
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzx(r15, r4, r10)
            goto L_0x07fb
        L_0x07b4:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzv(r15, r4, r10)
            goto L_0x07fb
        L_0x07c0:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzw(r15, r4, r10)
            goto L_0x07fb
        L_0x07cc:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzs(r15, r4, r10)
            goto L_0x07fb
        L_0x07d8:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzp(r15, r4, r10)
            goto L_0x07fb
        L_0x07e4:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzo(r15, r4, r10)
            goto L_0x07fb
        L_0x07f0:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzv(r15, r4, r10)
        L_0x07fb:
            int r5 = r5 + r4
            r4 = 1
        L_0x07fd:
            r7 = 0
            goto L_0x0810
        L_0x0800:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzw(r15, r4, r10)
        L_0x080b:
            int r5 = r5 + r4
        L_0x080c:
            r4 = 1
        L_0x080d:
            r7 = 0
            r10 = 0
        L_0x0810:
            r13 = 0
            goto L_0x092c
        L_0x0814:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080c
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.fitness.zzik r4 = (com.google.android.gms.internal.fitness.zzik) r4
            com.google.android.gms.internal.fitness.zzja r8 = r0.zzag(r3)
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzc(r15, r4, r8)
            goto L_0x080b
        L_0x0827:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080c
            long r8 = r2.getLong(r1, r8)
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzf((int) r15, (long) r8)
            goto L_0x080b
        L_0x0834:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080c
            int r4 = r2.getInt(r1, r8)
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzk(r15, r4)
            goto L_0x080b
        L_0x0841:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080c
            r8 = 0
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzh((int) r15, (long) r8)
            goto L_0x080b
        L_0x084c:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080c
            r4 = 0
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzm(r15, r4)
        L_0x0855:
            int r5 = r5 + r8
            goto L_0x080c
        L_0x0857:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080c
            int r4 = r2.getInt(r1, r8)
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzn(r15, r4)
            goto L_0x080b
        L_0x0864:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080c
            int r4 = r2.getInt(r1, r8)
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzj(r15, r4)
            goto L_0x080b
        L_0x0871:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080c
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.fitness.zzfx r4 = (com.google.android.gms.internal.fitness.zzfx) r4
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzc((int) r15, (com.google.android.gms.internal.fitness.zzfx) r4)
            goto L_0x080b
        L_0x0880:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080c
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.fitness.zzja r8 = r0.zzag(r3)
            int r4 = com.google.android.gms.internal.fitness.zzjc.zzc((int) r15, (java.lang.Object) r4, (com.google.android.gms.internal.fitness.zzja) r8)
            goto L_0x080b
        L_0x0892:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080c
            java.lang.Object r4 = r2.getObject(r1, r8)
            boolean r8 = r4 instanceof com.google.android.gms.internal.fitness.zzfx
            if (r8 == 0) goto L_0x08a6
            com.google.android.gms.internal.fitness.zzfx r4 = (com.google.android.gms.internal.fitness.zzfx) r4
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzc((int) r15, (com.google.android.gms.internal.fitness.zzfx) r4)
            goto L_0x080b
        L_0x08a6:
            java.lang.String r4 = (java.lang.String) r4
            int r4 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r15, (java.lang.String) r4)
            goto L_0x080b
        L_0x08ae:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080c
            r4 = 1
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r15, (boolean) r4)
            int r5 = r5 + r8
            goto L_0x080d
        L_0x08ba:
            r4 = 1
            r8 = r12 & r11
            if (r8 == 0) goto L_0x08c7
            r10 = 0
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzl(r15, r10)
            int r5 = r5 + r8
            goto L_0x07fd
        L_0x08c7:
            r10 = 0
            goto L_0x07fd
        L_0x08ca:
            r4 = 1
            r10 = 0
            r8 = r12 & r11
            if (r8 == 0) goto L_0x08d7
            r13 = 0
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzg((int) r15, (long) r13)
            goto L_0x0909
        L_0x08d7:
            r13 = 0
            goto L_0x0919
        L_0x08da:
            r4 = 1
            r10 = 0
            r13 = 0
            r11 = r11 & r12
            if (r11 == 0) goto L_0x0919
            int r8 = r2.getInt(r1, r8)
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzi(r15, r8)
            goto L_0x0909
        L_0x08ea:
            r4 = 1
            r10 = 0
            r13 = 0
            r11 = r11 & r12
            if (r11 == 0) goto L_0x0919
            long r8 = r2.getLong(r1, r8)
            int r8 = com.google.android.gms.internal.fitness.zzgk.zze((int) r15, (long) r8)
            goto L_0x0909
        L_0x08fa:
            r4 = 1
            r10 = 0
            r13 = 0
            r11 = r11 & r12
            if (r11 == 0) goto L_0x0919
            long r8 = r2.getLong(r1, r8)
            int r8 = com.google.android.gms.internal.fitness.zzgk.zzd((int) r15, (long) r8)
        L_0x0909:
            int r5 = r5 + r8
            goto L_0x0919
        L_0x090b:
            r4 = 1
            r10 = 0
            r13 = 0
            r8 = r12 & r11
            if (r8 == 0) goto L_0x0919
            r8 = 0
            int r9 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r15, (float) r8)
            int r5 = r5 + r9
        L_0x0919:
            r7 = 0
            goto L_0x092c
        L_0x091c:
            r4 = 1
            r8 = 0
            r10 = 0
            r13 = 0
            r9 = r12 & r11
            if (r9 == 0) goto L_0x0919
            r7 = 0
            int r11 = com.google.android.gms.internal.fitness.zzgk.zzb((int) r15, (double) r7)
            int r5 = r5 + r11
        L_0x092c:
            int r3 = r3 + 3
            r9 = r13
            r4 = 0
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r8 = 1
            r11 = 0
            goto L_0x0485
        L_0x0937:
            r10 = 0
            com.google.android.gms.internal.fitness.zzjs<?, ?> r2 = r0.zzzz
            int r2 = zza(r2, r1)
            int r5 = r5 + r2
            boolean r2 = r0.zzaaa
            if (r2 == 0) goto L_0x0991
            com.google.android.gms.internal.fitness.zzgo<?> r2 = r0.zzaab
            com.google.android.gms.internal.fitness.zzgt r1 = r2.zzc(r1)
            r2 = 0
        L_0x094a:
            com.google.android.gms.internal.fitness.zzjb<T, java.lang.Object> r3 = r1.zzvb
            int r3 = r3.zzdc()
            if (r10 >= r3) goto L_0x096a
            com.google.android.gms.internal.fitness.zzjb<T, java.lang.Object> r3 = r1.zzvb
            java.util.Map$Entry r3 = r3.zzal(r10)
            java.lang.Object r4 = r3.getKey()
            com.google.android.gms.internal.fitness.zzgv r4 = (com.google.android.gms.internal.fitness.zzgv) r4
            java.lang.Object r3 = r3.getValue()
            int r3 = com.google.android.gms.internal.fitness.zzgt.zzb((com.google.android.gms.internal.fitness.zzgv<?>) r4, (java.lang.Object) r3)
            int r2 = r2 + r3
            int r10 = r10 + 1
            goto L_0x094a
        L_0x096a:
            com.google.android.gms.internal.fitness.zzjb<T, java.lang.Object> r1 = r1.zzvb
            java.lang.Iterable r1 = r1.zzdd()
            java.util.Iterator r1 = r1.iterator()
        L_0x0974:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0990
            java.lang.Object r3 = r1.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            com.google.android.gms.internal.fitness.zzgv r4 = (com.google.android.gms.internal.fitness.zzgv) r4
            java.lang.Object r3 = r3.getValue()
            int r3 = com.google.android.gms.internal.fitness.zzgt.zzb((com.google.android.gms.internal.fitness.zzgv<?>) r4, (java.lang.Object) r3)
            int r2 = r2 + r3
            goto L_0x0974
        L_0x0990:
            int r5 = r5 + r2
        L_0x0991:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.zzio.zzm(java.lang.Object):int");
    }

    private static <UT, UB> int zza(zzjs<UT, UB> zzjs, T t) {
        return zzjs.zzm(zzjs.zzo(t));
    }

    private static List<?> zzd(Object obj, long j) {
        return (List) zzjy.zzo(obj, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0513  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x0553  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0a2b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r14, com.google.android.gms.internal.fitness.zzkm r15) throws java.io.IOException {
        /*
            r13 = this;
            int r0 = r15.zzbe()
            int r1 = com.google.android.gms.internal.fitness.zzkl.zzaeb
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x0529
            com.google.android.gms.internal.fitness.zzjs<?, ?> r0 = r13.zzzz
            zza(r0, r14, (com.google.android.gms.internal.fitness.zzkm) r15)
            boolean r0 = r13.zzaaa
            if (r0 == 0) goto L_0x0032
            com.google.android.gms.internal.fitness.zzgo<?> r0 = r13.zzaab
            com.google.android.gms.internal.fitness.zzgt r0 = r0.zzc(r14)
            com.google.android.gms.internal.fitness.zzjb<T, java.lang.Object> r1 = r0.zzvb
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0032
            java.util.Iterator r0 = r0.descendingIterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0034
        L_0x0032:
            r0 = r3
            r1 = r0
        L_0x0034:
            int[] r7 = r13.zzaae
            int r7 = r7.length
            int r7 = r7 + -3
        L_0x0039:
            if (r7 < 0) goto L_0x0511
            int r8 = r13.zzai(r7)
            int[] r9 = r13.zzaae
            r9 = r9[r7]
        L_0x0043:
            if (r1 == 0) goto L_0x0061
            com.google.android.gms.internal.fitness.zzgo<?> r10 = r13.zzaab
            int r10 = r10.zza(r1)
            if (r10 <= r9) goto L_0x0061
            com.google.android.gms.internal.fitness.zzgo<?> r10 = r13.zzaab
            r10.zza(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005f
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0043
        L_0x005f:
            r1 = r3
            goto L_0x0043
        L_0x0061:
            r10 = r8 & r2
            int r10 = r10 >>> 20
            switch(r10) {
                case 0: goto L_0x04fe;
                case 1: goto L_0x04ee;
                case 2: goto L_0x04de;
                case 3: goto L_0x04ce;
                case 4: goto L_0x04be;
                case 5: goto L_0x04ae;
                case 6: goto L_0x049e;
                case 7: goto L_0x048d;
                case 8: goto L_0x047c;
                case 9: goto L_0x0467;
                case 10: goto L_0x0454;
                case 11: goto L_0x0443;
                case 12: goto L_0x0432;
                case 13: goto L_0x0421;
                case 14: goto L_0x0410;
                case 15: goto L_0x03ff;
                case 16: goto L_0x03ee;
                case 17: goto L_0x03d9;
                case 18: goto L_0x03c8;
                case 19: goto L_0x03b7;
                case 20: goto L_0x03a6;
                case 21: goto L_0x0395;
                case 22: goto L_0x0384;
                case 23: goto L_0x0373;
                case 24: goto L_0x0362;
                case 25: goto L_0x0351;
                case 26: goto L_0x0340;
                case 27: goto L_0x032b;
                case 28: goto L_0x031a;
                case 29: goto L_0x0309;
                case 30: goto L_0x02f8;
                case 31: goto L_0x02e7;
                case 32: goto L_0x02d6;
                case 33: goto L_0x02c5;
                case 34: goto L_0x02b4;
                case 35: goto L_0x02a3;
                case 36: goto L_0x0292;
                case 37: goto L_0x0281;
                case 38: goto L_0x0270;
                case 39: goto L_0x025f;
                case 40: goto L_0x024e;
                case 41: goto L_0x023d;
                case 42: goto L_0x022c;
                case 43: goto L_0x021b;
                case 44: goto L_0x020a;
                case 45: goto L_0x01f9;
                case 46: goto L_0x01e8;
                case 47: goto L_0x01d7;
                case 48: goto L_0x01c6;
                case 49: goto L_0x01b1;
                case 50: goto L_0x01a6;
                case 51: goto L_0x0195;
                case 52: goto L_0x0184;
                case 53: goto L_0x0173;
                case 54: goto L_0x0162;
                case 55: goto L_0x0151;
                case 56: goto L_0x0140;
                case 57: goto L_0x012f;
                case 58: goto L_0x011e;
                case 59: goto L_0x010d;
                case 60: goto L_0x00f8;
                case 61: goto L_0x00e5;
                case 62: goto L_0x00d4;
                case 63: goto L_0x00c3;
                case 64: goto L_0x00b2;
                case 65: goto L_0x00a1;
                case 66: goto L_0x0090;
                case 67: goto L_0x007f;
                case 68: goto L_0x006a;
                default: goto L_0x0068;
            }
        L_0x0068:
            goto L_0x050d
        L_0x006a:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            com.google.android.gms.internal.fitness.zzja r10 = r13.zzag(r7)
            r15.zzb((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.fitness.zzja) r10)
            goto L_0x050d
        L_0x007f:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            r15.zzb((int) r9, (long) r10)
            goto L_0x050d
        L_0x0090:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zzg(r9, r8)
            goto L_0x050d
        L_0x00a1:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x050d
        L_0x00b2:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zzo(r9, r8)
            goto L_0x050d
        L_0x00c3:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zzp(r9, r8)
            goto L_0x050d
        L_0x00d4:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x050d
        L_0x00e5:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            com.google.android.gms.internal.fitness.zzfx r8 = (com.google.android.gms.internal.fitness.zzfx) r8
            r15.zza((int) r9, (com.google.android.gms.internal.fitness.zzfx) r8)
            goto L_0x050d
        L_0x00f8:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            com.google.android.gms.internal.fitness.zzja r10 = r13.zzag(r7)
            r15.zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.fitness.zzja) r10)
            goto L_0x050d
        L_0x010d:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.fitness.zzkm) r15)
            goto L_0x050d
        L_0x011e:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = zzi(r14, r10)
            r15.zza((int) r9, (boolean) r8)
            goto L_0x050d
        L_0x012f:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zzh(r9, r8)
            goto L_0x050d
        L_0x0140:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x050d
        L_0x0151:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zze(r9, r8)
            goto L_0x050d
        L_0x0162:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            r15.zza((int) r9, (long) r10)
            goto L_0x050d
        L_0x0173:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x050d
        L_0x0184:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = zzf(r14, r10)
            r15.zza((int) r9, (float) r8)
            goto L_0x050d
        L_0x0195:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = zze(r14, r10)
            r15.zza((int) r9, (double) r10)
            goto L_0x050d
        L_0x01a6:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            r13.zza(r15, r9, r8, r7)
            goto L_0x050d
        L_0x01b1:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzja r10 = r13.zzag(r7)
            com.google.android.gms.internal.fitness.zzjc.zzb((int) r9, (java.util.List<?>) r8, (com.google.android.gms.internal.fitness.zzkm) r15, (com.google.android.gms.internal.fitness.zzja) r10)
            goto L_0x050d
        L_0x01c6:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zze(r9, r8, r15, r4)
            goto L_0x050d
        L_0x01d7:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzj(r9, r8, r15, r4)
            goto L_0x050d
        L_0x01e8:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzg(r9, r8, r15, r4)
            goto L_0x050d
        L_0x01f9:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzl(r9, r8, r15, r4)
            goto L_0x050d
        L_0x020a:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzm(r9, r8, r15, r4)
            goto L_0x050d
        L_0x021b:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzi(r9, r8, r15, r4)
            goto L_0x050d
        L_0x022c:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzn(r9, r8, r15, r4)
            goto L_0x050d
        L_0x023d:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzk(r9, r8, r15, r4)
            goto L_0x050d
        L_0x024e:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzf(r9, r8, r15, r4)
            goto L_0x050d
        L_0x025f:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzh(r9, r8, r15, r4)
            goto L_0x050d
        L_0x0270:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzd(r9, r8, r15, r4)
            goto L_0x050d
        L_0x0281:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzc(r9, r8, r15, r4)
            goto L_0x050d
        L_0x0292:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzb((int) r9, (java.util.List<java.lang.Float>) r8, (com.google.android.gms.internal.fitness.zzkm) r15, (boolean) r4)
            goto L_0x050d
        L_0x02a3:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zza((int) r9, (java.util.List<java.lang.Double>) r8, (com.google.android.gms.internal.fitness.zzkm) r15, (boolean) r4)
            goto L_0x050d
        L_0x02b4:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zze(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02c5:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzj(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02d6:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzg(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02e7:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzl(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02f8:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzm(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0309:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzi(r9, r8, r15, r5)
            goto L_0x050d
        L_0x031a:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzb(r9, r8, r15)
            goto L_0x050d
        L_0x032b:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzja r10 = r13.zzag(r7)
            com.google.android.gms.internal.fitness.zzjc.zza((int) r9, (java.util.List<?>) r8, (com.google.android.gms.internal.fitness.zzkm) r15, (com.google.android.gms.internal.fitness.zzja) r10)
            goto L_0x050d
        L_0x0340:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zza((int) r9, (java.util.List<java.lang.String>) r8, (com.google.android.gms.internal.fitness.zzkm) r15)
            goto L_0x050d
        L_0x0351:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzn(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0362:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzk(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0373:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzf(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0384:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzh(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0395:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzd(r9, r8, r15, r5)
            goto L_0x050d
        L_0x03a6:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzc(r9, r8, r15, r5)
            goto L_0x050d
        L_0x03b7:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zzb((int) r9, (java.util.List<java.lang.Float>) r8, (com.google.android.gms.internal.fitness.zzkm) r15, (boolean) r5)
            goto L_0x050d
        L_0x03c8:
            int[] r9 = r13.zzaae
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.fitness.zzjc.zza((int) r9, (java.util.List<java.lang.Double>) r8, (com.google.android.gms.internal.fitness.zzkm) r15, (boolean) r5)
            goto L_0x050d
        L_0x03d9:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            com.google.android.gms.internal.fitness.zzja r10 = r13.zzag(r7)
            r15.zzb((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.fitness.zzja) r10)
            goto L_0x050d
        L_0x03ee:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.fitness.zzjy.zzk(r14, r10)
            r15.zzb((int) r9, (long) r10)
            goto L_0x050d
        L_0x03ff:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.fitness.zzjy.zzj(r14, r10)
            r15.zzg(r9, r8)
            goto L_0x050d
        L_0x0410:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.fitness.zzjy.zzk(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x050d
        L_0x0421:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.fitness.zzjy.zzj(r14, r10)
            r15.zzo(r9, r8)
            goto L_0x050d
        L_0x0432:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.fitness.zzjy.zzj(r14, r10)
            r15.zzp(r9, r8)
            goto L_0x050d
        L_0x0443:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.fitness.zzjy.zzj(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x050d
        L_0x0454:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            com.google.android.gms.internal.fitness.zzfx r8 = (com.google.android.gms.internal.fitness.zzfx) r8
            r15.zza((int) r9, (com.google.android.gms.internal.fitness.zzfx) r8)
            goto L_0x050d
        L_0x0467:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            com.google.android.gms.internal.fitness.zzja r10 = r13.zzag(r7)
            r15.zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.fitness.zzja) r10)
            goto L_0x050d
        L_0x047c:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r10)
            zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.fitness.zzkm) r15)
            goto L_0x050d
        L_0x048d:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.fitness.zzjy.zzl(r14, r10)
            r15.zza((int) r9, (boolean) r8)
            goto L_0x050d
        L_0x049e:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.fitness.zzjy.zzj(r14, r10)
            r15.zzh(r9, r8)
            goto L_0x050d
        L_0x04ae:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.fitness.zzjy.zzk(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x050d
        L_0x04be:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.fitness.zzjy.zzj(r14, r10)
            r15.zze(r9, r8)
            goto L_0x050d
        L_0x04ce:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.fitness.zzjy.zzk(r14, r10)
            r15.zza((int) r9, (long) r10)
            goto L_0x050d
        L_0x04de:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.fitness.zzjy.zzk(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x050d
        L_0x04ee:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.fitness.zzjy.zzm(r14, r10)
            r15.zza((int) r9, (float) r8)
            goto L_0x050d
        L_0x04fe:
            boolean r10 = r13.zzb(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.fitness.zzjy.zzn(r14, r10)
            r15.zza((int) r9, (double) r10)
        L_0x050d:
            int r7 = r7 + -3
            goto L_0x0039
        L_0x0511:
            if (r1 == 0) goto L_0x0528
            com.google.android.gms.internal.fitness.zzgo<?> r14 = r13.zzaab
            r14.zza(r15, r1)
            boolean r14 = r0.hasNext()
            if (r14 == 0) goto L_0x0526
            java.lang.Object r14 = r0.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            r1 = r14
            goto L_0x0511
        L_0x0526:
            r1 = r3
            goto L_0x0511
        L_0x0528:
            return
        L_0x0529:
            boolean r0 = r13.zzaaj
            if (r0 == 0) goto L_0x0a46
            boolean r0 = r13.zzaaa
            if (r0 == 0) goto L_0x054a
            com.google.android.gms.internal.fitness.zzgo<?> r0 = r13.zzaab
            com.google.android.gms.internal.fitness.zzgt r0 = r0.zzc(r14)
            com.google.android.gms.internal.fitness.zzjb<T, java.lang.Object> r1 = r0.zzvb
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x054a
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x054c
        L_0x054a:
            r0 = r3
            r1 = r0
        L_0x054c:
            int[] r7 = r13.zzaae
            int r7 = r7.length
            r8 = r1
            r1 = 0
        L_0x0551:
            if (r1 >= r7) goto L_0x0a29
            int r9 = r13.zzai(r1)
            int[] r10 = r13.zzaae
            r10 = r10[r1]
        L_0x055b:
            if (r8 == 0) goto L_0x0579
            com.google.android.gms.internal.fitness.zzgo<?> r11 = r13.zzaab
            int r11 = r11.zza(r8)
            if (r11 > r10) goto L_0x0579
            com.google.android.gms.internal.fitness.zzgo<?> r11 = r13.zzaab
            r11.zza(r15, r8)
            boolean r8 = r0.hasNext()
            if (r8 == 0) goto L_0x0577
            java.lang.Object r8 = r0.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            goto L_0x055b
        L_0x0577:
            r8 = r3
            goto L_0x055b
        L_0x0579:
            r11 = r9 & r2
            int r11 = r11 >>> 20
            switch(r11) {
                case 0: goto L_0x0a16;
                case 1: goto L_0x0a06;
                case 2: goto L_0x09f6;
                case 3: goto L_0x09e6;
                case 4: goto L_0x09d6;
                case 5: goto L_0x09c6;
                case 6: goto L_0x09b6;
                case 7: goto L_0x09a5;
                case 8: goto L_0x0994;
                case 9: goto L_0x097f;
                case 10: goto L_0x096c;
                case 11: goto L_0x095b;
                case 12: goto L_0x094a;
                case 13: goto L_0x0939;
                case 14: goto L_0x0928;
                case 15: goto L_0x0917;
                case 16: goto L_0x0906;
                case 17: goto L_0x08f1;
                case 18: goto L_0x08e0;
                case 19: goto L_0x08cf;
                case 20: goto L_0x08be;
                case 21: goto L_0x08ad;
                case 22: goto L_0x089c;
                case 23: goto L_0x088b;
                case 24: goto L_0x087a;
                case 25: goto L_0x0869;
                case 26: goto L_0x0858;
                case 27: goto L_0x0843;
                case 28: goto L_0x0832;
                case 29: goto L_0x0821;
                case 30: goto L_0x0810;
                case 31: goto L_0x07ff;
                case 32: goto L_0x07ee;
                case 33: goto L_0x07dd;
                case 34: goto L_0x07cc;
                case 35: goto L_0x07bb;
                case 36: goto L_0x07aa;
                case 37: goto L_0x0799;
                case 38: goto L_0x0788;
                case 39: goto L_0x0777;
                case 40: goto L_0x0766;
                case 41: goto L_0x0755;
                case 42: goto L_0x0744;
                case 43: goto L_0x0733;
                case 44: goto L_0x0722;
                case 45: goto L_0x0711;
                case 46: goto L_0x0700;
                case 47: goto L_0x06ef;
                case 48: goto L_0x06de;
                case 49: goto L_0x06c9;
                case 50: goto L_0x06be;
                case 51: goto L_0x06ad;
                case 52: goto L_0x069c;
                case 53: goto L_0x068b;
                case 54: goto L_0x067a;
                case 55: goto L_0x0669;
                case 56: goto L_0x0658;
                case 57: goto L_0x0647;
                case 58: goto L_0x0636;
                case 59: goto L_0x0625;
                case 60: goto L_0x0610;
                case 61: goto L_0x05fd;
                case 62: goto L_0x05ec;
                case 63: goto L_0x05db;
                case 64: goto L_0x05ca;
                case 65: goto L_0x05b9;
                case 66: goto L_0x05a8;
                case 67: goto L_0x0597;
                case 68: goto L_0x0582;
                default: goto L_0x0580;
            }
        L_0x0580:
            goto L_0x0a25
        L_0x0582:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            com.google.android.gms.internal.fitness.zzja r11 = r13.zzag(r1)
            r15.zzb((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.fitness.zzja) r11)
            goto L_0x0a25
        L_0x0597:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            r15.zzb((int) r10, (long) r11)
            goto L_0x0a25
        L_0x05a8:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zzg(r10, r9)
            goto L_0x0a25
        L_0x05b9:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0a25
        L_0x05ca:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zzo(r10, r9)
            goto L_0x0a25
        L_0x05db:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zzp(r10, r9)
            goto L_0x0a25
        L_0x05ec:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0a25
        L_0x05fd:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            com.google.android.gms.internal.fitness.zzfx r9 = (com.google.android.gms.internal.fitness.zzfx) r9
            r15.zza((int) r10, (com.google.android.gms.internal.fitness.zzfx) r9)
            goto L_0x0a25
        L_0x0610:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            com.google.android.gms.internal.fitness.zzja r11 = r13.zzag(r1)
            r15.zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.fitness.zzja) r11)
            goto L_0x0a25
        L_0x0625:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.fitness.zzkm) r15)
            goto L_0x0a25
        L_0x0636:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = zzi(r14, r11)
            r15.zza((int) r10, (boolean) r9)
            goto L_0x0a25
        L_0x0647:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zzh(r10, r9)
            goto L_0x0a25
        L_0x0658:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0a25
        L_0x0669:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0a25
        L_0x067a:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            r15.zza((int) r10, (long) r11)
            goto L_0x0a25
        L_0x068b:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0a25
        L_0x069c:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = zzf(r14, r11)
            r15.zza((int) r10, (float) r9)
            goto L_0x0a25
        L_0x06ad:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = zze(r14, r11)
            r15.zza((int) r10, (double) r11)
            goto L_0x0a25
        L_0x06be:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            r13.zza(r15, r10, r9, r1)
            goto L_0x0a25
        L_0x06c9:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzja r11 = r13.zzag(r1)
            com.google.android.gms.internal.fitness.zzjc.zzb((int) r10, (java.util.List<?>) r9, (com.google.android.gms.internal.fitness.zzkm) r15, (com.google.android.gms.internal.fitness.zzja) r11)
            goto L_0x0a25
        L_0x06de:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zze(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x06ef:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzj(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0700:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzg(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0711:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzl(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0722:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzm(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0733:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzi(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0744:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzn(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0755:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzk(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0766:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzf(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0777:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzh(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0788:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzd(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x0799:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzc(r10, r9, r15, r4)
            goto L_0x0a25
        L_0x07aa:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzb((int) r10, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.fitness.zzkm) r15, (boolean) r4)
            goto L_0x0a25
        L_0x07bb:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zza((int) r10, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.fitness.zzkm) r15, (boolean) r4)
            goto L_0x0a25
        L_0x07cc:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zze(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x07dd:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzj(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x07ee:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzg(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x07ff:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzl(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x0810:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzm(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x0821:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzi(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x0832:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzb(r10, r9, r15)
            goto L_0x0a25
        L_0x0843:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzja r11 = r13.zzag(r1)
            com.google.android.gms.internal.fitness.zzjc.zza((int) r10, (java.util.List<?>) r9, (com.google.android.gms.internal.fitness.zzkm) r15, (com.google.android.gms.internal.fitness.zzja) r11)
            goto L_0x0a25
        L_0x0858:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zza((int) r10, (java.util.List<java.lang.String>) r9, (com.google.android.gms.internal.fitness.zzkm) r15)
            goto L_0x0a25
        L_0x0869:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzn(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x087a:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzk(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x088b:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzf(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x089c:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzh(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x08ad:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzd(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x08be:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzc(r10, r9, r15, r5)
            goto L_0x0a25
        L_0x08cf:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzb((int) r10, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.fitness.zzkm) r15, (boolean) r5)
            goto L_0x0a25
        L_0x08e0:
            int[] r10 = r13.zzaae
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zza((int) r10, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.fitness.zzkm) r15, (boolean) r5)
            goto L_0x0a25
        L_0x08f1:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            com.google.android.gms.internal.fitness.zzja r11 = r13.zzag(r1)
            r15.zzb((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.fitness.zzja) r11)
            goto L_0x0a25
        L_0x0906:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.fitness.zzjy.zzk(r14, r11)
            r15.zzb((int) r10, (long) r11)
            goto L_0x0a25
        L_0x0917:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.fitness.zzjy.zzj(r14, r11)
            r15.zzg(r10, r9)
            goto L_0x0a25
        L_0x0928:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.fitness.zzjy.zzk(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0a25
        L_0x0939:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.fitness.zzjy.zzj(r14, r11)
            r15.zzo(r10, r9)
            goto L_0x0a25
        L_0x094a:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.fitness.zzjy.zzj(r14, r11)
            r15.zzp(r10, r9)
            goto L_0x0a25
        L_0x095b:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.fitness.zzjy.zzj(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0a25
        L_0x096c:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            com.google.android.gms.internal.fitness.zzfx r9 = (com.google.android.gms.internal.fitness.zzfx) r9
            r15.zza((int) r10, (com.google.android.gms.internal.fitness.zzfx) r9)
            goto L_0x0a25
        L_0x097f:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            com.google.android.gms.internal.fitness.zzja r11 = r13.zzag(r1)
            r15.zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.fitness.zzja) r11)
            goto L_0x0a25
        L_0x0994:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.fitness.zzjy.zzo(r14, r11)
            zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.fitness.zzkm) r15)
            goto L_0x0a25
        L_0x09a5:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.fitness.zzjy.zzl(r14, r11)
            r15.zza((int) r10, (boolean) r9)
            goto L_0x0a25
        L_0x09b6:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.fitness.zzjy.zzj(r14, r11)
            r15.zzh(r10, r9)
            goto L_0x0a25
        L_0x09c6:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.fitness.zzjy.zzk(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0a25
        L_0x09d6:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.fitness.zzjy.zzj(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0a25
        L_0x09e6:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.fitness.zzjy.zzk(r14, r11)
            r15.zza((int) r10, (long) r11)
            goto L_0x0a25
        L_0x09f6:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.fitness.zzjy.zzk(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0a25
        L_0x0a06:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.fitness.zzjy.zzm(r14, r11)
            r15.zza((int) r10, (float) r9)
            goto L_0x0a25
        L_0x0a16:
            boolean r11 = r13.zzb(r14, (int) r1)
            if (r11 == 0) goto L_0x0a25
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.fitness.zzjy.zzn(r14, r11)
            r15.zza((int) r10, (double) r11)
        L_0x0a25:
            int r1 = r1 + 3
            goto L_0x0551
        L_0x0a29:
            if (r8 == 0) goto L_0x0a40
            com.google.android.gms.internal.fitness.zzgo<?> r1 = r13.zzaab
            r1.zza(r15, r8)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0a3e
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r8 = r1
            goto L_0x0a29
        L_0x0a3e:
            r8 = r3
            goto L_0x0a29
        L_0x0a40:
            com.google.android.gms.internal.fitness.zzjs<?, ?> r0 = r13.zzzz
            zza(r0, r14, (com.google.android.gms.internal.fitness.zzkm) r15)
            return
        L_0x0a46:
            r13.zzb(r14, (com.google.android.gms.internal.fitness.zzkm) r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.zzio.zza(java.lang.Object, com.google.android.gms.internal.fitness.zzkm):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:170:0x049a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r18, com.google.android.gms.internal.fitness.zzkm r19) throws java.io.IOException {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            boolean r3 = r0.zzaaa
            if (r3 == 0) goto L_0x0023
            com.google.android.gms.internal.fitness.zzgo<?> r3 = r0.zzaab
            com.google.android.gms.internal.fitness.zzgt r3 = r3.zzc(r1)
            com.google.android.gms.internal.fitness.zzjb<T, java.lang.Object> r5 = r3.zzvb
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x0023
            java.util.Iterator r3 = r3.iterator()
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0025
        L_0x0023:
            r3 = 0
            r5 = 0
        L_0x0025:
            int[] r6 = r0.zzaae
            int r6 = r6.length
            sun.misc.Unsafe r7 = zzaad
            r11 = r5
            r5 = 0
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r12 = 0
        L_0x0030:
            if (r5 >= r6) goto L_0x0497
            int r13 = r0.zzai(r5)
            int[] r14 = r0.zzaae
            r15 = r14[r5]
            r16 = 267386880(0xff00000, float:2.3665827E-29)
            r16 = r13 & r16
            int r4 = r16 >>> 20
            r9 = 17
            if (r4 > r9) goto L_0x0060
            int r9 = r5 + 2
            r9 = r14[r9]
            r14 = 1048575(0xfffff, float:1.469367E-39)
            r8 = r9 & r14
            if (r8 == r10) goto L_0x0056
            r14 = r11
            long r10 = (long) r8
            int r12 = r7.getInt(r1, r10)
            goto L_0x0058
        L_0x0056:
            r14 = r11
            r8 = r10
        L_0x0058:
            int r9 = r9 >>> 20
            r10 = 1
            int r9 = r10 << r9
            r10 = r8
            r11 = r14
            goto L_0x0062
        L_0x0060:
            r14 = r11
            r9 = 0
        L_0x0062:
            if (r11 == 0) goto L_0x0081
            com.google.android.gms.internal.fitness.zzgo<?> r8 = r0.zzaab
            int r8 = r8.zza(r11)
            if (r8 > r15) goto L_0x0081
            com.google.android.gms.internal.fitness.zzgo<?> r8 = r0.zzaab
            r8.zza(r2, r11)
            boolean r8 = r3.hasNext()
            if (r8 == 0) goto L_0x007f
            java.lang.Object r8 = r3.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            r11 = r8
            goto L_0x0062
        L_0x007f:
            r11 = 0
            goto L_0x0062
        L_0x0081:
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r13 & r8
            long r13 = (long) r13
            switch(r4) {
                case 0: goto L_0x0488;
                case 1: goto L_0x047c;
                case 2: goto L_0x0470;
                case 3: goto L_0x0464;
                case 4: goto L_0x0458;
                case 5: goto L_0x044c;
                case 6: goto L_0x0440;
                case 7: goto L_0x0434;
                case 8: goto L_0x0428;
                case 9: goto L_0x0417;
                case 10: goto L_0x0408;
                case 11: goto L_0x03fb;
                case 12: goto L_0x03ee;
                case 13: goto L_0x03e1;
                case 14: goto L_0x03d4;
                case 15: goto L_0x03c7;
                case 16: goto L_0x03ba;
                case 17: goto L_0x03a9;
                case 18: goto L_0x0399;
                case 19: goto L_0x0389;
                case 20: goto L_0x0379;
                case 21: goto L_0x0369;
                case 22: goto L_0x0359;
                case 23: goto L_0x0349;
                case 24: goto L_0x0339;
                case 25: goto L_0x0329;
                case 26: goto L_0x031a;
                case 27: goto L_0x0307;
                case 28: goto L_0x02f8;
                case 29: goto L_0x02e8;
                case 30: goto L_0x02d8;
                case 31: goto L_0x02c8;
                case 32: goto L_0x02b8;
                case 33: goto L_0x02a8;
                case 34: goto L_0x0298;
                case 35: goto L_0x0288;
                case 36: goto L_0x0278;
                case 37: goto L_0x0268;
                case 38: goto L_0x0258;
                case 39: goto L_0x0248;
                case 40: goto L_0x0238;
                case 41: goto L_0x0228;
                case 42: goto L_0x0218;
                case 43: goto L_0x0208;
                case 44: goto L_0x01f8;
                case 45: goto L_0x01e8;
                case 46: goto L_0x01d8;
                case 47: goto L_0x01c8;
                case 48: goto L_0x01b8;
                case 49: goto L_0x01a5;
                case 50: goto L_0x019c;
                case 51: goto L_0x018d;
                case 52: goto L_0x017e;
                case 53: goto L_0x016f;
                case 54: goto L_0x0160;
                case 55: goto L_0x0151;
                case 56: goto L_0x0142;
                case 57: goto L_0x0133;
                case 58: goto L_0x0124;
                case 59: goto L_0x0115;
                case 60: goto L_0x0102;
                case 61: goto L_0x00f2;
                case 62: goto L_0x00e4;
                case 63: goto L_0x00d6;
                case 64: goto L_0x00c8;
                case 65: goto L_0x00ba;
                case 66: goto L_0x00ac;
                case 67: goto L_0x009e;
                case 68: goto L_0x008c;
                default: goto L_0x0089;
            }
        L_0x0089:
            r4 = 0
            goto L_0x0493
        L_0x008c:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            java.lang.Object r4 = r7.getObject(r1, r13)
            com.google.android.gms.internal.fitness.zzja r9 = r0.zzag(r5)
            r2.zzb((int) r15, (java.lang.Object) r4, (com.google.android.gms.internal.fitness.zzja) r9)
            goto L_0x0089
        L_0x009e:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            long r13 = zzh(r1, r13)
            r2.zzb((int) r15, (long) r13)
            goto L_0x0089
        L_0x00ac:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            int r4 = zzg(r1, r13)
            r2.zzg(r15, r4)
            goto L_0x0089
        L_0x00ba:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            long r13 = zzh(r1, r13)
            r2.zzj(r15, r13)
            goto L_0x0089
        L_0x00c8:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            int r4 = zzg(r1, r13)
            r2.zzo(r15, r4)
            goto L_0x0089
        L_0x00d6:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            int r4 = zzg(r1, r13)
            r2.zzp(r15, r4)
            goto L_0x0089
        L_0x00e4:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            int r4 = zzg(r1, r13)
            r2.zzf(r15, r4)
            goto L_0x0089
        L_0x00f2:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            java.lang.Object r4 = r7.getObject(r1, r13)
            com.google.android.gms.internal.fitness.zzfx r4 = (com.google.android.gms.internal.fitness.zzfx) r4
            r2.zza((int) r15, (com.google.android.gms.internal.fitness.zzfx) r4)
            goto L_0x0089
        L_0x0102:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            java.lang.Object r4 = r7.getObject(r1, r13)
            com.google.android.gms.internal.fitness.zzja r9 = r0.zzag(r5)
            r2.zza((int) r15, (java.lang.Object) r4, (com.google.android.gms.internal.fitness.zzja) r9)
            goto L_0x0089
        L_0x0115:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            java.lang.Object r4 = r7.getObject(r1, r13)
            zza((int) r15, (java.lang.Object) r4, (com.google.android.gms.internal.fitness.zzkm) r2)
            goto L_0x0089
        L_0x0124:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            boolean r4 = zzi(r1, r13)
            r2.zza((int) r15, (boolean) r4)
            goto L_0x0089
        L_0x0133:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            int r4 = zzg(r1, r13)
            r2.zzh(r15, r4)
            goto L_0x0089
        L_0x0142:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            long r13 = zzh(r1, r13)
            r2.zzc(r15, r13)
            goto L_0x0089
        L_0x0151:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            int r4 = zzg(r1, r13)
            r2.zze(r15, r4)
            goto L_0x0089
        L_0x0160:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            long r13 = zzh(r1, r13)
            r2.zza((int) r15, (long) r13)
            goto L_0x0089
        L_0x016f:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            long r13 = zzh(r1, r13)
            r2.zzi(r15, r13)
            goto L_0x0089
        L_0x017e:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            float r4 = zzf(r1, r13)
            r2.zza((int) r15, (float) r4)
            goto L_0x0089
        L_0x018d:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x0089
            double r13 = zze(r1, r13)
            r2.zza((int) r15, (double) r13)
            goto L_0x0089
        L_0x019c:
            java.lang.Object r4 = r7.getObject(r1, r13)
            r0.zza(r2, r15, r4, r5)
            goto L_0x0089
        L_0x01a5:
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzja r13 = r0.zzag(r5)
            com.google.android.gms.internal.fitness.zzjc.zzb((int) r4, (java.util.List<?>) r9, (com.google.android.gms.internal.fitness.zzkm) r2, (com.google.android.gms.internal.fitness.zzja) r13)
            goto L_0x0089
        L_0x01b8:
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r15 = 1
            com.google.android.gms.internal.fitness.zzjc.zze(r4, r9, r2, r15)
            goto L_0x0089
        L_0x01c8:
            r15 = 1
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzj(r4, r9, r2, r15)
            goto L_0x0089
        L_0x01d8:
            r15 = 1
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzg(r4, r9, r2, r15)
            goto L_0x0089
        L_0x01e8:
            r15 = 1
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzl(r4, r9, r2, r15)
            goto L_0x0089
        L_0x01f8:
            r15 = 1
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzm(r4, r9, r2, r15)
            goto L_0x0089
        L_0x0208:
            r15 = 1
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzi(r4, r9, r2, r15)
            goto L_0x0089
        L_0x0218:
            r15 = 1
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzn(r4, r9, r2, r15)
            goto L_0x0089
        L_0x0228:
            r15 = 1
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzk(r4, r9, r2, r15)
            goto L_0x0089
        L_0x0238:
            r15 = 1
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzf(r4, r9, r2, r15)
            goto L_0x0089
        L_0x0248:
            r15 = 1
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzh(r4, r9, r2, r15)
            goto L_0x0089
        L_0x0258:
            r15 = 1
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzd(r4, r9, r2, r15)
            goto L_0x0089
        L_0x0268:
            r15 = 1
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzc(r4, r9, r2, r15)
            goto L_0x0089
        L_0x0278:
            r15 = 1
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzb((int) r4, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.fitness.zzkm) r2, (boolean) r15)
            goto L_0x0089
        L_0x0288:
            r15 = 1
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zza((int) r4, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.fitness.zzkm) r2, (boolean) r15)
            goto L_0x0089
        L_0x0298:
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r15 = 0
            com.google.android.gms.internal.fitness.zzjc.zze(r4, r9, r2, r15)
            goto L_0x0089
        L_0x02a8:
            r15 = 0
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzj(r4, r9, r2, r15)
            goto L_0x0089
        L_0x02b8:
            r15 = 0
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzg(r4, r9, r2, r15)
            goto L_0x0089
        L_0x02c8:
            r15 = 0
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzl(r4, r9, r2, r15)
            goto L_0x0089
        L_0x02d8:
            r15 = 0
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzm(r4, r9, r2, r15)
            goto L_0x0089
        L_0x02e8:
            r15 = 0
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzi(r4, r9, r2, r15)
            goto L_0x0089
        L_0x02f8:
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzb(r4, r9, r2)
            goto L_0x0089
        L_0x0307:
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzja r13 = r0.zzag(r5)
            com.google.android.gms.internal.fitness.zzjc.zza((int) r4, (java.util.List<?>) r9, (com.google.android.gms.internal.fitness.zzkm) r2, (com.google.android.gms.internal.fitness.zzja) r13)
            goto L_0x0089
        L_0x031a:
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zza((int) r4, (java.util.List<java.lang.String>) r9, (com.google.android.gms.internal.fitness.zzkm) r2)
            goto L_0x0089
        L_0x0329:
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            r15 = 0
            com.google.android.gms.internal.fitness.zzjc.zzn(r4, r9, r2, r15)
            goto L_0x0089
        L_0x0339:
            r15 = 0
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzk(r4, r9, r2, r15)
            goto L_0x0089
        L_0x0349:
            r15 = 0
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzf(r4, r9, r2, r15)
            goto L_0x0089
        L_0x0359:
            r15 = 0
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzh(r4, r9, r2, r15)
            goto L_0x0089
        L_0x0369:
            r15 = 0
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzd(r4, r9, r2, r15)
            goto L_0x0089
        L_0x0379:
            r15 = 0
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzc(r4, r9, r2, r15)
            goto L_0x0089
        L_0x0389:
            r15 = 0
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zzb((int) r4, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.fitness.zzkm) r2, (boolean) r15)
            goto L_0x0089
        L_0x0399:
            r15 = 0
            int[] r4 = r0.zzaae
            r4 = r4[r5]
            java.lang.Object r9 = r7.getObject(r1, r13)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.fitness.zzjc.zza((int) r4, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.fitness.zzkm) r2, (boolean) r15)
            goto L_0x0089
        L_0x03a9:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            java.lang.Object r9 = r7.getObject(r1, r13)
            com.google.android.gms.internal.fitness.zzja r13 = r0.zzag(r5)
            r2.zzb((int) r15, (java.lang.Object) r9, (com.google.android.gms.internal.fitness.zzja) r13)
            goto L_0x0493
        L_0x03ba:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            long r13 = r7.getLong(r1, r13)
            r2.zzb((int) r15, (long) r13)
            goto L_0x0493
        L_0x03c7:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            int r9 = r7.getInt(r1, r13)
            r2.zzg(r15, r9)
            goto L_0x0493
        L_0x03d4:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            long r13 = r7.getLong(r1, r13)
            r2.zzj(r15, r13)
            goto L_0x0493
        L_0x03e1:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            int r9 = r7.getInt(r1, r13)
            r2.zzo(r15, r9)
            goto L_0x0493
        L_0x03ee:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            int r9 = r7.getInt(r1, r13)
            r2.zzp(r15, r9)
            goto L_0x0493
        L_0x03fb:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            int r9 = r7.getInt(r1, r13)
            r2.zzf(r15, r9)
            goto L_0x0493
        L_0x0408:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            java.lang.Object r9 = r7.getObject(r1, r13)
            com.google.android.gms.internal.fitness.zzfx r9 = (com.google.android.gms.internal.fitness.zzfx) r9
            r2.zza((int) r15, (com.google.android.gms.internal.fitness.zzfx) r9)
            goto L_0x0493
        L_0x0417:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            java.lang.Object r9 = r7.getObject(r1, r13)
            com.google.android.gms.internal.fitness.zzja r13 = r0.zzag(r5)
            r2.zza((int) r15, (java.lang.Object) r9, (com.google.android.gms.internal.fitness.zzja) r13)
            goto L_0x0493
        L_0x0428:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            java.lang.Object r9 = r7.getObject(r1, r13)
            zza((int) r15, (java.lang.Object) r9, (com.google.android.gms.internal.fitness.zzkm) r2)
            goto L_0x0493
        L_0x0434:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            boolean r9 = com.google.android.gms.internal.fitness.zzjy.zzl(r1, r13)
            r2.zza((int) r15, (boolean) r9)
            goto L_0x0493
        L_0x0440:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            int r9 = r7.getInt(r1, r13)
            r2.zzh(r15, r9)
            goto L_0x0493
        L_0x044c:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            long r13 = r7.getLong(r1, r13)
            r2.zzc(r15, r13)
            goto L_0x0493
        L_0x0458:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            int r9 = r7.getInt(r1, r13)
            r2.zze(r15, r9)
            goto L_0x0493
        L_0x0464:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            long r13 = r7.getLong(r1, r13)
            r2.zza((int) r15, (long) r13)
            goto L_0x0493
        L_0x0470:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            long r13 = r7.getLong(r1, r13)
            r2.zzi(r15, r13)
            goto L_0x0493
        L_0x047c:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            float r9 = com.google.android.gms.internal.fitness.zzjy.zzm(r1, r13)
            r2.zza((int) r15, (float) r9)
            goto L_0x0493
        L_0x0488:
            r4 = 0
            r9 = r9 & r12
            if (r9 == 0) goto L_0x0493
            double r13 = com.google.android.gms.internal.fitness.zzjy.zzn(r1, r13)
            r2.zza((int) r15, (double) r13)
        L_0x0493:
            int r5 = r5 + 3
            goto L_0x0030
        L_0x0497:
            r14 = r11
        L_0x0498:
            if (r14 == 0) goto L_0x04af
            com.google.android.gms.internal.fitness.zzgo<?> r4 = r0.zzaab
            r4.zza(r2, r14)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x04ad
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r14 = r4
            goto L_0x0498
        L_0x04ad:
            r14 = 0
            goto L_0x0498
        L_0x04af:
            com.google.android.gms.internal.fitness.zzjs<?, ?> r3 = r0.zzzz
            zza(r3, r1, (com.google.android.gms.internal.fitness.zzkm) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.zzio.zzb(java.lang.Object, com.google.android.gms.internal.fitness.zzkm):void");
    }

    private final <K, V> void zza(zzkm zzkm, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzkm.zza(i, this.zzaaq.zzj(zzah(i2)), this.zzaaq.zzh(obj));
        }
    }

    private static <UT, UB> void zza(zzjs<UT, UB> zzjs, T t, zzkm zzkm) throws IOException {
        zzjs.zza(zzjs.zzo(t), zzkm);
    }

    private final zzja zzag(int i) {
        int i2 = (i / 3) << 1;
        zzja zzja = (zzja) this.zzaaf[i2];
        if (zzja != null) {
            return zzja;
        }
        zzja zze = zziv.zzcy().zze((Class) this.zzaaf[i2 + 1]);
        this.zzaaf[i2] = zze;
        return zze;
    }

    private final Object zzah(int i) {
        return this.zzaaf[(i / 3) << 1];
    }

    public final void zze(T t) {
        int i;
        int i2 = this.zzaam;
        while (true) {
            i = this.zzaan;
            if (i2 >= i) {
                break;
            }
            long zzai = (long) (zzai(this.zzaal[i2]) & 1048575);
            Object zzo = zzjy.zzo(t, zzai);
            if (zzo != null) {
                zzjy.zza((Object) t, zzai, this.zzaaq.zzi(zzo));
            }
            i2++;
        }
        int length = this.zzaal.length;
        while (i < length) {
            this.zzaap.zza(t, (long) this.zzaal[i]);
            i++;
        }
        this.zzzz.zze(t);
        if (this.zzaaa) {
            this.zzaab.zze((Object) t);
        }
    }

    public final boolean zzl(T t) {
        int i;
        int i2;
        T t2 = t;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            boolean z = true;
            if (i5 >= this.zzaam) {
                return !this.zzaaa || this.zzaab.zzc(t2).isInitialized();
            }
            int i6 = this.zzaal[i5];
            int i7 = this.zzaae[i6];
            int zzai = zzai(i6);
            int i8 = this.zzaae[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                i = i9 != 1048575 ? zzaad.getInt(t2, (long) i9) : i4;
                i2 = i9;
            } else {
                i2 = i3;
                i = i4;
            }
            if (((268435456 & zzai) != 0) && !zza(t, i6, i2, i, i10)) {
                return false;
            }
            int i11 = (267386880 & zzai) >>> 20;
            if (i11 != 9 && i11 != 17) {
                if (i11 != 27) {
                    if (i11 == 60 || i11 == 68) {
                        if (zza(t2, i7, i6) && !zza((Object) t2, zzai, zzag(i6))) {
                            return false;
                        }
                    } else if (i11 != 49) {
                        if (i11 != 50) {
                            continue;
                        } else {
                            Map<?, ?> zzh = this.zzaaq.zzh(zzjy.zzo(t2, (long) (zzai & 1048575)));
                            if (!zzh.isEmpty()) {
                                if (this.zzaaq.zzj(zzah(i6)).zzzu.zzdx() == zzkj.MESSAGE) {
                                    zzja<?> zzja = null;
                                    Iterator<?> it = zzh.values().iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        Object next = it.next();
                                        if (zzja == null) {
                                            zzja = zziv.zzcy().zze(next.getClass());
                                        }
                                        if (!zzja.zzl(next)) {
                                            z = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (!z) {
                                return false;
                            }
                        }
                    }
                }
                List list = (List) zzjy.zzo(t2, (long) (zzai & 1048575));
                if (!list.isEmpty()) {
                    zzja zzag = zzag(i6);
                    int i12 = 0;
                    while (true) {
                        if (i12 >= list.size()) {
                            break;
                        } else if (!zzag.zzl(list.get(i12))) {
                            z = false;
                            break;
                        } else {
                            i12++;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (zza(t, i6, i2, i, i10) && !zza((Object) t2, zzai, zzag(i6))) {
                return false;
            }
            i5++;
            i3 = i2;
            i4 = i;
        }
    }

    private static boolean zza(Object obj, int i, zzja zzja) {
        return zzja.zzl(zzjy.zzo(obj, (long) (i & 1048575)));
    }

    private static void zza(int i, Object obj, zzkm zzkm) throws IOException {
        if (obj instanceof String) {
            zzkm.zza(i, (String) obj);
        } else {
            zzkm.zza(i, (zzfx) obj);
        }
    }

    private final int zzai(int i) {
        return this.zzaae[i + 1];
    }

    private final int zzaj(int i) {
        return this.zzaae[i + 2];
    }

    private static <T> double zze(T t, long j) {
        return ((Double) zzjy.zzo(t, j)).doubleValue();
    }

    private static <T> float zzf(T t, long j) {
        return ((Float) zzjy.zzo(t, j)).floatValue();
    }

    private static <T> int zzg(T t, long j) {
        return ((Integer) zzjy.zzo(t, j)).intValue();
    }

    private static <T> long zzh(T t, long j) {
        return ((Long) zzjy.zzo(t, j)).longValue();
    }

    private static <T> boolean zzi(T t, long j) {
        return ((Boolean) zzjy.zzo(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zzb(t, i) == zzb(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzb(t, i);
        }
        return (i3 & i4) != 0;
    }

    private final boolean zzb(T t, int i) {
        int zzaj = zzaj(i);
        long j = (long) (zzaj & 1048575);
        if (j == 1048575) {
            int zzai = zzai(i);
            long j2 = (long) (zzai & 1048575);
            switch ((zzai & 267386880) >>> 20) {
                case 0:
                    return zzjy.zzn(t, j2) != Utils.DOUBLE_EPSILON;
                case 1:
                    return zzjy.zzm(t, j2) != 0.0f;
                case 2:
                    return zzjy.zzk(t, j2) != 0;
                case 3:
                    return zzjy.zzk(t, j2) != 0;
                case 4:
                    return zzjy.zzj(t, j2) != 0;
                case 5:
                    return zzjy.zzk(t, j2) != 0;
                case 6:
                    return zzjy.zzj(t, j2) != 0;
                case 7:
                    return zzjy.zzl(t, j2);
                case 8:
                    Object zzo = zzjy.zzo(t, j2);
                    if (zzo instanceof String) {
                        return !((String) zzo).isEmpty();
                    }
                    if (zzo instanceof zzfx) {
                        return !zzfx.zzub.equals(zzo);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzjy.zzo(t, j2) != null;
                case 10:
                    return !zzfx.zzub.equals(zzjy.zzo(t, j2));
                case 11:
                    return zzjy.zzj(t, j2) != 0;
                case 12:
                    return zzjy.zzj(t, j2) != 0;
                case 13:
                    return zzjy.zzj(t, j2) != 0;
                case 14:
                    return zzjy.zzk(t, j2) != 0;
                case 15:
                    return zzjy.zzj(t, j2) != 0;
                case 16:
                    return zzjy.zzk(t, j2) != 0;
                case 17:
                    return zzjy.zzo(t, j2) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            return (zzjy.zzj(t, j) & (1 << (zzaj >>> 20))) != 0;
        }
    }

    private final void zzc(T t, int i) {
        int zzaj = zzaj(i);
        long j = (long) (1048575 & zzaj);
        if (j != 1048575) {
            zzjy.zza((Object) t, j, (1 << (zzaj >>> 20)) | zzjy.zzj(t, j));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzjy.zzj(t, (long) (zzaj(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzjy.zza((Object) t, (long) (zzaj(i2) & 1048575), i);
    }
}
