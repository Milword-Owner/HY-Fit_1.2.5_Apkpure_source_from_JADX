package com.google.android.gms.auth.api.signin.internal;

import android.content.Intent;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class zzg implements GoogleSignInApi {
    public final Intent getSignInIntent(GoogleApiClient googleApiClient) {
        return zzi.zzc(googleApiClient.getContext(), zzc(googleApiClient));
    }

    public final OptionalPendingResult<GoogleSignInResult> silentSignIn(GoogleApiClient googleApiClient) {
        return zzi.zzc(googleApiClient, googleApiClient.getContext(), zzc(googleApiClient), false);
    }

    public final PendingResult<Status> signOut(GoogleApiClient googleApiClient) {
        return zzi.zzc(googleApiClient, googleApiClient.getContext(), false);
    }

    public final PendingResult<Status> revokeAccess(GoogleApiClient googleApiClient) {
        return zzi.zzd(googleApiClient, googleApiClient.getContext(), false);
    }

    @Nullable
    public final GoogleSignInResult getSignInResultFromIntent(Intent intent) {
        return zzi.getSignInResultFromIntent(intent);
    }

    private static GoogleSignInOptions zzc(GoogleApiClient googleApiClient) {
        return ((zzf) googleApiClient.getClient(Auth.zzh)).zzo();
    }
}
