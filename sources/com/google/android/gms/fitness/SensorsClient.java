package com.google.android.gms.fitness;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.internal.fitness.zzap;
import com.google.android.gms.internal.fitness.zzdx;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class SensorsClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    private static final SensorsApi zzkw = new zzdx();

    @ShowFirstParty
    protected SensorsClient(@RecentlyNonNull Context context, @RecentlyNonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, zzap.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    SensorsClient(@NonNull Activity activity, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, zzap.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    public Task<List<DataSource>> findDataSources(@RecentlyNonNull DataSourcesRequest dataSourcesRequest) {
        return PendingResultUtil.toTask(zzkw.findDataSources(asGoogleApiClient(), dataSourcesRequest), zzm.zzjz);
    }

    @RecentlyNonNull
    @SuppressLint({"InlinedApi"})
    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"}, conditional = true)
    public Task<Void> add(@RecentlyNonNull SensorRequest sensorRequest, @RecentlyNonNull OnDataPointListener onDataPointListener) {
        ListenerHolder registerListener = registerListener(onDataPointListener, OnDataPointListener.class.getSimpleName());
        return doRegisterEventListener(RegistrationMethods.builder().withHolder(registerListener).register(new zzn(this, registerListener, sensorRequest)).unregister(new zzo(this, registerListener)).build());
    }

    @RecentlyNonNull
    @SuppressLint({"InlinedApi"})
    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"}, conditional = true)
    public Task<Void> add(@RecentlyNonNull SensorRequest sensorRequest, @RecentlyNonNull PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zzkw.add(asGoogleApiClient(), sensorRequest, pendingIntent));
    }

    @RecentlyNonNull
    public Task<Boolean> remove(@RecentlyNonNull OnDataPointListener onDataPointListener) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(onDataPointListener, OnDataPointListener.class.getSimpleName()));
    }

    @RecentlyNonNull
    public Task<Void> remove(@RecentlyNonNull PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zzkw.remove(asGoogleApiClient(), pendingIntent));
    }
}
