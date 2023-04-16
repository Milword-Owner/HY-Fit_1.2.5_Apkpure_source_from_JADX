package com.google.android.gms.internal.fitness;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public interface zzcn extends IInterface {
    void onResult(Status status) throws RemoteException;
}
