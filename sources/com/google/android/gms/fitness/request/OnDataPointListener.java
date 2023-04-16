package com.google.android.gms.fitness.request;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.fitness.data.DataPoint;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public interface OnDataPointListener {
    void onDataPoint(@RecentlyNonNull DataPoint dataPoint);
}
