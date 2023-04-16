package com.google.android.gms.fitness.data;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzg implements Parcelable.Creator<DataPoint> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new DataPoint[i];
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r15) {
        /*
            r14 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r15)
            r1 = 0
            r3 = 0
            r6 = r1
            r8 = r6
            r12 = r8
            r5 = r3
            r10 = r5
            r11 = r10
        L_0x000d:
            int r1 = r15.dataPosition()
            if (r1 >= r0) goto L_0x005e
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r15)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            r3 = 1
            if (r2 == r3) goto L_0x0054
            r3 = 3
            if (r2 == r3) goto L_0x004f
            r3 = 4
            if (r2 == r3) goto L_0x004a
            r3 = 5
            if (r2 == r3) goto L_0x0040
            r3 = 6
            if (r2 == r3) goto L_0x0036
            r3 = 7
            if (r2 == r3) goto L_0x0031
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r15, r1)
            goto L_0x000d
        L_0x0031:
            long r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r15, r1)
            goto L_0x000d
        L_0x0036:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.DataSource> r2 = com.google.android.gms.fitness.data.DataSource.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r11 = r1
            com.google.android.gms.fitness.data.DataSource r11 = (com.google.android.gms.fitness.data.DataSource) r11
            goto L_0x000d
        L_0x0040:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.Value> r2 = com.google.android.gms.fitness.data.Value.CREATOR
            java.lang.Object[] r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedArray(r15, r1, r2)
            r10 = r1
            com.google.android.gms.fitness.data.Value[] r10 = (com.google.android.gms.fitness.data.Value[]) r10
            goto L_0x000d
        L_0x004a:
            long r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r15, r1)
            goto L_0x000d
        L_0x004f:
            long r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r15, r1)
            goto L_0x000d
        L_0x0054:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.DataSource> r2 = com.google.android.gms.fitness.data.DataSource.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r5 = r1
            com.google.android.gms.fitness.data.DataSource r5 = (com.google.android.gms.fitness.data.DataSource) r5
            goto L_0x000d
        L_0x005e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r15, r0)
            com.google.android.gms.fitness.data.DataPoint r15 = new com.google.android.gms.fitness.data.DataPoint
            r4 = r15
            r4.<init>(r5, r6, r8, r10, r11, r12)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.zzg.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
