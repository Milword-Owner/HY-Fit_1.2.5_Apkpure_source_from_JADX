package com.google.android.gms.fitness;

import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.result.BleDevicesResult;

@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public interface BleApi {
    @RecentlyNonNull
    PendingResult<Status> claimBleDevice(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull BleDevice bleDevice);

    @RecentlyNonNull
    PendingResult<Status> claimBleDevice(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull String str);

    @RecentlyNonNull
    PendingResult<BleDevicesResult> listClaimedBleDevices(@RecentlyNonNull GoogleApiClient googleApiClient);

    @RecentlyNonNull
    @RequiresPermission("android.permission.BLUETOOTH_ADMIN")
    @Deprecated
    PendingResult<Status> startBleScan(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull StartBleScanRequest startBleScanRequest);

    @RecentlyNonNull
    PendingResult<Status> stopBleScan(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull BleScanCallback bleScanCallback);

    @RecentlyNonNull
    PendingResult<Status> unclaimBleDevice(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull BleDevice bleDevice);

    @RecentlyNonNull
    PendingResult<Status> unclaimBleDevice(@RecentlyNonNull GoogleApiClient googleApiClient, @RecentlyNonNull String str);
}
