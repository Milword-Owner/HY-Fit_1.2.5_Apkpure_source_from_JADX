package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzbg;
import com.google.android.gms.internal.fitness.zzbh;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SafeParcelable.Class(creator = "DataSourcesRequestCreator")
@SafeParcelable.Reserved({5, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class DataSourcesRequest extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<DataSourcesRequest> CREATOR = new zzp();
    @SafeParcelable.Field(getter = "getDataTypes", mo19514id = 1)
    private final List<DataType> zzlf;
    @SafeParcelable.Field(getter = "getDataSourceTypes", mo19514id = 2)
    private final List<Integer> zzrg;
    @SafeParcelable.Field(getter = "includeDbOnlySources", mo19514id = 3)
    private final boolean zzrh;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 4, type = "android.os.IBinder")
    @Nullable
    private final zzbh zzri;

    @SafeParcelable.Constructor
    DataSourcesRequest(@SafeParcelable.Param(mo19517id = 1) List<DataType> list, @SafeParcelable.Param(mo19517id = 2) List<Integer> list2, @SafeParcelable.Param(mo19517id = 3) boolean z, @SafeParcelable.Param(mo19517id = 4) @Nullable IBinder iBinder) {
        zzbh zzbh;
        this.zzlf = list;
        this.zzrg = list2;
        this.zzrh = z;
        if (iBinder == null) {
            zzbh = null;
        } else {
            zzbh = zzbg.zzd(iBinder);
        }
        this.zzri = zzbh;
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class Builder {
        /* access modifiers changed from: private */
        public List<DataType> zzlf = new ArrayList();
        /* access modifiers changed from: private */
        public List<Integer> zzrg = Arrays.asList(new Integer[]{0, 1});
        private boolean zzrh = false;

        @RecentlyNonNull
        public Builder setDataTypes(@RecentlyNonNull DataType... dataTypeArr) {
            this.zzlf = Arrays.asList(dataTypeArr);
            return this;
        }

        @RecentlyNonNull
        public Builder setDataSourceTypes(@RecentlyNonNull int... iArr) {
            this.zzrg = new ArrayList();
            for (int valueOf : iArr) {
                this.zzrg.add(Integer.valueOf(valueOf));
            }
            return this;
        }

        @RecentlyNonNull
        public DataSourcesRequest build() {
            Preconditions.checkState(!this.zzlf.isEmpty(), "Must add at least one data type");
            Preconditions.checkState(!this.zzrg.isEmpty(), "Must add at least one data source type");
            return new DataSourcesRequest(this);
        }
    }

    private DataSourcesRequest(Builder builder) {
        this((List<DataType>) builder.zzlf, (List<Integer>) builder.zzrg, false, (zzbh) null);
    }

    public DataSourcesRequest(DataSourcesRequest dataSourcesRequest, zzbh zzbh) {
        this(dataSourcesRequest.zzlf, dataSourcesRequest.zzrg, dataSourcesRequest.zzrh, zzbh);
    }

    private DataSourcesRequest(List<DataType> list, List<Integer> list2, boolean z, @Nullable zzbh zzbh) {
        this.zzlf = list;
        this.zzrg = list2;
        this.zzrh = z;
        this.zzri = zzbh;
    }

    @RecentlyNonNull
    public List<DataType> getDataTypes() {
        return this.zzlf;
    }

    @RecentlyNonNull
    public String toString() {
        Objects.ToStringHelper add = Objects.toStringHelper(this).add("dataTypes", this.zzlf).add("sourceTypes", this.zzrg);
        if (this.zzrh) {
            add.add("includeDbOnlySources", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        }
        return add.toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        SafeParcelWriter.writeIntegerList(parcel, 2, this.zzrg, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzrh);
        zzbh zzbh = this.zzri;
        SafeParcelWriter.writeIBinder(parcel, 4, zzbh == null ? null : zzbh.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
