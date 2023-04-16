package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

@SafeParcelable.Class(creator = "AuthAccountRequestCreator")
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public class AuthAccountRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AuthAccountRequest> CREATOR = new zaa();
    @SafeParcelable.VersionField(mo19520id = 1)
    private final int zali;
    @SafeParcelable.Field(mo19514id = 2)
    @Deprecated
    private final IBinder zaob;
    @SafeParcelable.Field(mo19514id = 3)
    private final Scope[] zaoc;
    @SafeParcelable.Field(mo19514id = 4)
    private Integer zaod;
    @SafeParcelable.Field(mo19514id = 5)
    private Integer zaoe;
    @SafeParcelable.Field(mo19514id = 6, type = "android.accounts.Account")
    private Account zax;

    @SafeParcelable.Constructor
    AuthAccountRequest(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) IBinder iBinder, @SafeParcelable.Param(mo19517id = 3) Scope[] scopeArr, @SafeParcelable.Param(mo19517id = 4) Integer num, @SafeParcelable.Param(mo19517id = 5) Integer num2, @SafeParcelable.Param(mo19517id = 6) Account account) {
        this.zali = i;
        this.zaob = iBinder;
        this.zaoc = scopeArr;
        this.zaod = num;
        this.zaoe = num2;
        this.zax = account;
    }

    @Deprecated
    public AuthAccountRequest(IAccountAccessor iAccountAccessor, Set<Scope> set) {
        this(3, iAccountAccessor.asBinder(), (Scope[]) set.toArray(new Scope[set.size()]), (Integer) null, (Integer) null, (Account) null);
    }

    public AuthAccountRequest(Account account, Set<Scope> set) {
        this(3, (IBinder) null, (Scope[]) set.toArray(new Scope[set.size()]), (Integer) null, (Integer) null, (Account) Preconditions.checkNotNull(account));
    }

    public Account getAccount() {
        Account account = this.zax;
        if (account != null) {
            return account;
        }
        IBinder iBinder = this.zaob;
        if (iBinder != null) {
            return AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(iBinder));
        }
        return null;
    }

    public Set<Scope> getScopes() {
        return new HashSet(Arrays.asList(this.zaoc));
    }

    public AuthAccountRequest setOauthPolicy(@Nullable Integer num) {
        this.zaod = num;
        return this;
    }

    @Nullable
    public Integer getOauthPolicy() {
        return this.zaod;
    }

    public AuthAccountRequest setPolicyAction(@Nullable Integer num) {
        this.zaoe = num;
        return this;
    }

    @Nullable
    public Integer getPolicyAction() {
        return this.zaoe;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zali);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zaob, false);
        SafeParcelWriter.writeTypedArray(parcel, 3, this.zaoc, i, false);
        SafeParcelWriter.writeIntegerObject(parcel, 4, this.zaod, false);
        SafeParcelWriter.writeIntegerObject(parcel, 5, this.zaoe, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zax, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
