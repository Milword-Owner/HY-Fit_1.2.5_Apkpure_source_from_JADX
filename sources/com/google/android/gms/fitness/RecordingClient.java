package com.google.android.gms.fitness;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.fitness.zzaj;
import com.google.android.gms.internal.fitness.zzdo;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class RecordingClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    private static final RecordingApi zzkv = new zzdo();

    @ShowFirstParty
    protected RecordingClient(@RecentlyNonNull Context context, @RecentlyNonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, zzaj.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    RecordingClient(@NonNull Activity activity, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, zzaj.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    @SuppressLint({"InlinedApi"})
    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"}, conditional = true)
    public Task<Void> subscribe(@RecentlyNonNull DataType dataType) {
        return PendingResultUtil.toVoidTask(zzkv.subscribe(asGoogleApiClient(), dataType));
    }

    @RecentlyNonNull
    @SuppressLint({"InlinedApi"})
    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"}, conditional = true)
    public Task<Void> subscribe(@RecentlyNonNull DataSource dataSource) {
        return PendingResultUtil.toVoidTask(zzkv.subscribe(asGoogleApiClient(), dataSource));
    }

    @RecentlyNonNull
    public Task<Void> unsubscribe(@RecentlyNonNull DataType dataType) {
        return PendingResultUtil.toVoidTask(zzkv.unsubscribe(asGoogleApiClient(), dataType));
    }

    @RecentlyNonNull
    public Task<Void> unsubscribe(@RecentlyNonNull DataSource dataSource) {
        return PendingResultUtil.toVoidTask(zzkv.unsubscribe(asGoogleApiClient(), dataSource));
    }

    @RecentlyNonNull
    public Task<Void> unsubscribe(@RecentlyNonNull Subscription subscription) {
        return PendingResultUtil.toVoidTask(zzkv.unsubscribe(asGoogleApiClient(), subscription));
    }

    @RecentlyNonNull
    public Task<List<Subscription>> listSubscriptions() {
        return PendingResultUtil.toTask(zzkv.listSubscriptions(asGoogleApiClient()), zzk.zzjz);
    }

    @RecentlyNonNull
    public Task<List<Subscription>> listSubscriptions(@RecentlyNonNull DataType dataType) {
        return PendingResultUtil.toTask(zzkv.listSubscriptions(asGoogleApiClient(), dataType), zzl.zzjz);
    }
}
