package com.google.android.gms.fitness;

import android.annotation.SuppressLint;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public interface RecordingApi {
    @RecentlyNonNull
    PendingResult<ListSubscriptionsResult> listSubscriptions(@RecentlyNonNull GoogleApiClient googleApiClient);

    @RecentlyNonNull
    PendingResult<ListSubscriptionsResult> listSubscriptions(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataType dataType);

    @RecentlyNonNull
    @SuppressLint({"InlinedApi"})
    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"}, conditional = true)
    PendingResult<Status> subscribe(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataSource dataSource);

    @RecentlyNonNull
    @SuppressLint({"InlinedApi"})
    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"}, conditional = true)
    PendingResult<Status> subscribe(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataType dataType);

    @RecentlyNonNull
    PendingResult<Status> unsubscribe(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataSource dataSource);

    @RecentlyNonNull
    PendingResult<Status> unsubscribe(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataType dataType);

    @RecentlyNonNull
    PendingResult<Status> unsubscribe(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull Subscription subscription);
}
