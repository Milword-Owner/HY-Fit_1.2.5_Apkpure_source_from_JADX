package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcn;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "StartBleScanRequestCreator")
@SafeParcelable.Reserved({5, 1000})
@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class StartBleScanRequest extends AbstractSafeParcelable {
    @RecentlyNonNull
    public static final Parcelable.Creator<StartBleScanRequest> CREATOR = new zzbh();
    @SafeParcelable.Field(getter = "getDataTypes", mo19514id = 1)
    private final List<DataType> zzlf;
    @SafeParcelable.Field(getter = "getCallbackBinder", mo19514id = 4, type = "android.os.IBinder")
    @Nullable
    private final zzcn zzql;
    @SafeParcelable.Field(getter = "getBleScanCallbackBinder", mo19514id = 2, type = "android.os.IBinder")
    @Nullable
    private final zzad zzsn;
    @SafeParcelable.Field(getter = "getTimeoutSecs", mo19514id = 3)
    private final int zzso;
    @Nullable
    private final BleScanCallback zzsp;

    /* JADX WARNING: type inference failed for: r0v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
    /* Code decompiled incorrectly, please refer to instructions dump. */
    StartBleScanRequest(@com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo19517id = 1) java.util.List<com.google.android.gms.fitness.data.DataType> r3, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo19517id = 2) @androidx.annotation.Nullable android.os.IBinder r4, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo19517id = 3) int r5, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(mo19517id = 4) @androidx.annotation.Nullable android.os.IBinder r6) {
        /*
            r2 = this;
            r2.<init>()
            r2.zzlf = r3
            r3 = 0
            if (r4 != 0) goto L_0x000a
        L_0x0008:
            r4 = r3
            goto L_0x0021
        L_0x000a:
            if (r4 != 0) goto L_0x000d
            goto L_0x0008
        L_0x000d:
            java.lang.String r0 = "com.google.android.gms.fitness.request.IBleScanCallback"
            android.os.IInterface r0 = r4.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.fitness.request.zzad
            if (r1 == 0) goto L_0x001b
            r4 = r0
            com.google.android.gms.fitness.request.zzad r4 = (com.google.android.gms.fitness.request.zzad) r4
            goto L_0x0021
        L_0x001b:
            com.google.android.gms.fitness.request.zzaf r0 = new com.google.android.gms.fitness.request.zzaf
            r0.<init>(r4)
            r4 = r0
        L_0x0021:
            r2.zzsn = r4
            r2.zzso = r5
            if (r6 != 0) goto L_0x0029
            r4 = r3
            goto L_0x002d
        L_0x0029:
            com.google.android.gms.internal.fitness.zzcn r4 = com.google.android.gms.internal.fitness.zzcm.zzj(r6)
        L_0x002d:
            r2.zzql = r4
            r2.zzsp = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.StartBleScanRequest.<init>(java.util.List, android.os.IBinder, int, android.os.IBinder):void");
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class Builder {
        private int zzso = 10;
        private DataType[] zzsq = new DataType[0];
        private BleScanCallback zzsr;

        @RecentlyNonNull
        public Builder setDataTypes(@RecentlyNonNull DataType... dataTypeArr) {
            this.zzsq = dataTypeArr;
            return this;
        }

        @RecentlyNonNull
        public Builder setBleScanCallback(@RecentlyNonNull BleScanCallback bleScanCallback) {
            this.zzsr = bleScanCallback;
            return this;
        }

        @RecentlyNonNull
        public Builder setTimeoutSecs(int i) {
            boolean z = true;
            Preconditions.checkArgument(i > 0, "Stop time must be greater than zero");
            if (i > 60) {
                z = false;
            }
            Preconditions.checkArgument(z, "Stop time must be less than 1 minute");
            this.zzso = i;
            return this;
        }

        @RecentlyNonNull
        public StartBleScanRequest build() {
            Preconditions.checkState(this.zzsr != null, "Must set BleScanCallback");
            return new StartBleScanRequest((List) ArrayUtils.toArrayList(this.zzsq), this.zzsr, this.zzso);
        }
    }

    private StartBleScanRequest(List<DataType> list, BleScanCallback bleScanCallback, int i) {
        this.zzlf = list;
        this.zzsn = null;
        this.zzso = i;
        this.zzql = null;
        this.zzsp = bleScanCallback;
    }

    public StartBleScanRequest(List<DataType> list, @Nullable zzad zzad, int i, @Nullable zzcn zzcn) {
        this.zzlf = list;
        this.zzsn = zzad;
        this.zzso = i;
        this.zzql = zzcn;
        this.zzsp = null;
    }

    @RecentlyNonNull
    public List<DataType> getDataTypes() {
        return Collections.unmodifiableList(this.zzlf);
    }

    public int getTimeoutSecs() {
        return this.zzso;
    }

    @RecentlyNullable
    public final BleScanCallback zzaa() {
        return this.zzsp;
    }

    @RecentlyNonNull
    public String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.zzlf).add("timeoutSecs", Integer.valueOf(this.zzso)).toString();
    }

    public void writeToParcel(@RecentlyNonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        zzad zzad = this.zzsn;
        IBinder iBinder = null;
        SafeParcelWriter.writeIBinder(parcel, 2, zzad == null ? null : zzad.asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 3, getTimeoutSecs());
        zzcn zzcn = this.zzql;
        if (zzcn != null) {
            iBinder = zzcn.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 4, iBinder, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
