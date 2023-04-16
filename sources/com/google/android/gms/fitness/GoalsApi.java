package com.google.android.gms.fitness;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.fitness.result.GoalsResult;

@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public interface GoalsApi {
    @RecentlyNonNull
    PendingResult<GoalsResult> readCurrentGoals(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull GoalsReadRequest goalsReadRequest);
}
