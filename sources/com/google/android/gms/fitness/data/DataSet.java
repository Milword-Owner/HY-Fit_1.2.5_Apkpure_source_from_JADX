package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@SafeParcelable.Class(creator = "DataSetCreator")
@SafeParcelable.Reserved({2, 5})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class DataSet extends AbstractSafeParcelable implements ReflectedParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<DataSet> CREATOR = new zzi();
    @SafeParcelable.VersionField(getter = "getVersionCode", mo19520id = 1000)
    private final int versionCode;
    @SafeParcelable.Field(getter = "getDataSource", mo19514id = 1)
    private final DataSource zzkq;
    @SafeParcelable.Field(getter = "getRawDataPoints", mo19514id = 3, type = "java.util.List")
    private final List<DataPoint> zzlq;
    @SafeParcelable.Field(getter = "getUniqueDataSources", mo19514id = 4)
    private final List<DataSource> zzlr;

    @SafeParcelable.Constructor
    DataSet(@SafeParcelable.Param(mo19517id = 1000) int i, @SafeParcelable.Param(mo19517id = 1) DataSource dataSource, @SafeParcelable.Param(mo19517id = 3) List<RawDataPoint> list, @SafeParcelable.Param(mo19517id = 4) List<DataSource> list2) {
        this.versionCode = i;
        this.zzkq = dataSource;
        this.zzlq = new ArrayList(list.size());
        this.zzlr = i < 2 ? Collections.singletonList(dataSource) : list2;
        for (RawDataPoint dataPoint : list) {
            this.zzlq.add(new DataPoint(this.zzlr, dataPoint));
        }
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class Builder {
        private boolean zzlp;
        private final DataSet zzls;

        private Builder(DataSource dataSource) {
            this.zzlp = false;
            this.zzls = DataSet.create(dataSource);
        }

        @RecentlyNonNull
        public DataSet build() {
            Preconditions.checkState(!this.zzlp, "DataSet#build() should only be called once.");
            this.zzlp = true;
            return this.zzls;
        }

        @RecentlyNonNull
        public Builder add(@RecentlyNonNull DataPoint dataPoint) {
            Preconditions.checkState(!this.zzlp, "Builder should not be mutated after calling #build.");
            this.zzls.add(dataPoint);
            return this;
        }

        @RecentlyNonNull
        public Builder addAll(@RecentlyNonNull Iterable<DataPoint> iterable) {
            Preconditions.checkState(!this.zzlp, "Builder should not be mutated after calling #build.");
            this.zzls.addAll(iterable);
            return this;
        }
    }

    @ShowFirstParty
    private DataSet(DataSource dataSource) {
        this.versionCode = 3;
        this.zzkq = (DataSource) Preconditions.checkNotNull(dataSource);
        this.zzlq = new ArrayList();
        this.zzlr = new ArrayList();
        this.zzlr.add(this.zzkq);
    }

    public DataSet(@RecentlyNonNull RawDataSet rawDataSet, @RecentlyNonNull List<DataSource> list) {
        this.versionCode = 3;
        this.zzkq = list.get(rawDataSet.zzoa);
        this.zzlr = list;
        List<RawDataPoint> list2 = rawDataSet.zzoc;
        this.zzlq = new ArrayList(list2.size());
        for (RawDataPoint dataPoint : list2) {
            this.zzlq.add(new DataPoint(this.zzlr, dataPoint));
        }
    }

    @RecentlyNonNull
    public static Builder builder(@RecentlyNonNull DataSource dataSource) {
        Preconditions.checkNotNull(dataSource, "DataSource should be specified");
        return new Builder(dataSource);
    }

    @RecentlyNonNull
    public static DataSet create(@RecentlyNonNull DataSource dataSource) {
        Preconditions.checkNotNull(dataSource, "DataSource should be specified");
        return new DataSet(dataSource);
    }

    @RecentlyNonNull
    public final DataPoint createDataPoint() {
        return DataPoint.create(this.zzkq);
    }

    @Deprecated
    public final void add(@RecentlyNonNull DataPoint dataPoint) {
        DataSource dataSource = dataPoint.getDataSource();
        Preconditions.checkArgument(dataSource.getStreamIdentifier().equals(this.zzkq.getStreamIdentifier()), "Conflicting data sources found %s vs %s", dataSource, this.zzkq);
        dataPoint.zzh();
        zzb(dataPoint);
        zza(dataPoint);
    }

    @ShowFirstParty
    @Deprecated
    private final void zza(DataPoint dataPoint) {
        this.zzlq.add(dataPoint);
        DataSource originalDataSource = dataPoint.getOriginalDataSource();
        if (originalDataSource != null && !this.zzlr.contains(originalDataSource)) {
            this.zzlr.add(originalDataSource);
        }
    }

    @Deprecated
    public final void addAll(@RecentlyNonNull Iterable<DataPoint> iterable) {
        for (DataPoint add : iterable) {
            add(add);
        }
    }

    @ShowFirstParty
    @Deprecated
    public final void zza(@RecentlyNonNull Iterable<DataPoint> iterable) {
        for (DataPoint zza : iterable) {
            zza(zza);
        }
    }

    @RecentlyNonNull
    public final DataSource getDataSource() {
        return this.zzkq;
    }

    @RecentlyNonNull
    public final DataType getDataType() {
        return this.zzkq.getDataType();
    }

    @RecentlyNonNull
    public final List<DataPoint> getDataPoints() {
        return Collections.unmodifiableList(this.zzlq);
    }

    public final boolean isEmpty() {
        return this.zzlq.isEmpty();
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataSet)) {
            return false;
        }
        DataSet dataSet = (DataSet) obj;
        return Objects.equal(this.zzkq, dataSet.zzkq) && Objects.equal(this.zzlq, dataSet.zzlq);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzkq);
    }

    @RecentlyNonNull
    public final String toString() {
        List<RawDataPoint> zzi = zzi();
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        objArr[0] = this.zzkq.toDebugString();
        Object obj = zzi;
        if (this.zzlq.size() >= 10) {
            obj = String.format(Locale.US, "%d data points, first 5: %s", new Object[]{Integer.valueOf(this.zzlq.size()), zzi.subList(0, 5)});
        }
        objArr[1] = obj;
        return String.format(locale, "DataSet{%s %s}", objArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c7, code lost:
        if (r4 != com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON) goto L_0x010d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x010f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0110  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void zzb(@androidx.annotation.RecentlyNonNull com.google.android.gms.fitness.data.DataPoint r11) {
        /*
            com.google.android.gms.fitness.data.DataType r0 = r11.getDataType()
            java.lang.String r0 = r0.getName()
            com.google.android.gms.fitness.data.DataType r0 = com.google.android.gms.fitness.data.zzl.zzb(r0)
            java.lang.String r1 = "DataPoint out of range"
            if (r0 == 0) goto L_0x010c
            com.google.android.gms.fitness.data.DataType r0 = r11.getDataType()
            r2 = 0
        L_0x0015:
            java.util.List r3 = r0.getFields()
            int r3 = r3.size()
            if (r2 >= r3) goto L_0x00da
            java.util.List r3 = r0.getFields()
            java.lang.Object r3 = r3.get(r2)
            com.google.android.gms.fitness.data.Field r3 = (com.google.android.gms.fitness.data.Field) r3
            java.lang.String r3 = r3.getName()
            com.google.android.gms.fitness.data.Value r4 = r11.zzb(r2)
            boolean r4 = r4.isSet()
            if (r4 != 0) goto L_0x0061
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            java.util.List r5 = r0.getFields()
            java.lang.Object r5 = r5.get(r2)
            com.google.android.gms.fitness.data.Field r5 = (com.google.android.gms.fitness.data.Field) r5
            java.lang.Boolean r5 = r5.isOptional()
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x00d6
            java.util.Set<java.lang.String> r4 = com.google.android.gms.fitness.data.zzah.zzom
            boolean r4 = r4.contains(r3)
            if (r4 != 0) goto L_0x00d6
            java.lang.String r0 = java.lang.String.valueOf(r3)
            java.lang.String r1 = " not set"
            java.lang.String r1 = r0.concat(r1)
            goto L_0x010d
        L_0x0061:
            java.util.List r4 = r0.getFields()
            java.lang.Object r4 = r4.get(r2)
            com.google.android.gms.fitness.data.Field r4 = (com.google.android.gms.fitness.data.Field) r4
            int r4 = r4.getFormat()
            double r4 = (double) r4
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x0080
            com.google.android.gms.fitness.data.Value r4 = r11.zzb(r2)
            int r4 = r4.asInt()
            double r4 = (double) r4
            goto L_0x008f
        L_0x0080:
            r6 = 4611686018427387904(0x4000000000000000, double:2.0)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x00d6
            com.google.android.gms.fitness.data.Value r4 = r11.zzb(r2)
            float r4 = r4.asFloat()
            double r4 = (double) r4
        L_0x008f:
            com.google.android.gms.fitness.data.zzah r6 = com.google.android.gms.fitness.data.zzah.zzt()
            com.google.android.gms.fitness.data.zzaj r6 = r6.zzi(r3)
            if (r6 == 0) goto L_0x00a2
            boolean r6 = r6.zza(r4)
            if (r6 != 0) goto L_0x00a2
            java.lang.String r1 = "Field out of range"
            goto L_0x010d
        L_0x00a2:
            java.lang.String r6 = r0.getName()
            com.google.android.gms.fitness.data.zzah r7 = com.google.android.gms.fitness.data.zzah.zzt()
            com.google.android.gms.fitness.data.zzaj r3 = r7.zza((java.lang.String) r6, (java.lang.String) r3)
            if (r3 == 0) goto L_0x00d6
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.NANOSECONDS
            long r6 = r11.getEndTime(r6)
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.NANOSECONDS
            long r8 = r11.getStartTime(r8)
            long r6 = r6 - r8
            r8 = 0
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x00ca
            r2 = 0
            int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x010c
            goto L_0x010d
        L_0x00ca:
            double r6 = (double) r6
            java.lang.Double.isNaN(r6)
            double r4 = r4 / r6
            boolean r3 = r3.zza(r4)
            if (r3 != 0) goto L_0x00d6
            goto L_0x010d
        L_0x00d6:
            int r2 = r2 + 1
            goto L_0x0015
        L_0x00da:
            com.google.android.gms.fitness.data.DataSource r0 = r11.getDataSource()
            com.google.android.gms.fitness.data.DataType r0 = r0.getDataType()
            java.lang.String r0 = r0.getName()
            java.lang.String r1 = "com.google.activity.segment"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x010c
            com.google.android.gms.fitness.data.Field r0 = com.google.android.gms.fitness.data.Field.FIELD_ACTIVITY
            com.google.android.gms.fitness.data.Value r0 = r11.getValue(r0)
            if (r0 != 0) goto L_0x00f9
            java.lang.String r1 = "activity is not set"
            goto L_0x010d
        L_0x00f9:
            int r0 = r0.asInt()
            com.google.android.gms.internal.fitness.zzkn r1 = com.google.android.gms.internal.fitness.zzkn.UNKNOWN
            com.google.android.gms.internal.fitness.zzkn r0 = com.google.android.gms.internal.fitness.zzkn.zza(r0, r1)
            boolean r0 = r0.zzdz()
            if (r0 == 0) goto L_0x010c
            java.lang.String r1 = "Sleep types are not a valid activity for com.google.activity.segment"
            goto L_0x010d
        L_0x010c:
            r1 = 0
        L_0x010d:
            if (r1 != 0) goto L_0x0110
            return
        L_0x0110:
            java.lang.String r11 = java.lang.String.valueOf(r11)
            java.lang.String r0 = java.lang.String.valueOf(r11)
            int r0 = r0.length()
            int r0 = r0 + 20
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r0)
            java.lang.String r0 = "Invalid data point: "
            r2.append(r0)
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            java.lang.String r0 = "Fitness"
            android.util.Log.w(r0, r11)
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            r11.<init>(r1)
            goto L_0x013b
        L_0x013a:
            throw r11
        L_0x013b:
            goto L_0x013a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.DataSet.zzb(com.google.android.gms.fitness.data.DataPoint):void");
    }

    private final List<RawDataPoint> zzi() {
        return zza(this.zzlr);
    }

    /* access modifiers changed from: package-private */
    public final List<RawDataPoint> zza(List<DataSource> list) {
        ArrayList arrayList = new ArrayList(this.zzlq.size());
        for (DataPoint rawDataPoint : this.zzlq) {
            arrayList.add(new RawDataPoint(rawDataPoint, list));
        }
        return arrayList;
    }

    public final void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeList(parcel, 3, zzi(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzlr, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
