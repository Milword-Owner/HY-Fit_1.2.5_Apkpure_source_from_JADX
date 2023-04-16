package com.google.android.gms.internal.fitness;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;

@SafeParcelable.Class(creator = "FitnessUnregistrationRequestCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzet extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzet> CREATOR = new zzes();
    @SafeParcelable.Field(getter = "getDataSource", mo19514id = 1)
    private final DataSource zzkq;

    @SafeParcelable.Constructor
    public zzet(@SafeParcelable.Param(mo19517id = 1) DataSource dataSource) {
        this.zzkq = dataSource;
    }

    public final DataSource getDataSource() {
        return this.zzkq;
    }

    public final String toString() {
        return String.format("ApplicationUnregistrationRequest{%s}", new Object[]{this.zzkq});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzkq, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
