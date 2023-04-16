package com.google.android.gms.internal.fitness;

import com.google.android.gms.internal.fitness.zzfo;
import com.google.android.gms.internal.fitness.zzfr;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzfr<MessageType extends zzfo<MessageType, BuilderType>, BuilderType extends zzfr<MessageType, BuilderType>> implements zzij {
    /* access modifiers changed from: protected */
    public abstract BuilderType zza(MessageType messagetype);

    /* renamed from: zzap */
    public abstract BuilderType clone();

    public final /* synthetic */ zzij zza(zzik zzik) {
        if (zzbu().getClass().isInstance(zzik)) {
            return zza((zzfo) zzik);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
