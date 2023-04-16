package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "AccountChangeEventsRequestCreator")
public class AccountChangeEventsRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AccountChangeEventsRequest> CREATOR = new zzb();
    @SafeParcelable.VersionField(mo19520id = 1)
    private final int zze;
    @SafeParcelable.Field(mo19514id = 3)
    @Deprecated
    private String zzg;
    @SafeParcelable.Field(mo19514id = 2)
    private int zzi;
    @SafeParcelable.Field(mo19514id = 4)
    private Account zzk;

    @SafeParcelable.Constructor
    AccountChangeEventsRequest(@SafeParcelable.Param(mo19517id = 1) int i, @SafeParcelable.Param(mo19517id = 2) int i2, @SafeParcelable.Param(mo19517id = 3) String str, @SafeParcelable.Param(mo19517id = 4) Account account) {
        this.zze = i;
        this.zzi = i2;
        this.zzg = str;
        if (account != null || TextUtils.isEmpty(str)) {
            this.zzk = account;
        } else {
            this.zzk = new Account(str, "com.google");
        }
    }

    public AccountChangeEventsRequest() {
        this.zze = 1;
    }

    public AccountChangeEventsRequest setEventIndex(int i) {
        this.zzi = i;
        return this;
    }

    @Deprecated
    public AccountChangeEventsRequest setAccountName(String str) {
        this.zzg = str;
        return this;
    }

    @Deprecated
    public String getAccountName() {
        return this.zzg;
    }

    public AccountChangeEventsRequest setAccount(Account account) {
        this.zzk = account;
        return this;
    }

    public Account getAccount() {
        return this.zzk;
    }

    public int getEventIndex() {
        return this.zzi;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zze);
        SafeParcelWriter.writeInt(parcel, 2, this.zzi);
        SafeParcelWriter.writeString(parcel, 3, this.zzg, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzk, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
