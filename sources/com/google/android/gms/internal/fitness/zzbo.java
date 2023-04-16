package com.google.android.gms.internal.fitness;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.zzai;
import com.google.android.gms.fitness.request.zzbg;
import com.google.android.gms.fitness.request.zzbk;
import com.google.android.gms.fitness.request.zzd;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public interface zzbo extends IInterface {
    void zza(StartBleScanRequest startBleScanRequest) throws RemoteException;

    void zza(zzai zzai) throws RemoteException;

    void zza(zzbg zzbg) throws RemoteException;

    void zza(zzbk zzbk) throws RemoteException;

    void zza(zzd zzd) throws RemoteException;
}
