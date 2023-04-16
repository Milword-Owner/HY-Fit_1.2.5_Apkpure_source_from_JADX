package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.internal.fitness.zzco;
import com.google.android.gms.internal.fitness.zzen;
import com.google.android.gms.internal.fitness.zzk;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.List;

@Deprecated
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class BleClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    private static final BleApi zzka;

    @ShowFirstParty
    protected BleClient(@RecentlyNonNull Context context, @RecentlyNonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, zzk.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    BleClient(@NonNull Activity activity, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, zzk.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    @RequiresPermission("android.permission.BLUETOOTH_ADMIN")
    public Task<Void> startBleScan(@RecentlyNonNull List<DataType> list, int i, @RecentlyNonNull BleScanCallback bleScanCallback) {
        if (!PlatformVersion.isAtLeastJellyBeanMR2()) {
            return Tasks.forException(new ApiException(zzen.zzqh));
        }
        ListenerHolder registerListener = registerListener(bleScanCallback, BleScanCallback.class.getSimpleName());
        return doRegisterEventListener(RegistrationMethods.builder().withHolder(registerListener).register(new zzb(this, registerListener, list, i)).unregister(new zzc(this, registerListener)).build());
    }

    @RecentlyNonNull
    public Task<Boolean> stopBleScan(@RecentlyNonNull BleScanCallback bleScanCallback) {
        if (!PlatformVersion.isAtLeastJellyBeanMR2()) {
            return Tasks.forException(new ApiException(zzen.zzqh));
        }
        return doUnregisterEventListener(ListenerHolders.createListenerKey(bleScanCallback, BleScanCallback.class.getSimpleName()));
    }

    @RecentlyNonNull
    public Task<Void> claimBleDevice(@RecentlyNonNull BleDevice bleDevice) {
        return PendingResultUtil.toVoidTask(zzka.claimBleDevice(asGoogleApiClient(), bleDevice));
    }

    @RecentlyNonNull
    public Task<Void> claimBleDevice(@RecentlyNonNull String str) {
        return PendingResultUtil.toVoidTask(zzka.claimBleDevice(asGoogleApiClient(), str));
    }

    @RecentlyNonNull
    public Task<Void> unclaimBleDevice(@RecentlyNonNull String str) {
        return PendingResultUtil.toVoidTask(zzka.unclaimBleDevice(asGoogleApiClient(), str));
    }

    @RecentlyNonNull
    public Task<Void> unclaimBleDevice(@RecentlyNonNull BleDevice bleDevice) {
        return PendingResultUtil.toVoidTask(zzka.unclaimBleDevice(asGoogleApiClient(), bleDevice));
    }

    @RecentlyNonNull
    public Task<List<BleDevice>> listClaimedBleDevices() {
        return PendingResultUtil.toTask(zzka.listClaimedBleDevices(asGoogleApiClient()), zza.zzjz);
    }

    static {
        BleApi bleApi;
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            bleApi = new zzco();
        } else {
            bleApi = new zzen();
        }
        zzka = bleApi;
    }
}
