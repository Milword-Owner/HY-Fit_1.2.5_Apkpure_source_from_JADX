package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.util.VisibleForTesting;

/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class zzq {
    @Nullable
    private static zzq zzcq;
    @VisibleForTesting
    private Storage zzcr;
    @Nullable
    @VisibleForTesting
    private GoogleSignInAccount zzcs = this.zzcr.getSavedDefaultGoogleSignInAccount();
    @Nullable
    @VisibleForTesting
    private GoogleSignInOptions zzct = this.zzcr.getSavedDefaultGoogleSignInOptions();

    private zzq(Context context) {
        this.zzcr = Storage.getInstance(context);
    }

    public static synchronized zzq zzd(@NonNull Context context) {
        zzq zze;
        synchronized (zzq.class) {
            zze = zze(context.getApplicationContext());
        }
        return zze;
    }

    private static synchronized zzq zze(Context context) {
        synchronized (zzq.class) {
            if (zzcq != null) {
                zzq zzq = zzcq;
                return zzq;
            }
            zzq zzq2 = new zzq(context);
            zzcq = zzq2;
            return zzq2;
        }
    }

    public final synchronized void clear() {
        this.zzcr.clear();
        this.zzcs = null;
        this.zzct = null;
    }

    public final synchronized void zzc(GoogleSignInOptions googleSignInOptions, GoogleSignInAccount googleSignInAccount) {
        this.zzcr.saveDefaultGoogleSignInAccount(googleSignInAccount, googleSignInOptions);
        this.zzcs = googleSignInAccount;
        this.zzct = googleSignInOptions;
    }

    @Nullable
    public final synchronized GoogleSignInAccount zzr() {
        return this.zzcs;
    }

    @Nullable
    public final synchronized GoogleSignInOptions zzs() {
        return this.zzct;
    }
}
