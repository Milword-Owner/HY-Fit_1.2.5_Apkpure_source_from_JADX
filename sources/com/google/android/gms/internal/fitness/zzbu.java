package com.google.android.gms.internal.fitness;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.request.zzf;
import com.google.android.gms.fitness.request.zzj;
import com.google.android.gms.fitness.request.zzv;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public interface zzbu extends IInterface {
    void zza(DataDeleteRequest dataDeleteRequest) throws RemoteException;

    void zza(DataReadRequest dataReadRequest) throws RemoteException;

    void zza(DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) throws RemoteException;

    void zza(DataUpdateRequest dataUpdateRequest) throws RemoteException;

    void zza(zzf zzf) throws RemoteException;

    void zza(zzj zzj) throws RemoteException;

    void zza(zzv zzv) throws RemoteException;
}
