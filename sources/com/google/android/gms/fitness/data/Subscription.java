package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;

@SafeParcelable.Class(creator = "SubscriptionCreator")
@SafeParcelable.Reserved({1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class Subscription extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<Subscription> CREATOR = new zzai();
    @SafeParcelable.Field(getter = "getDataType", mo19514id = 2)
    private final DataType zzkp;
    @SafeParcelable.Field(getter = "getDataSource", mo19514id = 1)
    private final DataSource zzkq;
    @SafeParcelable.Field(getter = "getSamplingRateMicros", mo19514id = 3)
    private final long zzof;
    @SafeParcelable.Field(getter = "getAccuracyMode", mo19514id = 4)
    private final int zzog;
    @SafeParcelable.Field(getter = "getSubscriptionType", mo19514id = 5)
    private final int zzoh;

    @SafeParcelable.Constructor
    Subscription(@SafeParcelable.Param(mo19517id = 1) DataSource dataSource, @SafeParcelable.Param(mo19517id = 2) DataType dataType, @SafeParcelable.Param(mo19517id = 3) long j, @SafeParcelable.Param(mo19517id = 4) int i, @SafeParcelable.Param(mo19517id = 5) int i2) {
        this.zzkq = dataSource;
        this.zzkp = dataType;
        this.zzof = j;
        this.zzog = i;
        this.zzoh = i2;
    }

    @ShowFirstParty
    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class zza {
        private DataType zzkp;
        private DataSource zzkq;
        private long zzof = -1;
        private int zzog = 2;
        private int zzoh = 0;

        public final zza zza(DataSource dataSource) {
            this.zzkq = dataSource;
            return this;
        }

        public final zza zza(DataType dataType) {
            this.zzkp = dataType;
            return this;
        }

        public final Subscription zzr() {
            DataSource dataSource;
            boolean z = false;
            Preconditions.checkState((this.zzkq == null && this.zzkp == null) ? false : true, "Must call setDataSource() or setDataType()");
            DataType dataType = this.zzkp;
            if (dataType == null || (dataSource = this.zzkq) == null || dataType.equals(dataSource.getDataType())) {
                z = true;
            }
            Preconditions.checkState(z, "Specified data type is incompatible with specified data source");
            return new Subscription(this.zzkq, this.zzkp, this.zzof, this.zzog, 0);
        }
    }

    @RecentlyNullable
    public DataSource getDataSource() {
        return this.zzkq;
    }

    @RecentlyNullable
    public DataType getDataType() {
        return this.zzkp;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Subscription)) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        return Objects.equal(this.zzkq, subscription.zzkq) && Objects.equal(this.zzkp, subscription.zzkp) && this.zzof == subscription.zzof && this.zzog == subscription.zzog && this.zzoh == subscription.zzoh;
    }

    public int hashCode() {
        DataSource dataSource = this.zzkq;
        return Objects.hashCode(dataSource, dataSource, Long.valueOf(this.zzof), Integer.valueOf(this.zzog), Integer.valueOf(this.zzoh));
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("dataSource", this.zzkq).add("dataType", this.zzkp).add("samplingIntervalMicros", Long.valueOf(this.zzof)).add("accuracyMode", Integer.valueOf(this.zzog)).add("subscriptionType", Integer.valueOf(this.zzoh)).toString();
    }

    @RecentlyNonNull
    public String toDebugString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        DataSource dataSource = this.zzkq;
        objArr[0] = dataSource == null ? this.zzkp.getName() : dataSource.toDebugString();
        objArr[1] = Integer.valueOf(this.zzoh);
        return String.format(locale, "Subscription{%s}, subscriptionType{%d}", objArr);
    }

    @RecentlyNonNull
    @ShowFirstParty
    public final DataType zzs() {
        DataType dataType = this.zzkp;
        return dataType == null ? this.zzkq.getDataType() : dataType;
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getDataType(), i, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzof);
        SafeParcelWriter.writeInt(parcel, 4, this.zzog);
        SafeParcelWriter.writeInt(parcel, 5, this.zzoh);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
