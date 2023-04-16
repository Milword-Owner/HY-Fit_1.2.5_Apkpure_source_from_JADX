package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SessionDataSetCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzae extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzae> CREATOR = new zzag();
    @SafeParcelable.Field(getter = "getSession", mo19514id = 1)
    private final Session zzky;
    @SafeParcelable.Field(getter = "getDataSet", mo19514id = 2)
    private final DataSet zzls;

    @SafeParcelable.Constructor
    public zzae(@SafeParcelable.Param(mo19517id = 1) Session session, @SafeParcelable.Param(mo19517id = 2) DataSet dataSet) {
        this.zzky = session;
        this.zzls = dataSet;
    }

    public final Session getSession() {
        return this.zzky;
    }

    public final DataSet getDataSet() {
        return this.zzls;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzae)) {
            return false;
        }
        zzae zzae = (zzae) obj;
        return Objects.equal(this.zzky, zzae.zzky) && Objects.equal(this.zzls, zzae.zzls);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzky, this.zzls);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("session", this.zzky).add("dataSet", this.zzls).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzky, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzls, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
