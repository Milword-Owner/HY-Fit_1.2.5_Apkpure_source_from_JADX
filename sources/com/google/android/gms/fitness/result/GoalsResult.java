package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Goal;
import java.util.List;

@SafeParcelable.Class(creator = "GoalsResultCreator")
@SafeParcelable.Reserved({1000})
@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class GoalsResult extends AbstractSafeParcelable implements Result {
    @RecentlyNonNull
    public static final Parcelable.Creator<GoalsResult> CREATOR = new zzf();
    @SafeParcelable.Field(getter = "getStatus", mo19514id = 1)
    private final Status zzsv;
    @SafeParcelable.Field(getter = "getGoals", mo19514id = 2)
    private final List<Goal> zzsy;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public GoalsResult(@RecentlyNonNull @SafeParcelable.Param(mo19517id = 1) Status status, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 2) List<Goal> list) {
        this.zzsv = status;
        this.zzsy = list;
    }

    @RecentlyNonNull
    public List<Goal> getGoals() {
        return this.zzsy;
    }

    @RecentlyNonNull
    public Status getStatus() {
        return this.zzsv;
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, getGoals(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
