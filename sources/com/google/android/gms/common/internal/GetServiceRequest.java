package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class(creator = "GetServiceRequestCreator")
@SafeParcelable.Reserved({9})
/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
public class GetServiceRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GetServiceRequest> CREATOR = new zzd();
    @SafeParcelable.VersionField(mo19520id = 1)
    private final int version;
    @SafeParcelable.Field(mo19514id = 4)
    String zzak;
    @SafeParcelable.Field(defaultValue = "0", mo19514id = 13)
    private int zzdo;
    @SafeParcelable.Field(mo19514id = 2)
    private final int zzdp;
    @SafeParcelable.Field(mo19514id = 3)
    private int zzdq;
    @SafeParcelable.Field(mo19514id = 5)
    IBinder zzdr;
    @SafeParcelable.Field(mo19514id = 6)
    Scope[] zzds;
    @SafeParcelable.Field(mo19514id = 7)
    Bundle zzdt;
    @SafeParcelable.Field(mo19514id = 8)
    Account zzdu;
    @SafeParcelable.Field(mo19514id = 10)
    Feature[] zzdv;
    @SafeParcelable.Field(mo19514id = 11)
    Feature[] zzdw;
    @SafeParcelable.Field(mo19514id = 12)
    private boolean zzdx;

    public GetServiceRequest(int i) {
        this.version = 4;
        this.zzdq = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.zzdp = i;
        this.zzdx = true;
    }

    @SafeParcelable.Constructor
    GetServiceRequest(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) int i2, @SafeParcelable.Param(mo19517id = 3) int i3, @SafeParcelable.Param(mo19517id = 4) String str, @SafeParcelable.Param(mo19517id = 5) IBinder iBinder, @SafeParcelable.Param(mo19517id = 6) Scope[] scopeArr, @SafeParcelable.Param(mo19517id = 7) Bundle bundle, @SafeParcelable.Param(mo19517id = 8) Account account, @SafeParcelable.Param(mo19517id = 10) Feature[] featureArr, @SafeParcelable.Param(mo19517id = 11) Feature[] featureArr2, @SafeParcelable.Param(mo19517id = 12) boolean z, @SafeParcelable.Param(mo19517id = 13) int i4) {
        this.version = i;
        this.zzdp = i2;
        this.zzdq = i3;
        if ("com.google.android.gms".equals(str)) {
            this.zzak = "com.google.android.gms";
        } else {
            this.zzak = str;
        }
        if (i < 2) {
            this.zzdu = iBinder != null ? AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(iBinder)) : null;
        } else {
            this.zzdr = iBinder;
            this.zzdu = account;
        }
        this.zzds = scopeArr;
        this.zzdt = bundle;
        this.zzdv = featureArr;
        this.zzdw = featureArr2;
        this.zzdx = z;
        this.zzdo = i4;
    }

    @KeepForSdk
    public Bundle getExtraArgs() {
        return this.zzdt;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.version);
        SafeParcelWriter.writeInt(parcel, 2, this.zzdp);
        SafeParcelWriter.writeInt(parcel, 3, this.zzdq);
        SafeParcelWriter.writeString(parcel, 4, this.zzak, false);
        SafeParcelWriter.writeIBinder(parcel, 5, this.zzdr, false);
        SafeParcelWriter.writeTypedArray(parcel, 6, this.zzds, i, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzdt, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzdu, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 10, this.zzdv, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 11, this.zzdw, i, false);
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzdx);
        SafeParcelWriter.writeInt(parcel, 13, this.zzdo);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
