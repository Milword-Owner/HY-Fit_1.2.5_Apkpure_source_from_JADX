package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.fitness.data.Goal;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.internal.fitness.zzdd;
import com.google.android.gms.internal.fitness.zzw;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class GoalsClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    private static final GoalsApi zzkn = new zzdd();

    @ShowFirstParty
    protected GoalsClient(@RecentlyNonNull Context context, @RecentlyNonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, zzw.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    GoalsClient(@NonNull Activity activity, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, zzw.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    public Task<List<Goal>> readCurrentGoals(@RecentlyNonNull GoalsReadRequest goalsReadRequest) {
        return PendingResultUtil.toTask(zzkn.readCurrentGoals(asGoogleApiClient(), goalsReadRequest), zzh.zzjz);
    }
}
