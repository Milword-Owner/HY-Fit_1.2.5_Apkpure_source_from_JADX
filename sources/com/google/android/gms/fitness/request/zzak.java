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
import com.google.android.gms.internal.fitness.zzcc;
import com.google.android.gms.internal.fitness.zzcf;

@ShowFirstParty
@SafeParcelable.Class(creator = "ListSubscriptionsRequestCreator")
@SafeParcelable.Reserved({3, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzak extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzak> CREATOR = new zzaj();
    @SafeParcelable.Field(getter = "getDataType", mo19514id = 1)
    @Nullable
    private final DataType zzkp;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 2, type = "android.os.IBinder")
    @Nullable
    private final zzcc zzro;

    @SafeParcelable.Constructor
    zzak(@SafeParcelable.Param(mo19517id = 1) @Nullable DataType dataType, @SafeParcelable.Param(mo19517id = 2) @Nullable IBinder iBinder) {
        zzcc zzcc;
        this.zzkp = dataType;
        if (iBinder == null) {
            zzcc = null;
        } else {
            zzcc = zzcf.zzg(iBinder);
        }
        this.zzro = zzcc;
    }

    public zzak(@Nullable DataType dataType, zzcc zzcc) {
        this.zzkp = dataType;
        this.zzro = zzcc;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzkp, i, false);
        zzcc zzcc = this.zzro;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcc == null ? null : zzcc.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
