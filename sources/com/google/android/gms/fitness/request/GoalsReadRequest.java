package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzbm;
import com.google.android.gms.internal.fitness.zzbn;
import com.google.android.gms.internal.fitness.zzh;
import com.google.android.gms.internal.fitness.zzko;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "GoalsReadRequestCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class GoalsReadRequest extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<GoalsReadRequest> CREATOR = new zzae();
    @SafeParcelable.Field(getter = "getDataTypes", mo19514id = 2, type = "java.util.List")
    private final List<DataType> zzlf;
    @SafeParcelable.Field(getter = "getActivities", mo19514id = 4, type = "java.util.List")
    private final List<Integer> zzno;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 1, type = "android.os.IBinder")
    @Nullable
    private final zzbn zzrl;
    @SafeParcelable.Field(getter = "getObjectiveTypeList", mo19514id = 3, type = "java.util.List")
    private final List<Integer> zzrm;

    @RecentlyNonNull
    public List<DataType> getDataTypes() {
        return this.zzlf;
    }

    @RecentlyNullable
    public List<Integer> getObjectiveTypes() {
        if (this.zzrm.isEmpty()) {
            return null;
        }
        return this.zzrm;
    }

    @RecentlyNullable
    public List<String> getActivityNames() {
        if (this.zzno.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Integer intValue : this.zzno) {
            arrayList.add(zzko.getName(intValue.intValue()));
        }
        return arrayList;
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class Builder {
        /* access modifiers changed from: private */
        public final List<DataType> zzlf = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Integer> zzno = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Integer> zzrm = new ArrayList();

        @RecentlyNonNull
        public Builder addDataType(@RecentlyNonNull DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            if (!this.zzlf.contains(dataType)) {
                this.zzlf.add(dataType);
            }
            return this;
        }

        @RecentlyNonNull
        public Builder addActivity(@RecentlyNonNull String str) {
            int zzo = zzko.zzo(str);
            Preconditions.checkState(zzo != 4, "Attempting to add an unknown activity");
            zzh.zza(Integer.valueOf(zzo), this.zzno);
            return this;
        }

        @RecentlyNonNull
        public Builder addObjectiveType(int i) {
            boolean z = true;
            if (!(i == 1 || i == 2 || i == 3)) {
                z = false;
            }
            Preconditions.checkState(z, "Attempting to add an invalid objective type");
            if (!this.zzrm.contains(Integer.valueOf(i))) {
                this.zzrm.add(Integer.valueOf(i));
            }
            return this;
        }

        @RecentlyNonNull
        public GoalsReadRequest build() {
            Preconditions.checkState(!this.zzlf.isEmpty(), "At least one data type should be specified.");
            return new GoalsReadRequest(this);
        }
    }

    @SafeParcelable.Constructor
    GoalsReadRequest(@SafeParcelable.Param(mo19517id = 1) @Nullable IBinder iBinder, @SafeParcelable.Param(mo19517id = 2) List<DataType> list, @SafeParcelable.Param(mo19517id = 3) List<Integer> list2, @SafeParcelable.Param(mo19517id = 4) List<Integer> list3) {
        zzbn zzbn;
        if (iBinder == null) {
            zzbn = null;
        } else {
            zzbn = zzbm.zzf(iBinder);
        }
        this.zzrl = zzbn;
        this.zzlf = list;
        this.zzrm = list2;
        this.zzno = list3;
    }

    private GoalsReadRequest(Builder builder) {
        this((zzbn) null, (List<DataType>) builder.zzlf, (List<Integer>) builder.zzrm, (List<Integer>) builder.zzno);
    }

    public GoalsReadRequest(GoalsReadRequest goalsReadRequest, zzbn zzbn) {
        this(zzbn, goalsReadRequest.getDataTypes(), goalsReadRequest.zzrm, goalsReadRequest.zzno);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    private GoalsReadRequest(@Nullable zzbn zzbn, List<DataType> list, List<Integer> list2, List<Integer> list3) {
        this(zzbn == null ? null : zzbn.asBinder(), list, list2, list3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GoalsReadRequest)) {
            return false;
        }
        GoalsReadRequest goalsReadRequest = (GoalsReadRequest) obj;
        return Objects.equal(this.zzlf, goalsReadRequest.zzlf) && Objects.equal(this.zzrm, goalsReadRequest.zzrm) && Objects.equal(this.zzno, goalsReadRequest.zzno);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzlf, this.zzrm, getActivityNames());
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.zzlf).add("objectiveTypes", this.zzrm).add("activities", getActivityNames()).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        zzbn zzbn = this.zzrl;
        SafeParcelWriter.writeIBinder(parcel, 1, zzbn == null ? null : zzbn.asBinder(), false);
        SafeParcelWriter.writeList(parcel, 2, getDataTypes(), false);
        SafeParcelWriter.writeList(parcel, 3, this.zzrm, false);
        SafeParcelWriter.writeList(parcel, 4, this.zzno, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
