package com.google.android.gms.internal.fitness;

import android.util.Log;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.DataReadResult;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzdp extends zzbf {
    private final BaseImplementation.ResultHolder<DataReadResult> zzpa;
    private int zzpt;
    private DataReadResult zzpu;

    private zzdp(BaseImplementation.ResultHolder<DataReadResult> resultHolder) {
        this.zzpt = 0;
        this.zzpa = resultHolder;
    }

    public final void zza(DataReadResult dataReadResult) {
        synchronized (this) {
            if (Log.isLoggable("Fitness", 2)) {
                int i = this.zzpt;
                StringBuilder sb = new StringBuilder(33);
                sb.append("Received batch result ");
                sb.append(i);
                Log.v("Fitness", sb.toString());
            }
            if (this.zzpu == null) {
                this.zzpu = dataReadResult;
            } else {
                this.zzpu.zzb(dataReadResult);
            }
            this.zzpt++;
            if (this.zzpt == this.zzpu.zzab()) {
                this.zzpa.setResult(this.zzpu);
            }
        }
    }

    /* synthetic */ zzdp(BaseImplementation.ResultHolder resultHolder, zzdh zzdh) {
        this(resultHolder);
    }
}
