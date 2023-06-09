package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.fitness.zzf;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzw extends zzj<zzbs> {
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("Fitness.GOALS_API", new zzy(), CLIENT_KEY);
    private static final Api.ClientKey<zzw> CLIENT_KEY = new Api.ClientKey<>();
    private static final zzf.zza zzoy = zzf.zza.FIT_GOALS;
    public static final Api<Api.ApiOptions.HasGoogleSignInAccountOptions> zzoz = new Api<>("Fitness.GOALS_CLIENT", new zzaa(), CLIENT_KEY);

    private zzw(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, zzoy, connectionCallbacks, onConnectionFailedListener, clientSettings);
    }

    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    public final String getServiceDescriptor() {
        return "com.google.android.gms.fitness.internal.IGoogleFitGoalsApi";
    }

    public final String getStartServiceAction() {
        return "com.google.android.gms.fitness.GoalsApi";
    }

    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitGoalsApi");
        if (queryLocalInterface instanceof zzbs) {
            return (zzbs) queryLocalInterface;
        }
        return new zzbv(iBinder);
    }
}
