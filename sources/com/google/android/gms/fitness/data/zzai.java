package com.google.android.gms.fitness.data;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzai implements Parcelable.Creator<Subscription> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new Subscription[i];
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r13) {
        /*
            r12 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r13)
            r1 = 0
            r2 = 0
            r3 = 0
            r6 = r2
            r7 = r6
            r8 = r3
            r10 = 0
            r11 = 0
        L_0x000d:
            int r1 = r13.dataPosition()
            if (r1 >= r0) goto L_0x0051
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r13)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            r3 = 1
            if (r2 == r3) goto L_0x0047
            r3 = 2
            if (r2 == r3) goto L_0x003d
            r3 = 3
            if (r2 == r3) goto L_0x0038
            r3 = 4
            if (r2 == r3) goto L_0x0033
            r3 = 5
            if (r2 == r3) goto L_0x002e
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r13, r1)
            goto L_0x000d
        L_0x002e:
            int r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r13, r1)
            goto L_0x000d
        L_0x0033:
            int r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r13, r1)
            goto L_0x000d
        L_0x0038:
            long r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r13, r1)
            goto L_0x000d
        L_0x003d:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.DataType> r2 = com.google.android.gms.fitness.data.DataType.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r1, r2)
            r7 = r1
            com.google.android.gms.fitness.data.DataType r7 = (com.google.android.gms.fitness.data.DataType) r7
            goto L_0x000d
        L_0x0047:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.DataSource> r2 = com.google.android.gms.fitness.data.DataSource.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r1, r2)
            r6 = r1
            com.google.android.gms.fitness.data.DataSource r6 = (com.google.android.gms.fitness.data.DataSource) r6
            goto L_0x000d
        L_0x0051:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r13, r0)
            com.google.android.gms.fitness.data.Subscription r13 = new com.google.android.gms.fitness.data.Subscription
            r5 = r13
            r5.<init>(r6, r7, r8, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.zzai.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
