package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;

@ShowFirstParty
@SafeParcelable.Class(creator = "SessionUnregistrationRequestCreator")
@SafeParcelable.Reserved({3, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzbc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbc> CREATOR = new zzbe();
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 2, type = "android.os.IBinder")
    @Nullable
    private final zzcn zzql;
    @SafeParcelable.Field(getter = "getIntent", mo19514id = 1)
    private final PendingIntent zzrk;

    @SafeParcelable.Constructor
    zzbc(@SafeParcelable.Param(mo19517id = 1) PendingIntent pendingIntent, @SafeParcelable.Param(mo19517id = 2) @Nullable IBinder iBinder) {
        zzcn zzcn;
        this.zzrk = pendingIntent;
        if (iBinder == null) {
            zzcn = null;
        } else {
            zzcn = zzcm.zzj(iBinder);
        }
        this.zzql = zzcn;
    }

    public zzbc(PendingIntent pendingIntent, @Nullable zzcn zzcn) {
        this.zzrk = pendingIntent;
        this.zzql = zzcn;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzrk, i, false);
        zzcn zzcn = this.zzql;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcn == null ? null : zzcn.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(BaseGmsClient.KEY_PENDING_INTENT, this.zzrk).toString();
    }

    public final boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof zzbc) && Objects.equal(this.zzrk, ((zzbc) obj).zzrk);
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzrk);
    }
}
