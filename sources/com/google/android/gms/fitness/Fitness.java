package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.fitness.zzad;
import com.google.android.gms.internal.fitness.zzaj;
import com.google.android.gms.internal.fitness.zzap;
import com.google.android.gms.internal.fitness.zzav;
import com.google.android.gms.internal.fitness.zzco;
import com.google.android.gms.internal.fitness.zzcw;
import com.google.android.gms.internal.fitness.zzdd;
import com.google.android.gms.internal.fitness.zzde;
import com.google.android.gms.internal.fitness.zzdo;
import com.google.android.gms.internal.fitness.zzdx;
import com.google.android.gms.internal.fitness.zzeb;
import com.google.android.gms.internal.fitness.zzen;
import com.google.android.gms.internal.fitness.zzk;
import com.google.android.gms.internal.fitness.zzq;
import com.google.android.gms.internal.fitness.zzw;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class Fitness {
    @RecentlyNonNull
    public static final String ACTION_TRACK = "vnd.google.fitness.TRACK";
    @RecentlyNonNull
    public static final String ACTION_VIEW = "vnd.google.fitness.VIEW";
    @RecentlyNonNull
    public static final String ACTION_VIEW_GOAL = "vnd.google.fitness.VIEW_GOAL";
    @RecentlyNonNull
    @Deprecated
    public static final Void API = null;
    @RecentlyNonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> BLE_API = zzk.API;
    @RecentlyNonNull
    @Deprecated
    public static final BleApi BleApi;
    @RecentlyNonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> CONFIG_API = zzq.API;
    @RecentlyNonNull
    @Deprecated
    public static final ConfigApi ConfigApi = new zzcw();
    @RecentlyNonNull
    public static final String EXTRA_END_TIME = "vnd.google.fitness.end_time";
    @RecentlyNonNull
    public static final String EXTRA_START_TIME = "vnd.google.fitness.start_time";
    @RecentlyNonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> GOALS_API = zzw.API;
    @RecentlyNonNull
    @Deprecated
    public static final GoalsApi GoalsApi = new zzdd();
    @RecentlyNonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> HISTORY_API = zzad.API;
    @RecentlyNonNull
    @Deprecated
    public static final HistoryApi HistoryApi = new zzde();
    @RecentlyNonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> RECORDING_API = zzaj.API;
    @RecentlyNonNull
    @Deprecated
    public static final RecordingApi RecordingApi = new zzdo();
    @RecentlyNonNull
    public static final Scope SCOPE_ACTIVITY_READ = new Scope(Scopes.FITNESS_ACTIVITY_READ);
    @RecentlyNonNull
    public static final Scope SCOPE_ACTIVITY_READ_WRITE = new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE);
    @RecentlyNonNull
    public static final Scope SCOPE_BODY_READ = new Scope(Scopes.FITNESS_BODY_READ);
    @RecentlyNonNull
    public static final Scope SCOPE_BODY_READ_WRITE = new Scope(Scopes.FITNESS_BODY_READ_WRITE);
    @RecentlyNonNull
    public static final Scope SCOPE_LOCATION_READ = new Scope(Scopes.FITNESS_LOCATION_READ);
    @RecentlyNonNull
    public static final Scope SCOPE_LOCATION_READ_WRITE = new Scope(Scopes.FITNESS_LOCATION_READ_WRITE);
    @RecentlyNonNull
    public static final Scope SCOPE_NUTRITION_READ = new Scope(Scopes.FITNESS_NUTRITION_READ);
    @RecentlyNonNull
    public static final Scope SCOPE_NUTRITION_READ_WRITE = new Scope(Scopes.FITNESS_NUTRITION_READ_WRITE);
    @RecentlyNonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> SENSORS_API = zzap.API;
    @RecentlyNonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> SESSIONS_API = zzav.API;
    @RecentlyNonNull
    @Deprecated
    public static final SensorsApi SensorsApi = new zzdx();
    @RecentlyNonNull
    @Deprecated
    public static final SessionsApi SessionsApi = new zzeb();
    @ShowFirstParty
    private static final Scope zzkf = new Scope("https://www.googleapis.com/auth/fitness.heart_rate.read");
    @ShowFirstParty
    private static final Scope zzkg = new Scope("https://www.googleapis.com/auth/fitness.heart_rate.write");
    @ShowFirstParty
    private static final Scope zzkh = new Scope("https://www.googleapis.com/auth/fitness.respiratory_rate.read");
    @ShowFirstParty
    private static final Scope zzki = new Scope("https://www.googleapis.com/auth/fitness.respiratory_rate.write");
    @ShowFirstParty
    private static final Scope zzkj = new Scope("https://www.googleapis.com/auth/fitness.sleep.read");
    @ShowFirstParty
    private static final Scope zzkk = new Scope("https://www.googleapis.com/auth/fitness.sleep.write");

    private Fitness() {
    }

    @RecentlyNonNull
    public static SensorsClient getSensorsClient(@RecentlyNonNull Activity activity, @RecentlyNonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SensorsClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(activity, googleSignInAccount));
    }

    @RecentlyNonNull
    public static SensorsClient getSensorsClient(@RecentlyNonNull Context context, @RecentlyNonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SensorsClient(context, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(context, googleSignInAccount));
    }

    @RecentlyNonNull
    public static RecordingClient getRecordingClient(@RecentlyNonNull Activity activity, @RecentlyNonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new RecordingClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(activity, googleSignInAccount));
    }

    @RecentlyNonNull
    public static RecordingClient getRecordingClient(@RecentlyNonNull Context context, @RecentlyNonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new RecordingClient(context, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(context, googleSignInAccount));
    }

    @RecentlyNonNull
    public static SessionsClient getSessionsClient(@RecentlyNonNull Activity activity, @RecentlyNonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SessionsClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(activity, googleSignInAccount));
    }

    @RecentlyNonNull
    public static SessionsClient getSessionsClient(@RecentlyNonNull Context context, @RecentlyNonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SessionsClient(context, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(context, googleSignInAccount));
    }

    @RecentlyNonNull
    public static HistoryClient getHistoryClient(@RecentlyNonNull Activity activity, @RecentlyNonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new HistoryClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(activity, googleSignInAccount));
    }

    @RecentlyNonNull
    public static HistoryClient getHistoryClient(@RecentlyNonNull Context context, @RecentlyNonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new HistoryClient(context, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(context, googleSignInAccount));
    }

    @RecentlyNonNull
    public static GoalsClient getGoalsClient(@RecentlyNonNull Activity activity, @RecentlyNonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new GoalsClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(activity, googleSignInAccount));
    }

    @RecentlyNonNull
    public static GoalsClient getGoalsClient(@RecentlyNonNull Context context, @RecentlyNonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new GoalsClient(context, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(context, googleSignInAccount));
    }

    @RecentlyNonNull
    public static ConfigClient getConfigClient(@RecentlyNonNull Activity activity, @RecentlyNonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new ConfigClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(activity, googleSignInAccount));
    }

    @RecentlyNonNull
    public static ConfigClient getConfigClient(@RecentlyNonNull Context context, @RecentlyNonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new ConfigClient(context, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(context, googleSignInAccount));
    }

    @RecentlyNonNull
    @Deprecated
    public static BleClient getBleClient(@RecentlyNonNull Activity activity, @RecentlyNonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new BleClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(activity, googleSignInAccount));
    }

    @RecentlyNonNull
    @Deprecated
    public static BleClient getBleClient(@RecentlyNonNull Context context, @RecentlyNonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new BleClient(context, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzg(context, googleSignInAccount));
    }

    public static long getStartTime(@RecentlyNonNull Intent intent, @RecentlyNonNull TimeUnit timeUnit) {
        long longExtra = intent.getLongExtra(EXTRA_START_TIME, -1);
        if (longExtra == -1) {
            return -1;
        }
        return timeUnit.convert(longExtra, TimeUnit.MILLISECONDS);
    }

    public static long getEndTime(@RecentlyNonNull Intent intent, @RecentlyNonNull TimeUnit timeUnit) {
        long longExtra = intent.getLongExtra(EXTRA_END_TIME, -1);
        if (longExtra == -1) {
            return -1;
        }
        return timeUnit.convert(longExtra, TimeUnit.MILLISECONDS);
    }

    static {
        BleApi bleApi;
        if (Build.VERSION.SDK_INT >= 18) {
            bleApi = new zzco();
        } else {
            bleApi = new zzen();
        }
        BleApi = bleApi;
    }
}
