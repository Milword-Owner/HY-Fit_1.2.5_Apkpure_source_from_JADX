package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SignInConfigurationCreator")
@SafeParcelable.Reserved({1})
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class SignInConfiguration extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<SignInConfiguration> CREATOR = new zzx();
    @SafeParcelable.Field(getter = "getConsumerPkgName", mo19514id = 2)
    private final String zzcu;
    @SafeParcelable.Field(getter = "getGoogleConfig", mo19514id = 5)
    private GoogleSignInOptions zzcv;

    @SafeParcelable.Constructor
    public SignInConfiguration(@SafeParcelable.Param(mo19517id = 2) String str, @SafeParcelable.Param(mo19517id = 5) GoogleSignInOptions googleSignInOptions) {
        this.zzcu = Preconditions.checkNotEmpty(str);
        this.zzcv = googleSignInOptions;
    }

    public final GoogleSignInOptions zzu() {
        return this.zzcv;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzcu, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzcv, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(@Nullable Object obj) {
        if (!(obj instanceof SignInConfiguration)) {
            return false;
        }
        SignInConfiguration signInConfiguration = (SignInConfiguration) obj;
        if (this.zzcu.equals(signInConfiguration.zzcu)) {
            GoogleSignInOptions googleSignInOptions = this.zzcv;
            if (googleSignInOptions == null) {
                if (signInConfiguration.zzcv == null) {
                    return true;
                }
            } else if (googleSignInOptions.equals(signInConfiguration.zzcv)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return new HashAccumulator().addObject(this.zzcu).addObject(this.zzcv).hash();
    }
}
