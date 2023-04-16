package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.internal.fitness.zzf;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzj<T extends IInterface> extends GmsClient<T> {
    public abstract T createServiceInterface(IBinder iBinder);

    public int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    public abstract String getServiceDescriptor();

    public abstract String getStartServiceAction();

    public boolean requiresAccount() {
        return true;
    }

    protected zzj(Context context, Looper looper, zzf.zza zza, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, ClientSettings clientSettings) {
        super(context, looper, zza.zzc(), clientSettings, connectionCallbacks, onConnectionFailedListener);
    }

    public boolean requiresSignIn() {
        return !DeviceProperties.isWearable(getContext());
    }

    public Set<Scope> getScopesForConnectionlessNonSignIn() {
        return getScopes();
    }
}
