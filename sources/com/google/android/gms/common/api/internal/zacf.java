package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;

@WorkerThread
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public interface zacf {
    void zaa(IAccountAccessor iAccountAccessor, Set<Scope> set);

    void zag(ConnectionResult connectionResult);
}
