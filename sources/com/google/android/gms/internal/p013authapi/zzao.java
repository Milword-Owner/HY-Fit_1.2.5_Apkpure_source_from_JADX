package com.google.android.gms.internal.p013authapi;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.identity.zzl;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.tasks.Task;

/* renamed from: com.google.android.gms.internal.auth-api.zzao */
/* compiled from: com.google.android.gms:play-services-auth@@19.0.0 */
public final class zzao extends GoogleApi<zzl> implements SignInClient {
    private static final Api<zzl> API = new Api<>("Auth.Api.Identity.SignIn.API", zzbn, CLIENT_KEY);
    private static final Api.ClientKey<zzaw> CLIENT_KEY = new Api.ClientKey<>();
    private static final Api.AbstractClientBuilder<zzaw, zzl> zzbn = new zzas();

    public zzao(@NonNull Context context, @NonNull zzl zzl) {
        super(context, API, zzl.zzc.zzc(zzl).zzh(zzba.zzw()).zzk(), GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public zzao(@NonNull Activity activity, @NonNull zzl zzl) {
        super(activity, API, zzl.zzc.zzc(zzl).zzh(zzba.zzw()).zzk(), GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task<BeginSignInResult> beginSignIn(@NonNull BeginSignInRequest beginSignInRequest) {
        BeginSignInRequest build = BeginSignInRequest.zzc(beginSignInRequest).zzd(((zzl) getApiOptions()).zzh()).build();
        return doRead(TaskApiCall.builder().setFeatures(zzay.zzdc).run(new zzar(this, build)).setAutoResolveMissingFeatures(false).build());
    }

    public final Task<Void> signOut() {
        getApplicationContext().getSharedPreferences("com.google.android.gms.signin", 0).edit().clear().apply();
        for (GoogleApiClient maybeSignOut : GoogleApiClient.getAllClients()) {
            maybeSignOut.maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
        return doRead(TaskApiCall.builder().setFeatures(zzay.zzdd).run(new zzaq(this)).setAutoResolveMissingFeatures(false).build());
    }

    public final SignInCredential getSignInCredentialFromIntent(@Nullable Intent intent) throws ApiException {
        if (intent != null) {
            Status status = (Status) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "status", Status.CREATOR);
            if (status == null) {
                throw new ApiException(Status.RESULT_CANCELED);
            } else if (status.isSuccess()) {
                SignInCredential signInCredential = (SignInCredential) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "sign_in_credential", SignInCredential.CREATOR);
                if (signInCredential != null) {
                    return signInCredential;
                }
                throw new ApiException(Status.RESULT_INTERNAL_ERROR);
            } else {
                throw new ApiException(status);
            }
        } else {
            throw new ApiException(Status.RESULT_INTERNAL_ERROR);
        }
    }

    public final Task<PendingIntent> getSignInIntent(@NonNull GetSignInIntentRequest getSignInIntentRequest) {
        GetSignInIntentRequest build = GetSignInIntentRequest.zzc(getSignInIntentRequest).zzf(((zzl) getApiOptions()).zzh()).build();
        return doRead(TaskApiCall.builder().setFeatures(zzay.zzdh).run(new zzat(this, build)).build());
    }
}
