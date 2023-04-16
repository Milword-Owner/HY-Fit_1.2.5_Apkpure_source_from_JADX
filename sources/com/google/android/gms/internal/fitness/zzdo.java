package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.RecordingApi;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzdo implements RecordingApi {
    public final PendingResult<ListSubscriptionsResult> listSubscriptions(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzdr(this, googleApiClient));
    }

    public final PendingResult<ListSubscriptionsResult> listSubscriptions(GoogleApiClient googleApiClient, DataType dataType) {
        return googleApiClient.enqueue(new zzdq(this, googleApiClient, dataType));
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, Subscription subscription) {
        return googleApiClient.enqueue(new zzdt(this, googleApiClient, subscription));
    }

    public final PendingResult<Status> subscribe(GoogleApiClient googleApiClient, DataType dataType) {
        return zza(googleApiClient, new Subscription.zza().zza(dataType).zzr());
    }

    public final PendingResult<Status> subscribe(GoogleApiClient googleApiClient, DataSource dataSource) {
        return zza(googleApiClient, new Subscription.zza().zza(dataSource).zzr());
    }

    public final PendingResult<Status> unsubscribe(GoogleApiClient googleApiClient, DataType dataType) {
        return googleApiClient.execute(new zzds(this, googleApiClient, dataType));
    }

    public final PendingResult<Status> unsubscribe(GoogleApiClient googleApiClient, DataSource dataSource) {
        return googleApiClient.execute(new zzdv(this, googleApiClient, dataSource));
    }

    public final PendingResult<Status> unsubscribe(GoogleApiClient googleApiClient, Subscription subscription) {
        if (subscription.getDataType() == null) {
            return unsubscribe(googleApiClient, (DataSource) Preconditions.checkNotNull(subscription.getDataSource()));
        }
        return unsubscribe(googleApiClient, (DataType) Preconditions.checkNotNull(subscription.getDataType()));
    }
}
