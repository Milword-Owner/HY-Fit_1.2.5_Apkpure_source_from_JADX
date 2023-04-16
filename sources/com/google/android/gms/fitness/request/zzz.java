package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;

@ShowFirstParty
@SafeParcelable.Class(creator = "DisableFitRequestCreator")
@SafeParcelable.Reserved({2, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzz extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzz> CREATOR = new zzab();
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 1, type = "android.os.IBinder")
    private final zzcn zzql;

    @SafeParcelable.Constructor
    zzz(@SafeParcelable.Param(mo19517id = 1) IBinder iBinder) {
        this.zzql = zzcm.zzj(iBinder);
    }

    public zzz(zzcn zzcn) {
        this.zzql = zzcn;
    }

    public final String toString() {
        return String.format("DisableFitRequest", new Object[0]);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzql.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
