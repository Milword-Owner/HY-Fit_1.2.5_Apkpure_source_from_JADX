package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.p013authapi.zzaz;

@KeepName
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public class SignInHubActivity extends FragmentActivity {
    private static boolean zzcw = false;
    private boolean zzcx = false;
    private SignInConfiguration zzcy;
    private boolean zzcz;
    /* access modifiers changed from: private */
    public int zzda;
    /* access modifiers changed from: private */
    public Intent zzdb;

    /* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
    private class zzc implements LoaderManager.LoaderCallbacks<Void> {
        private zzc() {
        }

        public final void onLoaderReset(Loader<Void> loader) {
        }

        public final Loader<Void> onCreateLoader(int i, Bundle bundle) {
            return new zzd(SignInHubActivity.this, GoogleApiClient.getAllClients());
        }

        public final /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
            Void voidR = (Void) obj;
            SignInHubActivity signInHubActivity = SignInHubActivity.this;
            signInHubActivity.setResult(signInHubActivity.zzda, SignInHubActivity.this.zzdb);
            SignInHubActivity.this.finish();
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String str = (String) zzaz.checkNotNull(intent.getAction());
        if ("com.google.android.gms.auth.NO_IMPL".equals(str)) {
            zzc((int) GoogleSignInStatusCodes.SIGN_IN_FAILED);
        } else if (str.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") || str.equals("com.google.android.gms.auth.APPAUTH_SIGN_IN")) {
            SignInConfiguration signInConfiguration = (SignInConfiguration) ((Bundle) zzaz.checkNotNull(intent.getBundleExtra("config"))).getParcelable("config");
            if (signInConfiguration == null) {
                Log.e("AuthSignInClient", "Activity started with invalid configuration.");
                setResult(0);
                finish();
                return;
            }
            this.zzcy = signInConfiguration;
            if (bundle != null) {
                this.zzcz = bundle.getBoolean("signingInGoogleApiClients");
                if (this.zzcz) {
                    this.zzda = bundle.getInt("signInResultCode");
                    this.zzdb = (Intent) zzaz.checkNotNull((Intent) bundle.getParcelable("signInResultData"));
                    zzv();
                }
            } else if (zzcw) {
                setResult(0);
                zzc((int) GoogleSignInStatusCodes.SIGN_IN_CURRENTLY_IN_PROGRESS);
            } else {
                zzcw = true;
                Intent intent2 = new Intent(str);
                if (str.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN")) {
                    intent2.setPackage("com.google.android.gms");
                } else {
                    intent2.setPackage(getPackageName());
                }
                intent2.putExtra("config", this.zzcy);
                try {
                    startActivityForResult(intent2, 40962);
                } catch (ActivityNotFoundException unused) {
                    this.zzcx = true;
                    Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
                    zzc(17);
                }
            }
        } else {
            String valueOf = String.valueOf(intent.getAction());
            Log.e("AuthSignInClient", valueOf.length() != 0 ? "Unknown action: ".concat(valueOf) : new String("Unknown action: "));
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.zzcz);
        if (this.zzcz) {
            bundle.putInt("signInResultCode", this.zzda);
            bundle.putParcelable("signInResultData", this.zzdb);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        if (!this.zzcx) {
            setResult(0);
            if (i == 40962) {
                if (intent != null) {
                    SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                    if (signInAccount != null && signInAccount.getGoogleSignInAccount() != null) {
                        GoogleSignInAccount googleSignInAccount = signInAccount.getGoogleSignInAccount();
                        zzq.zzd(this).zzc(this.zzcy.zzu(), (GoogleSignInAccount) zzaz.checkNotNull(googleSignInAccount));
                        intent.removeExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                        intent.putExtra("googleSignInAccount", googleSignInAccount);
                        this.zzcz = true;
                        this.zzda = i2;
                        this.zzdb = intent;
                        zzv();
                        return;
                    } else if (intent.hasExtra("errorCode")) {
                        int intExtra = intent.getIntExtra("errorCode", 8);
                        if (intExtra == 13) {
                            intExtra = GoogleSignInStatusCodes.SIGN_IN_CANCELLED;
                        }
                        zzc(intExtra);
                        return;
                    }
                }
                zzc(8);
            }
        }
    }

    private final void zzv() {
        getSupportLoaderManager().initLoader(0, (Bundle) null, new zzc());
        zzcw = false;
    }

    private final void zzc(int i) {
        Status status = new Status(i);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
        zzcw = false;
    }
}
