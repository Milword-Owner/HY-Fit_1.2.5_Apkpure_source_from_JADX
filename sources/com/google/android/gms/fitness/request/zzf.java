package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzba;
import com.google.android.gms.internal.fitness.zzbb;

@ShowFirstParty
@SafeParcelable.Class(creator = "DailyTotalRequestCreator")
@SafeParcelable.Reserved({3, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzf> CREATOR = new zzh();
    @SafeParcelable.Field(getter = "getDataType", mo19514id = 2)
    @Nullable
    private final DataType zzkp;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 1, type = "android.os.IBinder")
    private final zzbb zzqo;
    @SafeParcelable.Field(getter = "getLocalDataOnly", mo19514id = 4)
    private final boolean zzqp;

    @SafeParcelable.Constructor
    zzf(@SafeParcelable.Param(mo19517id = 1) IBinder iBinder, @SafeParcelable.Param(mo19517id = 2) @Nullable DataType dataType, @SafeParcelable.Param(mo19517id = 4) boolean z) {
        this.zzqo = zzba.zzb(iBinder);
        this.zzkp = dataType;
        this.zzqp = z;
    }

    public zzf(zzbb zzbb, @Nullable DataType dataType, boolean z) {
        this.zzqo = zzbb;
        this.zzkp = dataType;
        this.zzqp = z;
    }

    public final String toString() {
        Object[] objArr = new Object[1];
        DataType dataType = this.zzkp;
        objArr[0] = dataType == null ? "null" : dataType.zzm();
        return String.format("DailyTotalRequest{%s}", objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzqo.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzkp, i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzqp);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
