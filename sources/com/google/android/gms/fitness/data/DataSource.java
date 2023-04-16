package com.google.android.gms.fitness.data;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.baidu.mobstat.Config;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.fitness.zzkq;
import java.util.Locale;

@SafeParcelable.Class(creator = "DataSourceCreator")
@SafeParcelable.Reserved({2, 7, 8, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class DataSource extends AbstractSafeParcelable {
    @RecentlyNonNull
    @ShowFirstParty
    public static final Parcelable.Creator<DataSource> CREATOR = new zzk();
    @RecentlyNonNull
    public static final String EXTRA_DATA_SOURCE = "vnd.google.fitness.data_source";
    public static final int TYPE_DERIVED = 1;
    public static final int TYPE_RAW = 0;
    private static final String zzlt = zzkq.zzb.zzc.RAW.name().toLowerCase(Locale.ROOT);
    private static final String zzlu = zzkq.zzb.zzc.DERIVED.name().toLowerCase(Locale.ROOT);
    @SafeParcelable.Field(getter = "getType", mo19514id = 3)
    private final int type;
    @SafeParcelable.Field(getter = "getDataType", mo19514id = 1)
    private final DataType zzkp;
    @SafeParcelable.Field(getter = "getDevice", mo19514id = 4)
    @Nullable
    private final Device zzlv;
    @SafeParcelable.Field(getter = "getApplication", mo19514id = 5)
    @Nullable
    private final zza zzlw;
    @SafeParcelable.Field(getter = "getStreamName", mo19514id = 6)
    private final String zzlx;
    private final String zzly;

    @SafeParcelable.Constructor
    public DataSource(@SafeParcelable.Param(mo19517id = 1) DataType dataType, @SafeParcelable.Param(mo19517id = 3) int i, @SafeParcelable.Param(mo19517id = 4) @Nullable Device device, @SafeParcelable.Param(mo19517id = 5) @Nullable zza zza, @SafeParcelable.Param(mo19517id = 6) String str) {
        this.zzkp = dataType;
        this.type = i;
        this.zzlv = device;
        this.zzlw = zza;
        this.zzlx = str;
        StringBuilder sb = new StringBuilder();
        sb.append(zzd(i));
        sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
        sb.append(dataType.getName());
        if (zza != null) {
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb.append(zza.getPackageName());
        }
        if (device != null) {
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb.append(device.getStreamIdentifier());
        }
        if (str != null) {
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb.append(str);
        }
        this.zzly = sb.toString();
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public int type = -1;
        /* access modifiers changed from: private */
        public DataType zzkp;
        /* access modifiers changed from: private */
        public Device zzlv;
        /* access modifiers changed from: private */
        public zza zzlw;
        /* access modifiers changed from: private */
        public String zzlx = "";

        @RecentlyNonNull
        public final Builder setDataType(@RecentlyNonNull DataType dataType) {
            this.zzkp = dataType;
            return this;
        }

        @RecentlyNonNull
        public final Builder setType(int i) {
            this.type = i;
            return this;
        }

        @RecentlyNonNull
        public final Builder setDevice(@RecentlyNonNull Device device) {
            this.zzlv = device;
            return this;
        }

        @RecentlyNonNull
        public final Builder setAppPackageName(@RecentlyNonNull String str) {
            this.zzlw = zza.zza(str);
            return this;
        }

        @RecentlyNonNull
        public final Builder setAppPackageName(@RecentlyNonNull Context context) {
            return setAppPackageName(context.getPackageName());
        }

        @RecentlyNonNull
        public final Builder setStreamName(@RecentlyNonNull String str) {
            Preconditions.checkArgument(str != null, "Must specify a valid stream name");
            this.zzlx = str;
            return this;
        }

        @RecentlyNonNull
        public final DataSource build() {
            boolean z = true;
            Preconditions.checkState(this.zzkp != null, "Must set data type");
            if (this.type < 0) {
                z = false;
            }
            Preconditions.checkState(z, "Must set data source type");
            return new DataSource(this);
        }
    }

    private DataSource(Builder builder) {
        this(builder.zzkp, builder.type, builder.zzlv, builder.zzlw, builder.zzlx);
    }

    @RecentlyNullable
    public static DataSource extract(@RecentlyNonNull Intent intent) {
        if (intent == null) {
            return null;
        }
        return (DataSource) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_DATA_SOURCE, CREATOR);
    }

    @RecentlyNonNull
    public DataType getDataType() {
        return this.zzkp;
    }

    public int getType() {
        return this.type;
    }

    @RecentlyNullable
    public String getAppPackageName() {
        zza zza = this.zzlw;
        if (zza == null) {
            return null;
        }
        return zza.getPackageName();
    }

    @ShowFirstParty
    @Nullable
    public final zza zzj() {
        return this.zzlw;
    }

    @RecentlyNullable
    public Device getDevice() {
        return this.zzlv;
    }

    @RecentlyNonNull
    public String getStreamName() {
        return this.zzlx;
    }

    @RecentlyNonNull
    public String getStreamIdentifier() {
        return this.zzly;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataSource)) {
            return false;
        }
        return this.zzly.equals(((DataSource) obj).zzly);
    }

    public int hashCode() {
        return this.zzly.hashCode();
    }

    @RecentlyNonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("DataSource{");
        sb.append(zzd(this.type));
        if (this.zzlw != null) {
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb.append(this.zzlw);
        }
        if (this.zzlv != null) {
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb.append(this.zzlv);
        }
        if (this.zzlx != null) {
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb.append(this.zzlx);
        }
        sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
        sb.append(this.zzkp);
        sb.append("}");
        return sb.toString();
    }

    @RecentlyNonNull
    @ShowFirstParty
    public final String toDebugString() {
        String str;
        String str2;
        int i = this.type;
        String str3 = i != 0 ? i != 1 ? "?" : "d" : "r";
        String zzm = this.zzkp.zzm();
        zza zza = this.zzlw;
        String str4 = "";
        if (zza == null) {
            str = str4;
        } else if (zza.equals(zza.zzlb)) {
            str = ":gms";
        } else {
            String valueOf = String.valueOf(this.zzlw.getPackageName());
            str = valueOf.length() != 0 ? Config.TRACE_TODAY_VISIT_SPLIT.concat(valueOf) : new String(Config.TRACE_TODAY_VISIT_SPLIT);
        }
        Device device = this.zzlv;
        if (device != null) {
            String model = device.getModel();
            String uid = this.zzlv.getUid();
            StringBuilder sb = new StringBuilder(String.valueOf(model).length() + 2 + String.valueOf(uid).length());
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb.append(model);
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            sb.append(uid);
            str2 = sb.toString();
        } else {
            str2 = str4;
        }
        String str5 = this.zzlx;
        if (str5 != null) {
            String valueOf2 = String.valueOf(str5);
            str4 = valueOf2.length() != 0 ? Config.TRACE_TODAY_VISIT_SPLIT.concat(valueOf2) : new String(Config.TRACE_TODAY_VISIT_SPLIT);
        }
        StringBuilder sb2 = new StringBuilder(str3.length() + 1 + String.valueOf(zzm).length() + String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(str4).length());
        sb2.append(str3);
        sb2.append(Config.TRACE_TODAY_VISIT_SPLIT);
        sb2.append(zzm);
        sb2.append(str);
        sb2.append(str2);
        sb2.append(str4);
        return sb2.toString();
    }

    private static String zzd(int i) {
        if (i == 0) {
            return zzlt;
        }
        if (i != 1) {
            return zzlu;
        }
        return zzlu;
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataType(), i, false);
        SafeParcelWriter.writeInt(parcel, 3, getType());
        SafeParcelWriter.writeParcelable(parcel, 4, getDevice(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzlw, i, false);
        SafeParcelWriter.writeString(parcel, 6, getStreamName(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
