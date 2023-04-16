package com.google.android.gms.internal.fitness;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "FitnessDataSourcesRequestCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzer extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzer> CREATOR = new zzeq();
    @SafeParcelable.Field(getter = "getDataTypes", mo19514id = 1)
    private final List<DataType> zzlf;

    @SafeParcelable.Constructor
    public zzer(@SafeParcelable.Param(mo19517id = 1) List<DataType> list) {
        this.zzlf = list;
    }

    public final List<DataType> getDataTypes() {
        return Collections.unmodifiableList(this.zzlf);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.zzlf).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, Collections.unmodifiableList(this.zzlf), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
