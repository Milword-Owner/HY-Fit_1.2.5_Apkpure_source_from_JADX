package com.google.android.gms.fitness.data;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzz implements Parcelable.Creator<RawBucket> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new RawBucket[i];
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r15) {
        /*
            r14 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r15)
            r1 = 0
            r2 = 0
            r3 = 0
            r10 = r2
            r12 = r10
            r6 = r3
            r8 = r6
            r11 = 0
            r13 = 0
        L_0x000e:
            int r1 = r15.dataPosition()
            if (r1 >= r0) goto L_0x0048
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r15)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 1: goto L_0x0043;
                case 2: goto L_0x003e;
                case 3: goto L_0x0034;
                case 4: goto L_0x002f;
                case 5: goto L_0x0028;
                case 6: goto L_0x0023;
                default: goto L_0x001f;
            }
        L_0x001f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r15, r1)
            goto L_0x000e
        L_0x0023:
            int r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r15, r1)
            goto L_0x000e
        L_0x0028:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.RawDataSet> r2 = com.google.android.gms.fitness.data.RawDataSet.CREATOR
            java.util.ArrayList r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r15, r1, r2)
            goto L_0x000e
        L_0x002f:
            int r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r15, r1)
            goto L_0x000e
        L_0x0034:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.Session> r2 = com.google.android.gms.fitness.data.Session.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r10 = r1
            com.google.android.gms.fitness.data.Session r10 = (com.google.android.gms.fitness.data.Session) r10
            goto L_0x000e
        L_0x003e:
            long r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r15, r1)
            goto L_0x000e
        L_0x0043:
            long r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r15, r1)
            goto L_0x000e
        L_0x0048:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r15, r0)
            com.google.android.gms.fitness.data.RawBucket r15 = new com.google.android.gms.fitness.data.RawBucket
            r5 = r15
            r5.<init>(r6, r8, r10, r11, r12, r13)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.zzz.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
