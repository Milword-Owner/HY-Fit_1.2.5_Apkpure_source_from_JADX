package com.google.android.gms.fitness.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.zzu;
import com.google.android.gms.fitness.data.zzv;
import java.util.concurrent.TimeUnit;

@SafeParcelable.Class(creator = "FitnessSensorServiceRequestCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class FitnessSensorServiceRequest extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<FitnessSensorServiceRequest> CREATOR = new zzb();
    public static final int UNSPECIFIED = -1;
    @SafeParcelable.Field(getter = "getDataSource", mo19514id = 1)
    private final DataSource zzkq;
    @SafeParcelable.Field(getter = "getListenerBinder", mo19514id = 2, type = "android.os.IBinder")
    private final zzv zzrt;
    @SafeParcelable.Field(getter = "getSamplingRateMicros", mo19514id = 3)
    private final long zztd;
    @SafeParcelable.Field(getter = "getBatchIntervalMicros", mo19514id = 4)
    private final long zzte;

    @SafeParcelable.Constructor
    FitnessSensorServiceRequest(@SafeParcelable.Param(mo19517id = 1) DataSource dataSource, @SafeParcelable.Param(mo19517id = 2) IBinder iBinder, @SafeParcelable.Param(mo19517id = 3) long j, @SafeParcelable.Param(mo19517id = 4) long j2) {
        this.zzkq = dataSource;
        this.zzrt = zzu.zza(iBinder);
        this.zztd = j;
        this.zzte = j2;
    }

    @RecentlyNonNull
    public DataSource getDataSource() {
        return this.zzkq;
    }

    @RecentlyNonNull
    public SensorEventDispatcher getDispatcher() {
        return new zzc(this.zzrt);
    }

    public long getSamplingRate(@RecentlyNonNull TimeUnit timeUnit) {
        long j = this.zztd;
        if (j == -1) {
            return -1;
        }
        return timeUnit.convert(j, TimeUnit.MICROSECONDS);
    }

    public long getBatchInterval(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzte, TimeUnit.MICROSECONDS);
    }

    @RecentlyNonNull
    public String toString() {
        return String.format("FitnessSensorServiceRequest{%s}", new Object[]{this.zzkq});
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzrt.asBinder(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.zztd);
        SafeParcelWriter.writeLong(parcel, 4, this.zzte);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FitnessSensorServiceRequest)) {
            return false;
        }
        FitnessSensorServiceRequest fitnessSensorServiceRequest = (FitnessSensorServiceRequest) obj;
        return Objects.equal(this.zzkq, fitnessSensorServiceRequest.zzkq) && this.zztd == fitnessSensorServiceRequest.zztd && this.zzte == fitnessSensorServiceRequest.zzte;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzkq, Long.valueOf(this.zztd), Long.valueOf(this.zzte));
    }
}
