package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.zzu;
import com.google.android.gms.fitness.data.zzv;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;

@ShowFirstParty
@SafeParcelable.Class(creator = "SensorUnregistrationRequestCreator")
@SafeParcelable.Reserved({4, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzar extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzar> CREATOR = new zzas();
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 3, type = "android.os.IBinder")
    @Nullable
    private final zzcn zzql;
    @SafeParcelable.Field(getter = "getIntent", mo19514id = 2)
    @Nullable
    private final PendingIntent zzrk;
    @SafeParcelable.Field(getter = "getListenerBinder", mo19514id = 1, type = "android.os.IBinder")
    @Nullable
    private final zzv zzrt;

    @SafeParcelable.Constructor
    zzar(@SafeParcelable.Param(mo19517id = 1) @Nullable IBinder iBinder, @SafeParcelable.Param(mo19517id = 2) @Nullable PendingIntent pendingIntent, @SafeParcelable.Param(mo19517id = 3) @Nullable IBinder iBinder2) {
        zzv zzv;
        zzcn zzcn = null;
        if (iBinder == null) {
            zzv = null;
        } else {
            zzv = zzu.zza(iBinder);
        }
        this.zzrt = zzv;
        this.zzrk = pendingIntent;
        this.zzql = iBinder2 != null ? zzcm.zzj(iBinder2) : zzcn;
    }

    public zzar(@Nullable zzv zzv, @Nullable PendingIntent pendingIntent, @Nullable zzcn zzcn) {
        this.zzrt = zzv;
        this.zzrk = pendingIntent;
        this.zzql = zzcn;
    }

    public final String toString() {
        return String.format("SensorUnregistrationRequest{%s}", new Object[]{this.zzrt});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        zzv zzv = this.zzrt;
        IBinder iBinder = null;
        SafeParcelWriter.writeIBinder(parcel, 1, zzv == null ? null : zzv.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzrk, i, false);
        zzcn zzcn = this.zzql;
        if (zzcn != null) {
            iBinder = zzcn.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 3, iBinder, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
