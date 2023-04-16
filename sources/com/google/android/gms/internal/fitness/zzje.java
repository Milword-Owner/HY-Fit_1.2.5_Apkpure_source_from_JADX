package com.google.android.gms.internal.fitness;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzje extends zzjb<FieldDescriptorType, Object> {
    zzje(int i) {
        super(i, (zzje) null);
    }

    public final void zzar() {
        if (!isImmutable()) {
            for (int i = 0; i < zzdc(); i++) {
                Map.Entry zzal = zzal(i);
                if (((zzgv) zzal.getKey()).zzbn()) {
                    zzal.setValue(Collections.unmodifiableList((List) zzal.getValue()));
                }
            }
            for (Map.Entry entry : zzdd()) {
                if (((zzgv) entry.getKey()).zzbn()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzar();
    }
}
