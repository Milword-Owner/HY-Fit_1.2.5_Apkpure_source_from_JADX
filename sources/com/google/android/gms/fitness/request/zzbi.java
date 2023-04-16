package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;

@ShowFirstParty
@SafeParcelable.Class(creator = "SubscribeRequestCreator")
@SafeParcelable.Reserved({4, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzbi extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbi> CREATOR = new zzbl();
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 3, type = "android.os.IBinder")
    @Nullable
    private final zzcn zzql;
    @SafeParcelable.Field(getter = "getSubscription", mo19514id = 1)
    @Nullable
    private Subscription zzss;
    @SafeParcelable.Field(getter = "isServerOnly", mo19514id = 2)
    private final boolean zzst;

    @SafeParcelable.Constructor
    zzbi(@SafeParcelable.Param(mo19517id = 1) @Nullable Subscription subscription, @SafeParcelable.Param(mo19517id = 2) boolean z, @SafeParcelable.Param(mo19517id = 3) @Nullable IBinder iBinder) {
        zzcn zzcn;
        this.zzss = subscription;
        this.zzst = z;
        if (iBinder == null) {
            zzcn = null;
        } else {
            zzcn = zzcm.zzj(iBinder);
        }
        this.zzql = zzcn;
    }

    public zzbi(@Nullable Subscription subscription, boolean z, @Nullable zzcn zzcn) {
        this.zzss = subscription;
        this.zzst = false;
        this.zzql = zzcn;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("subscription", this.zzss).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzss, i, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzst);
        zzcn zzcn = this.zzql;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcn == null ? null : zzcn.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
