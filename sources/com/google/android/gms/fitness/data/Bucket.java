package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzko;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p015io.reactivex.annotations.SchedulerSupport;

@SafeParcelable.Class(creator = "BucketCreator")
@SafeParcelable.Reserved({7, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class Bucket extends AbstractSafeParcelable implements ReflectedParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<Bucket> CREATOR = new zze();
    public static final int TYPE_ACTIVITY_SEGMENT = 4;
    public static final int TYPE_ACTIVITY_TYPE = 3;
    public static final int TYPE_SESSION = 2;
    public static final int TYPE_TIME = 1;
    @SafeParcelable.Field(getter = "getStartTimeMillis", mo19514id = 1)
    private final long zzkr;
    @SafeParcelable.Field(getter = "getEndTimeMillis", mo19514id = 2)
    private final long zzks;
    @SafeParcelable.Field(getter = "getSession", mo19514id = 3)
    @Nullable
    private final Session zzky;
    @SafeParcelable.Field(getter = "getActivityType", mo19514id = 4)
    private final int zzlg;
    @SafeParcelable.Field(getter = "getDataSets", mo19514id = 5)
    private final List<DataSet> zzlh;
    @SafeParcelable.Field(getter = "getBucketType", mo19514id = 6)
    private final int zzli;

    @SafeParcelable.Constructor
    Bucket(@SafeParcelable.Param(mo19517id = 1) long j, @SafeParcelable.Param(mo19517id = 2) long j2, @SafeParcelable.Param(mo19517id = 3) @Nullable Session session, @SafeParcelable.Param(mo19517id = 4) int i, @SafeParcelable.Param(mo19517id = 5) List<DataSet> list, @SafeParcelable.Param(mo19517id = 6) int i2) {
        this.zzkr = j;
        this.zzks = j2;
        this.zzky = session;
        this.zzlg = i;
        this.zzlh = list;
        this.zzli = i2;
    }

    @RecentlyNonNull
    @ShowFirstParty
    public static String zza(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "bug" : "intervals" : "segment" : "type" : "session" : "time" : SchedulerSupport.NONE;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Bucket(@androidx.annotation.RecentlyNonNull com.google.android.gms.fitness.data.RawBucket r11, @androidx.annotation.RecentlyNonNull java.util.List<com.google.android.gms.fitness.data.DataSource> r12) {
        /*
            r10 = this;
            long r1 = r11.zzkr
            long r3 = r11.zzks
            com.google.android.gms.fitness.data.Session r5 = r11.zzky
            int r6 = r11.zzny
            java.util.List<com.google.android.gms.fitness.data.RawDataSet> r0 = r11.zzlh
            java.util.ArrayList r7 = new java.util.ArrayList
            int r8 = r0.size()
            r7.<init>(r8)
            java.util.Iterator r0 = r0.iterator()
        L_0x0017:
            boolean r8 = r0.hasNext()
            if (r8 == 0) goto L_0x002c
            java.lang.Object r8 = r0.next()
            com.google.android.gms.fitness.data.RawDataSet r8 = (com.google.android.gms.fitness.data.RawDataSet) r8
            com.google.android.gms.fitness.data.DataSet r9 = new com.google.android.gms.fitness.data.DataSet
            r9.<init>(r8, r12)
            r7.add(r9)
            goto L_0x0017
        L_0x002c:
            int r8 = r11.zzli
            r0 = r10
            r0.<init>(r1, r3, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.Bucket.<init>(com.google.android.gms.fitness.data.RawBucket, java.util.List):void");
    }

    @RecentlyNonNull
    public List<DataSet> getDataSets() {
        return this.zzlh;
    }

    public long getStartTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzkr, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(@RecentlyNonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.zzks, TimeUnit.MILLISECONDS);
    }

    @RecentlyNullable
    public Session getSession() {
        return this.zzky;
    }

    @RecentlyNonNull
    public String getActivity() {
        return zzko.getName(this.zzlg);
    }

    @ShowFirstParty
    public final int zzd() {
        return this.zzlg;
    }

    @RecentlyNullable
    public DataSet getDataSet(@RecentlyNonNull DataType dataType) {
        for (DataSet next : this.zzlh) {
            if (next.getDataType().equals(dataType)) {
                return next;
            }
        }
        return null;
    }

    public int getBucketType() {
        return this.zzli;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Bucket)) {
            return false;
        }
        Bucket bucket = (Bucket) obj;
        return this.zzkr == bucket.zzkr && this.zzks == bucket.zzks && this.zzlg == bucket.zzlg && Objects.equal(this.zzlh, bucket.zzlh) && this.zzli == bucket.zzli;
    }

    public final boolean zza(@RecentlyNonNull Bucket bucket) {
        return this.zzkr == bucket.zzkr && this.zzks == bucket.zzks && this.zzlg == bucket.zzlg && this.zzli == bucket.zzli;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzkr), Long.valueOf(this.zzks), Integer.valueOf(this.zzlg), Integer.valueOf(this.zzli));
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.zzkr)).add("endTime", Long.valueOf(this.zzks)).add("activity", Integer.valueOf(this.zzlg)).add("dataSets", this.zzlh).add("bucketType", zza(this.zzli)).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzkr);
        SafeParcelWriter.writeLong(parcel, 2, this.zzks);
        SafeParcelWriter.writeParcelable(parcel, 3, getSession(), i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzlg);
        SafeParcelWriter.writeTypedList(parcel, 5, getDataSets(), false);
        SafeParcelWriter.writeInt(parcel, 6, getBucketType());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
