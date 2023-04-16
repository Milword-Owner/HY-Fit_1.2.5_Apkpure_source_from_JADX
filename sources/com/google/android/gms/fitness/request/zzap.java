package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.zzu;
import com.google.android.gms.fitness.data.zzv;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
import java.util.Collections;
import java.util.List;

@ShowFirstParty
@SafeParcelable.Class(creator = "SensorRegistrationRequestCreator")
@SafeParcelable.Reserved({4, 5, 11, 14, 1000})
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzap extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzap> CREATOR = new zzao();
    @SafeParcelable.Field(getter = "getDataType", mo19514id = 2)
    @Nullable
    private DataType zzkp;
    @SafeParcelable.Field(getter = "getDataSource", mo19514id = 1)
    @Nullable
    private DataSource zzkq;
    @SafeParcelable.Field(getter = "getSamplingRateMicros", mo19514id = 6)
    private final long zzof;
    @SafeParcelable.Field(getter = "getAccuracyMode", mo19514id = 10)
    private final int zzog;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 13, type = "android.os.IBinder")
    @Nullable
    private final zzcn zzql;
    @SafeParcelable.Field(getter = "getIntent", mo19514id = 8)
    @Nullable
    private final PendingIntent zzrk;
    @SafeParcelable.Field(getter = "getListenerBinder", mo19514id = 3, type = "android.os.IBinder")
    @Nullable
    private final zzv zzrt;
    @SafeParcelable.Field(getter = "getMaxDeliveryLatencyMicros", mo19514id = 7)
    private final long zzru;
    @SafeParcelable.Field(getter = "getFastestRateMicros", mo19514id = 9)
    private final long zzrv;
    @SafeParcelable.Field(getter = "getRegistrationTimeOutMicros", mo19514id = 12)
    private final long zzrw;
    private final List<ClientIdentity> zzrx;

    @SafeParcelable.Constructor
    zzap(@SafeParcelable.Param(mo19517id = 1) @Nullable DataSource dataSource, @SafeParcelable.Param(mo19517id = 2) @Nullable DataType dataType, @SafeParcelable.Param(mo19517id = 3) @Nullable IBinder iBinder, @SafeParcelable.Param(mo19517id = 6) long j, @SafeParcelable.Param(mo19517id = 7) long j2, @SafeParcelable.Param(mo19517id = 8) @Nullable PendingIntent pendingIntent, @SafeParcelable.Param(mo19517id = 9) long j3, @SafeParcelable.Param(mo19517id = 10) int i, @SafeParcelable.Param(mo19517id = 12) long j4, @SafeParcelable.Param(mo19517id = 13) @Nullable IBinder iBinder2) {
        zzv zzv;
        this.zzkq = dataSource;
        this.zzkp = dataType;
        zzcn zzcn = null;
        if (iBinder == null) {
            zzv = null;
        } else {
            zzv = zzu.zza(iBinder);
        }
        this.zzrt = zzv;
        this.zzof = j;
        this.zzrv = j3;
        this.zzru = j2;
        this.zzrk = pendingIntent;
        this.zzog = i;
        this.zzrx = Collections.emptyList();
        this.zzrw = j4;
        this.zzql = iBinder2 != null ? zzcm.zzj(iBinder2) : zzcn;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzap(com.google.android.gms.fitness.request.SensorRequest r17, @androidx.annotation.Nullable com.google.android.gms.fitness.data.zzv r18, @androidx.annotation.Nullable android.app.PendingIntent r19, com.google.android.gms.internal.fitness.zzcn r20) {
        /*
            r16 = this;
            r0 = r17
            com.google.android.gms.fitness.data.DataSource r1 = r17.getDataSource()
            com.google.android.gms.fitness.data.DataType r2 = r17.getDataType()
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MICROSECONDS
            long r5 = r0.getSamplingRate(r3)
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MICROSECONDS
            long r7 = r0.getFastestRate(r3)
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MICROSECONDS
            long r9 = r0.getMaxDeliveryLatency(r3)
            int r11 = r17.getAccuracyMode()
            java.util.List r12 = java.util.Collections.emptyList()
            long r13 = r17.zzy()
            r0 = r16
            r3 = r18
            r4 = r19
            r15 = r20
            r0.<init>(r1, r2, r3, r4, r5, r7, r9, r11, r12, r13, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.zzap.<init>(com.google.android.gms.fitness.request.SensorRequest, com.google.android.gms.fitness.data.zzv, android.app.PendingIntent, com.google.android.gms.internal.fitness.zzcn):void");
    }

    private zzap(@Nullable DataSource dataSource, @Nullable DataType dataType, @Nullable zzv zzv, @Nullable PendingIntent pendingIntent, long j, long j2, long j3, int i, List<ClientIdentity> list, long j4, @Nullable zzcn zzcn) {
        this.zzkq = dataSource;
        this.zzkp = dataType;
        this.zzrt = zzv;
        this.zzrk = pendingIntent;
        this.zzof = j;
        this.zzrv = j2;
        this.zzru = j3;
        this.zzog = i;
        this.zzrx = list;
        this.zzrw = j4;
        this.zzql = zzcn;
    }

    public final String toString() {
        return String.format("SensorRegistrationRequest{type %s source %s interval %s fastest %s latency %s}", new Object[]{this.zzkp, this.zzkq, Long.valueOf(this.zzof), Long.valueOf(this.zzrv), Long.valueOf(this.zzru)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzkq, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzkp, i, false);
        zzv zzv = this.zzrt;
        IBinder iBinder = null;
        SafeParcelWriter.writeIBinder(parcel, 3, zzv == null ? null : zzv.asBinder(), false);
        SafeParcelWriter.writeLong(parcel, 6, this.zzof);
        SafeParcelWriter.writeLong(parcel, 7, this.zzru);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzrk, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.zzrv);
        SafeParcelWriter.writeInt(parcel, 10, this.zzog);
        SafeParcelWriter.writeLong(parcel, 12, this.zzrw);
        zzcn zzcn = this.zzql;
        if (zzcn != null) {
            iBinder = zzcn.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 13, iBinder, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzap)) {
            return false;
        }
        zzap zzap = (zzap) obj;
        return Objects.equal(this.zzkq, zzap.zzkq) && Objects.equal(this.zzkp, zzap.zzkp) && Objects.equal(this.zzrt, zzap.zzrt) && this.zzof == zzap.zzof && this.zzrv == zzap.zzrv && this.zzru == zzap.zzru && this.zzog == zzap.zzog;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzkq, this.zzkp, this.zzrt, Long.valueOf(this.zzof), Long.valueOf(this.zzrv), Long.valueOf(this.zzru), Integer.valueOf(this.zzog));
    }
}
