package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzcn;

@ShowFirstParty
@SafeParcelable.Class(creator = "StopBleScanRequestCreator")
@SafeParcelable.Reserved({3, 1000})
@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzbg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbg> CREATOR = new zzbj();
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 2, type = "android.os.IBinder")
    @Nullable
    private final zzcn zzql;
    @SafeParcelable.Field(getter = "getBleScanCallbackBinder", mo19514id = 1, type = "android.os.IBinder")
    private final zzad zzsn;

    /* JADX WARNING: type inference failed for: r1v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
    /* Code decompiled incorrectly, please refer to instructions dump. */
    zzbg(@com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo19517id = 1) android.os.IBinder r4, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo19517id = 2) @androidx.annotation.Nullable android.os.IBinder r5) {
        /*
            r3 = this;
            r3.<init>()
            r0 = 0
            if (r4 != 0) goto L_0x0008
            r4 = r0
            goto L_0x001c
        L_0x0008:
            java.lang.String r1 = "com.google.android.gms.fitness.request.IBleScanCallback"
            android.os.IInterface r1 = r4.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.fitness.request.zzad
            if (r2 == 0) goto L_0x0016
            r4 = r1
            com.google.android.gms.fitness.request.zzad r4 = (com.google.android.gms.fitness.request.zzad) r4
            goto L_0x001c
        L_0x0016:
            com.google.android.gms.fitness.request.zzaf r1 = new com.google.android.gms.fitness.request.zzaf
            r1.<init>(r4)
            r4 = r1
        L_0x001c:
            r3.zzsn = r4
            if (r5 != 0) goto L_0x0021
            goto L_0x0025
        L_0x0021:
            com.google.android.gms.internal.fitness.zzcn r0 = com.google.android.gms.internal.fitness.zzcm.zzj(r5)
        L_0x0025:
            r3.zzql = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.zzbg.<init>(android.os.IBinder, android.os.IBinder):void");
    }

    public zzbg(zzad zzad, @Nullable zzcn zzcn) {
        this.zzsn = zzad;
        this.zzql = zzcn;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzsn.asBinder(), false);
        zzcn zzcn = this.zzql;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcn == null ? null : zzcn.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
