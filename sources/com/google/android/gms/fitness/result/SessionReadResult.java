package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.zzae;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "SessionReadResultCreator")
@SafeParcelable.Reserved({4, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class SessionReadResult extends AbstractSafeParcelable implements Result {
    @RecentlyNonNull
    public static final Parcelable.Creator<SessionReadResult> CREATOR = new zzh();
    @SafeParcelable.Field(getter = "getSessions", mo19514id = 1)
    private final List<Session> zzqr;
    @SafeParcelable.Field(getter = "getStatus", mo19514id = 3)
    private final Status zzsv;
    @SafeParcelable.Field(getter = "getSessionDataSets", mo19514id = 2)
    private final List<zzae> zzta;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public SessionReadResult(@RecentlyNonNull @SafeParcelable.Param(mo19517id = 1) List<Session> list, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 2) List<zzae> list2, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 3) Status status) {
        this.zzqr = list;
        this.zzta = Collections.unmodifiableList(list2);
        this.zzsv = status;
    }

    @RecentlyNonNull
    @ShowFirstParty
    public static SessionReadResult zze(@RecentlyNonNull Status status) {
        return new SessionReadResult(new ArrayList(), new ArrayList(), status);
    }

    @RecentlyNonNull
    public List<Session> getSessions() {
        return this.zzqr;
    }

    @RecentlyNonNull
    public List<DataSet> getDataSet(@RecentlyNonNull Session session, @RecentlyNonNull DataType dataType) {
        Preconditions.checkArgument(this.zzqr.contains(session), "Attempting to read data for session %s which was not returned", session);
        ArrayList arrayList = new ArrayList();
        for (zzae next : this.zzta) {
            if (Objects.equal(session, next.getSession()) && dataType.equals(next.getDataSet().getDataType())) {
                arrayList.add(next.getDataSet());
            }
        }
        return arrayList;
    }

    @RecentlyNonNull
    public List<DataSet> getDataSet(@RecentlyNonNull Session session) {
        Preconditions.checkArgument(this.zzqr.contains(session), "Attempting to read data for session %s which was not returned", session);
        ArrayList arrayList = new ArrayList();
        for (zzae next : this.zzta) {
            if (Objects.equal(session, next.getSession())) {
                arrayList.add(next.getDataSet());
            }
        }
        return arrayList;
    }

    @RecentlyNonNull
    public Status getStatus() {
        return this.zzsv;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionReadResult)) {
            return false;
        }
        SessionReadResult sessionReadResult = (SessionReadResult) obj;
        return this.zzsv.equals(sessionReadResult.zzsv) && Objects.equal(this.zzqr, sessionReadResult.zzqr) && Objects.equal(this.zzta, sessionReadResult.zzta);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzsv, this.zzqr, this.zzta);
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzsv).add("sessions", this.zzqr).add("sessionDataSets", this.zzta).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getSessions(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzta, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
