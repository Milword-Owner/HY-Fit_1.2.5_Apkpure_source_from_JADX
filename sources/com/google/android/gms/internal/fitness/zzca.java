package com.google.android.gms.internal.fitness;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.zzaw;
import com.google.android.gms.fitness.request.zzay;
import com.google.android.gms.fitness.request.zzba;
import com.google.android.gms.fitness.request.zzbc;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public interface zzca extends IInterface {
    void zza(SessionInsertRequest sessionInsertRequest) throws RemoteException;

    void zza(SessionReadRequest sessionReadRequest) throws RemoteException;

    void zza(zzaw zzaw) throws RemoteException;

    void zza(zzay zzay) throws RemoteException;

    void zza(zzba zzba) throws RemoteException;

    void zza(zzbc zzbc) throws RemoteException;
}
