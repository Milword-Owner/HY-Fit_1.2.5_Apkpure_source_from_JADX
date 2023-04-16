package com.google.android.gms.internal.fitness;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.List;
import javax.annotation.concurrent.NotThreadSafe;

@ShowFirstParty
@NotThreadSafe
/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzh {
    public static <T> int zza(@Nullable T t, List<T> list) {
        if (t == null) {
            return -1;
        }
        int indexOf = list.indexOf(t);
        if (indexOf >= 0) {
            return indexOf;
        }
        list.add(t);
        return list.size() - 1;
    }
}
