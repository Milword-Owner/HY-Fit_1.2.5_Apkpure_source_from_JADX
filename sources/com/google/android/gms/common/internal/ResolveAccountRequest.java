package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ResolveAccountRequestCreator")
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public class ResolveAccountRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ResolveAccountRequest> CREATOR = new zam();
    @SafeParcelable.VersionField(mo19520id = 1)
    private final int zali;
    @SafeParcelable.Field(getter = "getSessionId", mo19514id = 3)
    private final int zapl;
    @SafeParcelable.Field(getter = "getSignInAccountHint", mo19514id = 4)
    private final GoogleSignInAccount zapm;
    @SafeParcelable.Field(getter = "getAccount", mo19514id = 2)
    private final Account zax;

    @SafeParcelable.Constructor
    ResolveAccountRequest(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) Account account, @SafeParcelable.Param(mo19517id = 3) int i2, @SafeParcelable.Param(mo19517id = 4) GoogleSignInAccount googleSignInAccount) {
        this.zali = i;
        this.zax = account;
        this.zapl = i2;
        this.zapm = googleSignInAccount;
    }

    public ResolveAccountRequest(Account account, int i, GoogleSignInAccount googleSignInAccount) {
        this(2, account, i, googleSignInAccount);
    }

    public Account getAccount() {
        return this.zax;
    }

    public int getSessionId() {
        return this.zapl;
    }

    @Nullable
    public GoogleSignInAccount getSignInAccountHint() {
        return this.zapm;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zali);
        SafeParcelWriter.writeParcelable(parcel, 2, getAccount(), i, false);
        SafeParcelWriter.writeInt(parcel, 3, getSessionId());
        SafeParcelWriter.writeParcelable(parcel, 4, getSignInAccountHint(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
