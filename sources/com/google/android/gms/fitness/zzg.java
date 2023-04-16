package com.google.android.gms.fitness;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.PlatformVersion;

@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzg implements Api.ApiOptions.HasGoogleSignInAccountOptions {
    @Nullable
    private final GoogleSignInAccount zzkm;

    public zzg(Context context, GoogleSignInAccount googleSignInAccount) {
        if ("<<default account>>".equals(googleSignInAccount.getEmail())) {
            if (PlatformVersion.isAtLeastLollipop() && context.getPackageManager().hasSystemFeature("cn.google")) {
                this.zzkm = null;
                return;
            }
        }
        this.zzkm = googleSignInAccount;
    }

    @Nullable
    public final GoogleSignInAccount getGoogleSignInAccount() {
        return this.zzkm;
    }

    public final int hashCode() {
        GoogleSignInAccount googleSignInAccount = this.zzkm;
        if (googleSignInAccount != null) {
            return googleSignInAccount.hashCode();
        }
        return 0;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != this) {
            return (obj instanceof zzg) && Objects.equal(((zzg) obj).zzkm, this.zzkm);
        }
        return true;
    }
}
