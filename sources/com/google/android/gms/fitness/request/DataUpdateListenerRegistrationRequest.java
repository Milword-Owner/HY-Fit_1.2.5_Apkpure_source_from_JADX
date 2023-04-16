package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;

@SafeParcelable.Class(creator = "DataUpdateListenerRegistrationRequestCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class DataUpdateListenerRegistrationRequest extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<DataUpdateListenerRegistrationRequest> CREATOR = new zzw();
    @SafeParcelable.Field(getter = "getDataType", mo19514id = 2)
    @Nullable
    private final DataType zzkp;
    @SafeParcelable.Field(getter = "getDataSource", mo19514id = 1)
    @Nullable
    private final DataSource zzkq;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 4, type = "android.os.IBinder")
    @Nullable
    private final zzcn zzql;
    @SafeParcelable.Field(getter = "getIntent", mo19514id = 3)
    @Nullable
    private final PendingIntent zzrk;

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class Builder {
        /* access modifiers changed from: private */
        public DataType zzkp;
        /* access modifiers changed from: private */
        public DataSource zzkq;
        /* access modifiers changed from: private */
        public PendingIntent zzrk;

        @RecentlyNonNull
        public Builder setDataSource(@RecentlyNonNull DataSource dataSource) {
            Preconditions.checkNotNull(dataSource);
            this.zzkq = dataSource;
            return this;
        }

        @RecentlyNonNull
        public Builder setDataType(@RecentlyNonNull DataType dataType) {
            Preconditions.checkNotNull(dataType);
            this.zzkp = dataType;
            return this;
        }

        @RecentlyNonNull
        public Builder setPendingIntent(@RecentlyNonNull PendingIntent pendingIntent) {
            Preconditions.checkNotNull(pendingIntent);
            this.zzrk = pendingIntent;
            return this;
        }

        @RecentlyNonNull
        public DataUpdateListenerRegistrationRequest build() {
            Preconditions.checkState((this.zzkq == null && this.zzkp == null) ? false : true, "Set either dataSource or dataTYpe");
            Preconditions.checkNotNull(this.zzrk, "pendingIntent must be set");
            return new DataUpdateListenerRegistrationRequest(this);
        }
    }

    @SafeParcelable.Constructor
    public DataUpdateListenerRegistrationRequest(@SafeParcelable.Param(mo19517id = 1) @Nullable DataSource dataSource, @SafeParcelable.Param(mo19517id = 2) @Nullable DataType dataType, @SafeParcelable.Param(mo19517id = 3) @Nullable PendingIntent pendingIntent, @SafeParcelable.Param(mo19517id = 4) @Nullable IBinder iBinder) {
        zzcn zzcn;
        this.zzkq = dataSource;
        this.zzkp = dataType;
        this.zzrk = pendingIntent;
        if (iBinder == null) {
            zzcn = null;
        } else {
            zzcn = zzcm.zzj(iBinder);
        }
        this.zzql = zzcn;
    }

    public DataUpdateListenerRegistrationRequest(@RecentlyNonNull DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest, @Nullable IBinder iBinder) {
        this(dataUpdateListenerRegistrationRequest.zzkq, dataUpdateListenerRegistrationRequest.zzkp, dataUpdateListenerRegistrationRequest.zzrk, iBinder);
    }

    private DataUpdateListenerRegistrationRequest(Builder builder) {
        this(builder.zzkq, builder.zzkp, builder.zzrk, (IBinder) null);
    }

    @RecentlyNullable
    public DataSource getDataSource() {
        return this.zzkq;
    }

    @RecentlyNullable
    public DataType getDataType() {
        return this.zzkp;
    }

    @RecentlyNullable
    public PendingIntent getIntent() {
        return this.zzrk;
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("dataSource", this.zzkq).add("dataType", this.zzkp).add(BaseGmsClient.KEY_PENDING_INTENT, this.zzrk).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getDataType(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getIntent(), i, false);
        zzcn zzcn = this.zzql;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcn == null ? null : zzcn.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataUpdateListenerRegistrationRequest)) {
            return false;
        }
        DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest = (DataUpdateListenerRegistrationRequest) obj;
        return Objects.equal(this.zzkq, dataUpdateListenerRegistrationRequest.zzkq) && Objects.equal(this.zzkp, dataUpdateListenerRegistrationRequest.zzkp) && Objects.equal(this.zzrk, dataUpdateListenerRegistrationRequest.zzrk);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzkq, this.zzkp, this.zzrk);
    }
}
