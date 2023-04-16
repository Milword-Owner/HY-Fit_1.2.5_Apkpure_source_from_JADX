package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzh;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@ShowFirstParty
@KeepName
@SafeParcelable.Class(creator = "RawDataPointCreator")
@SafeParcelable.Reserved({1000, 7})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class RawDataPoint extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<RawDataPoint> CREATOR = new zzaa();
    @SafeParcelable.Field(getter = "getStartTimeNanos", mo19514id = 2)
    private final long zzlk;
    @SafeParcelable.Field(getter = "getValues", mo19514id = 3)
    private final Value[] zzll;
    @SafeParcelable.Field(getter = "getRawTimestamp", mo19514id = 6)
    private final long zzln;
    @SafeParcelable.Field(getter = "getEndTimeNanos", mo19514id = 1)
    private final long zznz;
    @SafeParcelable.Field(getter = "getDataSourceIndex", mo19514id = 4)
    private final int zzoa;
    @SafeParcelable.Field(getter = "getOriginalDataSourceIndex", mo19514id = 5)
    private final int zzob;

    @SafeParcelable.Constructor
    public RawDataPoint(@SafeParcelable.Param(mo19517id = 1) long j, @SafeParcelable.Param(mo19517id = 2) long j2, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 3) Value[] valueArr, @SafeParcelable.Param(mo19517id = 4) int i, @SafeParcelable.Param(mo19517id = 5) int i2, @SafeParcelable.Param(mo19517id = 6) long j3) {
        this.zznz = j;
        this.zzlk = j2;
        this.zzoa = i;
        this.zzob = i2;
        this.zzln = j3;
        this.zzll = valueArr;
    }

    RawDataPoint(DataPoint dataPoint, List<DataSource> list) {
        this.zznz = dataPoint.getTimestamp(TimeUnit.NANOSECONDS);
        this.zzlk = dataPoint.getStartTime(TimeUnit.NANOSECONDS);
        this.zzll = dataPoint.zze();
        this.zzoa = zzh.zza(dataPoint.getDataSource(), list);
        this.zzob = zzh.zza(dataPoint.zzf(), list);
        this.zzln = dataPoint.zzg();
    }

    public final long zzn() {
        return this.zznz;
    }

    public final long zzo() {
        return this.zzlk;
    }

    @RecentlyNonNull
    public final Value[] zze() {
        return this.zzll;
    }

    public final int zzp() {
        return this.zzoa;
    }

    public final int zzq() {
        return this.zzob;
    }

    public final long zzg() {
        return this.zzln;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawDataPoint)) {
            return false;
        }
        RawDataPoint rawDataPoint = (RawDataPoint) obj;
        return this.zznz == rawDataPoint.zznz && this.zzlk == rawDataPoint.zzlk && Arrays.equals(this.zzll, rawDataPoint.zzll) && this.zzoa == rawDataPoint.zzoa && this.zzob == rawDataPoint.zzob && this.zzln == rawDataPoint.zzln;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zznz), Long.valueOf(this.zzlk));
    }

    @RecentlyNonNull
    public final String toString() {
        return String.format(Locale.US, "RawDataPoint{%s@[%s, %s](%d,%d)}", new Object[]{Arrays.toString(this.zzll), Long.valueOf(this.zzlk), Long.valueOf(this.zznz), Integer.valueOf(this.zzoa), Integer.valueOf(this.zzob)});
    }

    public final void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zznz);
        SafeParcelWriter.writeLong(parcel, 2, this.zzlk);
        SafeParcelWriter.writeTypedArray(parcel, 3, this.zzll, i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzoa);
        SafeParcelWriter.writeInt(parcel, 5, this.zzob);
        SafeParcelWriter.writeLong(parcel, 6, this.zzln);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
