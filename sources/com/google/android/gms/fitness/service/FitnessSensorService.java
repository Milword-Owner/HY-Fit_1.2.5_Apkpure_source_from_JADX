package com.google.android.gms.fitness.service;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.CallSuper;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.google.android.gms.internal.fitness.zzbh;
import com.google.android.gms.internal.fitness.zzcn;
import com.google.android.gms.internal.fitness.zzer;
import com.google.android.gms.internal.fitness.zzet;
import com.google.android.gms.internal.fitness.zzeu;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class FitnessSensorService extends Service {
    @RecentlyNonNull
    public static final String SERVICE_INTERFACE = "com.google.android.gms.fitness.service.FitnessSensorService";
    private zza zztb;

    @RecentlyNonNull
    public abstract List<DataSource> onFindDataSources(@RecentlyNonNull List<DataType> list);

    public abstract boolean onRegister(@RecentlyNonNull FitnessSensorServiceRequest fitnessSensorServiceRequest);

    public abstract boolean onUnregister(@RecentlyNonNull DataSource dataSource);

    @CallSuper
    public void onCreate() {
        super.onCreate();
        this.zztb = new zza();
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    private static class zza extends zzeu {
        private final FitnessSensorService zztc;

        private zza(FitnessSensorService fitnessSensorService) {
            this.zztc = fitnessSensorService;
        }

        public final void zza(zzer zzer, zzbh zzbh) throws RemoteException {
            this.zztc.zzac();
            zzbh.zza(new DataSourcesResult(this.zztc.onFindDataSources(zzer.getDataTypes()), Status.RESULT_SUCCESS));
        }

        public final void zza(FitnessSensorServiceRequest fitnessSensorServiceRequest, zzcn zzcn) throws RemoteException {
            this.zztc.zzac();
            if (this.zztc.onRegister(fitnessSensorServiceRequest)) {
                zzcn.onResult(Status.RESULT_SUCCESS);
            } else {
                zzcn.onResult(new Status(13));
            }
        }

        public final void zza(zzet zzet, zzcn zzcn) throws RemoteException {
            this.zztc.zzac();
            if (this.zztc.onUnregister(zzet.getDataSource())) {
                zzcn.onResult(Status.RESULT_SUCCESS);
            } else {
                zzcn.onResult(new Status(13));
            }
        }
    }

    @CallSuper
    @RecentlyNullable
    public IBinder onBind(@RecentlyNonNull Intent intent) {
        if (!SERVICE_INTERFACE.equals(intent.getAction())) {
            return null;
        }
        if (Log.isLoggable("FitnessSensorService", 3)) {
            String valueOf = String.valueOf(intent);
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20 + String.valueOf(name).length());
            sb.append("Intent ");
            sb.append(valueOf);
            sb.append(" received by ");
            sb.append(name);
            Log.d("FitnessSensorService", sb.toString());
        }
        return this.zztb.asBinder();
    }

    /* access modifiers changed from: protected */
    @TargetApi(19)
    @VisibleForTesting
    public final void zzac() {
        int callingUid = Binder.getCallingUid();
        if (PlatformVersion.isAtLeastKitKat()) {
            ((AppOpsManager) getSystemService("appops")).checkPackage(callingUid, "com.google.android.gms");
            return;
        }
        String[] packagesForUid = getPackageManager().getPackagesForUid(callingUid);
        if (packagesForUid != null) {
            int length = packagesForUid.length;
            int i = 0;
            while (i < length) {
                if (!packagesForUid[i].equals("com.google.android.gms")) {
                    i++;
                } else {
                    return;
                }
            }
        }
        throw new SecurityException("Unauthorized caller");
    }
}
