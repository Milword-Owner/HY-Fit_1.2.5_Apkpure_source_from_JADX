package com.google.android.gms.auth.api.identity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SignInCredentialCreator")
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class SignInCredential extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SignInCredential> CREATOR = new zzm();
    @SafeParcelable.Field(getter = "getId", mo19514id = 1)
    private final String zzbf;
    @SafeParcelable.Field(getter = "getDisplayName", mo19514id = 2)
    @Nullable
    private final String zzbg;
    @SafeParcelable.Field(getter = "getGivenName", mo19514id = 3)
    @Nullable
    private final String zzbh;
    @SafeParcelable.Field(getter = "getFamilyName", mo19514id = 4)
    @Nullable
    private final String zzbi;
    @SafeParcelable.Field(getter = "getProfilePictureUri", mo19514id = 5)
    @Nullable
    private final Uri zzbj;
    @SafeParcelable.Field(getter = "getPassword", mo19514id = 6)
    @Nullable
    private final String zzbk;
    @SafeParcelable.Field(getter = "getGoogleIdToken", mo19514id = 7)
    @Nullable
    private final String zzbl;

    @SafeParcelable.Constructor
    public SignInCredential(@SafeParcelable.Param(mo19517id = 1) String str, @SafeParcelable.Param(mo19517id = 2) @Nullable String str2, @SafeParcelable.Param(mo19517id = 3) @Nullable String str3, @SafeParcelable.Param(mo19517id = 4) @Nullable String str4, @SafeParcelable.Param(mo19517id = 5) @Nullable Uri uri, @SafeParcelable.Param(mo19517id = 6) @Nullable String str5, @SafeParcelable.Param(mo19517id = 7) @Nullable String str6) {
        this.zzbf = Preconditions.checkNotEmpty(str);
        this.zzbg = str2;
        this.zzbh = str3;
        this.zzbi = str4;
        this.zzbj = uri;
        this.zzbk = str5;
        this.zzbl = str6;
    }

    public final String getId() {
        return this.zzbf;
    }

    @Nullable
    public final String getDisplayName() {
        return this.zzbg;
    }

    @Nullable
    public final String getGivenName() {
        return this.zzbh;
    }

    @Nullable
    public final String getFamilyName() {
        return this.zzbi;
    }

    @Nullable
    public final Uri getProfilePictureUri() {
        return this.zzbj;
    }

    @Nullable
    public final String getPassword() {
        return this.zzbk;
    }

    @Nullable
    public final String getGoogleIdToken() {
        return this.zzbl;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzbf, this.zzbg, this.zzbh, this.zzbi, this.zzbj, this.zzbk, this.zzbl);
    }

    public final boolean equals(@Nullable Object obj) {
        if (!(obj instanceof SignInCredential)) {
            return false;
        }
        SignInCredential signInCredential = (SignInCredential) obj;
        if (!Objects.equal(this.zzbf, signInCredential.zzbf) || !Objects.equal(this.zzbg, signInCredential.zzbg) || !Objects.equal(this.zzbh, signInCredential.zzbh) || !Objects.equal(this.zzbi, signInCredential.zzbi) || !Objects.equal(this.zzbj, signInCredential.zzbj) || !Objects.equal(this.zzbk, signInCredential.zzbk) || !Objects.equal(this.zzbl, signInCredential.zzbl)) {
            return false;
        }
        return true;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getId(), false);
        SafeParcelWriter.writeString(parcel, 2, getDisplayName(), false);
        SafeParcelWriter.writeString(parcel, 3, getGivenName(), false);
        SafeParcelWriter.writeString(parcel, 4, getFamilyName(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getProfilePictureUri(), i, false);
        SafeParcelWriter.writeString(parcel, 6, getPassword(), false);
        SafeParcelWriter.writeString(parcel, 7, getGoogleIdToken(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
