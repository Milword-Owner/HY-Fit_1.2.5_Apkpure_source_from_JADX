package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public class Storage {
    private static final Lock zaai = new ReentrantLock();
    @GuardedBy("sLk")
    private static Storage zaaj;
    private final Lock zaak = new ReentrantLock();
    @GuardedBy("mLk")
    private final SharedPreferences zaal;

    @KeepForSdk
    public static Storage getInstance(Context context) {
        Preconditions.checkNotNull(context);
        zaai.lock();
        try {
            if (zaaj == null) {
                zaaj = new Storage(context.getApplicationContext());
            }
            return zaaj;
        } finally {
            zaai.unlock();
        }
    }

    @VisibleForTesting
    private Storage(Context context) {
        this.zaal = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    @KeepForSdk
    public void saveDefaultGoogleSignInAccount(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        zaa("defaultGoogleSignInAccount", googleSignInAccount.zab());
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        String zab = googleSignInAccount.zab();
        zaa(zab("googleSignInAccount", zab), googleSignInAccount.zac());
        zaa(zab("googleSignInOptions", zab), googleSignInOptions.zae());
    }

    private final void zaa(String str, String str2) {
        this.zaak.lock();
        try {
            this.zaal.edit().putString(str, str2).apply();
        } finally {
            this.zaak.unlock();
        }
    }

    @KeepForSdk
    @Nullable
    public GoogleSignInAccount getSavedDefaultGoogleSignInAccount() {
        return zad(zaf("defaultGoogleSignInAccount"));
    }

    @Nullable
    @VisibleForTesting
    private final GoogleSignInAccount zad(String str) {
        String zaf;
        if (!TextUtils.isEmpty(str) && (zaf = zaf(zab("googleSignInAccount", str))) != null) {
            try {
                return GoogleSignInAccount.zaa(zaf);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    @KeepForSdk
    @Nullable
    public GoogleSignInOptions getSavedDefaultGoogleSignInOptions() {
        return zae(zaf("defaultGoogleSignInAccount"));
    }

    @Nullable
    @VisibleForTesting
    private final GoogleSignInOptions zae(String str) {
        String zaf;
        if (!TextUtils.isEmpty(str) && (zaf = zaf(zab("googleSignInOptions", str))) != null) {
            try {
                return GoogleSignInOptions.zab(zaf);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    @KeepForSdk
    @Nullable
    public String getSavedRefreshToken() {
        return zaf("refreshToken");
    }

    @Nullable
    private final String zaf(String str) {
        this.zaak.lock();
        try {
            return this.zaal.getString(str, (String) null);
        } finally {
            this.zaak.unlock();
        }
    }

    public final void zaf() {
        String zaf = zaf("defaultGoogleSignInAccount");
        zag("defaultGoogleSignInAccount");
        if (!TextUtils.isEmpty(zaf)) {
            zag(zab("googleSignInAccount", zaf));
            zag(zab("googleSignInOptions", zaf));
        }
    }

    private final void zag(String str) {
        this.zaak.lock();
        try {
            this.zaal.edit().remove(str).apply();
        } finally {
            this.zaak.unlock();
        }
    }

    @KeepForSdk
    public void clear() {
        this.zaak.lock();
        try {
            this.zaal.edit().clear().apply();
        } finally {
            this.zaak.unlock();
        }
    }

    private static String zab(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
        sb.append(str2);
        return sb.toString();
    }
}
