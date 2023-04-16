package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.result.DataTypeResult;
import com.google.android.gms.internal.fitness.zzcw;
import com.google.android.gms.internal.fitness.zzq;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class ConfigClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    private static final ConfigApi zzke = new zzcw();

    @ShowFirstParty
    protected ConfigClient(@RecentlyNonNull Context context, @RecentlyNonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, zzq.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    ConfigClient(@NonNull Activity activity, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, zzq.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    @Deprecated
    public Task<DataType> createCustomDataType(@RecentlyNonNull DataTypeCreateRequest dataTypeCreateRequest) {
        return PendingResultUtil.toTask(zzke.createCustomDataType(asGoogleApiClient(), dataTypeCreateRequest), zze.zzjz);
    }

    @RecentlyNonNull
    @Deprecated
    public Task<DataType> readDataType(@RecentlyNonNull String str) {
        return PendingResultUtil.toTask(zzke.readDataType(asGoogleApiClient(), str), zzd.zzjz);
    }

    @RecentlyNonNull
    public Task<Void> disableFit() {
        return PendingResultUtil.toVoidTask(zzke.disableFit(asGoogleApiClient()));
    }

    static final /* synthetic */ DataType zza(DataTypeResult dataTypeResult) {
        return (DataType) Preconditions.checkNotNull(dataTypeResult.getDataType());
    }

    static final /* synthetic */ DataType zzb(DataTypeResult dataTypeResult) {
        return (DataType) Preconditions.checkNotNull(dataTypeResult.getDataType());
    }
}
