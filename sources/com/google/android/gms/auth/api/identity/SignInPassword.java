package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SignInPasswordCreator")
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public class SignInPassword extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SignInPassword> CREATOR = new zzo();
    @SafeParcelable.Field(getter = "getId", mo19514id = 1)
    private final String zzbf;
    @SafeParcelable.Field(getter = "getPassword", mo19514id = 2)
    private final String zzbk;

    @SafeParcelable.Constructor
    public SignInPassword(@SafeParcelable.Param(mo19517id = 1) String str, @SafeParcelable.Param(mo19517id = 2) String str2) {
        this.zzbf = Preconditions.checkNotEmpty(((String) Preconditions.checkNotNull(str, "Account identifier cannot be null")).trim(), "Account identifier cannot be empty");
        this.zzbk = Preconditions.checkNotEmpty(str2);
    }

    public String getId() {
        return this.zzbf;
    }

    public String getPassword() {
        return this.zzbk;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzbf, this.zzbk);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof SignInPassword)) {
            return false;
        }
        SignInPassword signInPassword = (SignInPassword) obj;
        if (!Objects.equal(this.zzbf, signInPassword.zzbf) || !Objects.equal(this.zzbk, signInPassword.zzbk)) {
            return false;
        }
        return true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getId(), false);
        SafeParcelWriter.writeString(parcel, 2, getPassword(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
