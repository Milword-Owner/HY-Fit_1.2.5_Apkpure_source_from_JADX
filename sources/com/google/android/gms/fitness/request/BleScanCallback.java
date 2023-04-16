package com.google.android.gms.fitness.request;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.fitness.data.BleDevice;

@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class BleScanCallback {
    public abstract void onDeviceFound(@RecentlyNonNull BleDevice bleDevice);

    public abstract void onScanStopped();
}
