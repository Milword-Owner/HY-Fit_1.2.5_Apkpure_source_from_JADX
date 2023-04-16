package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.fitness.result.SessionStopResult;

@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public interface SessionsApi {
    @RecentlyNonNull
    PendingResult<Status> insertSession(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull SessionInsertRequest sessionInsertRequest);

    @RecentlyNonNull
    PendingResult<SessionReadResult> readSession(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull SessionReadRequest sessionReadRequest);

    @RecentlyNonNull
    PendingResult<Status> registerForSessions(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull PendingIntent pendingIntent);

    @RecentlyNonNull
    PendingResult<Status> startSession(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull Session session);

    @RecentlyNonNull
    PendingResult<SessionStopResult> stopSession(@RecentlyNonNull GoogleApiClient googleApiClient, @Nullable String str);

    @RecentlyNonNull
    PendingResult<Status> unregisterForSessions(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull PendingIntent pendingIntent);

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class ViewIntentBuilder {
        private final Context zzko;
        @Nullable
        private String zzkt;
        private Session zzky;
        private boolean zzkz = false;

        public ViewIntentBuilder(@RecentlyNonNull Context context) {
            this.zzko = context;
        }

        @RecentlyNonNull
        public ViewIntentBuilder setSession(@RecentlyNonNull Session session) {
            this.zzky = session;
            return this;
        }

        @RecentlyNonNull
        public ViewIntentBuilder setPreferredApplication(@Nullable String str) {
            this.zzkt = str;
            this.zzkz = true;
            return this;
        }

        @RecentlyNonNull
        public Intent build() {
            Intent intent;
            ResolveInfo resolveActivity;
            Preconditions.checkState(this.zzky != null, "Session must be set");
            Intent intent2 = new Intent(Fitness.ACTION_VIEW);
            intent2.setType(Session.getMimeType(this.zzky.getActivity()));
            SafeParcelableSerializer.serializeToIntentExtra(this.zzky, intent2, Session.EXTRA_SESSION);
            if (!this.zzkz) {
                this.zzkt = this.zzky.getAppPackageName();
            }
            String str = this.zzkt;
            if (str == null || (resolveActivity = this.zzko.getPackageManager().resolveActivity(intent, 0)) == null) {
                return intent2;
            }
            (intent = new Intent(intent2).setPackage(str)).setComponent(new ComponentName(str, resolveActivity.activityInfo.name));
            return intent;
        }
    }
}
