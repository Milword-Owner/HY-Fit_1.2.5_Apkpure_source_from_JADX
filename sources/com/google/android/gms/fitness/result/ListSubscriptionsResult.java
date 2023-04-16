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
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "ListSubscriptionsResultCreator")
@SafeParcelable.Reserved({3, 1000})
@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class ListSubscriptionsResult extends AbstractSafeParcelable implements Result {
    @RecentlyNonNull
    public static final Parcelable.Creator<ListSubscriptionsResult> CREATOR = new zzg();
    @SafeParcelable.Field(getter = "getStatus", mo19514id = 2)
    private final Status zzsv;
    @SafeParcelable.Field(getter = "getSubscriptions", mo19514id = 1)
    private final List<Subscription> zzsz;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public ListSubscriptionsResult(@RecentlyNonNull @SafeParcelable.Param(mo19517id = 1) List<Subscription> list, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 2) Status status) {
        this.zzsz = list;
        this.zzsv = status;
    }

    @RecentlyNonNull
    @ShowFirstParty
    public static ListSubscriptionsResult zzd(@RecentlyNonNull Status status) {
        return new ListSubscriptionsResult(Collections.emptyList(), status);
    }

    @RecentlyNonNull
    public List<Subscription> getSubscriptions() {
        return this.zzsz;
    }

    @RecentlyNonNull
    public List<Subscription> getSubscriptions(@RecentlyNonNull DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (Subscription next : this.zzsz) {
            if (dataType.equals(next.zzs())) {
                arrayList.add(next);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @RecentlyNonNull
    public Status getStatus() {
        return this.zzsv;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ListSubscriptionsResult)) {
            return false;
        }
        ListSubscriptionsResult listSubscriptionsResult = (ListSubscriptionsResult) obj;
        return this.zzsv.equals(listSubscriptionsResult.zzsv) && Objects.equal(this.zzsz, listSubscriptionsResult.zzsz);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzsv, this.zzsz);
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzsv).add("subscriptions", this.zzsz).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getSubscriptions(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
