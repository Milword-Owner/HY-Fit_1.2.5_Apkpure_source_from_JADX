package com.google.android.gms.fitness;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResponse;
import com.google.android.gms.internal.fitness.zzav;
import com.google.android.gms.internal.fitness.zzeb;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class SessionsClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    private static final SessionsApi zzla = new zzeb();

    @ShowFirstParty
    protected SessionsClient(@RecentlyNonNull Context context, @RecentlyNonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, zzav.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    SessionsClient(@NonNull Activity activity, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, zzav.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    public Task<Void> startSession(@RecentlyNonNull Session session) {
        return PendingResultUtil.toVoidTask(zzla.startSession(asGoogleApiClient(), session));
    }

    @RecentlyNonNull
    public Task<List<Session>> stopSession(@Nullable String str) {
        return PendingResultUtil.toTask(zzla.stopSession(asGoogleApiClient(), str), zzp.zzjz);
    }

    @RecentlyNonNull
    public Task<Void> insertSession(@RecentlyNonNull SessionInsertRequest sessionInsertRequest) {
        return PendingResultUtil.toVoidTask(zzla.insertSession(asGoogleApiClient(), sessionInsertRequest));
    }

    @RecentlyNonNull
    public Task<SessionReadResponse> readSession(@RecentlyNonNull SessionReadRequest sessionReadRequest) {
        return PendingResultUtil.toResponseTask(zzla.readSession(asGoogleApiClient(), sessionReadRequest), new SessionReadResponse());
    }

    @RecentlyNonNull
    public Task<Void> registerForSessions(@RecentlyNonNull PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zzla.registerForSessions(asGoogleApiClient(), pendingIntent));
    }

    @RecentlyNonNull
    public Task<Void> unregisterForSessions(@RecentlyNonNull PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zzla.unregisterForSessions(asGoogleApiClient(), pendingIntent));
    }
}
