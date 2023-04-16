package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.util.PlatformVersion;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public abstract class zac {
    private final int type;

    public zac(int i) {
        this.type = i;
    }

    public abstract void zaa(@NonNull Status status);

    public abstract void zaa(@NonNull zaz zaz, boolean z);

    public abstract void zaa(@NonNull RuntimeException runtimeException);

    public abstract void zac(GoogleApiManager.zaa<?> zaa) throws DeadObjectException;

    /* access modifiers changed from: private */
    public static Status zaa(RemoteException remoteException) {
        StringBuilder sb = new StringBuilder();
        if (PlatformVersion.isAtLeastIceCreamSandwichMR1() && (remoteException instanceof TransactionTooLargeException)) {
            sb.append("TransactionTooLargeException: ");
        }
        sb.append(remoteException.getLocalizedMessage());
        return new Status(8, sb.toString());
    }
}
