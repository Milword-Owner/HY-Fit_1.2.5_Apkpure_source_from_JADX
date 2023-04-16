package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
import java.util.concurrent.TimeUnit;

@ShowFirstParty
@SafeParcelable.Class(creator = "SessionStartRequestCreator")
@SafeParcelable.Reserved({3, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzay extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzay> CREATOR = new zzbb();
    @SafeParcelable.Field(getter = "getSession", mo19514id = 1)
    private final Session zzky;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 2, type = "android.os.IBinder")
    @Nullable
    private final zzcn zzql;

    @SafeParcelable.Constructor
    zzay(@SafeParcelable.Param(mo19517id = 1) Session session, @SafeParcelable.Param(mo19517id = 2) @Nullable IBinder iBinder) {
        zzcn zzcn;
        this.zzky = session;
        if (iBinder == null) {
            zzcn = null;
        } else {
            zzcn = zzcm.zzj(iBinder);
        }
        this.zzql = zzcn;
    }

    public zzay(Session session, @Nullable zzcn zzcn) {
        Preconditions.checkArgument(session.getStartTime(TimeUnit.MILLISECONDS) <= System.currentTimeMillis(), "Cannot start a session in the future");
        Preconditions.checkArgument(session.isOngoing(), "Cannot start a session which has already ended");
        this.zzky = session;
        this.zzql = zzcn;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != this) {
            return (obj instanceof zzay) && Objects.equal(this.zzky, ((zzay) obj).zzky);
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzky);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("session", this.zzky).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzky, i, false);
        zzcn zzcn = this.zzql;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcn == null ? null : zzcn.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
