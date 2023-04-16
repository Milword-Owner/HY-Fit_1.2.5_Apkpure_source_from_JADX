package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ResolveAccountResponseCreator")
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public class ResolveAccountResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ResolveAccountResponse> CREATOR = new zan();
    @SafeParcelable.VersionField(mo19520id = 1)
    private final int versionCode;
    @SafeParcelable.Field(mo19514id = 2)
    private IBinder zapn;
    @SafeParcelable.Field(getter = "getConnectionResult", mo19514id = 3)
    private ConnectionResult zapo;
    @SafeParcelable.Field(getter = "getSaveDefaultAccount", mo19514id = 4)
    private boolean zapp;
    @SafeParcelable.Field(getter = "isFromCrossClientAuth", mo19514id = 5)
    private boolean zapq;

    @SafeParcelable.Constructor
    ResolveAccountResponse(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) IBinder iBinder, @SafeParcelable.Param(mo19517id = 3) ConnectionResult connectionResult, @SafeParcelable.Param(mo19517id = 4) boolean z, @SafeParcelable.Param(mo19517id = 5) boolean z2) {
        this.versionCode = i;
        this.zapn = iBinder;
        this.zapo = connectionResult;
        this.zapp = z;
        this.zapq = z2;
    }

    public ResolveAccountResponse(ConnectionResult connectionResult) {
        this(1, (IBinder) null, connectionResult, false, false);
    }

    public ResolveAccountResponse(int i) {
        this(new ConnectionResult(i, (PendingIntent) null));
    }

    public IAccountAccessor getAccountAccessor() {
        return IAccountAccessor.Stub.asInterface(this.zapn);
    }

    public ResolveAccountResponse setAccountAccessor(IAccountAccessor iAccountAccessor) {
        this.zapn = iAccountAccessor == null ? null : iAccountAccessor.asBinder();
        return this;
    }

    public ConnectionResult getConnectionResult() {
        return this.zapo;
    }

    public boolean getSaveDefaultAccount() {
        return this.zapp;
    }

    public ResolveAccountResponse setSaveDefaultAccount(boolean z) {
        this.zapp = z;
        return this;
    }

    public boolean isFromCrossClientAuth() {
        return this.zapq;
    }

    public ResolveAccountResponse setIsFromCrossClientAuth(boolean z) {
        this.zapq = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zapn, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getConnectionResult(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, getSaveDefaultAccount());
        SafeParcelWriter.writeBoolean(parcel, 5, isFromCrossClientAuth());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResolveAccountResponse)) {
            return false;
        }
        ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse) obj;
        return this.zapo.equals(resolveAccountResponse.zapo) && getAccountAccessor().equals(resolveAccountResponse.getAccountAccessor());
    }
}
