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
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;

@ShowFirstParty
@SafeParcelable.Class(creator = "UnsubscribeRequestCreator")
@SafeParcelable.Reserved({4, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzbm extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbm> CREATOR = new zzbo();
    @SafeParcelable.Field(getter = "getDataType", mo19514id = 1)
    @Nullable
    private final DataType zzkp;
    @SafeParcelable.Field(getter = "getDataSource", mo19514id = 2)
    @Nullable
    private final DataSource zzkq;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 3, type = "android.os.IBinder")
    @Nullable
    private final zzcn zzql;

    @SafeParcelable.Constructor
    zzbm(@SafeParcelable.Param(mo19517id = 1) DataType dataType, @SafeParcelable.Param(mo19517id = 2) DataSource dataSource, @SafeParcelable.Param(mo19517id = 3) IBinder iBinder) {
        this(dataType, dataSource, zzcm.zzj(iBinder));
    }

    public zzbm(@Nullable DataType dataType, @Nullable DataSource dataSource, @Nullable zzcn zzcn) {
        Preconditions.checkArgument((dataType == null) == (dataSource == null) ? false : true, "Must specify exactly one of dataType and dataSource.");
        this.zzkp = dataType;
        this.zzkq = dataSource;
        this.zzql = zzcn;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzkp, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzkq, i, false);
        zzcn zzcn = this.zzql;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcn == null ? null : zzcn.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbm)) {
            return false;
        }
        zzbm zzbm = (zzbm) obj;
        return Objects.equal(this.zzkq, zzbm.zzkq) && Objects.equal(this.zzkp, zzbm.zzkp);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzkq, this.zzkp);
    }
}
