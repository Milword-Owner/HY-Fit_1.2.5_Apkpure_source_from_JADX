package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public interface RemoteCall<T, U> {
    @KeepForSdk
    void accept(T t, U u) throws RemoteException;
}
