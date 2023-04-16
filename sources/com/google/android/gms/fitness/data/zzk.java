package com.google.android.gms.fitness.data;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzk implements Parcelable.Creator<DataSource> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new DataSource[i];
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r10)
            r1 = 0
            r2 = 0
            r4 = r1
            r6 = r4
            r7 = r6
            r8 = r7
            r5 = 0
        L_0x000b:
            int r1 = r10.dataPosition()
            if (r1 >= r0) goto L_0x0054
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r10)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            r3 = 1
            if (r2 == r3) goto L_0x004a
            r3 = 3
            if (r2 == r3) goto L_0x0045
            r3 = 4
            if (r2 == r3) goto L_0x003b
            r3 = 5
            if (r2 == r3) goto L_0x0031
            r3 = 6
            if (r2 == r3) goto L_0x002c
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r10, r1)
            goto L_0x000b
        L_0x002c:
            java.lang.String r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r1)
            goto L_0x000b
        L_0x0031:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.zza> r2 = com.google.android.gms.fitness.data.zza.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r1, r2)
            r7 = r1
            com.google.android.gms.fitness.data.zza r7 = (com.google.android.gms.fitness.data.zza) r7
            goto L_0x000b
        L_0x003b:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.Device> r2 = com.google.android.gms.fitness.data.Device.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r1, r2)
            r6 = r1
            com.google.android.gms.fitness.data.Device r6 = (com.google.android.gms.fitness.data.Device) r6
            goto L_0x000b
        L_0x0045:
            int r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r10, r1)
            goto L_0x000b
        L_0x004a:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.DataType> r2 = com.google.android.gms.fitness.data.DataType.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r1, r2)
            r4 = r1
            com.google.android.gms.fitness.data.DataType r4 = (com.google.android.gms.fitness.data.DataType) r4
            goto L_0x000b
        L_0x0054:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r10, r0)
            com.google.android.gms.fitness.data.DataSource r10 = new com.google.android.gms.fitness.data.DataSource
            r3 = r10
            r3.<init>(r4, r5, r6, r7, r8)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.zzk.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
