package com.google.android.gms.fitness.request;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzn implements Parcelable.Creator<DataReadRequest> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new DataReadRequest[i];
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r26) {
        /*
            r25 = this;
            r0 = r26
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r26)
            r2 = 0
            r4 = 0
            r5 = 0
            r9 = r2
            r11 = r9
            r16 = r11
            r7 = r5
            r8 = r7
            r13 = r8
            r14 = r13
            r18 = r14
            r22 = r18
            r23 = r22
            r24 = r23
            r15 = 0
            r19 = 0
            r20 = 0
            r21 = 0
        L_0x0021:
            int r2 = r26.dataPosition()
            if (r2 >= r1) goto L_0x008f
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r26)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 1: goto L_0x0088;
                case 2: goto L_0x0081;
                case 3: goto L_0x007c;
                case 4: goto L_0x0077;
                case 5: goto L_0x0070;
                case 6: goto L_0x0069;
                case 7: goto L_0x0064;
                case 8: goto L_0x005f;
                case 9: goto L_0x0054;
                case 10: goto L_0x004f;
                case 11: goto L_0x0032;
                case 12: goto L_0x004a;
                case 13: goto L_0x0045;
                case 14: goto L_0x0040;
                case 15: goto L_0x0032;
                case 16: goto L_0x0032;
                case 17: goto L_0x0032;
                case 18: goto L_0x003b;
                case 19: goto L_0x0036;
                default: goto L_0x0032;
            }
        L_0x0032:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0021
        L_0x0036:
            java.util.ArrayList r24 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createLongList(r0, r2)
            goto L_0x0021
        L_0x003b:
            java.util.ArrayList r23 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createLongList(r0, r2)
            goto L_0x0021
        L_0x0040:
            android.os.IBinder r22 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r0, r2)
            goto L_0x0021
        L_0x0045:
            boolean r21 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0021
        L_0x004a:
            boolean r20 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0021
        L_0x004f:
            int r19 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0021
        L_0x0054:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.DataSource> r3 = com.google.android.gms.fitness.data.DataSource.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r18 = r2
            com.google.android.gms.fitness.data.DataSource r18 = (com.google.android.gms.fitness.data.DataSource) r18
            goto L_0x0021
        L_0x005f:
            long r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0021
        L_0x0064:
            int r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0021
        L_0x0069:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.DataSource> r3 = com.google.android.gms.fitness.data.DataSource.CREATOR
            java.util.ArrayList r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r3)
            goto L_0x0021
        L_0x0070:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.DataType> r3 = com.google.android.gms.fitness.data.DataType.CREATOR
            java.util.ArrayList r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r3)
            goto L_0x0021
        L_0x0077:
            long r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0021
        L_0x007c:
            long r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0021
        L_0x0081:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.DataSource> r3 = com.google.android.gms.fitness.data.DataSource.CREATOR
            java.util.ArrayList r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r3)
            goto L_0x0021
        L_0x0088:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.DataType> r3 = com.google.android.gms.fitness.data.DataType.CREATOR
            java.util.ArrayList r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r3)
            goto L_0x0021
        L_0x008f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.fitness.request.DataReadRequest r0 = new com.google.android.gms.fitness.request.DataReadRequest
            r6 = r0
            r6.<init>((java.util.List<com.google.android.gms.fitness.data.DataType>) r7, (java.util.List<com.google.android.gms.fitness.data.DataSource>) r8, (long) r9, (long) r11, (java.util.List<com.google.android.gms.fitness.data.DataType>) r13, (java.util.List<com.google.android.gms.fitness.data.DataSource>) r14, (int) r15, (long) r16, (com.google.android.gms.fitness.data.DataSource) r18, (int) r19, (boolean) r20, (boolean) r21, (android.os.IBinder) r22, (java.util.List<java.lang.Long>) r23, (java.util.List<java.lang.Long>) r24)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.zzn.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
