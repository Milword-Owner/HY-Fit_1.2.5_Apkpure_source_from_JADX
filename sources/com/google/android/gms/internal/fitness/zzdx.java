package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.SensorsApi;
import com.google.android.gms.fitness.data.zzv;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.zzam;
import com.google.android.gms.fitness.request.zzan;
import com.google.android.gms.fitness.result.DataSourcesResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzdx implements SensorsApi {
    public final PendingResult<DataSourcesResult> findDataSources(GoogleApiClient googleApiClient, DataSourcesRequest dataSourcesRequest) {
        return googleApiClient.enqueue(new zzdw(this, googleApiClient, dataSourcesRequest));
    }

    public final PendingResult<Status> add(GoogleApiClient googleApiClient, SensorRequest sensorRequest, OnDataPointListener onDataPointListener) {
        return zza(googleApiClient, sensorRequest, zzan.zzx().zza(onDataPointListener, googleApiClient.getLooper()), (PendingIntent) null);
    }

    public final PendingResult<Status> add(GoogleApiClient googleApiClient, SensorRequest sensorRequest, PendingIntent pendingIntent) {
        return zza(googleApiClient, sensorRequest, (zzv) null, pendingIntent);
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, SensorRequest sensorRequest, @Nullable zzv zzv, @Nullable PendingIntent pendingIntent) {
        return googleApiClient.enqueue(new zzdz(this, googleApiClient, sensorRequest, zzv, pendingIntent));
    }

    public final PendingResult<Status> remove(GoogleApiClient googleApiClient, OnDataPointListener onDataPointListener) {
        zzam zzb = zzan.zzx().zzb(onDataPointListener, googleApiClient.getLooper());
        if (zzb == null) {
            return PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, googleApiClient);
        }
        return zza(googleApiClient, zzb, (PendingIntent) null);
    }

    public final PendingResult<Status> remove(GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        return zza(googleApiClient, (zzv) null, pendingIntent);
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, @Nullable zzv zzv, @Nullable PendingIntent pendingIntent) {
        return googleApiClient.execute(new zzdy(this, googleApiClient, zzv, pendingIntent));
    }
}
