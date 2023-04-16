package com.google.android.gms.fitness;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.internal.fitness.zzad;
import com.google.android.gms.internal.fitness.zzde;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class HistoryClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    private static final HistoryApi zzku = new zzde();

    @ShowFirstParty
    protected HistoryClient(@RecentlyNonNull Context context, @RecentlyNonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, zzad.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    HistoryClient(@NonNull Activity activity, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, zzad.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    public Task<DataReadResponse> readData(@RecentlyNonNull DataReadRequest dataReadRequest) {
        return PendingResultUtil.toResponseTask(zzku.readData(asGoogleApiClient(), dataReadRequest), new DataReadResponse());
    }

    @RecentlyNonNull
    public Task<DataSet> readDailyTotal(@RecentlyNonNull DataType dataType) {
        return PendingResultUtil.toTask(zzku.readDailyTotal(asGoogleApiClient(), dataType), zzi.zzjz);
    }

    @RecentlyNonNull
    public Task<DataSet> readDailyTotalFromLocalDevice(@RecentlyNonNull DataType dataType) {
        return PendingResultUtil.toTask(zzku.readDailyTotalFromLocalDevice(asGoogleApiClient(), dataType), zzj.zzjz);
    }

    @RecentlyNonNull
    public Task<Void> insertData(@RecentlyNonNull DataSet dataSet) {
        return PendingResultUtil.toVoidTask(zzku.insertData(asGoogleApiClient(), dataSet));
    }

    @RecentlyNonNull
    public Task<Void> deleteData(@RecentlyNonNull DataDeleteRequest dataDeleteRequest) {
        return PendingResultUtil.toVoidTask(zzku.deleteData(asGoogleApiClient(), dataDeleteRequest));
    }

    @RecentlyNonNull
    public Task<Void> updateData(@RecentlyNonNull DataUpdateRequest dataUpdateRequest) {
        return PendingResultUtil.toVoidTask(zzku.updateData(asGoogleApiClient(), dataUpdateRequest));
    }

    @RecentlyNonNull
    public Task<Void> registerDataUpdateListener(@RecentlyNonNull DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) {
        return PendingResultUtil.toVoidTask(zzku.registerDataUpdateListener(asGoogleApiClient(), dataUpdateListenerRegistrationRequest));
    }

    @RecentlyNonNull
    public Task<Void> unregisterDataUpdateListener(@RecentlyNonNull PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zzku.unregisterDataUpdateListener(asGoogleApiClient(), pendingIntent));
    }

    static final /* synthetic */ DataSet zza(DailyTotalResult dailyTotalResult) {
        return (DataSet) Preconditions.checkNotNull(dailyTotalResult.getTotal());
    }

    static final /* synthetic */ DataSet zzb(DailyTotalResult dailyTotalResult) {
        return (DataSet) Preconditions.checkNotNull(dailyTotalResult.getTotal());
    }
}
