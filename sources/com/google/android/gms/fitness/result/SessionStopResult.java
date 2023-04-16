package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Session;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "SessionStopResultCreator")
@SafeParcelable.Reserved({4, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class SessionStopResult extends AbstractSafeParcelable implements Result {
    @RecentlyNonNull
    public static final Parcelable.Creator<SessionStopResult> CREATOR = new zzi();
    @SafeParcelable.Field(getter = "getSessions", mo19514id = 3)
    private final List<Session> zzqr;
    @SafeParcelable.Field(getter = "getStatus", mo19514id = 2)
    private final Status zzsv;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public SessionStopResult(@RecentlyNonNull @SafeParcelable.Param(mo19517id = 2) Status status, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 3) List<Session> list) {
        this.zzsv = status;
        this.zzqr = Collections.unmodifiableList(list);
    }

    @RecentlyNonNull
    public Status getStatus() {
        return this.zzsv;
    }

    @RecentlyNonNull
    public List<Session> getSessions() {
        return this.zzqr;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionStopResult)) {
            return false;
        }
        SessionStopResult sessionStopResult = (SessionStopResult) obj;
        return this.zzsv.equals(sessionStopResult.zzsv) && Objects.equal(this.zzqr, sessionStopResult.zzqr);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzsv, this.zzqr);
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzsv).add("sessions", this.zzqr).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 3, getSessions(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
