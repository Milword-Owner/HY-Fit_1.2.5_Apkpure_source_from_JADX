package com.google.android.gms.fitness.data;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzaa implements Parcelable.Creator<RawDataPoint> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new RawDataPoint[i];
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r17) {
        /*
            r16 = this;
            r0 = r17
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r17)
            r2 = 0
            r3 = 0
            r5 = 0
            r7 = r3
            r9 = r7
            r14 = r9
            r11 = r5
            r12 = 0
            r13 = 0
        L_0x0010:
            int r2 = r17.dataPosition()
            if (r2 >= r1) goto L_0x0048
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r17)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 1: goto L_0x0043;
                case 2: goto L_0x003e;
                case 3: goto L_0x0034;
                case 4: goto L_0x002f;
                case 5: goto L_0x002a;
                case 6: goto L_0x0025;
                default: goto L_0x0021;
            }
        L_0x0021:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0010
        L_0x0025:
            long r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0010
        L_0x002a:
            int r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0010
        L_0x002f:
            int r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0010
        L_0x0034:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.Value> r3 = com.google.android.gms.fitness.data.Value.CREATOR
            java.lang.Object[] r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedArray(r0, r2, r3)
            r11 = r2
            com.google.android.gms.fitness.data.Value[] r11 = (com.google.android.gms.fitness.data.Value[]) r11
            goto L_0x0010
        L_0x003e:
            long r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0010
        L_0x0043:
            long r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0010
        L_0x0048:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.fitness.data.RawDataPoint r0 = new com.google.android.gms.fitness.data.RawDataPoint
            r6 = r0
            r6.<init>(r7, r9, r11, r12, r13, r14)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.zzaa.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
