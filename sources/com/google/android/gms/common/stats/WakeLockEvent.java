package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class(creator = "WakeLockEventCreator")
/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
public final class WakeLockEvent extends StatsEvent {
    public static final Parcelable.Creator<WakeLockEvent> CREATOR = new zza();
    private long durationMillis;
    @SafeParcelable.VersionField(mo19520id = 1)
    private final int versionCode;
    @SafeParcelable.Field(getter = "getTimeMillis", mo19514id = 2)
    private final long zzgd;
    @SafeParcelable.Field(getter = "getEventType", mo19514id = 11)
    private int zzge;
    @SafeParcelable.Field(getter = "getWakeLockName", mo19514id = 4)
    private final String zzgf;
    @SafeParcelable.Field(getter = "getSecondaryWakeLockName", mo19514id = 10)
    private final String zzgg;
    @SafeParcelable.Field(getter = "getCodePackage", mo19514id = 17)
    private final String zzgh;
    @SafeParcelable.Field(getter = "getWakeLockType", mo19514id = 5)
    private final int zzgi;
    @SafeParcelable.Field(getter = "getCallingPackages", mo19514id = 6)
    private final List<String> zzgj;
    @SafeParcelable.Field(getter = "getEventKey", mo19514id = 12)
    private final String zzgk;
    @SafeParcelable.Field(getter = "getElapsedRealtime", mo19514id = 8)
    private final long zzgl;
    @SafeParcelable.Field(getter = "getDeviceState", mo19514id = 14)
    private int zzgm;
    @SafeParcelable.Field(getter = "getHostPackage", mo19514id = 13)
    private final String zzgn;
    @SafeParcelable.Field(getter = "getBeginPowerPercentage", mo19514id = 15)
    private final float zzgo;
    @SafeParcelable.Field(getter = "getTimeout", mo19514id = 16)
    private final long zzgp;
    @SafeParcelable.Field(getter = "getAcquiredWithTimeout", mo19514id = 18)
    private final boolean zzgq;

    @SafeParcelable.Constructor
    WakeLockEvent(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) long j, @SafeParcelable.Param(mo19517id = 11) int i2, @SafeParcelable.Param(mo19517id = 4) String str, @SafeParcelable.Param(mo19517id = 5) int i3, @SafeParcelable.Param(mo19517id = 6) List<String> list, @SafeParcelable.Param(mo19517id = 12) String str2, @SafeParcelable.Param(mo19517id = 8) long j2, @SafeParcelable.Param(mo19517id = 14) int i4, @SafeParcelable.Param(mo19517id = 10) String str3, @SafeParcelable.Param(mo19517id = 13) String str4, @SafeParcelable.Param(mo19517id = 15) float f, @SafeParcelable.Param(mo19517id = 16) long j3, @SafeParcelable.Param(mo19517id = 17) String str5, @SafeParcelable.Param(mo19517id = 18) boolean z) {
        this.versionCode = i;
        this.zzgd = j;
        this.zzge = i2;
        this.zzgf = str;
        this.zzgg = str3;
        this.zzgh = str5;
        this.zzgi = i3;
        this.durationMillis = -1;
        this.zzgj = list;
        this.zzgk = str2;
        this.zzgl = j2;
        this.zzgm = i4;
        this.zzgn = str4;
        this.zzgo = f;
        this.zzgp = j3;
        this.zzgq = z;
    }

    public WakeLockEvent(long j, int i, String str, int i2, List<String> list, String str2, long j2, int i3, String str3, String str4, float f, long j3, String str5, boolean z) {
        this(2, j, i, str, i2, list, str2, j2, i3, str3, str4, f, j3, str5, z);
    }

    public final long getTimeMillis() {
        return this.zzgd;
    }

    public final int getEventType() {
        return this.zzge;
    }

    public final long zzu() {
        return this.durationMillis;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeLong(parcel, 2, getTimeMillis());
        SafeParcelWriter.writeString(parcel, 4, this.zzgf, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzgi);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzgj, false);
        SafeParcelWriter.writeLong(parcel, 8, this.zzgl);
        SafeParcelWriter.writeString(parcel, 10, this.zzgg, false);
        SafeParcelWriter.writeInt(parcel, 11, getEventType());
        SafeParcelWriter.writeString(parcel, 12, this.zzgk, false);
        SafeParcelWriter.writeString(parcel, 13, this.zzgn, false);
        SafeParcelWriter.writeInt(parcel, 14, this.zzgm);
        SafeParcelWriter.writeFloat(parcel, 15, this.zzgo);
        SafeParcelWriter.writeLong(parcel, 16, this.zzgp);
        SafeParcelWriter.writeString(parcel, 17, this.zzgh, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzgq);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzv() {
        String str;
        String str2 = this.zzgf;
        int i = this.zzgi;
        List<String> list = this.zzgj;
        String str3 = "";
        if (list == null) {
            str = str3;
        } else {
            str = TextUtils.join(",", list);
        }
        int i2 = this.zzgm;
        String str4 = this.zzgg;
        if (str4 == null) {
            str4 = str3;
        }
        String str5 = this.zzgn;
        if (str5 == null) {
            str5 = str3;
        }
        float f = this.zzgo;
        String str6 = this.zzgh;
        if (str6 != null) {
            str3 = str6;
        }
        boolean z = this.zzgq;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 51 + String.valueOf(str).length() + String.valueOf(str4).length() + String.valueOf(str5).length() + String.valueOf(str3).length());
        sb.append("\t");
        sb.append(str2);
        sb.append("\t");
        sb.append(i);
        sb.append("\t");
        sb.append(str);
        sb.append("\t");
        sb.append(i2);
        sb.append("\t");
        sb.append(str4);
        sb.append("\t");
        sb.append(str5);
        sb.append("\t");
        sb.append(f);
        sb.append("\t");
        sb.append(str3);
        sb.append("\t");
        sb.append(z);
        return sb.toString();
    }
}
