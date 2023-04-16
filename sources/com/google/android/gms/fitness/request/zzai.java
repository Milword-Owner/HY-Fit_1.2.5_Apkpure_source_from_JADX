package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzem;
import com.google.android.gms.internal.fitness.zzep;

@ShowFirstParty
@SafeParcelable.Class(creator = "ListClaimedBleDevicesRequestCreator")
@SafeParcelable.Reserved({2, 1000})
@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzai extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzai> CREATOR = new zzah();
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 1, type = "android.os.IBinder")
    private final zzem zzrn;

    @SafeParcelable.Constructor
    zzai(@SafeParcelable.Param(mo19517id = 1) IBinder iBinder) {
        this.zzrn = zzep.zzk(iBinder);
    }

    public zzai(zzem zzem) {
        this.zzrn = zzem;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzrn.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
