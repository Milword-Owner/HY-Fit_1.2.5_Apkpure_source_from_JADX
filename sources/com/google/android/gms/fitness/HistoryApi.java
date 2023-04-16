package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResult;
import java.util.concurrent.TimeUnit;

@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public interface HistoryApi {
    @RecentlyNonNull
    PendingResult<Status> deleteData(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataDeleteRequest dataDeleteRequest);

    @RecentlyNonNull
    PendingResult<Status> insertData(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataSet dataSet);

    @RecentlyNonNull
    PendingResult<DailyTotalResult> readDailyTotal(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataType dataType);

    @RecentlyNonNull
    PendingResult<DailyTotalResult> readDailyTotalFromLocalDevice(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataType dataType);

    @RecentlyNonNull
    PendingResult<DataReadResult> readData(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataReadRequest dataReadRequest);

    @RecentlyNonNull
    PendingResult<Status> registerDataUpdateListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest);

    @RecentlyNonNull
    PendingResult<Status> unregisterDataUpdateListener(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull PendingIntent pendingIntent);

    @RecentlyNonNull
    PendingResult<Status> updateData(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull DataUpdateRequest dataUpdateRequest);

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static class ViewIntentBuilder {
        private final Context zzko;
        private final DataType zzkp;
        private DataSource zzkq;
        private long zzkr;
        private long zzks;
        private String zzkt;

        public ViewIntentBuilder(@RecentlyNonNull Context context, @RecentlyNonNull DataType dataType) {
            this.zzko = context;
            this.zzkp = dataType;
        }

        @RecentlyNonNull
        public ViewIntentBuilder setTimeInterval(long j, long j2, @RecentlyNonNull TimeUnit timeUnit) {
            this.zzkr = timeUnit.toMillis(j);
            this.zzks = timeUnit.toMillis(j2);
            return this;
        }

        @RecentlyNonNull
        public ViewIntentBuilder setDataSource(@RecentlyNonNull DataSource dataSource) {
            Preconditions.checkArgument(dataSource.getDataType().equals(this.zzkp), "Data source %s is not for the data type %s", dataSource, this.zzkp);
            this.zzkq = dataSource;
            return this;
        }

        @RecentlyNonNull
        public ViewIntentBuilder setPreferredApplication(@RecentlyNonNull String str) {
            this.zzkt = str;
            return this;
        }

        @RecentlyNonNull
        public Intent build() {
            Intent intent;
            ResolveInfo resolveActivity;
            boolean z = true;
            Preconditions.checkState(this.zzkr > 0, "Start time must be set");
            if (this.zzks <= this.zzkr) {
                z = false;
            }
            Preconditions.checkState(z, "End time must be set and after start time");
            Intent intent2 = new Intent(Fitness.ACTION_VIEW);
            intent2.setType(DataType.getMimeType(this.zzkq.getDataType()));
            intent2.putExtra(Fitness.EXTRA_START_TIME, this.zzkr);
            intent2.putExtra(Fitness.EXTRA_END_TIME, this.zzks);
            SafeParcelableSerializer.serializeToIntentExtra(this.zzkq, intent2, DataSource.EXTRA_DATA_SOURCE);
            if (this.zzkt == null || (resolveActivity = this.zzko.getPackageManager().resolveActivity(intent, 0)) == null) {
                return intent2;
            }
            (intent = new Intent(intent2).setPackage(this.zzkt)).setComponent(new ComponentName(this.zzkt, resolveActivity.activityInfo.name));
            return intent;
        }
    }
}
