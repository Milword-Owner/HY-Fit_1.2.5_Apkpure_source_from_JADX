package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.concurrent.TimeUnit;

@SafeParcelable.Class(creator = "DataUpdateNotificationCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class DataUpdateNotification extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final String ACTION = "com.google.android.gms.fitness.DATA_UPDATE_NOTIFICATION";
    @RecentlyNonNull
    public static final Parcelable.Creator<DataUpdateNotification> CREATOR = new zzn();
    @RecentlyNonNull
    public static final String EXTRA_DATA_UPDATE_NOTIFICATION = "vnd.google.fitness.data_udpate_notification";
    public static final int OPERATION_DELETE = 2;
    public static final int OPERATION_INSERT = 1;
    public static final int OPERATION_UPDATE = 3;
    @SafeParcelable.Field(getter = "getDataType", mo19514id = 5)
    private final DataType zzkp;
    @SafeParcelable.Field(getter = "getDataSource", mo19514id = 4)
    private final DataSource zzkq;
    @SafeParcelable.Field(getter = "getUpdateStartTimeNanos", mo19514id = 1)
    private final long zzmp;
    @SafeParcelable.Field(getter = "getUpdateEndTimeNanos", mo19514id = 2)
    private final long zzmq;
    @SafeParcelable.Field(getter = "getOperationType", mo19514id = 3)
    private final int zzmr;

    @SafeParcelable.Constructor
    public DataUpdateNotification(@SafeParcelable.Param(mo19517id = 1) long j, @SafeParcelable.Param(mo19517id = 2) long j2, @SafeParcelable.Param(mo19517id = 3) int i, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 4) DataSource dataSource, @RecentlyNonNull @SafeParcelable.Param(mo19517id = 5) DataType dataType) {
        this.zzmp = j;
        this.zzmq = j2;
        this.zzmr = i;
        this.zzkq = dataSource;
        this.zzkp = dataType;
    }

    @RecentlyNullable
    public static DataUpdateNotification getDataUpdateNotification(@RecentlyNonNull Intent intent) {
        return (DataUpdateNotification) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_DATA_UPDATE_NOTIFICATION, CREATOR);
    }

    public long getUpdateStartTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzmp, TimeUnit.NANOSECONDS);
    }

    public long getUpdateEndTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzmq, TimeUnit.NANOSECONDS);
    }

    public int getOperationType() {
        return this.zzmr;
    }

    @RecentlyNonNull
    public DataSource getDataSource() {
        return this.zzkq;
    }

    @RecentlyNonNull
    public DataType getDataType() {
        return this.zzkp;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataUpdateNotification)) {
            return false;
        }
        DataUpdateNotification dataUpdateNotification = (DataUpdateNotification) obj;
        return this.zzmp == dataUpdateNotification.zzmp && this.zzmq == dataUpdateNotification.zzmq && this.zzmr == dataUpdateNotification.zzmr && Objects.equal(this.zzkq, dataUpdateNotification.zzkq) && Objects.equal(this.zzkp, dataUpdateNotification.zzkp);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzmp), Long.valueOf(this.zzmq), Integer.valueOf(this.zzmr), this.zzkq, this.zzkp);
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("updateStartTimeNanos", Long.valueOf(this.zzmp)).add("updateEndTimeNanos", Long.valueOf(this.zzmq)).add("operationType", Integer.valueOf(this.zzmr)).add("dataSource", this.zzkq).add("dataType", this.zzkp).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzmp);
        SafeParcelWriter.writeLong(parcel, 2, this.zzmq);
        SafeParcelWriter.writeInt(parcel, 3, getOperationType());
        SafeParcelWriter.writeParcelable(parcel, 4, getDataSource(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, getDataType(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
