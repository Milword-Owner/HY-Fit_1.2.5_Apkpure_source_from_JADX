package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.zzad;
import com.google.android.gms.fitness.request.zze;
import com.google.android.gms.internal.fitness.zzbo;
import com.google.android.gms.internal.fitness.zzcn;
import com.google.android.gms.internal.fitness.zzei;
import com.google.android.gms.internal.fitness.zzk;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzb implements RemoteCall<zzk, TaskCompletionSource<Void>> {
    private final /* synthetic */ ListenerHolder zzkb;
    private final /* synthetic */ List zzkc;
    private final /* synthetic */ int zzkd;

    zzb(BleClient bleClient, ListenerHolder listenerHolder, List list, int i) {
        this.zzkb = listenerHolder;
        this.zzkc = list;
        this.zzkd = i;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
        ((zzbo) ((zzk) obj).getService()).zza(new StartBleScanRequest((List<DataType>) this.zzkc, (zzad) zze.zzu().zza(this.zzkb), this.zzkd, (zzcn) zzei.zza((TaskCompletionSource) obj2)));
    }
}
